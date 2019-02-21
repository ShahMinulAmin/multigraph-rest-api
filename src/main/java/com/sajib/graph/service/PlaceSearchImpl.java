package com.sajib.graph.service;

import com.sajib.graph.core.GeoCodingUtil;
import com.sajib.graph.dao.PreferenceDao;
import com.sajib.graph.dao.SearchDAO;
import com.sajib.graph.entity.Preference;
import com.sajib.graph.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by sajib on 2/20/19.
 */
@Component
public class PlaceSearchImpl implements PlaceSearch<String, EdgeProperty, Coordinate, ResultRoute> {

    private static final Logger LOG = Logger.getLogger(PlaceSearchImpl.class.getName());

    @Autowired
    private PreferenceDao preferenceDao;

    private List<ResultRoute> combinedResultRouteList = new ArrayList<>();

    @Override
    public List<ResultRoute> searchFromLocation(Multigraph<String, EdgeProperty> multigraph, Map<String, Coordinate> coordinateMap,
                                                String source, String destination) {
        if (!coordinateMap.containsKey(destination)) {
            Preference preference = preferenceDao.findByName(GeoCodingUtil.PREFERENCE_GEOCODE_API_KEY);
            String apiKey = preference.getValue();
            LOG.info("Google geocode API Key: " + preference.getValue());

            // get nearest locations
            List<String> derivedDestinations = GeoCodingUtil.getDerivedDestinations(coordinateMap, destination, apiKey);
            List<ResultRoute> derivedResults = new ArrayList<>();

            // find route for each derived desinations
            for (String derivedDestination : derivedDestinations) {
                List<ResultRoute> allResultsFromDestination = searchFromLocationTo(multigraph, coordinateMap, source, derivedDestination);
                // derivedResults.addAll(allResultsFromDestination);
                // get the result with minimum cost only
                ResultRoute resultRoute = getRouteWithLeastCost(allResultsFromDestination);
                derivedResults.add(resultRoute);
            }
            return derivedResults;

        } else {
            return searchFromLocationTo(multigraph, coordinateMap, source, destination);
        }
    }


    private ResultRoute getRouteWithLeastCost(List<ResultRoute> allResultsFromDestination) {
        ResultRoute minResultRoute = null;
        Integer minCost = Integer.MAX_VALUE;
        for (ResultRoute resultRoute : allResultsFromDestination) {
            if (resultRoute.getCostOfRoute() < minCost) {
                minCost = resultRoute.getCostOfRoute();
                minResultRoute = resultRoute;
            }
        }
        return minResultRoute;
    }


    private List<ResultRoute> searchFromLocationTo(Multigraph<String, EdgeProperty> multigraph, Map<String, Coordinate> coordinateMap,
                                                   String source, String destination) {
        combinedResultRouteList = new ArrayList<ResultRoute>();

        LinkedList<String> visited = new LinkedList();
        visited.add(source);
        LinkedList<EdgeProperty> visitedEdgeProperty = new LinkedList();
        // recursive depth first search to find all possible routes
        depthFirst(multigraph, destination, visited, visitedEdgeProperty);

        return combinedResultRouteList;
    }


    private void depthFirst(Multigraph<String, EdgeProperty> multigraph, String destination,
                            LinkedList<String> visited, LinkedList<EdgeProperty> visitedEdgeProperty) {
        Collection<Map.Entry<String, EdgeProperty>> nodesEntrySet = multigraph.adjacentNodes(visited.getLast());

        // examine adjacent nodes
        for (Map.Entry<String, EdgeProperty> entry : nodesEntrySet) {
            String node = entry.getKey();
            EdgeProperty edgeProperty = entry.getValue();

            if (visited.contains(node)) {
                continue;
            }

            if (node.equals(destination)) {
                visited.add(node);
                visitedEdgeProperty.add(edgeProperty);

                // a route is found, formulate the results and store
                List<ResultRoute> resultRouteList = formulatePath(visited, visitedEdgeProperty);
                combinedResultRouteList.addAll(resultRouteList);

                visited.removeLast();
                visitedEdgeProperty.removeLast();
                break;
            }
        }

        for (Map.Entry<String, EdgeProperty> entry : nodesEntrySet) {
            String node = entry.getKey();
            EdgeProperty edgeProperty = entry.getValue();

            if (visited.contains(node) || node.equals(destination)) {
                continue;
            }

            visited.addLast(node);
            visitedEdgeProperty.add(edgeProperty);
            depthFirst(multigraph, destination, visited, visitedEdgeProperty);
            visited.removeLast();
            visitedEdgeProperty.removeLast();
        }
    }

    private List<ResultRoute> formulatePath(LinkedList<String> visited, LinkedList<EdgeProperty> visitedEdgeProperty) {
        int count = 0;
        List<PreResult<String, EdgeProperty>> preResultList = new ArrayList<PreResult<String, EdgeProperty>>();

        for (String node : visited) {
            if (count > 0) {
                PreResult preResult = new PreResult(visited.get(count - 1), node, visitedEdgeProperty.get(count - 1));
                preResultList.add(preResult);
            }
            count++;
        }

        List<ResultRoute> resultRouteList = getProcessedResults(preResultList);
        return resultRouteList;
    }


    private List<ResultRoute> getProcessedResults(List<PreResult<String, EdgeProperty>> preResultList) {
        // list of all routes
        List<ResultRoute> resultRouteList = new ArrayList<ResultRoute>();

        // array list for storing all result lists
        List<List<ResultPath>> finalResultList = new ArrayList<List<ResultPath>>();

        // make an initial result array
        List<ResultPath> initResultList = new ArrayList<ResultPath>();
        for (PreResult preResult : preResultList) {
            ResultPath result = new ResultPath(preResult.getFrom(), preResult.getTo(), null, null, null);
            initResultList.add(result);
        }
        // add initial result array to final result array
        finalResultList.add(initResultList);

        int segmentNo = 0;
        // iterate for each segment of pre result list
        for (PreResult<String, EdgeProperty> preResult : preResultList) {
            Integer numEdge = preResult.getEdgeProperty().getTransportModeList().size();

            for (int i = 0; i < numEdge; i++) {
                int costToFill = preResult.getEdgeProperty().getCostList().get(i);
                int durationToFill = preResult.getEdgeProperty().getDurationList().get(i);
                String transportTypeToFill = preResult.getEdgeProperty().getTransportModeList().get(i);

                // if edge index number is 0, then there will be no duplication of array list
                if (i == 0) {
                    // for 1st edge, fill cost data in existing result lists
                    int sizeFinalArray = finalResultList.size();
                    for (int j = 0; j < sizeFinalArray; j++) {
                        List<ResultPath> resultList = finalResultList.get(j);
                        resultList.get(segmentNo).setCost(costToFill);
                        resultList.get(segmentNo).setModeOfTransport(transportTypeToFill);
                        resultList.get(segmentNo).setDaysTaken(durationToFill);
                    }
                }
                // if edge index number is greater than 1, then there will be duplication of array list
                else if (i > 0) {
                    // duplicate current result array by it's current size
                    int sizeFinalArray = finalResultList.size();
                    for (int j = 0; j < sizeFinalArray; j++) {
                        List<ResultPath> tmpResultList = finalResultList.get(j);

                        // shallow copy array list
                        List<ResultPath> newResultList = new ArrayList<ResultPath>();
                        for (ResultPath result : tmpResultList) {
                            // create new result instance and add to array list
                            ResultPath newResult = new ResultPath(result.getStart(), result.getEnd(), result.getCost(),
                                    result.getDaysTaken(), result.getModeOfTransport());
                            // add new result instance to new result list
                            newResultList.add(newResult);
                        }

                        // fill data in duplicated result list
                        newResultList.get(segmentNo).setCost(costToFill);
                        newResultList.get(segmentNo).setModeOfTransport(transportTypeToFill);
                        newResultList.get(segmentNo).setDaysTaken(durationToFill);
                        // add the new result list to final result list
                        finalResultList.add(newResultList);
                    }
                }
            }
            segmentNo++;
        }

        for (List<ResultPath> resultList : finalResultList) {
            Integer totalCost = 0;
            Integer totalDuration = 0;
            for (ResultPath result : resultList) {
                totalCost += result.getCost();
                totalDuration += result.getDaysTaken();
            }
            ResultRoute resultRoute = new ResultRoute(resultList, totalCost, totalDuration);
            resultRouteList.add(resultRoute);
        }

        return resultRouteList;
    }

}


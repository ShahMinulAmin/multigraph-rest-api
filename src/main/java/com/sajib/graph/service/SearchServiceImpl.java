package com.sajib.graph.service;

import com.sajib.graph.dao.PathDao;
import com.sajib.graph.entity.City;
import com.sajib.graph.entity.Path;
import com.sajib.graph.types.Coordinate;
import com.sajib.graph.types.EdgeProperty;
import com.sajib.graph.types.Multigraph;
import com.sajib.graph.types.ResultRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by sajib on 2/20/19.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOG = Logger.getLogger(SearchServiceImpl.class.getName());

    @Autowired
    private PathDao pathDao;
    @Autowired
    private PlaceSearch placeSearch;
    @Autowired
    private CityService cityService;

    public SearchServiceImpl() {
    }

    @Override
    public List<ResultRoute> searchFromLocation(String source, String destination, List<String> modeOfTransports,
                                                Integer containerSize, Integer durationFrom, Integer durationTo,
                                                Integer costFrom, Integer costTo) {
        // instance of Graph
        Multigraph<String, EdgeProperty> multigraph = new Multigraph<>();
        Map<String, EdgeProperty> edgeMap = new HashMap();

        LOG.info("this.getPathsBy(), " + containerSize + " : " + this.getPathsBy(containerSize, modeOfTransports).size());
        // generating the graph from all paths available in database
        for (Path path : this.getPathsBy(containerSize, modeOfTransports)) {
            String edgeKey = path.getFromCity().getId() + "," + path.getToCity().getId() + "," + path.getContainer();
            if (edgeMap.containsKey(edgeKey)) {
                EdgeProperty ep = edgeMap.get(edgeKey);
                ep.addProperties(path.getCost(), path.getDuration(), path.getTransportType(), path.getContainer());

            } else {
                EdgeProperty ep = new EdgeProperty(path.getCost(), path.getDuration(), path.getTransportType(),
                        path.getContainer());
                multigraph.addEdge(path.getFromCity().getName(), path.getToCity().getName(), ep);
                edgeMap.put(edgeKey, ep);
            }
        }

        // Cities with coordinates in a HashMap
        Map<String, Coordinate> coordinateMap = new HashMap<String, Coordinate>();
        List<City> cityList = cityService.getAllCities();
        for (City city : cityList) {
            coordinateMap.put(city.getName(), new Coordinate(city.getLatitude(), city.getLongitude()));
        }


        List<ResultRoute> combinedResultRouteList = placeSearch.searchFromLocation(multigraph, coordinateMap,
                source, destination);
        return getFilteredResult(combinedResultRouteList, durationFrom, durationTo, costFrom, costTo);
    }


    List<ResultRoute> getFilteredResult(List<ResultRoute> combinedResultRouteList,
                                        Integer durationFrom, Integer durationTo, Integer costFrom, Integer costTo) {
        List<ResultRoute> filteredRouteList = new ArrayList<ResultRoute>();
        for (ResultRoute route : combinedResultRouteList) {

            if (durationFrom != null && route.getDurationOfRoute() < durationFrom) {
                continue;
            }
            if (durationTo != null && route.getDurationOfRoute() > durationTo) {
                continue;
            }
            if (costFrom != null && route.getCostOfRoute() < costFrom) {
                continue;
            }
            if (costTo != null && route.getCostOfRoute() > costTo) {
                continue;
            }
            filteredRouteList.add(route);
        }
        return filteredRouteList;
    }


    @Override
    public List<Path> getPathsBy(Integer containerSize, List<String> modeOfTransports) {
        return pathDao.getPathsBy(containerSize, modeOfTransports);
    }
}

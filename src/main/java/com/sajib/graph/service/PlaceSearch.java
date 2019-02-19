package com.sajib.graph.service;

import com.sajib.graph.types.Multigraph;

import java.util.List;
import java.util.Map;

/**
 * Generic interface for place search algorithm
 *
 * Created by sajib on 2/20/19.
 */
public interface PlaceSearch<N, E, C, R> {

    /**
     * Search possible routes from source to destination
     *
     * @param multigraph Multigraph instance to search with
     * @param coordinateMap Coordinate map having place as the key
     * @param source Source place
     * @param destination Destination place
     * @return list of all routes
     */
    public List<R> searchFromLocation(Multigraph<N, E> multigraph, Map<N, C> coordinateMap,
                                      N source, N destination);

}

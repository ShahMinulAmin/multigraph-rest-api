package com.sajib.graph.service;


import com.sajib.graph.entity.Path;
import com.sajib.graph.types.ResultRoute;

import java.util.List;

/**
 * Created by sajib on 2/20/19.
 */
public interface SearchService {

    /**
     * Search possible routes from source to destination with some specific filter parameters
     *
     * @param source           Source place
     * @param destination      Destination place
     * @param modeOfTransports Mode of transports
     * @param containerSize    Container size
     * @param durationFrom     Minimum duration in days
     * @param durationTo       Maximum duration in days
     * @param costFrom         Minimum cost in euro
     * @param costTo           Maximum cost in euro
     * @return List of all routes
     */
    public List<ResultRoute> searchFromLocation(String source, String destination, List<String> modeOfTransports,
                                                Integer containerSize, Integer durationFrom, Integer durationTo,
                                                Integer costFrom, Integer costTo);

    /**
     * Find paths with given container size and transport modes
     *
     * @param containerSize    Container size
     * @param modeOfTransports Mode of transports
     * @return List of paths
     */
    public List<Path> getPathsBy(Integer containerSize, List<String> modeOfTransports);

}




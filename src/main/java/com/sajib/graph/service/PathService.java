package com.sajib.graph.service;

import com.sajib.graph.entity.Path;
import com.sajib.graph.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Created by sajib on 2/19/19.
 */
public interface PathService {
    /**
     * Used to get list of all paths
     *
     * @return list of all paths
     */
    public List<Path> getAllPaths();

    /**
     * Used to get a specific path
     *
     * @return path instance
     */
    public Path getPath(Integer id) throws ResourceNotFoundException;

    /**
     * Used to save or update a path
     *
     * @param Path path instance to save or update
     * @return path instance
     */
    public Path saveOrUpdatePath(Path path);
}

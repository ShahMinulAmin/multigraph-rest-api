package com.sajib.graph.dao;

import com.sajib.graph.entity.Path;

import java.util.List;

/**
 * Created by sajib on 2/20/19.
 */
public interface SearchDAO {

    public List<Path> getPathsBy(Integer containerSize, List<String> modeOfTransports);

}


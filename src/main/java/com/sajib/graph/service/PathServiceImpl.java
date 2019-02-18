package com.sajib.graph.service;

import com.sajib.graph.dao.PathDao;
import com.sajib.graph.entity.Path;
import com.sajib.graph.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by sajib on 2/19/19.
 */
@Service
public class PathServiceImpl implements PathService {
    private static final Logger LOG = Logger.getLogger(PathServiceImpl.class.getName());

    @Autowired
    private PathDao pathDao;

    @Override
    public List<Path> getAllPaths() {
        LOG.info("Getting list of all paths");
        Iterable<Path> iterable = pathDao.findAll();
        List<Path> list = new ArrayList<>();
        iterable.iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Path getPath(Integer id) throws ResourceNotFoundException {
        Path path = pathDao.findOne(id);
        if (path == null) {
            throw new ResourceNotFoundException("Path not found for this id: " + id);
        }
        return path;
    }

    @Override
    public Path saveOrUpdatePath(Path path) {
        Path pathInstance = pathDao.save(path);
        return pathInstance;
    }
}


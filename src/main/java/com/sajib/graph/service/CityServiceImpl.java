package com.sajib.graph.service;

import com.sajib.graph.dao.CityDao;
import com.sajib.graph.entity.City;
import com.sajib.graph.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by sajib on 2/18/19.
 */
@Service
public class CityServiceImpl implements CityService {
    private static final Logger LOG = Logger.getLogger(CityServiceImpl.class.getName());

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getAllCities() {
        LOG.info("Getting list of all cities");
        Iterable<City> iterable = cityDao.findAll();
        List<City> list = new ArrayList<>();
        iterable.iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public City getCity(Integer id) throws ResourceNotFoundException {
        Optional<City> city = cityDao.findById(id);
        if (!city.isPresent()) {
            throw new ResourceNotFoundException("City not found for this id: " + id);
        }
        return city.get();
    }

    @Override
    public City saveOrUpdateCity(City city) {
        City cityInstance = cityDao.save(city);
        return cityInstance;

    }
}


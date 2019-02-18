package com.sajib.graph.service;

import com.sajib.graph.entity.City;
import com.sajib.graph.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Created by sajib on 2/18/19.
 */
public interface CityService {

    /**
     * Used to get list of all cities
     *
     * @return list of all cities
     */
    public List<City> getAllCities();

    /**
     * Used to get a specific city
     *
     * @return city instance
     */
    public City getCity(Integer id) throws ResourceNotFoundException;

    /**
     * Used to save or update a city
     *
     * @param City city instance to save or update
     * @return city instance
     */
    public City saveOrUpdateCity(City city);
}
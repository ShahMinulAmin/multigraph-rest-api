package com.sajib.graph.dao;

import com.sajib.graph.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by sajib on 2/18/19.
 */
@Repository
@Transactional
public interface CityDao extends CrudRepository<City, Integer> {

    City findByName(String name);

}
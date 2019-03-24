package com.sajib.graph.dao;

import com.sajib.graph.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by sajib on 2/18/19.
 */
@Repository
@Transactional
public interface CityDao extends CrudRepository<City, Integer> {

    Optional<City> findById(Integer id);
    City findByName(String name);

}
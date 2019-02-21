package com.sajib.graph.dao;

import com.sajib.graph.entity.Preference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by sajib on 2/22/19.
 */
@Repository
@Transactional
public interface PreferenceDao extends CrudRepository<Preference, Integer> {

    Preference findByName(String name);

}

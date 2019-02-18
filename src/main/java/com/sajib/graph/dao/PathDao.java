package com.sajib.graph.dao;

import com.sajib.graph.entity.Path;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by sajib on 2/19/19.
 */
@Repository
@Transactional
public interface PathDao extends CrudRepository<Path, Integer> {


}

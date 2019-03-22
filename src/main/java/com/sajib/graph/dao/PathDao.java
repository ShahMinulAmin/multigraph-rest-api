package com.sajib.graph.dao;

import com.sajib.graph.entity.Path;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sajib on 2/19/19.
 */
@Repository
@Transactional
public interface PathDao extends CrudRepository<Path, Integer> {

    @Query("select path from Path path WHERE path.container = :containerSize AND path.transportType IN (:modeOfTransports)")
    public List<Path> getPathsBy(@Param("containerSize") Integer containerSize, @Param("modeOfTransports") List<String> modeOfTransports);

}

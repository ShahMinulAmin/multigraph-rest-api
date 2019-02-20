package com.sajib.graph.dao;

import com.sajib.graph.entity.Path;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sajib on 2/20/19.
 */
@Repository
public class SearchDAOImpl implements SearchDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Path> getPathsBy(Integer containerSize, List<String> modeOfTransports) {
        Session session = this.sessionFactory.openSession();
        System.out.println("session:" + session);
        Query query = session.createQuery("from Path path WHERE path.container = :container AND path.transportType IN (:transportTypes) ");
        query.setParameter("container", containerSize);
        query.setParameterList("transportTypes", modeOfTransports);
        List<Path> pathList = query.list();
        session.close();
        return pathList;
    }

}

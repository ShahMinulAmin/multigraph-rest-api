package com.sajib.graph.entity;

import com.sajib.graph.dao.CityDao;
import com.sajib.graph.web.model.CityDto;
import com.sajib.graph.web.model.PathDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sajib on 2/18/19.
 */
@Component
public class EntityBuilder {

    @Autowired
    private CityDao cityDao;

    public City buildCityEntity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        city.setLatitude(cityDto.getLatitude());
        city.setLongitude(cityDto.getLongitude());
        return city;
    }

    public Path buildPathEntity(PathDto pathDto) {
        Path path = new Path();
        path.setId(pathDto.getId());
        path.setFromCity(cityDao.findByName(pathDto.getFromCity()));
        path.setToCity(cityDao.findByName(pathDto.getToCity()));
        path.setContainer(pathDto.getContainer());
        path.setTransportType(pathDto.getTransportType());
        path.setDuration(pathDto.getDuration());
        path.setCost(pathDto.getCost());
        return path;
    }

    public Path buildUpdatedPathEntity(Path path, PathDto pathDto) {
        path.setFromCity(cityDao.findByName(pathDto.getFromCity()));
        path.setToCity(cityDao.findByName(pathDto.getToCity()));
        path.setContainer(pathDto.getContainer());
        path.setTransportType(pathDto.getTransportType());
        path.setDuration(pathDto.getDuration());
        path.setCost(pathDto.getCost());
        return path;
    }
}

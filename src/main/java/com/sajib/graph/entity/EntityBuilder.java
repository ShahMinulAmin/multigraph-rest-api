package com.sajib.graph.entity;

import com.sajib.graph.web.model.CityDto;
import org.springframework.stereotype.Component;

/**
 * Created by sajib on 2/18/19.
 */
@Component
public class EntityBuilder {

    public City buildCityEntity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        city.setLatitude(cityDto.getLatitude());
        city.setLongitude(cityDto.getLongitude());
        return city;
    }

}

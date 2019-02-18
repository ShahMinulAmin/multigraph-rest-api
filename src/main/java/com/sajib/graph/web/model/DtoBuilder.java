package com.sajib.graph.web.model;

import com.sajib.graph.entity.City;
import com.sajib.graph.entity.Path;

/**
 * Created by sajib on 2/18/19.
 */
public class DtoBuilder {

    public static CityDto buildCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .build();
    }

    public static PathDto buildPathDto(Path path) {
        return PathDto.builder()
                .id(path.getId())
                .fromCity(path.getFromCity().getName())
                .toCity(path.getToCity().getName())
                .container(path.getContainer())
                .transportType(path.getTransportType())
                .duration(path.getDuration())
                .cost(path.getCost())
                .build();
    }
}

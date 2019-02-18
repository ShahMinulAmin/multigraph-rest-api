package com.sajib.graph.web.model;

import com.sajib.graph.entity.City;

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

}

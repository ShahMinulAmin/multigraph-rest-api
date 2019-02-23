package com.sajib.graph.web.unit.test;

import com.sajib.graph.entity.City;
import com.sajib.graph.entity.EntityBuilder;
import com.sajib.graph.exception.ResourceNotFoundException;
import com.sajib.graph.service.CityService;
import com.sajib.graph.web.model.CityDto;
import com.sajib.graph.web.model.DtoBuilder;
import com.sajib.graph.web.validator.CityDtoValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sajib on 2/18/19.
 */
@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @InitBinder("cityDto")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new CityDtoValidator());
    }


    @Autowired
    EntityBuilder entityBuilder;

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "Returns list of all cities")
    @GetMapping()
    public List<CityDto> getAllCities() {
        List<City> cityList = cityService.getAllCities();
        List<CityDto> cityDtos = cityList.stream()
                .map(DtoBuilder::buildCityDto)
                .collect(Collectors.toList());
        return cityDtos;
    }

    @ApiOperation(value = "Returns a city by id")
    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getOneCity(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        City city = cityService.getCity(id);
        if (city == null) {
            return ResponseEntity.notFound().build();
        }
        CityDto cityDto = DtoBuilder.buildCityDto(city);
        return ResponseEntity.ok().body(cityDto);
    }

    @ApiOperation(value = "Stores a new city")
    @PostMapping()
    public ResponseEntity<CityDto> addCity(@Valid @RequestBody CityDto cityDto) {
        City city = entityBuilder.buildCityEntity(cityDto);
        City createdCity = cityService.saveOrUpdateCity(city);
        if (createdCity == null) {
            return ResponseEntity.notFound().build();
        }
        CityDto createdCityDto = DtoBuilder.buildCityDto(createdCity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCityDto);
    }

    @ApiOperation(value = "Updates an existing city")
    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable(value = "id") Integer id,
                                              @Valid @RequestBody CityDto cityDtoToUpdate)
            throws ResourceNotFoundException {
        City cityEntity = cityService.getCity(id);
        if (cityEntity == null) {
            return ResponseEntity.notFound().build();
        }
        cityEntity.setName(cityDtoToUpdate.getName());
        cityEntity.setLatitude(cityDtoToUpdate.getLatitude());
        cityEntity.setLongitude(cityDtoToUpdate.getLongitude());
        City updatedCity = cityService.saveOrUpdateCity(cityEntity);
        CityDto updatedCityDto = DtoBuilder.buildCityDto(updatedCity);

        return ResponseEntity.ok().body(updatedCityDto);
    }
}

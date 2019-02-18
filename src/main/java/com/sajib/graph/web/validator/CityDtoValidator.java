package com.sajib.graph.web.validator;

import com.sajib.graph.web.model.CityDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sajib on 2/19/19.
 */
public class CityDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CityDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CityDto cityDto = (CityDto) target;

        if (cityDto.getName() == null || cityDto.getName().isEmpty()) {
            errors.reject("cityDto.name", ValidationMessages.CITY_NAME_NOT_NULL);
        }
        if (cityDto.getLatitude() == null) {
            errors.reject("cityDto.latitude", ValidationMessages.LATITUDE_NOT_NULL);
        }
        if (cityDto.getLongitude() == null) {
            errors.reject("cityDto.longitude", ValidationMessages.LONGITUE_NOT_NOLL);
        }
    }
}
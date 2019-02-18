package com.sajib.graph.web.validator;

import com.sajib.graph.web.model.PathDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sajib on 2/19/19.
 */
public class PathDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PathDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PathDto pathDto = (PathDto) target;
        if (pathDto.getFromCity() == null || pathDto.getFromCity().isEmpty()) {
            errors.reject("pathDto.fromCity", ValidationMessages.FROM_CITY_NAME_NOT_NULL);
        }
        if (pathDto.getToCity() == null || pathDto.getToCity().isEmpty()) {
            errors.reject("pathDto.toCity", ValidationMessages.TO_CITY_NAME_NOT_NULL);
        }
        if (pathDto.getContainer() == null) {
            errors.reject("pathDto.container", ValidationMessages.CONTAINER_NOT_NOLL);
        }
        if (pathDto.getTransportType() == null || pathDto.getTransportType().isEmpty()) {
            errors.reject("pathDto.transportType", ValidationMessages.TRANSPORT_TYPE_NOT_NULL);
        }
        if (pathDto.getDuration() == null) {
            errors.reject("pathDto.duration", ValidationMessages.DURATION_NOT_NOLL);
        }
        if (pathDto.getCost() == null) {
            errors.reject("pathDto.cost", ValidationMessages.COST_NOT_NOLL);
        }
    }
}

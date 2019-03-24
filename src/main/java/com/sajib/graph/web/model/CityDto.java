package com.sajib.graph.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sajib on 2/18/19.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
}

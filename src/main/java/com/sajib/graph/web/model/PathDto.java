package com.sajib.graph.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by sajib on 2/19/19.
 */
@Data
@Builder
@AllArgsConstructor
public class PathDto {
    private Integer id;
    private String fromCity;
    private String toCity;
    private Integer container;
    private String transportType;
    private Integer duration;
    private Integer cost;
}


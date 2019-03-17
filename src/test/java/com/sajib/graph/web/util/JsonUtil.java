package com.sajib.graph.web.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by sajib on 2/18/19.
 */
public class JsonUtil {

    public static String PARAM_FROM = "from";
    public static String PARAM_TO = "to";
    public static String PARAM_TRANSPORT_TYPES = "transportTypes";
    public static String PARAM_CONTAINER = "container";
    public static String PARAM_MIN_DAYS = "minDays";
    public static String PARAM_MAX_DAYS = "maxDays";
    public static String PARAM_MIN_COST = "minCost";
    public static String PARAM_MAX_COST = "maxCost";

    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}

package com.sajib.graph.web.controller;

import com.sajib.graph.service.SearchService;
import com.sajib.graph.types.ResultRoute;
import com.sajib.graph.types.SearchError;
import com.sajib.graph.types.SearchResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajib on 2/21/19.
 */
@RestController
@RequestMapping("/api/v1/routes")
public class SearchController {

    public static class SearchParams {
        public String from;
        public String to;
        public List<String> transportTypes;
        public Integer container;
        public Integer minDays;
        public Integer maxDays;
        public Integer minCost;
        public Integer maxCost;
    }

    @Autowired
    SearchService searchService;

    @ApiOperation(value = "Find routes of multigraph with given source, destination and other parameters")
    @PostMapping()
    public Object routeSearch(@RequestBody SearchParams params) {

        if (params.from == null || params.to == null || params.transportTypes == null
                || params.container == null) {
            return new SearchError("Mandatory parameter missing.");
        }

        if (params.transportTypes.contains("All")) {
            params.transportTypes = new ArrayList<String>();
            params.transportTypes.add("Road");
            params.transportTypes.add("Ocean");
            params.transportTypes.add("Air");
        }

        // get the result by call search API
        List<ResultRoute> combinedResultRouteList = searchService.searchFromLocation(params.from, params.to,
                params.transportTypes, params.container, params.minDays, params.maxDays,
                params.minCost, params.maxCost);

        return new SearchResult(combinedResultRouteList);
    }
}

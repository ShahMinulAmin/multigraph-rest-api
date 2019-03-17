package com.sajib.graph.web.controller;

import com.sajib.graph.service.SearchService;
import com.sajib.graph.types.ResultRoute;
import com.sajib.graph.types.SearchError;
import com.sajib.graph.types.SearchResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping()
    public Object routeSearch(@RequestParam(required = true) String from,
                              @RequestParam(required = true) String to,
                              @RequestParam(required = true) List<String> transportTypes,
                              @RequestParam(required = true) Integer container,
                              @RequestParam(required = false) Integer minDays,
                              @RequestParam(required = false) Integer maxDays,
                              @RequestParam(required = false) Integer minCost,
                              @RequestParam(required = false) Integer maxCost) {

        if (transportTypes.contains("All")) {
            transportTypes = new ArrayList<String>();
            transportTypes.add("Road");
            transportTypes.add("Ocean");
            transportTypes.add("Air");
        }

        // get the result by call search API
        List<ResultRoute> combinedResultRouteList = searchService.searchFromLocation(from, to,
                transportTypes, container, minDays, maxDays,
                minCost, maxCost);

        return new SearchResult(combinedResultRouteList);
    }
}

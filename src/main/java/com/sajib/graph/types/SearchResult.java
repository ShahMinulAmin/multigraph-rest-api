package com.sajib.graph.types;

import java.util.List;

/**
 * Created by sajib on 2/20/19.
 */
public class SearchResult {

    private List<ResultRoute> routes;

    public SearchResult() {
    }

    public SearchResult(List<ResultRoute> routes) {
        this.routes = routes;
    }

    public List<ResultRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<ResultRoute> routes) {
        this.routes = routes;
    }

}

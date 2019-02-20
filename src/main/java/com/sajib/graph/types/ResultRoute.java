package com.sajib.graph.types;

import java.util.List;

/**
 * Created by sajib on 2/20/19.
 */
public class ResultRoute {

    private List<ResultPath> route;
    private Integer costOfRoute;
    private Integer durationOfRoute;

    public ResultRoute() {
    }

    public ResultRoute(List<ResultPath> route, Integer costOfRoute, Integer durationOfRoute) {
        this.route = route;
        this.costOfRoute = costOfRoute;
        this.durationOfRoute = durationOfRoute;
    }


    public List<ResultPath> getRoute() {
        return route;
    }

    public void setRoute(List<ResultPath> route) {
        this.route = route;
    }

    public Integer getCostOfRoute() {
        return costOfRoute;
    }

    public void setCostOfRoute(Integer costOfRoute) {
        this.costOfRoute = costOfRoute;
    }

    public Integer getDurationOfRoute() {
        return durationOfRoute;
    }

    public void setDurationOfRoute(Integer durationOfRoute) {
        this.durationOfRoute = durationOfRoute;
    }
}

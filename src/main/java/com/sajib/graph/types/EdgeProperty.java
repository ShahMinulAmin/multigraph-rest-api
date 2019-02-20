package com.sajib.graph.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajib on 2/20/19.
 */
public class EdgeProperty {

    private List<Integer> costList;
    private List<Integer> durationList;
    private List<String> transportModeList;
    private Integer containerSize;

    public EdgeProperty(List<Integer> costList, List<Integer> durationList, List<String> transportModeList, Integer containerSize) {
        this.costList = costList;
        this.durationList = durationList;
        this.transportModeList = transportModeList;
    }

    public EdgeProperty(Integer cost, Integer duration, String transportMode, Integer containerSize) {
        this.costList = new ArrayList<Integer>();
        this.costList.add(cost);

        this.durationList = new ArrayList<Integer>();
        this.durationList.add(duration);

        this.transportModeList = new ArrayList<String>();
        this.transportModeList.add(transportMode);
    }

    public void addProperties(Integer cost, Integer duration, String transportMode, Integer containerSize) {
        this.costList.add(cost);
        this.durationList.add(duration);
        this.transportModeList.add(transportMode);
    }


    public List<Integer> getCostList() {
        return costList;
    }

    public void setCostList(List<Integer> costList) {
        this.costList = costList;
    }

    public List<Integer> getDurationList() {
        return durationList;
    }

    public void setDurationList(List<Integer> durationList) {
        this.durationList = durationList;
    }

    public List<String> getTransportModeList() {
        return transportModeList;
    }

    public void setTransportModeList(List<String> transportModeList) {
        this.transportModeList = transportModeList;
    }

    public Integer getContainerSize() {
        return containerSize;
    }

    public void setContainerSize(Integer containerSize) {
        this.containerSize = containerSize;
    }
}

package com.sajib.graph.types;

/**
 * Created by sajib on 2/20/19.
 */
public class ResultPath<N> {
    private N start;
    private N end;
    private String modeOfTransport;
    private Integer cost;
    private Integer daysTaken;

    public ResultPath() {
    }

    public ResultPath(N start, N end, Integer cost, Integer daysTaken, String modeOfTransport) {
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.daysTaken = daysTaken;
        this.modeOfTransport = modeOfTransport;
    }

    public N getStart() {
        return start;
    }

    public void setStart(N start) {
        this.start = start;
    }

    public N getEnd() {
        return end;
    }

    public void setEnd(N end) {
        this.end = end;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(Integer daysTaken) {
        this.daysTaken = daysTaken;
    }

    public String getModeOfTransport() {
        return modeOfTransport;
    }

    public void setModeOfTransport(String modeOfTransport) {
        this.modeOfTransport = modeOfTransport;
    }
}


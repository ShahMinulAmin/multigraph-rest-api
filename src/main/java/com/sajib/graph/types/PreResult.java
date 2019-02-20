package com.sajib.graph.types;

/**
 * Created by sajib on 2/20/19.
 */
public class PreResult<N, E> {
    private N from;
    private N to;
    private E edgeProperty;

    public PreResult(N from, N to, E edgeProperty) {
        this.from = from;
        this.to = to;
        this.edgeProperty = edgeProperty;
    }

    public N getFrom() {
        return from;
    }

    public void setFrom(N from) {
        this.from = from;
    }

    public N getTo() {
        return to;
    }

    public void setTo(N to) {
        this.to = to;
    }

    public E getEdgeProperty() {
        return edgeProperty;
    }

    public void setEdgeProperty(E edgeProperty) {
        this.edgeProperty = edgeProperty;
    }
}

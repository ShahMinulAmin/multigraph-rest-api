package com.sajib.graph.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic multigraph data structure
 *
 * Created by sajib on 2/20/19.
 */
public class Multigraph<N, E> {
    private Map<N, Map<N, E>> map = new HashMap<>();

    public void addEdge(N node1, N node2, E edgeProperty) {
        Map<N, E> adjacent = map.get(node1);
        if (adjacent == null) {
            adjacent = new HashMap();
            map.put(node1, adjacent);
        }
        adjacent.put(node2, edgeProperty);
    }

    public void addTwoWayVertex(N node1, N node2, E edgeProperty) {
        addEdge(node1, node2, edgeProperty);
        addEdge(node2, node1, edgeProperty);
    }

    public boolean isConnected(N node1, N node2) {
        Map<N, E> adjacent = map.get(node1);
        if (adjacent == null) {
            return false;
        }
        return adjacent.containsKey(node2);
    }

    public Collection<Map.Entry<N, E>> adjacentNodes(N node) {
        Map<N, E> adjacent = map.get(node);
        if (adjacent == null) {
            return new ArrayList<Map.Entry<N, E>>();
        }
        return adjacent.entrySet();
    }
}

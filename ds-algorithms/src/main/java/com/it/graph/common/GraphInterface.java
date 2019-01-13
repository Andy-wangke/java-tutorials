package com.it.graph.common;

import java.util.Iterator;

public interface GraphInterface {

    int getNumV();

    boolean isDirected();

    void insert(Edge edge);

    boolean isEdge(int source, int dest);

    Edge getEdge(int source, int dest);

    Iterator<Edge> edgeItorator(int source);
}

package com.it.graph.common;

import java.util.Iterator;

public interface GraphOperation {

    int getNumV();

    boolean isDirected();

    void insert(Graph.Edge edge);

    boolean isEdge(int source, int dest);

    Graph.Edge getEdge(int source, int dest);

    Iterator<Graph.Edge> edgeItorator(int source);
}

package com.it.graph.common;

public class Graph {

    
    /**
     * 
     * @author K.Andy Wang
     *
     */
    private class Vertex{
        private String id;
        private Edge edges;
    }
    /**
     * represents arcs which connect a pair of vertices
     * 
     * @author K.Andy Wang
     */
    private class Edge {

        private int dest;
        private int source;
        private double weight;

        public Edge(int s, int d) {

        }

        public Edge(int s, int d, double w) {

        }

        public int getDest() {
            return dest;
        }

        public void setDest(int dest) {
            this.dest = dest;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

    }

}

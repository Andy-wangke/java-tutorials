package com.it.graph.common;

public class Graph {

    
    /**
     * Vertex or Node
     * @author K.Andy Wang
     *
     */
    protected class Vertex{
        private String id;
        private Edge edges;
        
        
        
        
    }
    /**
     * represents arcs which connect a pair of vertices
     * 
     * @author K.Andy Wang
     */
    protected class Edge {

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

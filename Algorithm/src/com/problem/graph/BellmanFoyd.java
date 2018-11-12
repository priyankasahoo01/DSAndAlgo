package com.problem.graph;

import java.util.List;
class Edge<T>{
    T vertex1;
    T vertex2;
    public Edge(T vertex1, T vertex2) {
        super();
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }
    public T getVertex1() {
        return vertex1;
    }
    public void setVertex1(T vertex1) {
        this.vertex1 = vertex1;
    }
    public T getVertex2() {
        return vertex2;
    }
    public void setVertex2(T vertex2) {
        this.vertex2 = vertex2;
    }
    
}

class Graph<T>{
    List<Edge<T>> edges;
    int vertexes;
    
    public Graph(int vertexes) {
        super();
        this.vertexes = vertexes;
    }
    public List<Edge<T>> getEdges() {
        return edges;
    }
    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }
    public int getVertexes() {
        return vertexes;
    }
    public void setVertexes(int vertexes) {
        this.vertexes = vertexes;
    }
    
    public void add(T u, T v) {
        Edge
    }
    
}

public class BellmanFoyd {
    
    public void shortestPath(Graph<Integer> graph, int start) {
        
    }

}

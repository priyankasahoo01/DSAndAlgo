package com.problem.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Edge<T>{
	Vertex<T> vertex1;
	Vertex<T> vertex2;
    Integer weight;
    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, Integer wt) {
        super();
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        weight = wt;
    }
    public Vertex<T> getVertex1() {
        return vertex1;
    }
    public void setVertex1(Vertex<T> vertex1) {
        this.vertex1 = vertex1;
    }
    public Vertex<T> getVertex2() {
        return vertex2;
    }
    public void setVertex2(Vertex<T> vertex2) {
        this.vertex2 = vertex2;
    }
    
}

class Vertex<T>{
	T i;
	Vertex(T i){
		this.i=i;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i == null) ? 0 : i.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex<T> other = (Vertex<T>) obj;
		if (i == null) {
			if (other.i != null)
				return false;
		} else if (!i.equals(other.i))
			return false;
		return true;
	}
	
	
}
public class MyGraph<T>{
    List<Edge<T>> edges;
    List<Vertex<T>> vertices;
    
    Map<Vertex<T>, List<Edge<T>>> map= new HashMap<>();
    
    
//    public Graph(int vertexCount) {
//        super();
//        this.vertices = vertexCount;
//    }
    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }
    public List<Vertex<T>> getVertexes() {
        return vertices;
    }
    public void setVertexes(List<Vertex<T>> vertexes) {
        this.vertices = vertexes;
    }
    
    
    public boolean addVertex(T data) {
    	Vertex<T> vertex = new Vertex<T>(data);
    	if(!getVertexes().contains(vertex)) {
    		getVertexes().add(vertex);
    		return true;
    	}
    	return false;
    }
    
    
    
    public void addEdge(T u, Vertex<T> v, Integer w) {
        Edge<T> edge = new Edge<T>(u, v, w);
        addVertex(u);addVertex(v);
        this.edges.add(edge);
    }
    
}



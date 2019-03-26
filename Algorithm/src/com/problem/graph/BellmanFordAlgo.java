package com.problem.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFordAlgo {
	public static void main(String[] args) {
		MyGraph<Integer> graph = createGraph();
	}

	private static MyGraph<Integer> createGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	public int findMinPath(MyGraph<Integer> graph, Integer start) {
		int vCount = graph.getVertexes().size();
		List<Edge<Integer>> edges = graph.edges;
		Map<Vertex<Integer>,	Integer> map = new HashMap<>();
		for(Vertex<Integer> v: graph.vertices) {
			map.put(v, Integer.MAX_VALUE);
		}
		map.put(new Vertex<Integer>(start), 0);
		
		
		for (int i = 0; i < vCount-1; i++) {
			relax(edges, map);
		}
		return 0;

	}
	
	public void relax(List<Edge<Integer>> edges, Map<Vertex<Integer>,	Integer> map) {
		for(Edge<Integer> edge: edges) {
			Vertex<Integer> v1 = edge.getVertex1();
			Vertex<Integer> v2 = edge.getVertex2();
		}
	}

}

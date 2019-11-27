package com.app.dfs.adjacencylist.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {
	
	private int vertex;
	private int edge;
	
	private LinkedList<Integer> adjListArray[];
	
	public Graph(int vertex) {
		this.vertex = vertex;
		this.edge = 0;
		this.adjListArray = new LinkedList[vertex];
		for(int i = 0;i<vertex;i++) {
			this.adjListArray[i] =  new LinkedList<Integer>();
		}
	}
	

	public void addEdge(int src, int dest) {
		this.adjListArray[src].add(dest);
		this.adjListArray[dest].add(src);
		
	}
	public LinkedList<Integer> getAdjVertex(int vertex){
		return this.adjListArray[vertex];
	}
	public void printGraph() {
		for(int v=0;v<vertex;v++) {
				System.out.println("Adjacency list of vertex "+ v); 
	            System.out.print("head"); 
	            for(Integer pCrawl: adjListArray[v]){ 
	                System.out.print(" -> "+pCrawl); 
	            } 
	            System.out.println("\n"); 
		}
	}
	
	public static void main(String args[]) {
		Graph graph = new Graph(8);
		graph.addEdge(1,7);
		graph.addEdge(1, 4);
		graph.addEdge(4, 2);
		graph.addEdge(0, 2);
		graph.printGraph();
		
		for(Integer i:dfs(graph,1)) {
			System.out.println(i);
		}
		System.out.println("bfs....");
		for(Integer i:bfs(graph,1)) {
			System.out.println(i);
		}
		
		System.out.println("bfs2....");
		for(Integer i:bfs02(graph,1)) {
			System.out.println(i);
		}
	}
	
	public static List<Integer> dfs(Graph graph, int root){
		ArrayList<Integer> listOfVisited = new ArrayList<Integer>();
		
		Stack<Integer> stack = new Stack<Integer>();
		Set<Integer> visited = new LinkedHashSet<Integer>();
		stack.push(root);
		
		while(!stack.isEmpty())
		{	
			Integer popup = stack.pop();
			if(!visited.contains(popup)) {
				listOfVisited.add(popup);
				visited.add(popup);
				for(Integer i:graph.getAdjVertex(popup)) {
					stack.push(i);
				}
			}
			
		}
		return listOfVisited;
		
	}
	
	public static List<Integer> bfs(Graph graph, int root){
		ArrayList<Integer> listOfVisited = new ArrayList<Integer>();		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		Set<Integer> visited = new LinkedHashSet<Integer>();
		queue.add(root);
		
		while(!queue.isEmpty())
		{	
			Integer popup = queue.remove();
			if(!visited.contains(popup)) {
				listOfVisited.add(popup);
				visited.add(popup);
				for(Integer i:graph.getAdjVertex(popup)) {
					queue.add(i);
				}
			}
			
		}
		return listOfVisited;
		
	}
	
	
	public static List<Integer> bfs02(Graph graph, int root){
		ArrayList<Integer> listOfVisited = new ArrayList<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		Set<Integer> visited = new LinkedHashSet<Integer>();
		
		queue.add(root);
		visited.add(root);
		
		listOfVisited.add(root);
		while(!queue.isEmpty())
		{
			Integer popup = queue.remove();
			for(Integer i:graph.getAdjVertex(popup)) {

				if(!visited.contains(i)) {
					visited.add(i);
					queue.add(i);
					listOfVisited.add(i);
				};
					
			}
		}
		
		return listOfVisited;
	}
}

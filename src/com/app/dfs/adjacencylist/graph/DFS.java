package com.app.dfs.adjacencylist.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class DFS {
	
	/*Depth-First Search (DFS)

	 Problem (informal): Given starting node, traverse a graph by looking at deeper nodes before closer ones
	 
	 Algorithm: Continue down branch as far as possible, visit deepest unvisited node, backtrack
	 
	 Complexity:
	 	* Time - O(|V| + |E|) where V is set of vertices and E is set of edges
	 	* Space - O(|V|) to store visited vertices
	 
	 Functions Defined:
	 	* iterativeDFS() - Iterative stack-based implementation of DFS
	 	* recursiveDFS - Recursive implementation of DFS (calls DFS on unvisited children)
	 	* mat2list() - Takes adjacency matrix input and returns equivalent adjacency list
	 	* list2mat() - Takes adjacency list input and returns equivalent adjacency matrix
	 	* display() - Takes adjacency matrix input and displays GUI graph visualization (*Requires JUNG Library*)
	 
	 */

	public static void main(String[] args) {
		boolean[][] adjmat={/*1*/ {true, true, true, true, false, false, false, false, false},
		 		  /*2*/ {true, false, true, false, true, true, false, false, false},
		 		  /*3*/ {true, true, false, true, false, true, true, false, false},
		 		  /*4*/ {true, false, true, false, false, false, true, false, false},
		 		  /*5*/ {false, true, false, false, false, true, false, true, false},
		 		  /*6*/ {false, true, true, false, true, false, true, true, false},
		 		  /*7*/ {false, false, true, true, false, true, false, true, false},
		 		  /*8*/ {false, false, false, false, true, true, true, false, false},
		 		  /*9*/ {false, false, false, false, false, false, false, false, false}};



		ArrayList<ArrayList<Integer>> adjlist=mat2list(adjmat);
		
		//Starting node
		int root=5;
		
		//Prints DFT for graph
		//Recursive or iterative DFS
		//recursiveDFS(adjlist, root, new boolean[adjlist.size()+1]);
		System.out.println(iterativeDFS(adjlist, root));
		
		//Uncomment next line to visualize graph
		display(adjmat, "Graph");
	}
	
	//Iterative implementation of DFS
	public static ArrayList<Integer> iterativeDFS(ArrayList<ArrayList<Integer>> adjlist, int source){
		boolean[] visited = new boolean[adjlist.size()+1];
		Stack<Integer> stack=new Stack<Integer>();
		stack.add(source);
		visited[source] = true;
		ArrayList<Integer> dft = new ArrayList<Integer>();
		while(!stack.isEmpty()){
			int top=stack.peek();
			stack.pop();
			if(!visited[top]){
				dft.add(top);
				visited[top] = true;
			}
			for(int node:adjlist.get(top-1))
				if(!visited[node]){
					stack.push(node);
				}
		}
		return dft;
	}
	
	//Recursive implementation of DFS
	public static void recursiveDFS(ArrayList<ArrayList<Integer>> adjlist, int node, boolean[] visited){
    	visited[node] = true;
    	//System.out.println(node);
    	for(int child:adjlist.get(node)){
    		if(!visited[child]){
    			recursiveDFS(adjlist, child, visited);
    		}
    	}
    }
		
	//converts adjacency matrix to adjacency list
	public static ArrayList<ArrayList<Integer>> mat2list(boolean[][] adj){
		ArrayList<ArrayList<Integer>> adjlist=new ArrayList<ArrayList<Integer>>();
		
		for(int i=0;i<adj.length;i++){
			adjlist.add(new ArrayList<Integer>());
			for(int j=0;j<adj[0].length;j++){
				if(adj[i][j])
					adjlist.get(i).add(j+1);
			}
		}
		return adjlist;
	}
	
	//Converts adjacency list to adjacency matrix
	public static boolean[][] list2mat(ArrayList<ArrayList<Integer>> adjlist){
		boolean[][] adjmat = new boolean[adjlist.size()][adjlist.size()];
		for(int i=0;i<adjmat.length;i++){
			for(int adj : adjlist.get(i)){
				adjmat[i][adj-1] = true;
			}
		}
		return adjmat;
	}
	
	//Uses JUNG to display 2D graph 
	public static void display(boolean[][] adj, String name){
		
		//Populate Graph object
		Graph<Integer,Integer> graph=new SparseMultigraph<Integer,Integer>();
		for(int i=0;i<adj.length;i++){
			graph.addVertex(i+1);
			for(int j=0;j<adj[0].length;j++){
				if(adj[i][j])
					graph.addEdge((int)(Math.random()*Integer.MAX_VALUE), i+1, j+1, EdgeType.UNDIRECTED);
			}
		}
		 
		//VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(650, 650));
		//Initialize visualization
        Layout<Integer, String> layout = new CircleLayout(graph);
		layout.setSize(new Dimension(620,620));
        VisualizationViewer<Integer,String> vs = new VisualizationViewer<Integer,String>(layout);
        vs.setPreferredSize(new Dimension(650,650));
        
        //Creates GraphMouse and adds to visualization
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vs.setGraphMouse(gm);
		
		//Initialize JFrames
		JFrame frame = new JFrame(name);
	    frame.getContentPane().setBackground(Color.RED);
	    frame.getContentPane().add(vs);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	 
	    
	    //Colors Vertices
        Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
           public Paint transform(Integer i) {
        	   return Color.GREEN;
           } 
       };
		
	    //Labels Edges
	    float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeStrokeTransformer =new Transformer<String, Stroke>(){
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };
        
        //Renders Vertex colors/labels
        vs.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        //Renders Edge labels
        //vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

	}

}


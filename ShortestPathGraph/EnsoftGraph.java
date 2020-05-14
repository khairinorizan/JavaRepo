package ProgrammingPractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import ProgrammingPractice.EnsoftGraph.Edge;
import ProgrammingPractice.EnsoftGraph.Vertex;

public class EnsoftGraph {
	 ArrayList<Edge> edgeList;
	 ArrayList<Vertex> vertexList;
	 ArrayList<Vertex> vertexToAvoid;
	 ArrayList<Vertex> peggyPos;
	 ArrayList<Vertex> samPos;
	 Set<Vertex> retVal;
	 HashMap<Vertex, ArrayList<Vertex>> table;
	 
	 public static <E> void main(String[] args) throws FileNotFoundException{

		EnsoftGraph graph = new EnsoftGraph();
			
		String fileName = "inputFile.txt";
			
		graph.createGraph(fileName);
		
		graph.sort(graph.DFS(graph.peggyPos));
		
		for(Vertex v : graph.sort(graph.DFS(graph.peggyPos))) {
			System.out.println(v.object);
		}

	}
		
	public EnsoftGraph() {
		edgeList = new ArrayList<Edge>();
		vertexList = new ArrayList<Vertex>();
		vertexToAvoid = new ArrayList<Vertex>();
		peggyPos = new ArrayList<Vertex>();
		samPos = new ArrayList<Vertex>();
		retVal = new HashSet<Vertex>();
		table = new HashMap<Vertex, ArrayList<Vertex>>();
	}
		
	public class Vertex{
		String object;
			
		public Vertex(String object) {
			this.object = object;
		}
	}
		
	public class Edge{
		Vertex source;
		Vertex destination;
			
		public Edge(Vertex source, Vertex destination){
			this.source = source;
			this.destination = destination;
		}
			
	}
	
	public boolean addVertex(String object) {
		Vertex newVertex = new Vertex(object);
		
		if(vertexList.size() == 0) {
			vertexList.add(newVertex);
			return true;
		}else {
			for(Vertex vertex : vertexList) {
				if(vertex.object.equals(object)) {
					return false;
				}
			}
			vertexList.add(newVertex);
			return true;
		}
		
	}
	
	public String getVertex(Vertex v) {
		String retVal = null;
		
		if(!vertexList.contains(v)) {
			return null;
		}else {
			for(Vertex vertex  : vertexList) {
				if(vertex.object.equals(v.object)) {
					retVal = v.object;
				}else {
					continue;
				}
			}
		}
		
		return retVal;
	}
	
	public boolean addEdge(Vertex source, Vertex destination) {
		Edge newEdge = new Edge(source, destination);
		
		if(edgeList.contains(newEdge)) {
			return false;
		}else {
			edgeList.add(newEdge);
			return true;
		}
		
	}
	
	public Set<Vertex> getNeighbors(Vertex vertex){
		Set<Vertex> returnSet = new HashSet<Vertex>();
		
		for(Edge e : edgeList) {
			if(e.source.equals(vertex)){
        		returnSet.add(e.destination);
            }
		}
		
		return returnSet;
		
	}
	
	public void createVertices(List<String> vertices){
		
		for(int i=0; i < vertices.size(); i++) {
			addVertex(vertices.get(i));
		}
		
	}
	
	public void createGraph(String inputFileName) {
		File file = new File(inputFileName);
		
		try {
			Scanner scanFile = new Scanner(file);
			
			String newLine = scanFile.nextLine(); // must be 'Map:'
			
			
			while(scanFile.hasNextLine()) {
				if(newLine.equals("Avoid:")) {
					newLine = scanFile.nextLine();
					Scanner scanNewLine = new Scanner(newLine);
					
					while(scanNewLine.hasNext()) {
						String val = scanNewLine.next();
						
						for(Vertex v : vertexList) {
							if(v.object.equals(val)) {
								vertexToAvoid.add(v);
							}
						}
					}
										
					newLine = scanFile.nextLine();
					
				}else if(newLine.equals("Peggy:")) {
					newLine = scanFile.nextLine();
					Scanner scanNewLine = new Scanner(newLine);
					
					
					while(scanNewLine.hasNext()) {
						String val = scanNewLine.next();
						
						for(Vertex v : vertexList) {
							if(v.object.equals(val)){
								peggyPos.add(v);
							}
						}
					}
					
					newLine = scanFile.nextLine();
					
				}else if(newLine.equals("Sam:")) {
					newLine = scanFile.nextLine();
					Scanner scanNewLine = new Scanner(newLine);
					
					
					while(scanNewLine.hasNext()) {
						String val = scanNewLine.next();
						
						for(Vertex v : vertexList) {
							if(v.object.equals(val)) {
								samPos.add(v);
							}
						}
					}
					
					
				}else {
					if(newLine.equals("Map:")) {
						newLine = scanFile.nextLine();
					}
					
					Scanner scanNewLine = new Scanner(newLine);
					ArrayList<Vertex> vertexToEdge = new ArrayList<Vertex>();
					
					while(scanNewLine.hasNext()) {
						String val = scanNewLine.next();
						
						addVertex(val);
						
						for(Vertex v : vertexList) {
							if(v.object.equals(val)) {
								vertexToEdge.add(v);
							}
						}
					}
					
					addEdge(vertexToEdge.get(0),vertexToEdge.get(1));
					
					newLine = scanFile.nextLine();
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean containsinSam(Vertex vertex) {
		for(Vertex v : samPos) {
			if(v.object.equals(vertex.object)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean containsAvoid(Vertex vertex) {
		for(Vertex v : vertexToAvoid) {
			if(v.object.equals(vertex.object)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public Set<Vertex> DFS(List<Vertex> startingPoint) {
		
		Set<Vertex> returnSet = new HashSet<Vertex>();
		ArrayList<Vertex> visited = new ArrayList<Vertex>();
		ArrayList<Vertex> unvisited = new ArrayList<Vertex>();
		
		
		for(Vertex vertex : startingPoint) {
			
			if(containsAvoid(vertex) == true) {
				continue;
			}
				
			for(Vertex v : vertexList) {
				ArrayList<Vertex> predList = new ArrayList<Vertex>();
				table.put(v, predList); 
			}
			
			unvisited.add(vertex);
			
			while(!unvisited.isEmpty()) {
				Vertex currVertex = unvisited.get(0);
				unvisited.remove(currVertex);
				visited.add(currVertex);
				
				for(Vertex neighbor : getNeighbors(currVertex)) {
					boolean addToUnvisited = true;
					
					if(!visited.contains(neighbor)) {
						
						if(vertexToAvoid.size() > 0) {
							for(Vertex avoid : vertexToAvoid) {
								if(!neighbor.object.equals(avoid.object)) {
									ArrayList<Vertex> predListRetrieve = table.get(neighbor);
									predListRetrieve.add(currVertex);
									table.replace(neighbor, predListRetrieve);
									addToUnvisited = true;
								}else {
									addToUnvisited = false;
									continue;
								}
								
								if(addToUnvisited == true) {
									if(!unvisited.contains(neighbor)) {
										unvisited.add(neighbor);
									}
								}
								
							}
						}else {
							ArrayList<Vertex> predListRetrieve = table.get(neighbor);
							predListRetrieve.add(currVertex);
							table.replace(neighbor, predListRetrieve);
							
							if(addToUnvisited == true) {
								if(!unvisited.contains(neighbor)) {
									unvisited.add(neighbor);
								}
							}
						}
						

						
					}else {
						ArrayList<Vertex> predListRetrieve = table.get(neighbor);
						predListRetrieve.add(currVertex);
						table.replace(neighbor, predListRetrieve);
					}
					
				}
			}
			
			for(Vertex v : samPos) {
				
				returnSet.addAll(pathList(v));
			}
			
			
		}
		
		return returnSet;
	}
	

	public Set<Vertex> pathList(Vertex destinationPos){
		
		if(table.get(destinationPos).size() == 0) {
			if(containsinSam(destinationPos) == true) {
				return retVal;
			}else {
				retVal.add(destinationPos);
				return retVal;
			}
		}else {
			retVal.add(destinationPos);
			Vertex predVertex = destinationPos;
			
			ArrayList<Vertex> path = table.get(predVertex);
				
			for(Vertex vertex : path) {
				pathList(vertex);
			}
			
		}
		
		return retVal;
	} 
	
	public Vertex[] sort(Set<Vertex> set){
		Vertex retVal1[] = new Vertex[set.size()];
		int counter = 0;
		Vertex temp;
		
		for(Vertex v : set) {
			retVal1[counter] = v;
			counter++;			
		}
		
		for(int i=0; i < set.size(); i++) {
			for(int j=i+1; j < set.size(); j++) {
				if(retVal1[i].object.compareTo(retVal1[j].object) > 0) {
					temp = retVal1[i];
					retVal1[i] = retVal1[j];
					retVal1[j] = temp;
				}
			}
		}
		
		return retVal1;
		
		
	}
	
	
	
	
}

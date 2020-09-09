
import java.io.*;
import java.util.*;
import java.util.ArrayList; 

public class GraphStuff {

	private static int length;
	public static ArrayList<Integer>[] adjacent;//adjacentcy list used for eularian

	/**
	 * main method
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Must pass a file.\nexiting...");
			System.exit(0);
		}
		File inputFile = new File(args[0]);
		length = findLength(inputFile);
		System.out.println("The number of verticies is : " + length);
		int[][] master = fileParser(inputFile);
		
		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(11, new EdgeComparator());
		// System.out.println("edges:");
		// printEdges(pQueue);
		pQueue = getEdges(master);// creates a priority queue for the
		System.out.println("Kruskals: ");
		printKrusKals(KruskalsMethod(pQueue, length));

		System.out.println("prims:");
		prims(master);
		printArrays(master);
		System.out.println("Hams: ");
		hamiltonianMain(master);
		System.out.println("Eularian:");
		eularian(master);

	}

	/**
	 * This method will print up the minimum spanning tree form Kruskals Method.
	 * 
	 * @param kruskalsMethod
	 *            a singly linked list that represnts the minimum spanning tree by
	 *            saying what edges are added in what order.
	 */
	private static void printKrusKals(SLList<Edge> kruskalsMethod) {
		Node<Edge> node = kruskalsMethod.getHead();

		while (node != null) {// while you are not at the end of the list

			System.out.println(node.getElement().toString());// print out the verticies and the weight
			node = node.getNext();// move to the next node in the list.
		}

	}

	/**
	 * method to find length of arrays
	 * 
	 * @param inputFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public static int findLength(File inputFile) throws FileNotFoundException {
		int length = 0;
		Scanner file = new Scanner(inputFile);
		while (file.hasNextLine()) {
			file.nextLine();
			length++;
		}
		file.close();
		return length;
	}

	/**
	 * file parser that makes 2d array from matirx file
	 * 
	 * @param inputFile
	 *            the file the user wishes to subject to this tourture/ break the
	 *            code with it is one of the two who knows.
	 * @return a 2d array that represents the matrix that was in the file hopefully
	 * @throws FileNotFoundException
	 *             hey we couldn't find that file.
	 */
	private static int[][] fileParser(File inputFile) throws FileNotFoundException {
		int i = 0;// track position in master array
		Scanner file = new Scanner(inputFile);
		int master[][] = new int[length][length];
		while (file.hasNextLine()) {
			int pos = 0;
			int subArray[] = new int[length];
			String line = file.nextLine();// getting the line from file
			Scanner sLine = new Scanner(line);// making scanner of line.
			while (sLine.hasNext()) {
				String number = sLine.next();
				System.out.print(number);
				int num = Integer.parseInt(number);
				subArray[pos] = num;
				pos++;
			} // inner while loop
			master[i] = subArray;
			i++;
			System.out.println();
			sLine.close();
		} // outer while loop
		file.close();
		return master;

	}// function end

	/**
	 * method to print array
	 * 
	 * @param master
	 */
	private static void printArrays(int[][] master) {
		for (int i = 0; i < master.length; i++) {
			for (int j = 0; j < master.length; j++) {
				System.out.print(" " + master[i][j]);
			}
			System.out.println();
		}

	}

	/**
	 * This just prints up all the edges in the graph.
	 * 
	 * @param pQueue
	 *            a priority queue of all the edges.
	 */
	private static void printEdges(PriorityQueue<Edge> pQueue) {
		while (!pQueue.isEmpty()) {
			Edge edge = pQueue.remove();
			System.out.println(edge.getVertex()[0] + " --> " + edge.getVertex()[1] + " Weight: " + edge.getWeight());

		}
	}

	// -----------------------Prims stuff here--------------------------------

	/**
	 * This function will create the minimum spanning tree for a grpah it is passed
	 * the code is based off a geeks for geeks c++ implementation.
	 * 
	 * @param master
	 *            the 2d array representing the graph matrix.
	 */
	private static void prims(int[][] master) {
		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(11, new EdgeComparator());
		int parent[] = new int[length];
		int key[] = new int[length];
		Boolean visited[] = new Boolean[length];
		pQueue = getEdges(master);
		for (int i = 0; i < length; i++) {
			key[i] = Integer.MAX_VALUE;// edge has an infinite cost
			visited[i] = false;// empty tree
		} // end of the forloop
		key[0] = 0;// the starting vertex.
		parent[0] = -1;// there is no parent for the root.
		for (int i = 0; i < length; i++) {
			int vertex = lowestCost(key, visited);
			visited[vertex] = true;
			for (int j = 0; j < length; j++) {
				if (master[vertex][j] > 0) {
					if (visited[j] == false && master[vertex][j] < key[j]) {
						parent[j] = vertex;
						key[j] = master[vertex][j];
					} // ends inner if statement
				} // ends outer if statement

			} // ends inner loop

		} // ends outer loop

		printPrims(parent, master);
	}// end of the function

	private static PriorityQueue<Edge> getEdges(int[][] master) {
		PriorityQueue<Edge> pQueue = new PriorityQueue<Edge>(11, new EdgeComparator());
		for (int i = 1; i < length; i++) {
			for (int j = 0; j < i; j++) {
				if (master[i][j] != 0) {
					Edge newEdge = new Edge(i, j, master[i][j]);
					pQueue.add(newEdge);
				} // end of if

			} // end inner loop
		} // end outer loop
		return pQueue;
	}// end function

	/**
	 * This function prints up the minimum spanning tree for a 2d array it is
	 * passed.
	 * 
	 * @param parent
	 *            the array representing the minimum spanning tree
	 * @param master
	 *            the 2d array that represents the graph. used to get the weights.
	 */
	private static void printPrims(int[] parent, int[][] master) {

		for (int i = 1; i < length; i++) {
			System.out.println(parent[i] + " --> " + i + " weight: " + master[i][parent[i]]);
		} // end of loop

	}// end of printPrims

	/**
	 * This function will search for the next move to make for the prims method of
	 * finding the Minimum Spanning Tree
	 * 
	 * @param key
	 *            a array of type integer.
	 * @param visited
	 *            a boolean array to track the vertecies that have been visited.
	 * @return
	 */
	private static int lowestCost(int[] key, Boolean[] visited) {
		int minKey = Integer.MAX_VALUE;// start out not knowing where we are going
		int vertex = 0;// We don't know which vertex to go to.
		for (int i = 0; i < length; i++) {
			if (visited[i] == false && key[i] < minKey) {// if node is unvisited and the key is less than max value.
				minKey = key[i];// found the new best cost move
				vertex = i;// remember where we are going.
			}
		}
		return vertex;// the next move.

	}

	// ----------------------------------Kruskals stuff---------------------------------------------------
	/**
	 * These methods are based on the code referenced on ide.turorialhorizon.com this is
	 * where we got the idea to use sets
	 */

	/**
	 * This method will perform Kruskals method on the graph form the file. 
	 * 
	 * @param edges
	 *            a priority Queue of the edges sorted least weight to greatest
	 *            weight
	 * @param length
	 *            how many edges there are
	 * @return a SLList (from cs245) that contains the minimum spanning tree.
	 */
	public static SLList<Edge> KruskalsMethod(PriorityQueue<Edge> edges, int length) {
		Map<Integer, Integer> papa = new HashMap<>();
		SLList<Edge> minimumTree = new SLList<Edge>();// the future tree

		makeSets(length, papa);// make all the vertexes their own root
		while (minimumTree.length() != length - 1) {
			Edge current = edges.remove();
			int x = root(current.getStart(), papa);// get the root of vertex start
			int y = root(current.getEnd(), papa);// get the root of vertex end
			if (x != y) {// are they the same root if they are they are in the same set and will cause a
							// cycle else they can be merged
				minimumTree.add(current);// add the edge
				unite(x, y, papa);// unite the sets so that we know they are attached.
			}
		}
		return minimumTree;// return the tree

	}

	/**
	 * This function makes the disjoint set for all things in the graph.
	 * 
	 * @param num
	 */
	private static void makeSets(int num, Map<Integer, Integer> papa) {
		for (int i = 0; i < num; i++) {// for each vertex
			papa.put(i, i);// put it in the hash map.
		}

	}

	/**
	 * unite the two sets into one almighty megatron set
	 * 
	 * @param a
	 *            the first set
	 * @param b
	 *            the second set
	 */
	private static void unite(int a, int b, Map<Integer, Integer> papa) {
		int d = root(a, papa);// find root of the first set (David)
		int n = root(b, papa);// find the root of the second set (Niko)
		papa.put(d, n); // they are now married (forever)
	}

	/**
	 * This function gets the root of a set
	 * 
	 * @param key
	 *            what you are looking up
	 * @return the root of the set
	 */
	private static int root(int key, Map<Integer, Integer> papa) {
		int root;// the root we are looking for.
		if (papa.get(key) == key) {// are you at the root already?
			root = key;// yes reuturn that thing then
		} else {// otherwise keep looking
			root = root(papa.get(key), papa);
		}
		return root;// the root
	}

	// ----------------------------------Hamiltonian stuff-------------------------------------------------
	
	/**
	 * These functions are based on the algorithm implemented on 
	 */
	/**
	 * 
	 * @param master
	 */

	private static void hamiltonianMain(int[][] master) {
		Vertex[] verticies = new Vertex[length];// array for the verticies of the graph
		int path[] = new int[length];// keep track of the circuit

		for (int i = 1; i < length; i++) {
			path[i] = -1;
			// System.out.println(path[i]);
		}
		path[0] = 0;
		if (hasHams(master, path, 1) == false) {
			System.out.println("The graph has no Hamiltonian Circuit.");
		} else {
			System.out.println("The Graph has a Hamiltonian Circuit");
			System.out.println("Hamiltonian Cycle: ");
			printHams(path);
		} // end of the if block

	}

	private static void printHams(int[] path) {
		String circuit = "" + path[0];
		for (int i = 1; i < length; i++) {
			circuit = circuit + "--> " + path[i];
		}
		System.out.println(circuit + " --> " + path[0]);

	}

	private static boolean hasHams(int[][] master, int[] path, int position) {
		if (position == length) {// is everything in the path?
			if (master[path[position - 1]][path[0]] != 0) {// is there a path from the most recent vertex to the
															// starting vertex.
				return true; // yes
			} else {
				return false;// no
			} // end else
		} // end big if block that is base case test.

		for (int i = 1; i < length; i++) {// add as many vertices as needed.
			if (isValid(i, master, path, position)) {// if it is okay to add that edge.
				path[position] = i;
				if (hasHams(master, path, position + 1) == true) {
					return true;
				} // end path recursion.
				path[position] = -1;
			} // end if block
		} // end for loop

		return false;
	}// end of hasHams

	/**
	 * 
	 * @param vertex
	 * @param master
	 * @param path
	 * @param position
	 * @return
	 */
	private static boolean isValid(int vertex, int[][] master, int[] path, int position) {

		if (master[path[position - 1]][vertex] == 0) {// Before adding a vertex, check for whether it is adjacent to the
														// previously added vertex
			return false;
		}
		for (int i = 0; i < position; i++) {// Has the vertex already been visited.
			if (path[i] == vertex) {// •If we do not find a vertex then we return false.
				return false;
			}
		}

		return true;// •If we find such a vertex, we add the vertex as part of the solution.

	}
	
	/* The following code is used to find and print the eularian circuits and paths
	 * It is heavily based off of code we found online: https://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/
	 *
	 */
	
	/**
	 * this method makes the graph to be used to calculate the Eularian path/circuit
	 * it also checks if there is an eularian path/circuit
	 * if there is one, it prints it
	 * @param matrix
	 */
	@SuppressWarnings("unchecked")
	public static void eularian(int[][] matrix) {
		adjacent = new ArrayList[length];
		Integer start = 0;
		for(int i = 0; i < length; i++) {
			adjacent[i] = new ArrayList<>();
		}
		/* these loops loop through the top half of the matrix and add edges to array list
		 * this will then keep track of which vertices are connected
		 */
		for(int i = 0; i < length; i++) {
			for(int k = i; k < length; k++) {
				if(matrix[i][k] != 0) {
					addEdge(i, k);
				}
			}
		}
		if(isPath()) {//checks if it has a valid eularian path
			start = findStart();//finds odd degree to start
			System.out.print("Graph has a Euler path\nEularian path:\n" + start);
			printEularian(start);//print circuit
		}
		else if(isCircuit()){//finds in graph has valid eularian circuit
			System.out.print("Graph has a Euler circuit\nEularian circut:\n" + start);
			printEularian(start);
		}
		else {//no valid eularian stuff
			System.out.println("Graph does not have a Euler circuit or path");
		}
	}
	
	/**
	 * this method finds an odd degree and returns it for the start point
	 * @return
	 */
	public static Integer findStart() {
		Integer oddDegree = 0;//integer for finding vertex with odd degree
		for(int i = 0; i < length; i++) {//loop through adjacent array
			if(adjacent[i].size() % 2 == 1) {//if vertex has an odd degree
				oddDegree = i;//set oddDegree to return
				i = length + 1;//breaks loop
			}
		}
		return oddDegree;//returns oddDegree to start path
	}
	
	/**
	 * this method checks if the given graph has an eularian path
	 * @return
	 */
	public static boolean isPath() {
		int count = 0;//count number of odd degree vertices
		for(int i = 0; i < length; i++) {//loop through the array
			if(adjacent[i].size() % 2 == 1) {//if vertex has odd degree
				count++;//increase count
			}
		}
		if(count == 2) {//if count is 2 return true
			return true;
		}
		return false;//count was not 2
	}
	
	/**
	 * this method checks if the garph has an eularian circuit
	 * @return
	 */
	public static boolean isCircuit() {
		for(int i = 0; i < length; i++) {//loop through array
			if(adjacent[i].size() % 2 != 0) {//if a vertex has an odd degree the graph will not have a valid eularian circuit
				return false;
			}
		}
		return true;
	}
	
	/**
	 * this method adds edges between to passed vertices
	 * @param e1
	 * @param e2
	 */
	public static void addEdge(Integer v1, Integer v2) {
		adjacent[v1].add(v2);//adds edge between vertex 1 and vertex 2
		adjacent[v2].add(v1);//adds edge between vertex 2 and vertex 1
	}
	
	/**
	 * this method removes edges from two passed vertices
	 * @param e1
	 * @param e2
	 */
	public static void removeEdge(Integer v1, Integer v2) {
		adjacent[v1].remove(v2);//removes edge between vertex 1 and vertex 2
		adjacent[v2].remove(v1);//removes edge between vertex 2 and vertex 1
	}
	
	/**
	 * this method prints the eularian circuit/path
	 * @param start
	 */
	public static void printEularian(Integer start) {
		for(int i = 0; i < adjacent[start].size(); i++) {//loops through the edges of the given vertex
			Integer next = adjacent[start].get(i);//grab vertex connecting to given vertex
			if(isValidEdge(start, next)) {//checks if valid
				System.out.print("-->" + next);//print arrow and next vertex in path or circuit
				removeEdge(start, next);//remove edges
				printEularian(next);//recursivly call upon method
			}
		}
	}
	
	/**
	 * helper method to make sure that the edges are valid
	 * @param edge1
	 * @param edge2
	 * @return
	 */
	public static boolean isValidEdge(Integer v1, Integer v2) {
		if(adjacent[v1].size() == 1 ) {//if vertex 1 has only one edge return true
			return true;
		}
		boolean[] isVisited = new boolean[length];
		int count1 = dfsCount(v1, isVisited);//finds degree of vertex 1
		removeEdge(v1, v2);//temp removes edges
		isVisited = new boolean[length];
		int count2 = dfsCount(v1, isVisited);//finds degree of vertex 1
		addEdge(v1, v2);//puts edges back
		if(count1 > count2) {//finds if it is a bridge
			return false;
		}
		return true;
	}
	
	/**
	 * this method does a dfs to count how many vertices can be reached from v
	 * @param edge
	 * @param isVisted
	 * @return
	 */
	public static int dfsCount(Integer v, boolean[] isVisted) {
		isVisted[v] = true;//sets the vertex as visited
		int count = 1;
		for(int i : adjacent[v]) {//recurs through all the vertices 
			if(!isVisted[i]) {
				count = count + dfsCount(i, isVisted);
			}
		}
		return count;
	}
}

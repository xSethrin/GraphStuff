public class Edge{
	
	private int weight;
	private int vertex[] = new int [2];
	
	public Edge(int a, int b, int c) {
		vertex[0] = a;
		vertex[1] = b;
		weight = c;
		
	}
	public void setWeight(int x) {
		weight = x;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setVertex(int a, int b){
		vertex[0] = a;
		vertex[1] = b;
	}
	public int[] getVertex() {
		return vertex;
	}
	/**
	 * This returns the first point of the edge. 
	 * @return an int representing the starting vertex 
	 */
	public int getStart() {
		return vertex[0];
	}

	/**
	 * This returns the end point of the edge. 
	 * @return an int representing the ending vertex 
	 */
	public int getEnd() {
		return vertex[1];
	}
	
	public String toString() {
		return (vertex[1]+"--> "+ vertex[0]+ " weight: "+ weight);
	}

}
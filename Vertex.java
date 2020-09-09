
public class Vertex {
	public int iD;
	//private Edge[] edges;
	public Vertex[] vertexes;
	public int degree = 0;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            of vertex
	 * @param edges
	 *            of the vertex
	 */
/*
	public Vertex(String name, Edge[] edges) {
		this.setVertextNumber(name);
		this.setEdges(edges);
	}
*/

	/**
	 * General Constructor
	 */
	public Vertex(int x) {
		iD = x;
		
	}
	
	public void addVertex(Vertex v) {
		degree++;
		Vertex[] temp = new Vertex[degree];
		if(degree != 1) {
			for(int i = 0; i < degree-1; i++) {
				temp[i] = vertexes[i];
			}
		}
		temp[degree-1] = v;
		vertexes = temp;
	}


	
	public Vertex[] getVertexes() {
		return vertexes;
	}

	/**
	 * @return the edges
	 */
/*
	public Edge[] getEdges() {
		return edges;
	}
*/
	/**
	 * @param edges
	 *            the edges to set
	 */
/*
	public void setEdges(Edge[] edges) {
		this.edges = edges;
	}
*/
	/**
	 * @return the vertextNumber
	 */
	public int getID() {
		return iD;
	}

	/**
	 * @param vertextNumber
	 *            the vertextNumber to set
	 */
	public void setID(int v) {
		iD = v;
	}
	
	public int getDegree() {
		return degree;
	}

}

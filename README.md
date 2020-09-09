AUTHOR: 
	Nikolo Sperberg and Sterling Rohlinger

VERSION:
	1.0

CONTENTS: 
	
	Edge.java - creates edge objects to be used in GraphStuff.java
	EdgeComprator.java - enables priority queues for edges
	GraphStuff.java - river class
	Node.java - class to create nodes to be used in SLList.java, StackList.java
	SLList.java - class to create a single linked list
	Stack.java - class to create the interface of the StackList.java
	StackList.java - allows use of stacks
	Vertex.java - creates vertex objects used to test valid files

DISCRIPTION: 
	This program takes an adjacency matrix and searches for minimum spanning trees using Kruskals and Prims.
	It also checks in the graph contains a Hamiltonian circuit and checks for a eularian circuit/path.
	
COMPILE AND RUN:
	To run this program, you first must compile the .java files.  
	Use command prompt and type the following: javac *.java
	This will compile the code.
	To run the code, use the command prompt again. Type: java GraphStuff [FILENAME].txt*
	
	*replace [FILENAME] with name of the text file.  Text file must contain a valid matrix. Code will exit if 
	the passed file is not in the correct format or does not contain a valid matrix
	

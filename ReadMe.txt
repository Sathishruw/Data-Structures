/******************************************************************************
 *  Name:       Sathish Krishnasamy Ravichandran 
******************************************************************************/


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/

1. synset_data class is defined with attributes: Synset ID, noun array, synonym definition

2. A hash map with Integer key (Synset ID) and synset data value(Noun , synonym definition) is used to store all the values

synset_data class should be encapsulated well to store all its attributes. HashMap is used to store (synsetID, synset data) combination so that retrieval of data based on ID is faster and efficient. 

/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/

1. To store the relationship as defined by hypernym file, adjacency list is used. An arraylist contains TreeNode objects with Synset ID for all the synsets. TreeNode object of every vertex contains the next reference object to TreeNode objects of adjacent vertices. 

Adjacency matrix is not used to store the graph as it is not efficient in terms of performance. So adjacency list pointing to a linked list of adjacent verties are used. 

/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. What is the order of growth of
 *  the running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 *
 *  
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description:

                                              running time
method                               best case            worst case
-----------------------------------------------------------------------
length(int v, int w)				O(|V|+|E|)				O(|V|+|E|)

ancestor(int v, int w)				O(|V|+|E|)				O(|V|+|E|)



/******************************************************************************
Output:
Print your output here !
You output should contain the following:
1. List of all the edges of DAG
2. SAP ancestor and length between two noun ID’s
3. Sematic Relatedness between two nouns passed as console input.
 *****************************************************************************/

 Please refer Outputfile.txt

 Java Files attached: 

 Edge.java
 synset_data.java
 TreeNode.java
 Path.java
 WordNet.java
 SAP.java

 User interface to test SAP and Wordnet functions: 

 user_interface_wordnet.java

This program expects synsets.txt and hypernyms.txt file under root folder (/synsets.txt,/hypernyms.txt)
 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Path {
    
    private boolean[] marked;  //Checks if a vertex is visited or not on the path
    private int[] edgeTo;      
    private int[] distTo;      

    /*
     * Display the vertices that are visited on path
     */
    public void displayMarkedVertices(ArrayList<TreeNode> alVerticesList)
    {
    		for(int iLoop = 0 ;iLoop<marked.length;iLoop++)
    		{
    			if(marked[iLoop]==true)
    			{
    				System.out.println(alVerticesList.get(iLoop).getSynset_id_value()+"-->");
    				System.out.println(distTo[iLoop]);
    			}
    		}
    }
    /*
     * Constructor to build breadth first search path from a specified vertex
     */
    public Path(ArrayList<TreeNode> alVerticesList,HashMap<Integer,synset_data> hmSynsets, int pathFromVertex) {
    		
        marked = new boolean[hmSynsets.size()];
        distTo = new int[hmSynsets.size()];
        edgeTo = new int[hmSynsets.size()];
        for (int iLoop = 0; iLoop < hmSynsets.size(); iLoop++)
        {
            distTo[iLoop] = Integer.MAX_VALUE;
        }
        bfs(alVerticesList,hmSynsets, pathFromVertex);
    }
    /*
     * Get index of vertex by using synset ID
     */
    public int getIndexBySynsetId(ArrayList<TreeNode> alVerticesList,int current_element)
    {
    		
    		int index = -1; 
    		
        for(int iLoop = 0 ; iLoop<alVerticesList.size();iLoop++)
        {
        		if(alVerticesList.get(iLoop).getSynset_id_value() == current_element)
        		{
        			index = iLoop;
        		}
        }
        return index; 
    }


    /*
     * Perform breadth first search starting from the specified element
     */
    private void bfs(ArrayList<TreeNode> alVerticesList,HashMap<Integer,synset_data> hmSynsets, int element) {
        
    		Queue<Integer> queueBFS = new LinkedList<Integer>();
    		int startIndex = getIndexBySynsetId(alVerticesList,element);
        TreeNode startVertex_node = alVerticesList.get(startIndex);

        marked[startIndex] = true;
		distTo[startIndex] = 0;
		queueBFS.add(startVertex_node.synset_id_value);
		//System.out.println("Adding to queue"+startVertex_node.synset_id_value);
        while (!queueBFS.isEmpty()) {
            int current_vertex = queueBFS.remove();
            //System.out.println("Looking at "+current_vertex);
            
            TreeNode currentVertex_node = alVerticesList.get(getIndexBySynsetId(alVerticesList,current_vertex));
            
            while(currentVertex_node!=null)
            {
            		int current_synsetid = currentVertex_node.getSynset_id_value();
            		int vertix_index = getIndexBySynsetId(alVerticesList,current_synsetid);
            		//System.out.println("Looking at "+current_synsetid);
            		//System.out.println("Vertix_index = "+vertix_index+" marked?:"+marked[vertix_index]);
            		if(!marked[vertix_index])
            		{
            			edgeTo[vertix_index] = current_synsetid;
            			//System.out.println("Current ID: "+current_synsetid+" dist of current: "+distTo[current_vertex]+"path vertex"+alVerticesList.get(vertix_index).getSynset_id_value());
            			distTo[vertix_index] = distTo[current_vertex]+1;
            			marked[vertix_index] = true;
            			//System.out.println("Adding to queue: "+alVerticesList.get(vertix_index).getSynset_id_value());
            			queueBFS.add(alVerticesList.get(vertix_index).getSynset_id_value());
            			
            		}
            		currentVertex_node = currentVertex_node.getNext_element();
            }
            //System.out.println("Queue size"+queueBFS.size());
        }
    }

    
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /*
     * Returns the distance to the specified vertex on this path
     */
    public int distTo(int v) {
        return distTo[v];
    }


}
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;


public class SAP {
	
    private HashMap<Integer,synset_data> hmSynsets; 
    ArrayList<TreeNode> alVerticesList ;
    
    //Constructor takes input of adjacency list
    public SAP(ArrayList<TreeNode> alVerticesList,HashMap<Integer,synset_data> hmSynsets) {
        
        this.hmSynsets = hmSynsets;
        this.alVerticesList = alVerticesList; 
    }
    
    public int getIndexBySynsetId(int current_element)
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
    

        
    //Find length of v and w to common ancestral path
    public int length(int v, int w) {

    		int indexofV = getIndexBySynsetId(v);
    		int indexofW = getIndexBySynsetId(w);
    		//Find index of the elements and check if vertex is available or not
    		if(indexofV == -1 || indexofW == -1)
    			throw new IndexOutOfBoundsException();

        Path bfs_nodeV = new Path(alVerticesList,hmSynsets, v);
        Path bfs_nodeW = new Path(alVerticesList,hmSynsets, w);

        int ancestor = ancestor(v, w);
        System.out.println("Ancestor for "+v+ " and "+w+": "+ancestor);
        int pathLength;
        if (ancestor == - 1) {
            pathLength = -1;
        } else {
            pathLength = bfs_nodeV.distTo(ancestor) + bfs_nodeW.distTo(ancestor);
        }


        return pathLength;
    }


    // Find common ancestor of the SAP path between v and w
    public int ancestor(int v, int w) {

		int indexofV = getIndexBySynsetId(v);
		int indexofW = getIndexBySynsetId(w);
		//Find index of the elements and check if vertex is available or not
		if(indexofV == -1 || indexofW == -1)
			throw new IndexOutOfBoundsException();        

        Path bfs_nodeV = new Path(alVerticesList,hmSynsets,v);
        Path bfs_nodeW = new Path(alVerticesList,hmSynsets,w);
        
        int shortesAncestor = -1;
        int shortesPath = Integer.MAX_VALUE;
        Deque<Integer> ancestors = new ArrayDeque<Integer>();

        for (int iLoop = 0; iLoop < alVerticesList.size(); iLoop++) {
        		int current_synsetid = alVerticesList.get(iLoop).getSynset_id_value();
            if (bfs_nodeV.hasPathTo(current_synsetid) && bfs_nodeW.hasPathTo(current_synsetid)) {
                ancestors.push(current_synsetid);
            }
        }

        for (Integer current_element : ancestors) {
        	
            if ((bfs_nodeV.distTo(bfs_nodeV.getIndexBySynsetId(alVerticesList, current_element)) + bfs_nodeW.distTo(bfs_nodeW.getIndexBySynsetId(alVerticesList, current_element))) < shortesPath) {
                
            		shortesPath = bfs_nodeV.distTo(bfs_nodeV.getIndexBySynsetId(alVerticesList, current_element)) + bfs_nodeW.distTo(bfs_nodeW.getIndexBySynsetId(alVerticesList, current_element));
                shortesAncestor = current_element;
            }
        }
        return shortesAncestor;
    }

}

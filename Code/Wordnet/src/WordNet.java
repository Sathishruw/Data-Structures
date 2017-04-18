import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordNet {
	//Hashmap to store the tree structure 
	//Hashmap has keys(Datatype:TreeNode) for all the vertices. TreeNode datastructure contains references to adjacent vertices
	HashMap<Integer,synset_data> hmSynsets = new HashMap<Integer,synset_data>();

	//Arraylist to store list of vertices
	ArrayList<TreeNode> alVerticesList = new ArrayList<TreeNode>();
	//Arraylist to store list of edges
	ArrayList<Edge> edgeList = new ArrayList<Edge>();

	public HashMap<Integer, synset_data> getHmSynsets() {
		return hmSynsets;
	}
	public ArrayList<TreeNode> getAlVerticesList() {
		return alVerticesList;
	}
	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}
	
	/*
	 * constructor to build tree from synset and hypernym file
	 * synsets - file name of synsets data
	 * hypernyms - file name of hypernyms data
	 */
	public WordNet(String synsets, String hypernyms)
	{
		//Read from synset file and store the vertices as keys in hashmap hmSynsets & arraylist
		System.out.println("Preparing data for synsets");
		FileInputStream fsSynset=null;
		try {
			fsSynset = new FileInputStream(synsets);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		DataInputStream disSynset = new DataInputStream(fsSynset);
		BufferedReader brSynset = new BufferedReader(new InputStreamReader(disSynset));
		
		String strSynset; 
		try {
			while( (strSynset = brSynset.readLine())!=null )
			{
				synset_data synset_data_obj = new synset_data();
				String individual_words [] = new String[10];
				individual_words = strSynset.split(",");
				
				Integer id_value = Integer.parseInt(individual_words[0]);
				String noun[] = individual_words[1].split(" ");
				String definition = individual_words[2];
				
				synset_data_obj.setSynset_id(id_value);
				synset_data_obj.setNoun(noun);
				synset_data_obj.setSynonym(definition);
			
				//id_value is the vertex ID and synset_data_obj contains all the details like noun, definition etc 
				hmSynsets.put(id_value, synset_data_obj);
				
				TreeNode vertix_obj = new TreeNode();
				vertix_obj.setSynset_id_value(id_value);
				vertix_obj.setNext_element(null);
				
				alVerticesList.add(vertix_obj);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println("Number of synsets in hash map: "+hmSynsets.size());
		System.out.println("Number of synset vertices in array list: "+alVerticesList.size());
		
		//Reading from hypernym file and preparing the data 
		System.out.println("Preparing data for hypernyms");
		FileInputStream fshypernym=null;
		try {
			fshypernym = new FileInputStream(hypernyms);
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		DataInputStream dishypernym = new DataInputStream(fshypernym);
		BufferedReader brhypernym = new BufferedReader(new InputStreamReader(dishypernym));
		String strHypernym; 
		try {
			while( (strHypernym = brhypernym.readLine())!=null )
			{
				String individual_words[] = strHypernym.split(",");
				int source = Integer.parseInt(individual_words[0]);
				for(int iLoop=1;iLoop<individual_words.length;iLoop++)
				{
					Edge edge_obj = new Edge();
					edge_obj.setSource(source);
					edge_obj.setDestination(Integer.parseInt(individual_words[iLoop]));
					//Adding edges to edges list showing hypernym relation between words
					//System.out.println("Adding "+source+" and "+individual_words[iLoop]);
					edgeList.add(edge_obj);
				
				}
				
			}
		
		}
		catch(Exception e)
		{
			
		}
		
		System.out.println("Number of edges: "+edgeList.size());
		
		/*for(int iLoop=0;iLoop<100;iLoop++)
		{
			//System.out.println(edgeList.get(iLoop).getSource()+"-->"+edgeList.get(iLoop).getDestination());
		}*/
		
		//parse through all the edges and fill the adjacent vertices in hash map (Adjacency list)
		for(int iLoop=0;iLoop<edgeList.size();iLoop++)
		{
			int source_edge = edgeList.get(iLoop).getSource();
			int destination_edge = edgeList.get(iLoop).getDestination();
			//System.out.println("Source: "+source_edge+" Destination: "+destination_edge);
			
			TreeNode destination_obj = new TreeNode();
			destination_obj.setSynset_id_value(destination_edge);
			destination_obj.setNext_element(null);
			
			for(int jLoop = 0;jLoop<alVerticesList.size();jLoop++)
			{
				//Creating adjacency list
				TreeNode current_vertex = alVerticesList.get(jLoop);
				if(current_vertex.getSynset_id_value() == source_edge)
				{
					//System.out.println("Source: "+source_edge+" Destination: "+destination_edge);
					//System.out.println("Current vertex"+current_vertex.getSynset_id_value());
					TreeNode current_node = current_vertex; 
					while(current_node.getNext_element()!=null)
					{
						current_node = current_node.getNext_element();
					}
					
					current_node.setNext_element(destination_obj);
				
				}
			}
			
		}
		
		System.out.println("All edges are added");

	}
	/*
	 * Returns all the nouns in wordnet
	 */
	public Iterable<String> nouns()
	{
		List<String> list_nouns = new ArrayList<String>();
	    
		//Parse through all the vertices and collect noun details
		for(int iLoop = 0; iLoop<alVerticesList.size();iLoop++)
		{
			TreeNode temp = alVerticesList.get(iLoop);
			int tmp_synset_id = temp.getSynset_id_value();
			synset_data tmp_data = hmSynsets.get(tmp_synset_id);
			String noun[] = tmp_data.getNoun();
			for(int jLoop = 0; jLoop<noun.length;jLoop++)
			{
				//Add noun details to list
				list_nouns.add(noun[jLoop]);
			}
		}
		Iterable<String> iterable_noun = list_nouns;
		return iterable_noun;
		
	}
	/*
	 * Find index of vertex of a noun
	 */
	public int FindVertexOfNoun(String word)
	{
		int index_of_vertex = -1;
	    
		//Parse through all the vertices and compare with word
		for(int iLoop = 0; iLoop<alVerticesList.size();iLoop++)
		{
			TreeNode temp = alVerticesList.get(iLoop);
			int tmp_synset_id = temp.getSynset_id_value();
			
			synset_data tmp_data = hmSynsets.get(tmp_synset_id);
			String noun[] = tmp_data.getNoun();
			for(int jLoop = 0; jLoop<noun.length;jLoop++)
			{
				if(noun[jLoop].equals(word))
				{
					index_of_vertex = iLoop;
					break;
				}
			}
		}
		return index_of_vertex;
		
	}
	/*
	 * Checks if the word is noun or not
	 */
	public boolean isNoun(String word)
	{
		
		boolean is_noun = false;
		Iterable<String> itr_noun = nouns();
		//Compare with noun list and return availability flag
		for (String str_element : itr_noun) {
	        if(str_element.equals(word))
	        {
	        		is_noun=true; 
	        		break;
	        }
	    }
		return is_noun;
	}
	
	public int distance(String nounA,String nounB)
	{
		//Find distance between 2 nouns
		int indexOfA = FindVertexOfNoun(nounA);
		System.out.println("Index of "+nounA+":"+indexOfA);
		int indexOfB = FindVertexOfNoun(nounB);
		System.out.println("Index of "+nounB+":"+indexOfB);
		SAP sap_helper = new SAP(alVerticesList, hmSynsets);
		int distance = sap_helper.length(indexOfA, indexOfB);
		return distance; 
	}
	public String sap(String nounA,String nounB)
	{
		//Finds common ancestor for 2 nouns and return noun list appended by , of common ancestor
		int indexOfA = FindVertexOfNoun(nounA);
		System.out.println("Index of "+nounA+":"+indexOfA);
		int indexOfB = FindVertexOfNoun(nounB);
		System.out.println("Index of "+nounB+":"+indexOfB);
		SAP sap_helper = new SAP(alVerticesList, hmSynsets);
		int synsetID = sap_helper.ancestor(indexOfA, indexOfB);
		synset_data common_ancestor_data = hmSynsets.get(synsetID);
		String[] noun_list = common_ancestor_data.getNoun();
		String common_ancestor_noun = "";
		for(int iLoop =0 ;iLoop<noun_list.length;iLoop++)
		{
			common_ancestor_noun=common_ancestor_noun.concat(noun_list[iLoop]);
		}
		return common_ancestor_noun;
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
	public void findBFSPath(int element)
	{	
		SAP sap_helper = new SAP(alVerticesList, hmSynsets);
		
		
		System.out.println("Ancestor"+sap_helper.ancestor(7, 3));
		System.out.println("Length:"+sap_helper.length(7, 3));
		//System.out.println(distance("American_water_spaniel","histology"));
	}

	
}

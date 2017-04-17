import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;


public class HuffmanTree {
	
	public void inorder(HuffmanTreeNode root)
	{
		if (root != null)
		{
			inorder(root.getLeft());
			System.out.println("--- "+ root.getFrequency()+root.getLeft() + root.getRight());
			inorder(root.getRight());
		} 	    	
	}
	public HuffmanTreeNode buildHuffmanTree(HashMap<String,Integer> hmHuffman)
	{
		/*
		 * Create one node for each character
		 * Insert all the nodes into priority queue
		 * Combine 2 nodes in ascending order into single node
		 * Insert the node which is combined of 2 lowest value nodes
		 * Parse through the loop till priority queue contains only one node
		 */
		PriorityQueue pqHuffman = new PriorityQueue();
		Iterator it=hmHuffman.entrySet().iterator();
		
		//Create a node for every single element and insert into priority queue 
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			HuffmanTreeNode temp_node = new HuffmanTreeNode();
			temp_node.setData(pair.getKey().toString());
			temp_node.setFrequency(Integer.parseInt(pair.getValue().toString()));
			pqHuffman.add(temp_node);     
		}
		//Iterator itQueue = pqHuffman.iterator();
		System.out.println(pqHuffman.size());
		int noe = pqHuffman.size();
		
		//Repeat the loop until there is only one node in the priority queue 
		while(noe > 1)
		{
			//Dequeue two nodes from Priority queue to get the elements of lowest value 
			HuffmanTreeNode node1 = (HuffmanTreeNode) pqHuffman.poll();
			HuffmanTreeNode node2 = (HuffmanTreeNode) pqHuffman.poll();
			HuffmanTreeNode new_node = new HuffmanTreeNode();
			
			//Create a new node with combined frequency 
			new_node.setFrequency(node1.getFrequency()+node2.getFrequency());
			new_node.setLeft(node1);
			new_node.setRight(node2);
			//Insert new node in priority queue 
			pqHuffman.add(new_node);
			noe = pqHuffman.size();
			if(noe==1)
			{
				new_node.setData("Root");
			}
		} 

		//Finally there is only one element in priority queue
		HuffmanTreeNode result_tree = (HuffmanTreeNode) pqHuffman.poll();
		return result_tree;
	}

}

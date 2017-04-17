import java.util.HashMap;


public class HuffmanCode {
	
	/*
	 * Function used to build huffman code table 
	 */
	HashMap<String,String> hmHuffmanCode = new HashMap<String,String>();
	public HashMap<String,String> buildHuffmanCodeTable(HuffmanTreeNode result_tree)
	{
		traverse(result_tree,"");
		return hmHuffmanCode;
	}
	/*
	 * Traverse the tree completely - When travelling through left, append 0
	 * When travelling through right, append 1
	 * Whenever leaf is reached, return code for the alphabet in leaf
	 */
	public void traverse(HuffmanTreeNode root,String s)
	{
		if(root!=null){
		//System.out.println("Traversing at "+root.getData()+root.getFrequency()+root.getLeft()+root.getRight());
		
		if(root.getLeft() == null && root.getRight()==null)
		{
			//System.out.println("Node: "+root.getData()+" Code:"+s);
			hmHuffmanCode.put(root.getData(),s);
		}
		
			traverse(root.getLeft(),s+"0");
			traverse(root.getRight(),s+"1");
		}
		
	}
}

/*
 * Node to store data, frequency, left right
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
	
	HuffmanTreeNode left ;
	HuffmanTreeNode right ;
	String data;
	int frequency; 
	
	public int compareTo(HuffmanTreeNode node2) 
	{
		//node1.compareTo(node2) -- 1 node1 is greater
		//-1 node2 is greater 
		
		
		if(this.frequency > node2.getFrequency() )
		{
			return 1;
		}
		else if(this.frequency < node2.getFrequency())
		{
			return -1;
		}
		else 
		{
			return 0;
		}

	}
	
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int weight) {
		this.frequency = weight;
	}
	public HuffmanTreeNode getLeft() {
		return left;
	}
	public void setLeft(HuffmanTreeNode left) {
		this.left = left;
	}
	public HuffmanTreeNode getRight() {
		return right;
	}
	public void setRight(HuffmanTreeNode right) {
		this.right = right;
	}
	
 	
 	public String getData()
 	{
 		return data;
 	}
 	public void setData(String data_value)
 	{
 		data = data_value;
 	}

}

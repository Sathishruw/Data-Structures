import java.util.*;
public class User_interface 
{
	public static String doHuffman(String strInput)
	{
		HashMap<String,String> hmHuffmanCode = new HashMap<String,String>();
		HashMap<String,Integer> hmHuffman = new HashMap<String,Integer>();
		
		HuffmanFrequencyTable freq_table_obj = new HuffmanFrequencyTable();
		HuffmanTree tree_obj = new HuffmanTree();
		HuffmanCode code_obj = new HuffmanCode();
		TableItem tableitem_obj = new TableItem();
		Encoding encoding_obj = new Encoding();
		Decoding decoding_obj = new Decoding();
		
		
		
		hmHuffman = freq_table_obj.buildFrequencyTable(strInput);


		HuffmanTreeNode result_tree = tree_obj.buildHuffmanTree(hmHuffman);
		//tree_obj.inorder(result_tree);
		
		hmHuffmanCode = code_obj.buildHuffmanCodeTable(result_tree);
		tableitem_obj.printHuffmanCode(hmHuffman, hmHuffmanCode);
		
		String strInput_encoding;
		
		/*
		System.out.println("Enter the input for encoding :");
		strInput_encoding = in.nextLine();
		*/
		strInput_encoding = strInput;
		String encoded_string="";
		encoded_string=encoding_obj.encode_string(hmHuffman, hmHuffmanCode,strInput_encoding);
		System.out.println("Total number of bits without Huffman coding: "+strInput.length()*16);
		System.out.println("Total number of bits with Huffman coding: "+encoded_string.length());
		String decoded_string = decoding_obj.decode(result_tree,encoded_string);
		return decoded_string;
	}
	
	public static void main(String args[]) throws Exception
	{
		String strInput;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the input for huffman tree :");
		strInput = in.nextLine();
		doHuffman(strInput);
	}
}



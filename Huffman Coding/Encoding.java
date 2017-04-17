import java.util.HashMap;
import java.util.Scanner;


public class Encoding {
	public String encode_string(HashMap<String,Integer> hmHuffman,HashMap<String,String> hmHuffmanCode,String strInput)
	{
		
		String encoded_string="";
		System.out.println("Frequency table size: "+hmHuffman.size());
		System.out.println("Code table size: "+hmHuffmanCode.size());
		
		//System.out.println(hmHuffmanCode.get("a"));
		
		//Parse through the string value and find the bit code from Hashmap codes
		for(int iLoop = 0; iLoop<strInput.length();iLoop++)
		{
			if(!(hmHuffman.get(strInput.charAt(iLoop)+"").equals(null)))
			{
				encoded_string=encoded_string+hmHuffmanCode.get(strInput.charAt(iLoop)+"");
			}
		}
		//Print the encoded string and return value 
		System.out.println("Encoded value: "+encoded_string);
		return encoded_string;
	}

}

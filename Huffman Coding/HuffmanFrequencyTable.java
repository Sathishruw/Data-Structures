import java.util.HashMap;


public class HuffmanFrequencyTable {
	/*
	 * Function used to build frequency table based on number of occurrences of each character in string
	 */
	public HashMap<String,Integer> buildFrequencyTable(String strInput)
	{
		//Hash map to store character with frequency as value 
		HashMap<String,Integer> hmHuffman = new HashMap<String,Integer>();
		for(int j=0;j<strInput.length();j++)
		{
			int c=0;
			String charAtPosition = strInput.charAt(j)+"";

			//if the hash map contains the character already, increase the number of occurrences
			if(hmHuffman.containsKey(charAtPosition))
			{
				int value_of_char = hmHuffman.get(charAtPosition);
				value_of_char=value_of_char+1;
				hmHuffman.put(charAtPosition, value_of_char);
			}
			//if the hash map does not contain character already, insert it with number of occurrence as 1
			else
			{
				hmHuffman.put(charAtPosition, 1);
			}
		}
		return hmHuffman;
	}

}

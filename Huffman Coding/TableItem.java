import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TableItem {
	public void printHuffmanCode(HashMap<String,Integer> hmHuffman,HashMap<String,String> hmHuffmanCode)
	{
		Iterator it=hmHuffman.entrySet().iterator();
		System.out.println("character"+"    |     "+"Frequency"+"    |     "+"code");
		System.out.println("____________________________");
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println("   "+pair.getKey()+"         |         "+pair.getValue()+"         |         "+hmHuffmanCode.get(pair.getKey()));
		}
		
	}

}

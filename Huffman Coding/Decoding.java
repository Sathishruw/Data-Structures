import java.util.Scanner;

/*
 * Class used for decoding the bits to string values
 */
public class Decoding {
	/*
	 * Function used to decode the bit values and return the decoded string 
	 */
	   public String decode(HuffmanTreeNode root_tree,String strInput) {
		   
		   	/*String strInput;
			Scanner in = new Scanner(System.in);
			System.out.println("Enter the input for decoding :");
			strInput = in.nextLine();*/
		   
		   
		      String decodedString = ""; 
		      //Start traversing from root node through current node variable
		      HuffmanTreeNode current_node = root_tree; 
		      int iLoop = 0; 
		      
		      
		      while (iLoop < strInput.length()) { 
		         current_node = root_tree; 
		         while (true) {
		        	 	//Reached leaf - So adding the data at the leaf to decoded string 
		            if (current_node.getLeft()==null && current_node.getRight()==null) { 
		            	decodedString += current_node.getData();
		               break;
		            }
		            //if 0 is encountered, travel left 
		            else if (strInput.charAt(iLoop) == '0') { 
		               current_node = current_node.left;
		               iLoop++;
		            }
		            //if 1 is encountered, travel right
		            else  { 
		               current_node = current_node.right;
		               iLoop++;
		            }
		         } 
		      } 
		      //Print the final decoded string & return value
		      System.out.println("Decoded string: "+decodedString);
		      return decodedString;
		   }
}

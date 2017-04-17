import java.util.Iterator;


public class Linked_List<T> {
	
	LinkedNode<T> head; 
	//add node to linked list
	public LinkedNode<T> addNode(T element_to_be_added)
	{
		if(element_to_be_added == null)
		{
			//element is empty. Cannot create a node
			System.out.println("Node in the linked list cannot be created for null element");
		}
		else
		{
		LinkedNode<T> new_element = new LinkedNode<T>();
		new_element.setElement(element_to_be_added);
		
		new_element.setNext(null);
		
		if(head == null)
		{
			//Linked list is empty. So new element created is head 
			head = new_element;
		}
		else
		{
			//parsing through the linked list and placing new node at the end 
			LinkedNode<T> temp_to_parse = head; 
			while(temp_to_parse.getNext() != null)
			{
				temp_to_parse = temp_to_parse.getNext(); 
			}
			
			temp_to_parse.setNext(new_element);
			
		}
		}
		return head;
		
	}
	
	  public void displayList()
	   {
		  //displaying the values of linked list 
		  if(head == null)
		  {
			  System.out.println("List is empty");
			  
		  }
		  else
		  {
		   LinkedNode<T> temp;
		   
		   for (temp = head; temp.getNext() != null; temp = temp.getNext())
		   {
			   System.out.println(temp.getElement().toString());
			   System.out.println("DT"+temp.getDataType());
		   }
		   System.out.println(temp.getElement().toString());
		  }
		  
		   
	   }
	  


}

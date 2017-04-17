import java.util.ArrayList;


public class Sorting<T>{
	
	public LinkedNode<T> shell_Sort_knuth(LinkedNode<T> list_to_be_sorted)
	{
        int number_elements = count_Elements_In_List(list_to_be_sorted);

		int key_comparison_count = 0;
		int total_swaps_count = 0;
		int iteration_number = 0; 
        
        int knuth_increment = 1;
        while (knuth_increment < number_elements/3) 
        	{
        		knuth_increment = 3*knuth_increment + 1; 
        	}

        
        while (knuth_increment >= 1) {
            
            for (int i = knuth_increment; i < number_elements; i++) {
                for (int j = i; j >= knuth_increment && pick_Element_At_N_Position(list_to_be_sorted,j).compareTo(pick_Element_At_N_Position(list_to_be_sorted,j-knuth_increment))<0; j -= knuth_increment) {
                		key_comparison_count++;total_swaps_count++;
                    list_to_be_sorted = swap_Nodes(list_to_be_sorted,pick_Element_At_N_Position(list_to_be_sorted,j),pick_Element_At_N_Position(list_to_be_sorted,j-knuth_increment));
                }
                key_comparison_count++;
            }
            
            knuth_increment /= 3;
        }
        
		System.out.println("Key comparisons: "+key_comparison_count);
		System.out.println("Number of swaps: "+total_swaps_count);
		//displayList(list_to_be_sorted);
		return list_to_be_sorted;
	}

	
	public LinkedNode<T> swap_Nodes(LinkedNode<T> head,LinkedNode<T> element1,LinkedNode<T> element2)
	{
		//function used to swap two nodes in linked list. key of the nodes are not swapped. Complete nodes undergo swapping
		LinkedNode<T> firstPrevious = null;
		LinkedNode<T> secondPrevious = null; 
		LinkedNode<T> current = head; 
		while(current.getNext()!=null)
		{
			//finding the previous nodes for both the elements to be swapped 
			if(current.getNext() == element1)
			{
				firstPrevious = current; 
			}
			if(current.getNext() == element2)
			{
				secondPrevious = current; 
			}
			current = current.getNext(); 
		}
		if(head == element1)
		{	//if the element to be swapped is head, swap the head pointer 			
			head = element2; 
		}
		else if(head == element2)
		{
			//if the element to be swapped is head, swap the head pointer 
			head = element1; 
		}
		if(firstPrevious!=null)
		{
			firstPrevious.setNext(element2);
		}
		if(secondPrevious!=null)
		{
			secondPrevious.setNext(element1);
		}
		
		//Swap two nodes in linked list 
		LinkedNode<T> temp = new LinkedNode<T>();
		temp.setElement(element1.getElement());
		temp.setNext(element1.getNext());
		
		element1.setNext(element2.getNext());
		
		element2.setNext(temp.getNext());
		
		//head may have changed after the swap if head elements undergoes swap. So return new head
		return head; 
	}
	
	public int count_Elements_In_List(LinkedNode<T> head)
	{
		//function to count the number of elements in the linked list 
		int number_of_elements = 0; 
		if(head == null)
		  {
			number_of_elements = 0; 
			  
		  }
		  else
		  {			  
		   LinkedNode<T> temp;
		   System.out.println("Displaying the list: ");
		   for (temp = head; temp.getNext() != null; temp = temp.getNext())
		   {
			   
			   number_of_elements++;
			   
		   }
		   number_of_elements++;
		  }
		return number_of_elements;
	}
	
	public void displayList(LinkedNode<T> head)
	{
			//function to display list 
		  if(head == null)
		  {
			  System.out.println("List is empty");
			  
		  }
		  else
		  {
			  
		   LinkedNode<T> temp;
		   //System.out.println("Displaying the list: ");
		   for (temp = head; temp.getNext() != null; temp = temp.getNext())
		   {
			   
			   System.out.print(temp.getElement().toString()+",");
			   
		   }
		   System.out.println(temp.getElement().toString());
		  }
		  
	}
	
	public LinkedNode<T> pick_Element_At_N_Position(LinkedNode<T> list_to_be_sorted,int n)
	{
		//function to pick the node at position n of the linked list 
		if(list_to_be_sorted == null)
		{
			return null;
		}
		else
		{
			LinkedNode<T> current = list_to_be_sorted; 
			int iPosition = 0; 
			while(current.getNext()!=null)
			{
				if(iPosition == n)
				{
					return current;
				}
				iPosition++;
				current = current.getNext();
			}
			if(iPosition == n)
			{
				return current; 
			}
		}
		return null;
	}
	

	
	public LinkedNode<T> shell_Sort(LinkedNode<T> list_to_be_sorted)
	{
		//perform shell sort operation 
		int key_comparison_count = 0;
		int total_swaps_count = 0;
		int iteration_number = 0; 
		//displayList(list_to_be_sorted);
		int number_of_elements = count_Elements_In_List(list_to_be_sorted);
		
		int gap_value = number_of_elements;
		while( gap_value > 1 )
		{
			iteration_number++;
			gap_value = (gap_value + 1)/2;
			System.out.println("Pass: "+iteration_number+" & Gap value: "+gap_value);
			int comparisons = 0;
			int number_of_swaps = 0; 
			
			for(int iLoop = 0 ;iLoop<number_of_elements - gap_value;iLoop++)
			{
				key_comparison_count++;comparisons++;
				if(pick_Element_At_N_Position(list_to_be_sorted,iLoop).compareTo(pick_Element_At_N_Position(list_to_be_sorted,iLoop+gap_value))>0)
				{
					//element at position iLoop > element at position iLoop+gap_value - So swap is necessary 
					total_swaps_count++;number_of_swaps++;
					list_to_be_sorted = swap_Nodes(list_to_be_sorted,pick_Element_At_N_Position(list_to_be_sorted,iLoop),pick_Element_At_N_Position(list_to_be_sorted,iLoop+gap_value));
				}
					
				//displayList(list_to_be_sorted);
			}
			System.out.println("Number of key comparisons at "+iteration_number+" pass: "+comparisons);
			System.out.println("Number of exchanges at "+iteration_number+" pass: "+number_of_swaps);
			System.out.println("List at the end of pass "+iteration_number+" : ");
			displayList(list_to_be_sorted);
			
		}
		//displayList(list_to_be_sorted);
		System.out.println("Total number of key comparisons: "+key_comparison_count);
		System.out.println("Total number of exchanges: "+total_swaps_count);
		return list_to_be_sorted;
	}
	

	public LinkedNode<T> bubbleSort(LinkedNode<T> list_to_be_sorted)
	{
		//perform bubble sort operation 
		int key_comparison_count = 0;
		int total_swap_count = 0;
		
		LinkedNode<T> current = list_to_be_sorted;
		LinkedNode<T> previous = null;
		LinkedNode<T> next = null; 
		int number_of_swaps = 1; 
		int iteration_number = 0; 
		while(number_of_swaps > 0 )
		{
			iteration_number++; 
			
			System.out.println("Pass: "+iteration_number);
			number_of_swaps = 0;
			int comparisons = 0; 
			previous = null;
			next = null;
			current = list_to_be_sorted;
		while(current.getNext()!=null)
		{
			
			next = current.getNext(); 
			//comparing the current element to element at next position 
			int  comparison_result = current.compareTo(next); 
			comparisons++;
			key_comparison_count++;
			if(comparison_result<0)
			{
				//element at current is less than element at next . No swap required
				previous = current ; 
				current = current.getNext(); 
			}
			else if (comparison_result>0)
			{
				//element at current is greater than element at next . swap required
				//System.out.println(current.getElement() + " is greater than "+next.getElement());
				total_swap_count++;
				number_of_swaps++;
				if(previous!=null)
				{
					//deal with non head element swaps
					//System.out.println(current.getElement() + " is greater than "+next.getElement());
					//System.out.println("Swapping "+current.getElement()+","+next.getElement());
					current.setNext(next.getNext());
					previous.setNext(next);
					next.setNext(current);
				}
				else if(list_to_be_sorted == current)
				{
					//Head changes
					//System.out.println("Swapping "+current.getElement()+","+next.getElement());
					current.setNext(next.getNext());
					list_to_be_sorted = next;
					next.setNext(current);
					
				}
				
				previous = next;
			}
			else if(comparison_result==0)
			{
				//element at current is equal to element at next. no swap required 
				//System.out.println(current.getElement() + " is equal to " + next.getElement());
				previous = current ; 
				current = current.getNext(); 
			}
			
			//System.out.println("List after the iteration");
			//displayList(list_to_be_sorted);
			
		}
		System.out.println("Number of key comparisons at "+iteration_number+" pass: "+comparisons);
		System.out.println("Number of exchanges at "+iteration_number+" pass: "+number_of_swaps);
		System.out.println("List at the end of pass "+iteration_number+" : ");
		displayList(list_to_be_sorted);
		//System.out.println(list_to_be_sorted.getElement());
		}
		System.out.println("Total number of key comparisons: "+key_comparison_count);
		System.out.println("Total number of exchanges: "+total_swap_count);
		return list_to_be_sorted;
		
	}
	
}

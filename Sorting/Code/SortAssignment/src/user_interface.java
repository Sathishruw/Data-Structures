
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner; 

public class user_interface {
	
	public static LinkedNode<Integer> readValues()
	{
		//Function to read the values and add nodes for linked list
		//Can either get input from user or from the input values from file
		
		Linked_List<Integer> linkedList = new Linked_List<Integer>();
		LinkedNode<Integer> linkedNode = new LinkedNode<Integer>();
		
		//Prompts user for a choice. 
		System.out.println("Enter a choice: 1. Enter 1 if you want to input list of intergers 2. Enter 2 if you want to input a file name with list of integers");
		Scanner input = new Scanner(System.in);
		
		int choice_of_input = input.nextInt(); 
		
		
		if(choice_of_input == 1)
		{
			//Input will be the list of integers from users from standard input 
			System.out.println("Enter the list of integers. Enter -1 if the input list is complete");
			int input_number = 0; 
			
			//Get the numbers from user till he enters -1
			while(input_number!=-1)
			{
				//Get input from the list 
				System.out.print("Enter number");
				input_number = input.nextInt(); 
				if(input_number!=-1)
				{
					//if the input number is not -1, add a node to existing or new linked list
					linkedNode = linkedList.addNode(input_number);
				}
			}
			
		}
		else if (choice_of_input == 2)
		{
			
			//Input will be the name of the file containing list of integers
			System.out.println("Enter the name of the file which contains the list of integers to be added");
			
			try{
			String file_name = input.next(); 
			//Opening the file to read the integers
			FileInputStream fisFileRead = new FileInputStream(file_name);
			DataInputStream disFileRead = new DataInputStream(fisFileRead);
			BufferedReader brFileRead = new BufferedReader(new InputStreamReader(disFileRead));
			
			String line_read_from_file = null;
			
			//Reading the file
			while((line_read_from_file = brFileRead.readLine())!=null)
			{
				System.out.println(line_read_from_file);
				//linkedList.addNode(Integer.parseInt(line_read_from_file));
				
				//Delimiter is either new line character or space 
				//Split based on new line and space delimiters and add nodes to linked list for every number
				String list_of_numbers_1[]=line_read_from_file.split("\n");
				for(int iLoop=0;iLoop<list_of_numbers_1.length;iLoop++)
				{
					String list_of_numbers_2[]=list_of_numbers_1[iLoop].split(" ");
					for(int jLoop=0;jLoop<list_of_numbers_2.length;jLoop++)
					{
						linkedNode = linkedList.addNode(Integer.parseInt(list_of_numbers_2[jLoop]));
					}
					
				}
			}
			
			}
			catch(FileNotFoundException fe)
			{
				System.out.println("File not found.Try running the program again");
			}
			catch(Exception e)
			{
				System.out.println("Exception occurred.");
			}
			
		}
		else
		{
			//Input is not valid from list of choices
			System.out.println("Not valid input. Try running the program again with valid input");
		}
		return linkedNode;
		
	}
	
	public static void displayList(LinkedNode<Integer> head)
	{
		  if(head == null)
		  {//list is empty. Nothing to display 
			  System.out.println("List is empty");
		  }
		  else
		  {
		   LinkedNode<Integer> temp;
		   //Parsing through the linked list and displaying the element values
		   for (temp = head; temp.getNext() != null; temp = temp.getNext())
		   {
			   System.out.print(temp.getElement().toString()+",");
		   }
		   System.out.println(temp.getElement().toString());
		  }
		  
	}

	public static void main(String[] args) {
		
		LinkedNode<Integer> linkedList_head = new LinkedNode<Integer>();
		Scanner input = new Scanner(System.in);
		
		//Get choice of input from user - bubble sort or shell sort 
		System.out.println("Enter a choice : 1. Bubble sort 2. Shell sort 3. Shell sort - Knuth sequence");
		int choice_sort = input.nextInt();
		
		if(choice_sort == 1)
		{
			//choice of sort is bubble sort 
			//call readvalues function to get input from user 
		linkedList_head = readValues();
		
		
		//Print list of elements by parsing through the linked list
		System.out.println("Elements in the list before sort: ");
		displayList(linkedList_head);
		
		//Call the bubble sort function to do the sorting . Capture the time taken for the sort
		Sorting<Integer> sorting_helper = new Sorting<Integer>();
		LinkedNode<Integer> list_Bubble_Sort = new LinkedNode<Integer>();
		long start_of_sort = System.nanoTime();
		list_Bubble_Sort=sorting_helper.bubbleSort(linkedList_head);
		long end_of_sort = System.nanoTime();
		
		System.out.println("Elements in the list after bubble sort: ");
		displayList(list_Bubble_Sort);
		System.out.println("Time taken for the sort: "+(end_of_sort-start_of_sort)/1000000000.0+" seconds");
		}
		else if(choice_sort == 2)
		{
		//choice of sort is shell sort 
		//linkedList_head=sorting_helper.shell_Sort(linkedList_head);
		//displayList(linkedList_head);
		linkedList_head = readValues();
		System.out.println("Elements in the list before shell sort");
		displayList(linkedList_head);
		
		LinkedNode<Integer> list_Shell_Sort = new LinkedNode<Integer>();
		Sorting<Integer> sorting_helper = new Sorting<Integer>();
		
		//Call shell sort function and capture the time taken for the sort 
		long start_of_sort = System.nanoTime();
		list_Shell_Sort=sorting_helper.shell_Sort(linkedList_head);
		long end_of_sort = System.nanoTime();
		System.out.println("Elements in the list after shell sort");
		displayList(list_Shell_Sort);
		System.out.println("Time taken for the sort: "+(end_of_sort-start_of_sort)/1000000000.0+" seconds");
		}
		else if(choice_sort == 3)
		{
			linkedList_head = readValues();
			System.out.println("Elements in the list before shell sort using knuth sequence");
			displayList(linkedList_head);
			LinkedNode<Integer> list_Shell_Sort = new LinkedNode<Integer>();
			Sorting<Integer> sorting_helper = new Sorting<Integer>();
			long start_of_sort = System.nanoTime();
			list_Shell_Sort=sorting_helper.shell_Sort_knuth(linkedList_head);
			long end_of_sort = System.nanoTime();
			System.out.println("Elements in the list after shell sort");
			displayList(list_Shell_Sort);
			System.out.println("Time taken for the shell sort using knuth sequence: "+(end_of_sort-start_of_sort)/1000000000.0+" seconds");
			
		}
	}

}

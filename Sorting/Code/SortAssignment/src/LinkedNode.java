
public class LinkedNode<T> implements Comparable<LinkedNode> {
	
	//element value to store the key
	//next element to store the address of next node
	T element;
	LinkedNode<T> nextElement;
	
	//default constructor
	public LinkedNode()
	{
		element = null;
		nextElement = null;
	}
	//constructor with values 
	public LinkedNode(T element)
	{
		this.element = element; 
	}
	
	void setNext(LinkedNode<T> node)
	{
		nextElement = node;
	}
	
	LinkedNode<T> getNext()
	{
		return nextElement;
	}
	
	T getElement()
	{
		return element;
	}
	
	void setElement(T element)
	{
		this.element = element; 
	}

	public String getDataType() {
	    return "Type Parameter  is " + this.element.getClass().getName() + ".";
	  }
	
	@Override
	//Implementing compare to function 
	public int compareTo(LinkedNode o) {
		if(getDataType().contains("Integer"))
		{	
			int number1 = Integer.parseInt(this.element.toString());
			int number2 = Integer.parseInt(o.getElement().toString());
			if(number1 == number2)
			{
				return 0;
			}
			else if(number1<number2)
			{
				return -1;
			}
			else if(number1>number2)
			{
				return 1; 
			}
			else
			{
				return -100;
			}
		}
		else 
			return -100; 
	}
	
	
	
}

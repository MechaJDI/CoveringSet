
//Stack class which refers to a stack of objects
public class Stack <E> implements Cloneable
{
  E [] obj; //refers to a array of objects
  int top; // number of items

//constructor creates an array of objects and sets top to 0
  public Stack()
	{
		obj = (E[]) new Object[20];
		top = 0;
	}

  //creates a stack with i indices
  public Stack(int i)
  {
      obj = (E[]) new Object[i];
      top = 0;
  }

//accessors
	public int getSize()
	{
		return top;
	}


//doubles the size of the Stack + 1
	private void increaseSize()
	{
		E[] temp = (E[]) new Object[2*obj.length + 1];

		for(int i=0; i<obj.length; i++)
			temp[i] = obj[i];

		obj = temp;

	}

    // returns true if empty
    public boolean isEmpty()
    {
        boolean answer = true;
        if(top >= 1)
            answer = false;
        return answer;
    }

//adds to the stack
	public void push(E item)
	{

		if(top==obj.length)
			increaseSize();

		if(item == null)
            System.out.println("Error.");
		obj[top] = item;
		top++;
	}

//removes from the stack
	public E pop()
	{

		E answer = null;


		if(top>0)
		{
            top--;
			answer = obj[top];
			
		
		}

		return answer;

	}

//returns the top obj of the stack
	public E peek()
	{
		E answer = null;

		if(top>0)
			answer = obj[top-1];

		return answer;
	}

    public Stack <E> Clone()
    {
        Stack <E> clone = new Stack <E>(this.obj.length);
        for(int i = 0; i < this.top; i++)
            clone.obj[i] = this.obj[i];
        return clone;
    }

}

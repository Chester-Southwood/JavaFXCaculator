

public class Stack
{  
   public class Node
   {
      private Node next;
      private Object data;
      public Node(Node next, Object data)
      {
         this.next = next;
         this.data = data;
      }
   }
   private Node top;
   public Stack()
   {
      this.top = null;
   }
   public boolean isEmpty()
   {
      return this.top == null;
   }
   public void push(Object data)
   {
      if(data == null)
      {
         System.out.println("Cannot add null value");
      }
      else
      {
         this.top = new Node(this.top, data);
      }
   }
   public Object peek()
   {
      if(isEmpty())
      {
         throw new IllegalStateException("Cannot peek from a empty Stack.");
      }
      else
      {
         return this.top.data;
      }
   }
   public Object pop()
   {
      if(isEmpty())
      {
         throw new IllegalStateException("Cannot pop value from a already empty Stack.");
      }
      else
      {
         Object tempData = this.top.data;
         this.top = this.top.next;
         return tempData;
      }
   }
}


import java.math.BigDecimal;
public class Expression
{
   private String inFix;
   private LinkedList<String> postFix;
   private Stack expressionStack;
   private char[] operator = {'(', '+', '-', '*', '/', '%','^'};
   private int[] onStack = {0,2,2,4,4,4,5};
   private int[] currentItem = {100,1,1,3,3,3,6};
   
   public Expression(String str)
   {
      this.inFix = str;
      this.postFix = new LinkedList<String>();
      this.expressionStack = new Stack();
   }
   
   private boolean isDouble(String str) 
   {
	    try 
	    {
	        double x = Double.parseDouble(str);
	        return true;
	    }
	    catch(NumberFormatException e) 
	    {
	        return false;
	    }

	} 
   
   public LinkedList<String> postFixExpression(String str)
   {
      int index = 0;
      boolean negAndNeg = false;
      while(index < inFix.length())
      {  
         char symbol = inFix.charAt(index);
         if((symbol > 47 && symbol < 58) || symbol == '.' || (symbol == '-' && (inFix.charAt(index + 1) > 47 && inFix.charAt(index + 1) < 58)))  
         {
            String num;
         	int i = index + 1;
         	num = symbol + "";
         	char tempSymbol; //= postFix.charAt(i)
         	while(i < inFix.length())
         	{
         		tempSymbol = inFix.charAt(i);
         		if((tempSymbol >= '0' && tempSymbol <= '9') || tempSymbol == '.')
         		{
                  index++;
         			num += tempSymbol + "";
         		}
         		else
         		{
         			i = inFix.length();//break out of loop
         		}
         		i++;
         	}
            this.postFix.add(""+num);
            index++;
            if(index < inFix.length() && inFix.charAt(index) == '-' && inFix.charAt(index + 1) != '-')
            {
               this.expressionStack.push("+");
            }
         	//System.out.println("num is" + num);
         }

         else if(symbol == '(')
         {
            this.expressionStack.push(symbol);
            index++;
         }
         else if(symbol == ')')
         {
            while(((char)this.expressionStack.peek() != '('))
            {
               this.postFix.add(this.expressionStack.pop() + "");
            }
            this.expressionStack.pop();//pops left ')'
            index++;
         }
         else
         {
            int currentOperator = findIndex(symbol, operator.length);
            while((!(this.expressionStack.isEmpty())) && (onStack[findIndex((char)this.expressionStack.peek(), operator.length)] >= currentItem[currentOperator]))
            {
               this.postFix.add(this.expressionStack.pop() + "");
            }
            this.expressionStack.push(symbol);
            index++;
                        /*if(index < inFix.Length && inFix.charAt(index) == '-')
            {
               this.expressionStack.push("+");
            }*/
         	//Console.WriteLine("num is" + num);
         }
      }
      while(!(this.expressionStack.isEmpty()))
      {
         this.postFix.add(this.expressionStack.pop() + "");
      }
      //System.out.println("This list " + this.postFix.toString());
      return this.postFix;
   }
   
   public int findIndex(char symbol, int length)
   {
      for(int x = 0; x < length; x++)
      {
         if(symbol == this.operator[x])
         {
            return x;
         }
      }
      return -1;
   }
   
   public String postFix()
   {
      int index = 0;
      while(index < postFix.size())
      {
         String symbol = postFix.get(index);
         System.out.println(symbol);
         if(isDouble(symbol + ""))
         {
        	 this.expressionStack.push(symbol + "");
         }
         else
         {
            BigDecimal left = new BigDecimal(this.expressionStack.pop().toString());
            BigDecimal right = new BigDecimal(this.expressionStack.pop().toString());
            if(symbol.equals("^"))
            {
               BigDecimal maxInt = new BigDecimal("" + Integer.MAX_VALUE);
               try
               {
                  if(left.compareTo(maxInt) >= 0)
                  {
                     return "COULDN";
                  }
                  else if(left.intValue() < 0)
                  {
                     return "WOOD";
                  }
                  else
                  {
                     System.out.println(decimalPow(right, left, maxInt) + " is stuff");                    
                     this.expressionStack.push(decimalPow(right, left, maxInt));
                  }
               }
               catch(ArithmeticException E)
               {
                  if(left.intValue() < 0)
                  {
                     return ("Error: Power cannot be negative.");
                  }  
                  return ("Error: Exponent operation would cause overflow.");
               }
            }
            else if(symbol.equals("/"))
            {
               try
               {
                  this.expressionStack.push(right.divide(left, 20, BigDecimal.ROUND_UP).stripTrailingZeros().toString());         
               }
               catch(ArithmeticException E)
               {
                  return("Error: Cannot divide by zero");
               }
            }
            else if(symbol.equals("%") )
            {
               if(left.toString().equals("0"))
               {
                  return "Error: Cannot mode by zero";
               }
               else
               {
                  this.expressionStack.push(right.remainder(left).toString());
               }
            }
            else if(symbol.equals("*"))
            {
               this.expressionStack.push(right.multiply(left).toString());
            }
            else if(symbol.equals("-"))//minus no longer necessary
            {
               System.out.println("left is " + left.toString() + " right is " + right.toString());
               this.expressionStack.push(right.subtract(left).toString());
            }
            else if(symbol.equals("+"))//minus no longer necessary
            {
               System.out.println("left is " + left.toString() + " right is " + right.toString());
               this.expressionStack.push(right.add(left).toString());
            }
            else
            {
               return "Symbol not found";
            }
         }
         index++;
      }
     return "" + this.expressionStack.pop();
   }
   
   private BigDecimal decimalPow(BigDecimal a, BigDecimal b, BigDecimal max)
   {
      if(b.toString().equals("0"))
      {
         return new BigDecimal("0");
      }
      int toPower = Integer.parseInt(b.toString());
      for(int i = 0; i < toPower; i++)
      {
         a = a.add(a);
      }
      return a;
   }
}
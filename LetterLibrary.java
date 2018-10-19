

import java.math.BigInteger;
import java.util.Arrays;

public class LetterLibrary
{
   private BigInteger[] library;
   public LetterLibrary()
   {
      this.library = new BigInteger[26];
   }
   
   public void addToLibrary(char letter, int value)
   {
      int num = letter - 65;
      this.library[num] = new BigInteger("" + value); //adds value to coresponding letter on array, index 0 = A to index 25 = Z
   }
   
   public BigInteger libraryAccess(int index)
   {
      return library[index];
   }
   
}
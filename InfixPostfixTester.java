
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
package calculator;
import java.util.Arrays;
public class InfixPostfixTester
{
		Expression newExpression = new Expression("2+2");
      String str = "2+2";
		newExpression.postFixExpression(str);
		System.out.println(newExpression.postFix());
}
package Calculator;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Expression {
    @Getter
    private final String infix;
    @Getter
    private final String postfix;
    @Getter
    private final String value;
    private final List<Character> operator = List.of('(', '+', '-', '%', '*', '/', '^');
    private final List<Integer> onStack = List.of(0, 2, 2, 4, 4, 4, 5);

    public Expression(String str) {
        this.infix = str;
        this.postfix = postfixExpression(infix);
        this.value = postFixToValue();
        if (this.value.contains("Error"))
            throw new IllegalStateException("Cannot parse given value");
    }

    private List<String> stringToElementList(String str) {
        List<String> elementList = new ArrayList<>();
        for (int i = 0; i < str.length(); ) {
            boolean isAtMinus = '-' == str.charAt(i);

            if (isAtMinus && i + 1 < str.length() && '-' == str.charAt(i + 1)) {
                elementList.add("+");
                i += 2;
            } else if (!(isAtMinus && i + 1 < str.length() && Character.isDigit(str.charAt(i + 1))) && (operator.contains(str.charAt(i)) || str.charAt(i) == ')')) {
                if (i > 0 && Character.isDigit(str.charAt(i - 1)) && str.charAt(i) == '(') {
                    elementList.add("*");
                }
                elementList.add(Character.toString(str.charAt(i)));
                i++;
            } else {
                if (isAtMinus && Character.isDigit(str.charAt(i - 1))) {
                    elementList.add("-");
                    i++;
                } else {
                    int decimals = 0;
                    StringBuilder newNum = new StringBuilder();
                    if (isAtMinus) {
                        newNum.append(str.charAt(i));
                        i++;
                    }
                    while (i < str.length() && (!operator.contains(str.charAt(i)) && str.charAt(i) != ')' && decimals < 2 && !(decimals == 1 && str.charAt(i) == '.'))) {
                        newNum.append(str.charAt(i));
                        if (str.charAt(i) == '.') {
                            decimals++;
                        }
                        i++;
                    }
                    elementList.add(newNum.toString());
                }
            }
        }
        return elementList.stream().map(String::trim).collect(Collectors.toList());
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int getPrecedence(String element) {
        return onStack.get(operator.indexOf(element.charAt(0)));
    }

    private String postfixExpression(String str) {
        List<String> infix = stringToElementList(str);
        Stack<String> stack = new Stack<>();
        List<String> postfix = new ArrayList<>();

        for (String element : infix) {
            if (element.equals("(")) {
                stack.push(element);
            } else if (element.equals(")")) {
                while (!(stack.peek().equals("("))) {
                    postfix.add(stack.pop());
                }
                stack.pop();
            } else if (isDouble(element)) {
                postfix.add(element);
            } else {
                while (!stack.isEmpty() && getPrecedence(element) <= getPrecedence(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(element);
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return String.join(" ", postfix);
    }

    private String postFixToValue() {
        int index = 0;
        Stack<String> expressionStack = new Stack<>();
        List<String> postFixExpression = new ArrayList<>(Arrays.asList(postfix.split(" ")));
        while (index < postFixExpression.size()) {
            String symbol = postFixExpression.get(index);
            if (isDouble(symbol)) {
                expressionStack.push(symbol);
            } else {
                BigDecimal left;
                BigDecimal right;
                try {
                    left = new BigDecimal(expressionStack.pop());
                    right = new BigDecimal(expressionStack.pop());
                } catch (Exception E) {
                    return "Error: Improper Equation Structure";
                }
                switch (symbol) {
                    case "^":
                        double rightD = Double.parseDouble(right.toString());
                        double leftD = Double.parseDouble(left.toString());
                        expressionStack.push(Double.toString(Math.pow(rightD, leftD)));
                        break;
                    case "/":
                        expressionStack.push(right.divide(left, 20, RoundingMode.DOWN).stripTrailingZeros().toString());
                        break;
                    case "*":
                        expressionStack.push(right.multiply(left).toString());
                        break;
                    case "%":
                        expressionStack.push(right.remainder(left).toString());
                        break;
                    case "-":
                        expressionStack.push(right.subtract(left).toString());
                        break;
                    case "+":
                        expressionStack.push(right.add(left).toString());
                        break;
                }
            }
            index++;
        }

        String result = expressionStack.pop();
        if (result.endsWith(".0")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }
}

package JavaFX;

import Calculator.Expression;
import javafx.scene.control.TextField;

public class Controller {
    public TextField myTextField;

    public void zeroButtonClicked() {
        myTextField.setText(myTextField.getText() + "0");
    }

    public void oneButtonClicked() {
        myTextField.setText(myTextField.getText() + "1");
    }

    public void twoButtonClicked() {
        myTextField.setText(myTextField.getText() + "2");
    }

    public void threeButtonClicked() {
        myTextField.setText(myTextField.getText() + "3");
    }

    public void fourButtonClicked() {
        myTextField.setText(myTextField.getText() + "4");
    }

    public void fiveButtonClicked() {
        myTextField.setText(myTextField.getText() + "5");
    }

    public void sixButtonClicked() {
        myTextField.setText(myTextField.getText() + "6");
    }

    public void sevenButtonClicked() {
        myTextField.setText(myTextField.getText() + "7");
    }

    public void eightButtonClicked() {
        myTextField.setText(myTextField.getText() + "8");
    }

    public void nineButtonClicked() { myTextField.setText(myTextField.getText() + "9"); }

    public void plusButtonClicked() {
        myTextField.setText(myTextField.getText() + "+");
    }

    public void minusButtonClicked() {
        myTextField.setText(myTextField.getText() + "-");
    }

    public void multiplyButtonClicked() {
        myTextField.setText(myTextField.getText() + "*");
    }

    public void divideButtonClicked() {
        myTextField.setText(myTextField.getText() + "/");
    }

    public void leftParButtonClicked() {
        myTextField.setText(myTextField.getText() + ")");
    }

    public void rightParButtonClicked() {
        myTextField.setText(myTextField.getText() + "(");
    }

    public void exponentButtonClicked() {
        myTextField.setText(myTextField.getText() + "^");
    }

    public void moduloButtonClicked() {
        myTextField.setText(myTextField.getText() + "%");
    }

    public void decimalButtonClicked() {
        myTextField.setText(myTextField.getText() + ".");
    }

    public void clearButtonClicked() {
        myTextField.setText("");
    }

    public void backButtonClicked() {
        int textSize = myTextField.getText().length();
        if (textSize > 0) {
            myTextField.setText(myTextField.getText().substring(0, textSize - 1));
        }
    }

    public void equalButtonClicked() {
        if (myTextField.getText().length() > 0) {
            Expression newExpression = new Expression(myTextField.getText());
            myTextField.setText(newExpression.getValue());
        }
    }
}

package Calculator;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExpressionTest {
    @Test
    public void basicAddition_1() {
        Expression newExpression = new Expression("2+2");
        assertThat(newExpression.getValue(), equalTo("4"));
    }

    @Test
    public void basicAddition_2() {
        Expression newExpression = new Expression("2+-2");
        assertThat(newExpression.getValue(), equalTo("0"));
    }

    @Test
    public void basicSubtraction_1() {
        Expression newExpression = new Expression("3-2");
        assertThat(newExpression.getValue(), equalTo("1"));
    }

    @Test
    public void basicSubtraction_2() {
        Expression newExpression = new Expression("2-3");
        assertThat(newExpression.getValue(), equalTo("-1"));
    }

    @Test
    public void basicSubtraction_3() {
        Expression newExpression = new Expression("2--3");
        assertThat(newExpression.getValue(), equalTo("5"));
    }

    @Test
    public void basicMultiplication() {
        Expression newExpression = new Expression("20*3");
        assertThat(newExpression.getValue(), equalTo("60"));
    }

    @Test
    public void basicMultiplication_2() {
        Expression newExpression = new Expression("20(3)");
        assertThat(newExpression.getValue(), equalTo("60"));
    }

    @Test
    public void basicDivision() {
        Expression newExpression = new Expression("20/4");
        assertThat(newExpression.getValue(), equalTo("5"));
    }

    @Test
    public void basicExponents() {
        Expression newExpression = new Expression("2^3");
        assertThat(newExpression.getValue(), equalTo("8"));
    }

    @Test
    public void basicExponents_2() {
        Expression newExpression = new Expression("2^-3");
        assertThat(newExpression.getValue(), equalTo("0.125"));
    }

    @Test
    public void basicMod() {
        Expression newExpression = new Expression("20%3");
        assertThat(newExpression.getValue(), equalTo("2"));
    }

    @Test
    public void complex_1() {
        Expression newExpression = new Expression("60/5*(7-5)");
        assertThat(newExpression.getValue(), equalTo("24"));
    }

    @Test
    public void complex_2() {
        Expression newExpression = new Expression("((2*3)+5)^2");
        assertThat(newExpression.getValue(), equalTo("121"));
    }

    @Test
    public void complex_3() {
        Expression newExpression = new Expression("2^2^2^2");
        assertThat(newExpression.getValue(), equalTo("256"));
    }

    @Test
    public void complex_4() {
        Expression newExpression = new Expression("20/4+2*(9/3-2^3)");
        assertThat(newExpression.getValue(), equalTo("-5"));
    }

    @Test
    public void complex_5() {
        Expression newExpression = new Expression("12%3--4(23)");
        assertThat(newExpression.getValue(), equalTo("92"));
    }

    @Test
    public void complex_6() {
        Expression newExpression = new Expression("60/5*(7-5)");
        assertThat("24", equalTo(newExpression.getValue()));
    }

    @Test
    public void complex_7() {
        Expression newExpression = new Expression("60/5*(7--5)");
        assertThat("144", equalTo(newExpression.getValue()));
    }

    @Test
    public void complex_8s() {
        Expression newExpression = new Expression("3^(3(3))+2");
        assertThat("19685", equalTo(newExpression.getValue()));
    }

    @Test(expected = IllegalStateException.class)
    public void error() {
        new Expression("asdf");
    }

    @Test
    public void infixExpression() {
        Expression newExpression = new Expression("123");
        assertThat("123", equalTo(newExpression.getInfix()));
    }
}

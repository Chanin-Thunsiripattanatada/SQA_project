package round1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class Expression_Interpreter_R1_test_GeminiPro {
    @Test
    void testNumber() {
        Number number = new Number(5);
        Context context = new Context();
        assertEquals(5, number.interpret(context));
    }

    @Test
    void testAdd() {
        Add add = new Add(new Number(2), new Number(3));
        Context context = new Context();
        assertEquals(5, add.interpret(context));
    }

    @Test
    void testSubtract() {
        Subtract subtract = new Subtract(new Number(5), new Number(2));
        Context context = new Context();
        assertEquals(3, subtract.interpret(context));
    }

    @Test
    void testMultiply() {
        Multiply multiply = new Multiply(new Number(2), new Number(3));
        Context context = new Context();
        assertEquals(6, multiply.interpret(context));
    }

    @Test
    void testDivide() {
        Divide divide = new Divide(new Number(6), new Number(2));
        Context context = new Context();
        assertEquals(3, divide.interpret(context));
    }

    @Test
    void testDivideByZero() {
        Divide divide = new Divide(new Number(6), new Number(0));
        Context context = new Context();
        assertThrows(ArithmeticException.class, () -> divide.interpret(context));
    }

    @Test
    void testVariable() {
        Variable variable = new Variable("x");
        Context context = new Context();
        context.setVariable("x", 10.0);
        assertEquals(10, variable.interpret(context));
    }

    @Test
    void testUndefinedVariable() {
        Variable variable = new Variable("y");
        Context context = new Context();
        assertThrows(IllegalArgumentException.class, () -> variable.interpret(context));
    }

    @Test
    void testComplexExpression() {
        Context context = new Context();
        context.setVariable("x", 10.0);
        context.setVariable("y", 5.0);

        Expression expression = new Divide(
                new Add(new Number(10), new Variable("x")),
                new Multiply(new Variable("y"), new Number(2))
        );
        assertEquals(2.0, expression.interpret(context));
    }
}
package interpreter_chatgpt4o_round3;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {

    @Test
    public void testBasicMathOperations() {
        Map<String, Double> context = new HashMap<>();

        Expression expr1 = Interpreter.parse("5 3 +");
        assertEquals(8.0, expr1.interpret(context), 0.001);

        Expression expr2 = Interpreter.parse("5 3 -");
        assertEquals(2.0, expr2.interpret(context), 0.001);

        Expression expr3 = Interpreter.parse("5 3 *");
        assertEquals(15.0, expr3.interpret(context), 0.001);

        Expression expr4 = Interpreter.parse("6 3 /");
        assertEquals(2.0, expr4.interpret(context), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        Map<String, Double> context = new HashMap<>();
        Expression expr = Interpreter.parse("6 0 /");
        expr.interpret(context);
    }

    @Test
    public void testVariableHandling() {
        Map<String, Double> context = new HashMap<>();
        context.put("x", 10.0);
        context.put("y", 2.0);

        Expression expr = Interpreter.parse("x y *");
        assertEquals(20.0, expr.interpret(context), 0.001);
    }

    @Test(expected = RuntimeException.class)
    public void testUndefinedVariable() {
        Map<String, Double> context = new HashMap<>();
        Expression expr = Interpreter.parse("z 2 +");
        expr.interpret(context);
    }

    @Test
    public void testComplexExpression() {
        Map<String, Double> context = new HashMap<>();
        context.put("x", 10.0);
        context.put("y", 2.0);

        Expression expr = Interpreter.parse("x y + 5 *");
        assertEquals(60.0, expr.interpret(context), 0.001);
    }
}

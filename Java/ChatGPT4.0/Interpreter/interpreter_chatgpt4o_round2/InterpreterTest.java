package interpreter_chatgpt4o_round2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {

    @Test
    public void testAddition() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 10);
        context.put("y", 20);

        Expression expr = ExpressionParser.parse("x y +", context);
        assertEquals(30, expr.interpret(context));
    }

    @Test
    public void testSubtraction() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 20);
        context.put("y", 10);

        Expression expr = ExpressionParser.parse("x y -", context);
        assertEquals(10, expr.interpret(context));
    }

    @Test
    public void testMultiplication() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 5);
        context.put("y", 4);

        Expression expr = ExpressionParser.parse("x y *", context);
        assertEquals(20, expr.interpret(context));
    }

    @Test
    public void testDivision() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 20);
        context.put("y", 5);

        Expression expr = ExpressionParser.parse("x y /", context);
        assertEquals(4, expr.interpret(context));
    }

    @Test
    public void testDivisionByZero() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 20);
        context.put("y", 0);

        Expression expr = ExpressionParser.parse("x y /", context);
        assertThrows(ArithmeticException.class, () -> expr.interpret(context));
    }

    @Test
    public void testInvalidVariable() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 20);

        Expression expr = ExpressionParser.parse("x z +", context);
        assertThrows(RuntimeException.class, () -> expr.interpret(context));
    }

    @Test
    public void testComplexExpression() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 10);
        context.put("y", 20);

        Expression expr = ExpressionParser.parse("x y + 2 *", context);
        assertEquals(60, expr.interpret(context));
    }
}

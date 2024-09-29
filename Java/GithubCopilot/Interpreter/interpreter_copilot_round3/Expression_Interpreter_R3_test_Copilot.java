package interpreter_copilot_round3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class Expression_Interpreter_R3_test_Copilot {
    @Test
    void testNumberExpression() {
        Expression expression = new NumberExpression(5);
        Map<String, Integer> context = new HashMap<>();
        assertEquals(5, expression.interpret(context));
    }

    @Test
    void testVariableExpression() {
        Expression expression = new VariableExpression("x");
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 10);
        assertEquals(10, expression.interpret(context));
    }

    @Test
    void testAdditionExpression() {
        Expression expression = new BinaryExpression(new NumberExpression(5), "+", new NumberExpression(3));
        Map<String, Integer> context = new HashMap<>();
        assertEquals(8, expression.interpret(context));
    }

    @Test
    void testSubtractionExpression() {
        Expression expression = new BinaryExpression(new NumberExpression(5), "-", new NumberExpression(3));
        Map<String, Integer> context = new HashMap<>();
        assertEquals(2, expression.interpret(context));
    }

    @Test
    void testMultiplicationExpression() {
        Expression expression = new BinaryExpression(new NumberExpression(5), "*", new NumberExpression(3));
        Map<String, Integer> context = new HashMap<>();
        assertEquals(15, expression.interpret(context));
    }

    @Test
    void testDivisionExpression() {
        Expression expression = new BinaryExpression(new NumberExpression(10), "/", new NumberExpression(2));
        Map<String, Integer> context = new HashMap<>();
        assertEquals(5, expression.interpret(context));
    }

    @Test
    void testComplexExpression() {
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 10);
        context.put("y", 5);

        Expression expression = new BinaryExpression(
                new BinaryExpression(new NumberExpression(10), "+", new VariableExpression("x")),
                "/",
                new BinaryExpression(new VariableExpression("y"), "*", new NumberExpression(2))
        );

        assertEquals(2, expression.interpret(context));
    }

    @Test
    void testDivisionByZero() {
        Expression expression = new BinaryExpression(new NumberExpression(1), "/", new NumberExpression(0));
        Map<String, Integer> context = new HashMap<>();
        assertThrows(ArithmeticException.class, () -> expression.interpret(context));
    }

    @Test
    void testInvalidOperator() {
        Expression expression = new BinaryExpression(new NumberExpression(1), "%", new NumberExpression(1));
        Map<String, Integer> context = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> expression.interpret(context));
    }

    @Test
    void testInvalidCharacter() {
        Parser parser = new Parser("5 + 3 & 2");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testMissingClosingParenthesis() {
        Parser parser = new Parser("(5 + 3");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testNestedParentheses() {
        Parser parser = new Parser("((5 + 3) * 2)");
        Expression expression = parser.parse();
        Map<String, Integer> context = new HashMap<>();
        assertEquals(16, expression.interpret(context));
    }

    @Test
    void testEmptyExpression() {
        Parser parser = new Parser("");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testVariableNotFound() {
        Expression expression = new VariableExpression("z");
        Map<String, Integer> context = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> expression.interpret(context));
    }
}
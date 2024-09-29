package interpreter_chatgpt4o_round1;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {

    @Test
    void testNumberExpression() {
        Expression expression = new NumberExpression(10);
        assertEquals(10, expression.interpret(new HashMap<>()));
    }

    @Test
    void testVariableExpression() {
        Expression expression = new VariableExpression("x");
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        assertEquals(5.0, expression.interpret(variables));

        // Test variable not found
        assertThrows(IllegalArgumentException.class, () -> expression.interpret(new HashMap<>()));
    }

    @Test
    void testBinaryExpressionAddition() {
        Expression expression = new BinaryExpression(new NumberExpression(10), new NumberExpression(5), '+');
        assertEquals(15, expression.interpret(new HashMap<>()));
    }

    @Test
    void testBinaryExpressionSubtraction() {
        Expression expression = new BinaryExpression(new NumberExpression(10), new NumberExpression(5), '-');
        assertEquals(5, expression.interpret(new HashMap<>()));
    }

    @Test
    void testBinaryExpressionMultiplication() {
        Expression expression = new BinaryExpression(new NumberExpression(10), new NumberExpression(5), '*');
        assertEquals(50, expression.interpret(new HashMap<>()));
    }

    @Test
    void testBinaryExpressionDivision() {
        Expression expression = new BinaryExpression(new NumberExpression(10), new NumberExpression(5), '/');
        assertEquals(2, expression.interpret(new HashMap<>()));
    }

    @Test
    void testDivisionByZero() {
        Expression expression = new BinaryExpression(new NumberExpression(10), new NumberExpression(0), '/');
        assertThrows(IllegalArgumentException.class, () -> expression.interpret(new HashMap<>()));
    }

    @Test
    void testComplexExpression() {
        String expr = "(x + 5) * (y - 2)";
        Expression parsedExpression = ExpressionParser.parse(expr);
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 3.0);
        variables.put("y", 8.0);
        assertEquals(48, parsedExpression.interpret(variables));
    }

    @Test
    void testUnknownOperator() {
        assertThrows(IllegalArgumentException.class, () -> new BinaryExpression(new NumberExpression(1), new NumberExpression(1), '%').interpret(new HashMap<>()));
    }
}

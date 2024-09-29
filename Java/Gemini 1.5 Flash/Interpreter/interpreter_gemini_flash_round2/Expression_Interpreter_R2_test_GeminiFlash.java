package interpreter_gemini_flash_round2;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class Expression_Interpreter_R2_test_GeminiFlash {
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
    void testVariableExpressionNotFound() {
        Expression expression = new VariableExpression("y");
        Map<String, Integer> context = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> expression.interpret(context));
    }

    @Test
    void testBinaryExpressionAddition() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(3);
        Expression expression = new BinaryExpression(left, "+", right);
        Map<String, Integer> context = new HashMap<>();
        assertEquals(8, expression.interpret(context));
    }

    @Test
    void testBinaryExpressionSubtraction() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(3);
        Expression expression = new BinaryExpression(left, "-", right);
        Map<String, Integer> context = new HashMap<>();
        assertEquals(2, expression.interpret(context));
    }

    @Test
    void testBinaryExpressionMultiplication() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(3);
        Expression expression = new BinaryExpression(left, "*", right);
        Map<String, Integer> context = new HashMap<>();
        assertEquals(15, expression.interpret(context));
    }

    @Test
    void testBinaryExpressionDivision() {
        Expression left = new NumberExpression(10);
        Expression right = new NumberExpression(2);
        Expression expression = new BinaryExpression(left, "/", right);
        Map<String, Integer> context = new HashMap<>();
        assertEquals(5, expression.interpret(context));
    }

    @Test
    void testBinaryExpressionDivisionByZero() {
        Expression left = new NumberExpression(10);
        Expression right = new NumberExpression(0);
        Expression expression = new BinaryExpression(left, "/", right);
        Map<String, Integer> context = new HashMap<>();
        assertThrows(ArithmeticException.class, () -> expression.interpret(context));
    }

    @Test
    void testParseSimpleExpression() {
        Parser parser = new Parser("5");
        Expression expression = parser.parse();
        assertTrue(expression instanceof NumberExpression);
        assertEquals(5, ((NumberExpression) expression).getValue());
    }

    @Test
    void testParseVariableExpression() {
        Parser parser = new Parser("x");
        Expression expression = parser.parse();
        assertTrue(expression instanceof VariableExpression);
        assertEquals("x", ((VariableExpression) expression).name);
    }

    @Test
    void testParseAdditionExpression() {
        Parser parser = new Parser("5 + 3");
        Expression expression = parser.parse();
        assertTrue(expression instanceof BinaryExpression);
        assertEquals("+", ((BinaryExpression) expression).operator);
        assertTrue(((BinaryExpression) expression).left instanceof NumberExpression);
        assertEquals(5, ((NumberExpression) ((BinaryExpression) expression).left).getValue());
        assertTrue(((BinaryExpression) expression).right instanceof NumberExpression);
        assertEquals(3, ((NumberExpression) ((BinaryExpression) expression).right).getValue());
    }

    @Test
    void testParseComplexExpression() {
        Parser parser = new Parser("a + (b * 2) - c");
        Expression expression = parser.parse();
        assertTrue(expression instanceof BinaryExpression);

        BinaryExpression outerExpression = (BinaryExpression) expression;
        assertEquals("-", outerExpression.operator);

        // Test left side (a + (b * 2))
        assertTrue(outerExpression.left instanceof BinaryExpression);
        BinaryExpression innerExpression = (BinaryExpression) outerExpression.left;
        assertEquals("+", innerExpression.operator);

        // Check the left operand of the inner expression (a)
        assertTrue(innerExpression.left instanceof VariableExpression);
        assertEquals("a", ((VariableExpression) innerExpression.left).name);

        // Check the right operand of the inner expression (b * 2)
        assertTrue(innerExpression.right instanceof BinaryExpression);
        BinaryExpression multiplyExpression = (BinaryExpression) innerExpression.right;
        assertEquals("*", multiplyExpression.operator);

        // Check left operand of multiplyExpression (b)
        assertTrue(multiplyExpression.left instanceof VariableExpression);
        assertEquals("b", ((VariableExpression) multiplyExpression.left).name);

        // Check right operand of multiplyExpression (2)
        assertTrue(multiplyExpression.right instanceof NumberExpression);
        assertEquals(2, ((NumberExpression) multiplyExpression.right).getValue());

        // Test the right side of the outer expression (c)
        assertTrue(outerExpression.right instanceof VariableExpression);
        assertEquals("c", ((VariableExpression) outerExpression.right).name);
    }

    @Test
    void testInterpretComplexExpression() {
        Parser parser = new Parser("a + (b * 2) - c");
        Expression expression = parser.parse();
        Map<String, Integer> context = new HashMap<>();
        context.put("a", 5);
        context.put("b", 3);
        context.put("c", 1);
        assertEquals(10, expression.interpret(context));
    }

    @Test
    void testParseInvalidExpression() {
        Parser parser = new Parser("5 +");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testParseInvalidOperator() {
        Parser parser = new Parser("5 ^ 3");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testParseMissingClosingParenthesis() {
        Parser parser = new Parser("(5 + 3");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testParseInvalidCharacter() {
        Parser parser = new Parser("5 & 3");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testParseEmptyExpression() {
        Parser parser = new Parser("");
        assertThrows(IllegalArgumentException.class, parser::parse);
    }

    @Test
    void testParseNestedParentheses() {
        Parser parser = new Parser("((5 + 3) * 2)");
        Expression expression = parser.parse();
        assertTrue(expression instanceof BinaryExpression);
        BinaryExpression outerExpression = (BinaryExpression) expression;
        assertEquals("*", outerExpression.operator);
        assertTrue(outerExpression.left instanceof BinaryExpression);
        BinaryExpression innerExpression = (BinaryExpression) outerExpression.left;
        assertEquals("+", innerExpression.operator);
        assertTrue(innerExpression.left instanceof NumberExpression);
        assertEquals(5, ((NumberExpression) innerExpression.left).getValue());
        assertTrue(innerExpression.right instanceof NumberExpression);
        assertEquals(3, ((NumberExpression) innerExpression.right).getValue());
        assertTrue(outerExpression.right instanceof NumberExpression);
        assertEquals(2, ((NumberExpression) outerExpression.right).getValue());
    }    
}
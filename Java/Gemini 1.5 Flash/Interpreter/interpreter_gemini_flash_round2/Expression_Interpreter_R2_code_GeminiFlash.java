package interpreter_gemini_flash_round2;
import java.util.HashMap;
import java.util.Map;
interface Expression {
    int interpret(Map<String, Integer> context);
}

class NumberExpression implements Expression {
    private int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    public int getValue() { // Add this method
        return value;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return value;
    }
}

class VariableExpression implements Expression {
    String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        if (context.containsKey(name)) {
            return context.get(name);
        } else {
            throw new IllegalArgumentException("Variable '" + name + "' not found in context.");
        }
    }
}

class BinaryExpression implements Expression {
    Expression left;
    String operator;
    Expression right;

    public BinaryExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        int leftValue = left.interpret(context);
        int rightValue = right.interpret(context);

        switch (operator) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

class Parser {
    private String expression;
    private int index;

    public Parser(String expression) {
        this.expression = expression.replaceAll("\\s+", ""); // Remove all whitespace
        this.index = 0;
    }

    public Expression parse() {
        return _parseExpression();
    }

    private Expression _parseExpression() {
        Expression left = _parseTerm();
        while (index < expression.length()) {
            char currentChar = expression.charAt(index);
            validateCharacter(currentChar); // Validate the current character
            if (currentChar == '+' || currentChar == '-') {
                String operator = String.valueOf(currentChar);
                index++;
                Expression right = _parseTerm();
                left = new BinaryExpression(left, operator, right);
            } else {
                break; // Exit loop if not an operator
            }
        }
        return left;
    }
    
    private Expression _parseTerm() {
        Expression left = _parseFactor();
        while (index < expression.length()) {
            char currentChar = expression.charAt(index);
            if (currentChar == '*' || currentChar == '/') {
                String operator = String.valueOf(currentChar);
                index++;
                Expression right = _parseFactor();
                left = new BinaryExpression(left, operator, right);
            } else {
                break; // Exit loop if not an operator
            }
        }
        return left;
    }
    
    private Expression _parseFactor() {
        if (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            int start = index;
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                index++;
            }
            int value = Integer.parseInt(expression.substring(start, index));
            return new NumberExpression(value);
        } else if (index < expression.length() && Character.isLetter(expression.charAt(index))) {
            String name = String.valueOf(expression.charAt(index));
            index++;
            return new VariableExpression(name);
        } else if (index < expression.length() && expression.charAt(index) == '(') {
            index++;
            Expression expression = _parseExpression();
            if (index < this.expression.length() && this.expression.charAt(index) == ')') {
                index++; // Skip ')'
            } else {
                throw new IllegalArgumentException("Missing closing parenthesis.");
            }
            return expression;
        } else {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
    }    
    // New method to check for invalid characters
    private void validateCharacter(char currentChar) {
        if (!Character.isDigit(currentChar) && !Character.isLetter(currentChar) &&
            currentChar != '+' && currentChar != '-' &&
            currentChar != '*' && currentChar != '/' &&
            currentChar != '(' && currentChar != ')') {
            throw new IllegalArgumentException("Invalid character: " + currentChar);
        }
    }
}

public class Expression_Interpreter_R2_code_GeminiFlash {
    public static void main(String[] args) {
        String[] expressions = {
            "5 + 3",
            "2 * (3 + 4)",
            "x + y",
            "10 / 2",
            "(5 + 3) * (10 - 2)",
            "x * (y + 2)",
        };

        Map<String, Integer> context = new HashMap<>();
        context.put("x", 10);
        context.put("y", 5);

        for (String expression : expressions) {
            Parser parser = new Parser(expression);
            try {
                Expression ast = parser.parse();
                int result = ast.interpret(context);
                System.out.println("Expression: " + expression + "  Result: " + result);
            } catch (Exception e) {
                System.out.println("Expression: " + expression + "  Error: " + e.getMessage());
            }
        }
    }
}

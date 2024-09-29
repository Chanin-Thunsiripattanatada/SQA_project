package interpreter_chatgpt4o_round2;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// Expression interface
interface Expression {
    int interpret(Map<String, Integer> context);
}

// Number expression for constants
class NumberExpression implements Expression {
    private final int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return number;
    }
}

// Variable expression for handling variables
class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        if (context.containsKey(name)) {
            return context.get(name);
        }
        throw new RuntimeException("Variable not found: " + name);
    }
}

// Add expression
class AddExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) + right.interpret(context);
    }
}

// Subtract expression
class SubtractExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) - right.interpret(context);
    }
}

// Multiply expression
class MultiplyExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        return left.interpret(context) * right.interpret(context);
    }
}

// Divide expression
class DivideExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public DivideExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        int rightValue = right.interpret(context);
        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.interpret(context) / rightValue;
    }
}

// Parser for handling complex expressions
class ExpressionParser {
    public static Expression parse(String expression, Map<String, Integer> context) {
        Stack<Expression> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        
        for (String token : tokens) {
            switch (token) {
                case "+":
                    Expression rightAdd = stack.pop();
                    Expression leftAdd = stack.pop();
                    stack.push(new AddExpression(leftAdd, rightAdd));
                    break;
                case "-":
                    Expression rightSub = stack.pop();
                    Expression leftSub = stack.pop();
                    stack.push(new SubtractExpression(leftSub, rightSub));
                    break;
                case "*":
                    Expression rightMul = stack.pop();
                    Expression leftMul = stack.pop();
                    stack.push(new MultiplyExpression(leftMul, rightMul));
                    break;
                case "/":
                    Expression rightDiv = stack.pop();
                    Expression leftDiv = stack.pop();
                    stack.push(new DivideExpression(leftDiv, rightDiv));
                    break;
                default:
                    try {
                        int number = Integer.parseInt(token);
                        stack.push(new NumberExpression(number));
                    } catch (NumberFormatException e) {
                        stack.push(new VariableExpression(token));
                    }
                    break;
            }
        }
        return stack.pop();
    }
}

// Client
public class InterpreterDemo {
    public static void main(String[] args) {
        // Example usage:
        Map<String, Integer> context = new HashMap<>();
        context.put("x", 10);
        context.put("y", 20);
        
        String expression = "x y + 2 *";
        Expression parsedExpression = ExpressionParser.parse(expression, context);
        
        System.out.println("Result: " + parsedExpression.interpret(context));  // Output: 60
    }
}

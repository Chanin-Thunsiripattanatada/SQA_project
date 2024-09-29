package interpreter_chatgpt4o_round3;
import java.util.Map;
import java.util.Stack;

// Expression Interface
interface Expression {
    double interpret(Map<String, Double> context);
}

// Number Expression
class NumberExpression implements Expression {
    private double number;

    public NumberExpression(double number) {
        this.number = number;
    }

    @Override
    public double interpret(Map<String, Double> context) {
        return number;
    }
}

// Variable Expression
class VariableExpression implements Expression {
    private String variable;

    public VariableExpression(String variable) {
        this.variable = variable;
    }

    @Override
    public double interpret(Map<String, Double> context) {
        if (context.containsKey(variable)) {
            return context.get(variable);
        } else {
            throw new RuntimeException("Variable " + variable + " not found.");
        }
    }
}

// Addition Expression
class AddExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AddExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret(Map<String, Double> context) {
        return leftExpression.interpret(context) + rightExpression.interpret(context);
    }
}

// Subtraction Expression
class SubtractExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret(Map<String, Double> context) {
        return leftExpression.interpret(context) - rightExpression.interpret(context);
    }
}

// Multiplication Expression
class MultiplyExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public MultiplyExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret(Map<String, Double> context) {
        return leftExpression.interpret(context) * rightExpression.interpret(context);
    }
}

// Division Expression
class DivideExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public DivideExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public double interpret(Map<String, Double> context) {
        double denominator = rightExpression.interpret(context);
        if (denominator == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        return leftExpression.interpret(context) / denominator;
    }
}

// Interpreter Class
class Interpreter {
    public static Expression parse(String expression) {
        Stack<Expression> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            if (isOperator(token)) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(new AddExpression(left, right));
                        break;
                    case "-":
                        stack.push(new SubtractExpression(left, right));
                        break;
                    case "*":
                        stack.push(new MultiplyExpression(left, right));
                        break;
                    case "/":
                        stack.push(new DivideExpression(left, right));
                        break;
                }
            } else {
                Expression numberOrVariable = isVariable(token) ? new VariableExpression(token)
                        : new NumberExpression(Double.parseDouble(token));
                stack.push(numberOrVariable);
            }
        }
        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static boolean isVariable(String token) {
        return token.matches("[a-zA-Z]+");
    }
}

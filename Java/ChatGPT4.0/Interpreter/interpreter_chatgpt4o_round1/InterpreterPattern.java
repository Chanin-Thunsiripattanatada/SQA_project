package interpreter_chatgpt4o_round1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


interface Expression {
    double interpret(Map<String, Double> variables) throws IllegalArgumentException;
}

class NumberExpression implements Expression {
    private final double number;

    public NumberExpression(double number) {
        this.number = number;
    }

    @Override
    public double interpret(Map<String, Double> variables) {
        return number;
    }
}

class VariableExpression implements Expression {
    private final String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public double interpret(Map<String, Double> variables) throws IllegalArgumentException {
        if (variables.containsKey(variableName)) {
            return variables.get(variableName);
        } else {
            throw new IllegalArgumentException("Variable " + variableName + " not found");
        }
    }
}

class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final char operator;

    public BinaryExpression(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double interpret(Map<String, Double> variables) throws IllegalArgumentException {
        double leftResult = left.interpret(variables);
        double rightResult = right.interpret(variables);

        switch (operator) {
            case '+': return leftResult + rightResult;
            case '-': return leftResult - rightResult;
            case '*': return leftResult * rightResult;
            case '/':
                if (rightResult == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return leftResult / rightResult;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

class ExpressionParser {
    public static Expression parse(String expression) throws IllegalArgumentException {
        Stack<Expression> stack = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int index = 0;

        while (index < expression.length()) {
            char c = expression.charAt(index);

            if (Character.isWhitespace(c)) {
                index++;
                continue;
            }

            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                    number.append(expression.charAt(index++));
                }
                stack.push(new NumberExpression(Double.parseDouble(number.toString())));
                continue;
            }

            if (Character.isLetter(c)) {
                StringBuilder variable = new StringBuilder();
                while (index < expression.length() && Character.isLetter(expression.charAt(index))) {
                    variable.append(expression.charAt(index++));
                }
                stack.push(new VariableExpression(variable.toString()));
                continue;
            }

            if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    stack.push(createBinaryExpression(stack, operators.pop()));
                }
                operators.pop(); // Remove '('
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    stack.push(createBinaryExpression(stack, operators.pop()));
                }
                operators.push(c);
            }
            index++;
        }

        while (!operators.isEmpty()) {
            stack.push(createBinaryExpression(stack, operators.pop()));
        }

        return stack.pop();
    }

    private static Expression createBinaryExpression(Stack<Expression> stack, char operator) {
        Expression right = stack.pop();
        Expression left = stack.pop();
        return new BinaryExpression(left, right, operator);
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
            default: return -1;
        }
    }
}


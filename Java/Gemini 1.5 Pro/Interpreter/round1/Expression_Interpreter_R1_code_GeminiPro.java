package round1;
import java.util.HashMap;
import java.util.Map;
interface Expression {
    double interpret(Context context);
}

class Number implements Expression {
    private double value;

    public Number(double value) {
        this.value = value;
    }

    @Override
    public double interpret(Context context) {
        return value;
    }
}

class Variable implements Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public double interpret(Context context) {
        Double value = context.getVariable(name);
        if (value == null) {
            throw new IllegalArgumentException("Variable '" + name + "' is not defined");
        }
        return value;
    }
}

class Add implements Expression {
    private Expression left;
    private Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

class Subtract implements Expression {
    private Expression left;
    private Expression right;

    public Subtract(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}

class Multiply implements Expression {
    private Expression left;
    private Expression right;

    public Multiply(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
}

class Divide implements Expression {
    private Expression left;
    private Expression right;

    public Divide(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret(Context context) {
        double divisor = right.interpret(context);
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.interpret(context) / divisor;
    }
}

class Context {
    private Map<String, Double> variables = new HashMap<>();

    public void setVariable(String name, double value) {
        variables.put(name, value);
    }

    public Double getVariable(String name) {
        return variables.get(name);
    }
}
public class Expression_Interpreter_R1_code_GeminiPro {
    public static void main(String[] args) {
        // Example usage:
        Context context = new Context();
        context.setVariable("x", 10.0);
        context.setVariable("y", 5.0);

        // (10 + x) / (y * 2)
        Expression expression = new Divide(
                new Add(new Number(10), new Variable("x")),
                new Multiply(new Variable("y"), new Number(2))
        );

        double result = expression.interpret(context);
        System.out.println("Result: " + result);
    }
}
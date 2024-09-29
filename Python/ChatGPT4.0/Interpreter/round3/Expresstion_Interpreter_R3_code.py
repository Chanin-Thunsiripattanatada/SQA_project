class Expression:
    """Abstract base class for expressions."""
    def interpret(self, context):
        raise NotImplementedError("You must implement the interpret method.")


class Number(Expression):
    """Represents a numeric value."""
    def __init__(self, value):
        self.value = value

    def interpret(self, context):
        return self.value


class Variable(Expression):
    """Represents a variable."""
    def __init__(self, name):
        self.name = name

    def interpret(self, context):
        if self.name in context:
            return context[self.name]
        raise ValueError(f"Variable '{self.name}' is not defined.")


class Add(Expression):
    """Represents an addition operation."""
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        return self.left.interpret(context) + self.right.interpret(context)


class Subtract(Expression):
    """Represents a subtraction operation."""
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        return self.left.interpret(context) - self.right.interpret(context)


class Multiply(Expression):
    """Represents a multiplication operation."""
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        return self.left.interpret(context) * self.right.interpret(context)


class Divide(Expression):
    """Represents a division operation."""
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        right_value = self.right.interpret(context)
        if right_value == 0:
            raise ZeroDivisionError("Division by zero is not allowed.")
        return self.left.interpret(context) / right_value


class Parser:
    """Parses input expressions into an expression tree."""
    def parse(self, expression):
        tokens = self.tokenize(expression)
        return self.build_expression_tree(tokens)

    def tokenize(self, expression):
        return expression.replace('(', ' ( ').replace(')', ' ) ').split()

    def build_expression_tree(self, tokens):
        if not tokens:
            raise ValueError("Empty expression.")
        token = tokens.pop(0)
        if token.isdigit():
            return Number(int(token))
        elif token.isalpha():
            return Variable(token)
        elif token == '(':
            left = self.build_expression_tree(tokens)
            op = tokens.pop(0)
            right = self.build_expression_tree(tokens)
            tokens.pop(0)  # Remove ')'
            if op == '+':
                return Add(left, right)
            elif op == '-':
                return Subtract(left, right)
            elif op == '*':
                return Multiply(left, right)
            elif op == '/':
                return Divide(left, right)
            else:
                raise ValueError(f"Unknown operator: {op}")
        raise ValueError(f"Unexpected token: {token}")


# Usage example
if __name__ == "__main__":
    context = {'x': 10, 'y': 5}
    parser = Parser()

    # Expression: (x + y) * (x - y)
    expr = "(x + y) * (x - y)"
    try:
        expression_tree = parser.parse(expr)
        result = expression_tree.interpret(context)
        print(f"Result of '{expr}': {result}")
    except Exception as e:
        print(f"Error: {e}")

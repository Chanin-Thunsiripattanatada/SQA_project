from abc import ABC, abstractmethod
class Expression(ABC):
    @abstractmethod
    def interpret(self, context):
        pass


class Number(Expression):
    def __init__(self, value):
        self.value = value

    def interpret(self, context):
        return self.value


class Add(Expression):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        return self.left.interpret(context) + self.right.interpret(context)


class Subtract(Expression):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        return self.left.interpret(context) - self.right.interpret(context)


class Multiply(Expression):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        return self.left.interpret(context) * self.right.interpret(context)


class Divide(Expression):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def interpret(self, context):
        if self.right.interpret(context) == 0:
            raise ZeroDivisionError("Division by zero")
        return self.left.interpret(context) / self.right.interpret(context)


class Variable(Expression):
    def __init__(self, name):
        self.name = name

    def interpret(self, context):
        value = context.get_variable(self.name)
        if value is None:
            raise NameError(f"Variable '{self.name}' is not defined")
        return value


class Context:
    def __init__(self):
        self.variables = {}

    def set_variable(self, name, value):
        self.variables[name] = value

    def get_variable(self, name):
        return self.variables.get(name)


def main():
    # Example usage
    context = Context()
    context.set_variable("x", 10)
    context.set_variable("y", 5)

    expression1 = Add(Number(10), Variable("x"))
    expression2 = Multiply(Variable("y"), Number(2))
    expression3 = Divide(expression1, expression2)

    result = expression3.interpret(context)
    print(f"Result: {result}")


if __name__ == "__main__":
    main()
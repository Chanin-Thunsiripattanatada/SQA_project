from abc import ABC, abstractmethod
import operator

# Expression Interface
class Expression(ABC):
    @abstractmethod
    def interpret(self, context):
        pass

# Number Expression
class Number(Expression):
    def __init__(self, value):
        self.value = value

    def interpret(self, context):
        return self.value

# Variable Expression
class Variable(Expression):
    def __init__(self, name):
        self.name = name

    def interpret(self, context):
        if self.name in context:
            return context[self.name]
        raise ValueError(f"Variable '{self.name}' not found")

# Binary Operation Expression
class BinaryOperation(Expression):
    def __init__(self, left, right, operator):
        self.left = left
        self.right = right
        self.operator = operator

    def interpret(self, context):
        left_value = self.left.interpret(context)
        right_value = self.right.interpret(context)
        return self.operator(left_value, right_value)

# Context for variables
class Context(dict):
    pass

# Interpreter
class Interpreter:
    def __init__(self):
        self.context = Context()

    def interpret(self, expression):
        try:
            return expression.interpret(self.context)
        except Exception as e:
            return str(e)

# Usage Example
if __name__ == "__main__":
    interpreter = Interpreter()
    interpreter.context['x'] = 10
    interpreter.context['y'] = 5

    expression = BinaryOperation(
        BinaryOperation(Number(2), Variable('x'), operator.mul),
        BinaryOperation(Number(3), Variable('y'), operator.add),
        operator.sub
    )

    result = interpreter.interpret(expression)
    print(f"Result: {result}")
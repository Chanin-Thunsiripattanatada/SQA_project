# interpreter.py

from abc import ABC, abstractmethod

# Abstract Expression
class Expression(ABC):
    @abstractmethod
    def interpret(self) -> int:
        pass

# Terminal Expressions
class NumberExpression(Expression):
    def __init__(self, number: int):
        self.number = number

    def interpret(self) -> int:
        return self.number

class AddExpression(Expression):
    def __init__(self, left: Expression, right: Expression):
        self.left = left
        self.right = right

    def interpret(self) -> int:
        return self.left.interpret() + self.right.interpret()

class SubtractExpression(Expression):
    def __init__(self, left: Expression, right: Expression):
        self.left = left
        self.right = right

    def interpret(self) -> int:
        return self.left.interpret() - self.right.interpret()

# Client
class Interpreter:
    def interpret(self, expression: str) -> int:
        tokens = expression.split()
        stack = []

        for token in tokens:
            if token.isdigit():
                stack.append(NumberExpression(int(token)))
            elif token == '+':
                right = stack.pop()
                left = stack.pop()
                stack.append(AddExpression(left, right))
            elif token == '-':
                right = stack.pop()
                left = stack.pop()
                stack.append(SubtractExpression(left, right))
        
        return stack.pop().interpret()

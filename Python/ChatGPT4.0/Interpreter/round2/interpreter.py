# interpreter.py

from abc import ABC, abstractmethod
from typing import Dict, Union

class Expression(ABC):
    @abstractmethod
    def interpret(self, context: Dict[str, Union[int, float]]) -> Union[int, float]:
        pass

class NumberExpression(Expression):
    def __init__(self, number: int) -> None:
        self.number = number

    def interpret(self, context: Dict[str, Union[int, float]]) -> Union[int, float]:
        return self.number

class AddExpression(Expression):
    def __init__(self, left: Expression, right: Expression) -> None:
        self.left = left
        self.right = right

    def interpret(self, context: Dict[str, Union[int, float]]) -> Union[int, float]:
        return self.left.interpret(context) + self.right.interpret(context)

class SubtractExpression(Expression):
    def __init__(self, left: Expression, right: Expression) -> None:
        self.left = left
        self.right = right

    def interpret(self, context: Dict[str, Union[int, float]]) -> Union[int, float]:
        return self.left.interpret(context) - self.right.interpret(context)

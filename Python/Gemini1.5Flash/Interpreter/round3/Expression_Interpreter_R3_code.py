from abc import ABC, abstractmethod
from typing import Dict

class AbstractExpression(ABC):
    @abstractmethod
    def interpret(self, context: Dict[str, int]) -> int:
        pass

class NumberExpression(AbstractExpression):
    def __init__(self, value: int):
        self.value = value

    def interpret(self, context: Dict[str, int]) -> int:
        return self.value

class VariableExpression(AbstractExpression):
    def __init__(self, name: str):
        self.name = name

    def interpret(self, context: Dict[str, int]) -> int:
        if self.name in context:
            return context[self.name]
        raise NameError(f"Variable '{self.name}' not found in context")

class BinaryExpression(AbstractExpression):
    def __init__(self, left: AbstractExpression, operator: str, right: AbstractExpression):
        self.left = left
        self.operator = operator
        self.right = right

    def interpret(self, context: Dict[str, int]) -> int:
        left_value = self.left.interpret(context)
        right_value = self.right.interpret(context)

        return self._evaluate(left_value, right_value)

    def _evaluate(self, left_value: int, right_value: int) -> int:
        if self.operator == '+':
            return left_value + right_value
        elif self.operator == '-':
            return left_value - right_value
        elif self.operator == '*':
            return left_value * right_value
        elif self.operator == '/':
            if right_value == 0:
                raise ZeroDivisionError("Division by zero")
            return left_value // right_value
        raise ValueError(f"Invalid operator: {self.operator}")

class Parser:
    def __init__(self, expression: str):
        self.expression = expression.replace(" ", "")  # Remove whitespace
        self.index = 0

    def parse(self) -> AbstractExpression:
        result = self._parse_expression()
        self._check_remaining()  # Validate remaining tokens
        return result

    def _parse_expression(self) -> AbstractExpression:
        left = self._parse_term()
        while self._has_more_tokens() and self.expression[self.index] in ('+', '-'):
            operator = self.expression[self.index]
            self.index += 1
            right = self._parse_term()
            left = BinaryExpression(left, operator, right)
        return left

    def _parse_term(self) -> AbstractExpression:
        left = self._parse_factor()
        while self._has_more_tokens() and self.expression[self.index] in ('*', '/'):
            operator = self.expression[self.index]
            self.index += 1
            right = self._parse_factor()
            left = BinaryExpression(left, operator, right)
        return left

    def _parse_factor(self) -> AbstractExpression:
        if self._has_more_tokens():
            char = self.expression[self.index]
            if char.isdigit():
                return self._parse_number()
            elif char.isalpha():
                return self._parse_variable()
            elif char == '(':
                return self._parse_parentheses()
        raise ValueError(f"Invalid expression: {self.expression[self.index:]}")

    def _parse_number(self) -> NumberExpression:
        number_str = ''
        while self._has_more_tokens() and self.expression[self.index].isdigit():
            number_str += self.expression[self.index]
            self.index += 1
        return NumberExpression(int(number_str))

    def _parse_variable(self) -> VariableExpression:
        name = self.expression[self.index]
        self.index += 1
        return VariableExpression(name)

    def _parse_parentheses(self) -> AbstractExpression:
        self.index += 1  # Skip '('
        expression = self._parse_expression()
        if not self._has_more_tokens() or self.expression[self.index] != ')':
            raise ValueError("Mismatched parentheses")
        self.index += 1  # Skip ')'
        return expression

    def _has_more_tokens(self) -> bool:
        return self.index < len(self.expression)

    def _check_remaining(self):
        if self._has_more_tokens():
            raise ValueError(f"Invalid operator or token: {self.expression[self.index]}")

def main():
    expressions = [
        "7 + 14",
        "2 * (3 + 4)",
        "x - y",
        "10 / 2",
        "(5 + 3) * (10 - 2)",
        "x * (y + 2)",
        "5 ^ 3",  # Invalid operator example
    ]

    context = {"x": 10, "y": 5}

    for expression in expressions:
        parser = Parser(expression)
        try:
            ast = parser.parse()
            result = ast.interpret(context)
            print(f"Expression: {expression}  Result: {result}")
        except Exception as e:
            print(f"Expression: {expression}  Error: {e}")

if __name__ == "__main__":
    main()

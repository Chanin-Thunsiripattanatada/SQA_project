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
        else:
            raise NameError(f"Variable '{self.name}' not found in context")

class BinaryExpression(AbstractExpression):
    def __init__(self, left: AbstractExpression, operator: str, right: AbstractExpression):
        self.left = left
        self.operator = operator
        self.right = right

    def interpret(self, context: Dict[str, int]) -> int:
        left_value = self.left.interpret(context)
        right_value = self.right.interpret(context)

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
        else:
            raise ValueError(f"Invalid operator: {self.operator}")

class Parser:
    def __init__(self, expression: str):
        self.expression = expression.replace(" ", "")  # ลบช่องว่าง
        self.index = 0

    def parse(self) -> AbstractExpression:
        result = self._parse_expression()
        self.check_remaining()  # ตรวจสอบหลังจากการประมวลผลเสร็จสิ้น
        return result

    def _parse_expression(self) -> AbstractExpression:
        left = self._parse_term()
        while self.index < len(self.expression) and self.expression[self.index] in ('+', '-'):
            operator = self.expression[self.index]
            self.index += 1
            right = self._parse_term()
            left = BinaryExpression(left, operator, right)

        return left

    def _parse_term(self) -> AbstractExpression:
        left = self._parse_factor()
        while self.index < len(self.expression) and self.expression[self.index] in ('*', '/'):
            operator = self.expression[self.index]
            self.index += 1
            right = self._parse_factor()
            left = BinaryExpression(left, operator, right)

        return left

    def _parse_factor(self) -> AbstractExpression:
        if self.index < len(self.expression) and self.expression[self.index].isdigit():
            number = ''
            while self.index < len(self.expression) and self.expression[self.index].isdigit():
                number += self.expression[self.index]
                self.index += 1
            return NumberExpression(int(number))
        elif self.index < len(self.expression) and self.expression[self.index].isalpha():
            name = self.expression[self.index]
            self.index += 1
            return VariableExpression(name)
        elif self.index < len(self.expression) and self.expression[self.index] == '(':
            self.index += 1
            expression = self._parse_expression()
            if self.index < len(self.expression) and self.expression[self.index] == ')':
                self.index += 1  # Skip ')'
                return expression
            else:
                raise ValueError("Mismatched parentheses")
        else:
            raise ValueError(f"Invalid expression: {self.expression[self.index:]}")

    def check_remaining(self):
        # ตรวจสอบตัวดำเนินการที่ไม่ถูกต้องหลังจากการประมวลผล
        if self.index < len(self.expression):
            raise ValueError(f"Invalid operator or token: {self.expression[self.index]}")

def main():
    expressions = [
        "5 + 3",
        "2 * (3 + 4)",
        "x + y",
        "10 / 2",
        "(5 + 3) * (10 - 2)",
        "x * (y + 2)",
        "5 ^ 3",  # ตัวอย่างตัวดำเนินการที่ไม่ถูกต้อง
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

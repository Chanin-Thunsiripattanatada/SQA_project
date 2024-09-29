import pytest
from Expression_Interpreter_R1_code import (
    AbstractExpression,
    NumberExpression,
    VariableExpression,
    BinaryExpression,
    Parser,
)

class TestInterpreter:
    def test_number_expression(self):
        expression = NumberExpression(8)
        context = {}
        assert expression.interpret(context) == 8

    def test_variable_expression(self):
        expression = VariableExpression("x")
        context = {"x": 12}
        assert expression.interpret(context) == 12

    def test_variable_expression_not_found(self):
        expression = VariableExpression("y")
        context = {"x": 12}
        with pytest.raises(NameError):
            expression.interpret(context)

    def test_binary_expression_addition(self):
        left = NumberExpression(9)
        right = NumberExpression(4)
        expression = BinaryExpression(left, "+", right)
        context = {}
        assert expression.interpret(context) == 13

    def test_binary_expression_subtraction(self):
        left = NumberExpression(6)
        right = NumberExpression(2)
        expression = BinaryExpression(left, "-", right)
        context = {}
        assert expression.interpret(context) == 4

    def test_binary_expression_multiplication(self):
        left = NumberExpression(7)
        right = NumberExpression(6)
        expression = BinaryExpression(left, "*", right)
        context = {}
        assert expression.interpret(context) == 42

    def test_binary_expression_division(self):
        left = NumberExpression(30)
        right = NumberExpression(5)
        expression = BinaryExpression(left, "/", right)
        context = {}
        assert expression.interpret(context) == 6

    def test_binary_expression_division_by_zero(self):
        left = NumberExpression(15)
        right = NumberExpression(0)
        expression = BinaryExpression(left, "/", right)
        context = {}
        with pytest.raises(ZeroDivisionError):
            expression.interpret(context)

    def test_parse_simple_expression(self):
        parser = Parser("9")
        expression = parser.parse()
        assert isinstance(expression, NumberExpression)
        assert expression.value == 9

    def test_parse_variable_expression(self):
        parser = Parser("y")
        expression = parser.parse()
        assert isinstance(expression, VariableExpression)
        assert expression.name == "y"

    def test_parse_addition_expression(self):
        parser = Parser("4 + 7")
        expression = parser.parse()
        assert isinstance(expression, BinaryExpression)
        assert expression.operator == "+"
        assert isinstance(expression.left, NumberExpression)
        assert expression.left.value == 4
        assert isinstance(expression.right, NumberExpression)
        assert expression.right.value == 7

    def test_parse_complex_expression(self):
        parser = Parser("x + (y * 3) - z")
        expression = parser.parse()
        assert isinstance(expression, BinaryExpression)
        assert expression.operator == "-"
        assert isinstance(expression.left, BinaryExpression)
        assert expression.left.operator == "+"
        assert isinstance(expression.left.left, VariableExpression)
        assert expression.left.left.name == "x"
        assert isinstance(expression.left.right, BinaryExpression)
        assert expression.left.right.operator == "*"
        assert isinstance(expression.left.right.left, VariableExpression)
        assert expression.left.right.left.name == "y"
        assert isinstance(expression.left.right.right, NumberExpression)
        assert expression.left.right.right.value == 3
        assert isinstance(expression.right, VariableExpression)
        assert expression.right.name == "z"

    def test_interpret_complex_expression(self):
        parser = Parser("x + (y * 3) - z")
        expression = parser.parse()
        context = {"x": 10, "y": 5, "z": 7}
        result = expression.interpret(context)
        assert result == 18

    def test_parse_invalid_expression(self):
        parser = Parser("8 +")
        with pytest.raises(ValueError):
            parser.parse()

    def test_parse_invalid_operator(self):
        parser = Parser("7 ^ 2")
        with pytest.raises(ValueError):
            parser.parse()
            
if __name__ == "__main__":
    pytest.main()
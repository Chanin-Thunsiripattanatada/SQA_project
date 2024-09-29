import pytest
from Expression_Interpreter_R2_code import (
    AbstractExpression,
    NumberExpression,
    VariableExpression,
    BinaryExpression,
    Parser,
)

class TestInterpreter:
    def test_number_expression(self):
        expression = NumberExpression(5)
        context = {}
        assert expression.interpret(context) == 5

    def test_variable_expression(self):
        expression = VariableExpression("x")
        context = {"x": 10}
        assert expression.interpret(context) == 10

    def test_variable_expression_not_found(self):
        expression = VariableExpression("y")
        context = {"x": 10}
        with pytest.raises(NameError):
            expression.interpret(context)

    def test_binary_expression_addition(self):
        left = NumberExpression(5)
        right = NumberExpression(3)
        expression = BinaryExpression(left, "+", right)
        context = {}
        assert expression.interpret(context) == 8

    def test_binary_expression_subtraction(self):
        left = NumberExpression(5)
        right = NumberExpression(3)
        expression = BinaryExpression(left, "-", right)
        context = {}
        assert expression.interpret(context) == 2

    def test_binary_expression_multiplication(self):
        left = NumberExpression(5)
        right = NumberExpression(3)
        expression = BinaryExpression(left, "*", right)
        context = {}
        assert expression.interpret(context) == 15

    def test_binary_expression_division(self):
        left = NumberExpression(10)
        right = NumberExpression(2)
        expression = BinaryExpression(left, "/", right)
        context = {}
        assert expression.interpret(context) == 5

    def test_binary_expression_division_by_zero(self):
        left = NumberExpression(10)
        right = NumberExpression(0)
        expression = BinaryExpression(left, "/", right)
        context = {}
        with pytest.raises(ZeroDivisionError):
            expression.interpret(context)

    def test_parse_simple_expression(self):
        parser = Parser("5")
        expression = parser.parse()
        assert isinstance(expression, NumberExpression)
        assert expression.value == 5

    def test_parse_variable_expression(self):
        parser = Parser("x")
        expression = parser.parse()
        assert isinstance(expression, VariableExpression)
        assert expression.name == "x"

    def test_parse_addition_expression(self):
        parser = Parser("5 + 3")
        expression = parser.parse()
        assert isinstance(expression, BinaryExpression)
        assert expression.operator == "+"
        assert isinstance(expression.left, NumberExpression)
        assert expression.left.value == 5
        assert isinstance(expression.right, NumberExpression)
        assert expression.right.value == 3

    def test_parse_complex_expression(self):
        parser = Parser("a + (b * 2) - c")
        expression = parser.parse()
        assert isinstance(expression, BinaryExpression)
        assert expression.operator == "-"
        assert isinstance(expression.left, BinaryExpression)
        assert expression.left.operator == "+"
        assert isinstance(expression.left.left, VariableExpression)
        assert expression.left.left.name == "a"
        assert isinstance(expression.left.right, BinaryExpression)
        assert expression.left.right.operator == "*"
        assert isinstance(expression.left.right.left, VariableExpression)
        assert expression.left.right.left.name == "b"
        assert isinstance(expression.left.right.right, NumberExpression)
        assert expression.left.right.right.value == 2
        assert isinstance(expression.right, VariableExpression)
        assert expression.right.name == "c"

    def test_interpret_complex_expression(self):
        parser = Parser("a + (b * 2) - c")
        expression = parser.parse()
        context = {"a": 5, "b": 3, "c": 1}
        result = expression.interpret(context)
        assert result == 10

    def test_parse_invalid_expression(self):
        parser = Parser("5 +")
        with pytest.raises(ValueError):
            parser.parse()

    def test_parse_invalid_operator(self):
        parser = Parser("5 ^ 3")
        with pytest.raises(ValueError):
            parser.parse()
            
if __name__ == "__main__":
    pytest.main()
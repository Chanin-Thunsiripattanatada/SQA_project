import pytest
from code import Expression, NumberExpression, AddExpression, SubtractExpression, MultiplyExpression, DivideExpression

def test_number_expression():
    number = NumberExpression(5)
    assert number.interpret({}) == 5, "NumberExpression should return the number it holds."

def test_add_expression():
    expr = AddExpression(NumberExpression(5), NumberExpression(3))
    assert expr.interpret({}) == 8, "AddExpression should correctly add two numbers."

def test_subtract_expression():
    expr = SubtractExpression(NumberExpression(10), NumberExpression(4))
    assert expr.interpret({}) == 6, "SubtractExpression should correctly subtract the second number from the first."

def test_multiply_expression():
    expr = MultiplyExpression(NumberExpression(7), NumberExpression(6))
    assert expr.interpret({}) == 42, "MultiplyExpression should correctly multiply two numbers."

def test_divide_expression():
    expr = DivideExpression(NumberExpression(8), NumberExpression(2))
    assert expr.interpret({}) == 4.0, "DivideExpression should correctly divide the first number by the second."

def test_divide_by_zero():
    expr = DivideExpression(NumberExpression(8), NumberExpression(0))
    with pytest.raises(ValueError, match="Cannot divide by zero"):
        expr.interpret({})

if __name__ == "__main__":
    pytest.main()

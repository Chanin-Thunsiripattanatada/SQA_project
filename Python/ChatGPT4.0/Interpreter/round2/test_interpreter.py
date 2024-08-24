# test_interpreter.py

import pytest
from interpreter import NumberExpression, AddExpression, SubtractExpression

def test_number_expression():
    expr = NumberExpression(5)
    context = {}
    assert expr.interpret(context) == 5

def test_add_expression():
    left = NumberExpression(5)
    right = NumberExpression(3)
    expr = AddExpression(left, right)
    context = {}
    assert expr.interpret(context) == 8

def test_subtract_expression():
    left = NumberExpression(5)
    right = NumberExpression(3)
    expr = SubtractExpression(left, right)
    context = {}
    assert expr.interpret(context) == 2

def test_combined_expression():
    # (5 + 3) - (2 + 1)
    expr = SubtractExpression(
        AddExpression(NumberExpression(5), NumberExpression(3)),
        AddExpression(NumberExpression(2), NumberExpression(1))
    )
    context = {}
    assert expr.interpret(context) == 5

if __name__ == "__main__":
    pytest.main()

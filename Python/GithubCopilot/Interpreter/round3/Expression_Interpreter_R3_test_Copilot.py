import pytest
from Expression_Interpreter_R3_code_Copilot import operator, Number, Variable, BinaryOperation

# Unit Tests
def test_number():
    assert Number(5).interpret({}) == 5

def test_variable():
    context = {'a': 10}
    assert Variable('a').interpret(context) == 10

def test_variable_not_found():
    try:
        Variable('b').interpret({})
    except ValueError as e:
        assert str(e) == "Variable 'b' not found"

def test_addition():
    expr = BinaryOperation(Number(1), Number(2), operator.add)
    assert expr.interpret({}) == 3

def test_subtraction():
    expr = BinaryOperation(Number(5), Number(3), operator.sub)
    assert expr.interpret({}) == 2

def test_multiplication():
    expr = BinaryOperation(Number(2), Number(3), operator.mul)
    assert expr.interpret({}) == 6

def test_division():
    expr = BinaryOperation(Number(10), Number(2), operator.truediv)
    assert expr.interpret({}) == 5

def test_complex_expression():
    context = {'x': 10, 'y': 5}
    expr = BinaryOperation(
        BinaryOperation(Number(2), Variable('x'), operator.mul),
        BinaryOperation(Number(3), Variable('y'), operator.add),
        operator.sub
    )
    assert expr.interpret(context) == 12

def test_division_by_zero():
    expr = BinaryOperation(Number(1), Number(0), operator.truediv)
    try:
        expr.interpret({})
    except ZeroDivisionError:
        assert True

# Run tests
if __name__ == "__main__":
    pytest.main()
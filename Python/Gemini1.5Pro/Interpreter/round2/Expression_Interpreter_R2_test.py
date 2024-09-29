from Expression_Interpreter_R2_code_GeminiPro import (
    Add,
    Context,
    Divide,
    Expression,
    Multiply,
    Number,
    Subtract,
    Variable,
)
import pytest

def test_number():
    number = Number(5)
    context = Context()
    assert number.interpret(context) == 5


def test_add():
    add = Add(Number(2), Number(3))
    context = Context()
    assert add.interpret(context) == 5


def test_subtract():
    subtract = Subtract(Number(5), Number(2))
    context = Context()
    assert subtract.interpret(context) == 3


def test_multiply():
    multiply = Multiply(Number(2), Number(3))
    context = Context()
    assert multiply.interpret(context) == 6


def test_divide():
    divide = Divide(Number(6), Number(2))
    context = Context()
    assert divide.interpret(context) == 3


def test_divide_by_zero():
    divide = Divide(Number(6), Number(0))
    context = Context()
    with pytest.raises(ZeroDivisionError):
        divide.interpret(context)


def test_variable():
    variable = Variable("x")
    context = Context()
    context.set_variable("x", 10)
    assert variable.interpret(context) == 10


def test_undefined_variable():
    variable = Variable("y")
    context = Context()
    with pytest.raises(NameError):
        variable.interpret(context)


def test_complex_expression():
    context = Context()
    context.set_variable("x", 10)
    context.set_variable("y", 5)

    expression = Divide(
        Add(Number(10), Variable("x")), Multiply(Variable("y"), Number(2))
    )
    assert expression.interpret(context) == 2.0
    
if __name__ == "__main__":
    pytest.main()
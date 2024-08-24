import pytest
from interpreter import Interpreter

def test_interpreter_addition():
    interpreter = Interpreter()
    result = interpreter.interpret("3 5 +")
    assert result == 8

def test_interpreter_subtraction():
    interpreter = Interpreter()
    result = interpreter.interpret("10 2 -")
    assert result == 8

def test_interpreter_complex_expression():
    interpreter = Interpreter()
    result = interpreter.interpret("10 5 + 3 -")
    assert result == 12

def test_interpreter_number_expression():
    interpreter = Interpreter()
    result = interpreter.interpret("7")
    assert result == 7

def test_interpreter_invalid_expression():
    interpreter = Interpreter()
    with pytest.raises(IndexError):
        interpreter.interpret("5 +")
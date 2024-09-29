import pytest
from Expresstion_Interpreter_R1 import interpret
def test_basic_operations():
    assert interpret("3 + 5") == 8
    assert interpret("10 - 4") == 6
    assert interpret("6 * 2") == 12
    assert interpret("8 / 2") == 4.0

def test_parentheses_operations():
    assert interpret("( 3 + 5 )") == 8
    assert interpret("( 3 + ( 2 * 5 ) )") == 13
    assert interpret("( ( 2 + 3 ) * ( 5 - 2 ) )") == 15

def test_variable_operations():
    context = {"a": 10, "b": 5}
    assert interpret("a + b", context) == 15
    assert interpret("a * b", context) == 50
    assert interpret("( a + 2 ) * b", context) == 60

def test_error_handling():
    assert interpret("a + b") == "Variable 'a' not found in context"
    assert interpret("10 / 0") == "Division by zero"
    assert interpret("a + 5", {"b": 3}) == "Variable 'a' not found in context"

def test_nested_expressions():
    assert interpret("( ( 2 * ( 3 + 2 ) ) + 5 )") == 15
    assert interpret("( ( ( 1 + 2 ) * 3 ) - ( 6 / 2 ) )") == 7.0

# To run the tests, use: pytest <filename>.py --cov=<filename>.py --cov-branch

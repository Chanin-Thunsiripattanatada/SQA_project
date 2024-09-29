import pytest
from Expresstion_Interpreter_R3_code import Number, Variable, Add, Subtract, Multiply, Divide, Parser
def test_number_interpret():
    num = Number(5)
    assert num.interpret({}) == 5

def test_variable_interpret():
    context = {'a': 3}
    var = Variable('a')
    assert var.interpret(context) == 3

def test_add_interpret():
    expr = Add(Number(2), Number(3))
    assert expr.interpret({}) == 5

def test_subtract_interpret():
    expr = Subtract(Number(5), Number(2))
    assert expr.interpret({}) == 3

def test_multiply_interpret():
    expr = Multiply(Number(3), Number(4))
    assert expr.interpret({}) == 12

def test_divide_interpret():
    expr = Divide(Number(10), Number(2))
    assert expr.interpret({}) == 5

def test_divide_by_zero():
    expr = Divide(Number(10), Number(0))
    with pytest.raises(ZeroDivisionError):
        expr.interpret({})

def test_variable_not_defined():
    var = Variable('b')
    with pytest.raises(ValueError):
        var.interpret({})

def test_parser_simple_expression():
    parser = Parser()
    expr = parser.parse("3 + 4")
    assert expr.interpret({}) == 7

def test_parser_complex_expression():
    parser = Parser()
    expr = parser.parse("(3 + 4) * 2")
    assert expr.interpret({}) == 14

def test_parser_variable_expression():
    parser = Parser()
    context = {'a': 5, 'b': 2}
    expr = parser.parse("(a + b) * (a - b)")
    assert expr.interpret(context) == 21

def test_parser_empty_expression():
    parser = Parser()
    with pytest.raises(ValueError):
        parser.parse("")

def test_parser_invalid_operator():
    parser = Parser()
    with pytest.raises(ValueError):
        parser.parse("(3 & 4)")

def test_parser_unexpected_token():
    parser = Parser()
    with pytest.raises(ValueError):
        parser.parse("3 + (4")

# Run the tests with pytest
if __name__ == "__main__":
    pytest.main()

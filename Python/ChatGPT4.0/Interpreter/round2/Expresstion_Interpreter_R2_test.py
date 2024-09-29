import pytest
from Expresstion_Interpreter_R2_code import Evaluator
@pytest.mark.parametrize("expression, variables, expected", [
    ("3 + 5", None, 8),
    ("10 - 3", None, 7),
    ("2 * 3", None, 6),
    ("8 / 4", None, 2.0),
    ("(3 + 5) * 2", None, 16),
    ("x * (7 + y)", {"x": 4, "y": 3}, 40),
    ("(x + 5) * (y - 1)", {"x": 3, "y": 6}, 40),
])
def test_valid_expressions(expression, variables, expected):
    evaluator = Evaluator(expression, variables)
    assert evaluator.evaluate() == expected

@pytest.mark.parametrize("expression, variables", [
    ("1 / 0", None),
    ("(3 + 5", None),
    ("x + 2", None),
    ("1 / (2 - 2)", None),
])
def test_invalid_expressions(expression, variables):
    evaluator = Evaluator(expression, variables)
    with pytest.raises(ValueError):
        evaluator.evaluate()

@pytest.mark.parametrize("expression, variables, expected", [
    ("x + 5", {"x": 10}, 15),
    ("x + y", {"x": 4, "y": 6}, 10),
])
def test_variable_expressions(expression, variables, expected):
    evaluator = Evaluator(expression, variables)
    assert evaluator.evaluate() == expected

@pytest.mark.parametrize("expression, variables, expected", [
    ("a + b * (c - d) / e", {"a": 3, "b": 4, "c": 10, "d": 2, "e": 2}, 15.0),
])
def test_complex_expression(expression, variables, expected):
    evaluator = Evaluator(expression, variables)
    assert evaluator.evaluate() == expected

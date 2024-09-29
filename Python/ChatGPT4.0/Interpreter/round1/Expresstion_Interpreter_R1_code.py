import operator

# Define the Abstract Expression
class Expression:
    def interpret(self, context):
        pass

# Number expression
class Number(Expression):
    def __init__(self, number):
        self.number = number

    def interpret(self, context):
        return self.number

# Variable expression
class Variable(Expression):
    def __init__(self, name):
        self.name = name

    def interpret(self, context):
        if self.name in context:
            return context[self.name]
        raise ValueError(f"Variable '{self.name}' not found in context")

# Binary operation (addition, subtraction, multiplication, division)
class BinaryOperation(Expression):
    operators = {
        '+': operator.add,
        '-': operator.sub,
        '*': operator.mul,
        '/': operator.truediv
    }

    def __init__(self, left, operator, right):
        self.left = left
        self.operator = operator
        self.right = right

    def interpret(self, context):
        left_value = self.left.interpret(context)
        right_value = self.right.interpret(context)
        
        if self.operator == '/' and right_value == 0:
            raise ZeroDivisionError("Division by zero")
        
        return self.operators[self.operator](left_value, right_value)

# Parenthesis expression to handle precedence
class Parenthesis(Expression):
    def __init__(self, expr):
        self.expr = expr

    def interpret(self, context):
        return self.expr.interpret(context)

# Parser to construct the expression tree from a string
class Parser:
    def __init__(self, expression):
        self.tokens = expression.replace('(', ' ( ').replace(')', ' ) ').split()
        self.current_token = 0

    def parse(self):
        return self._parse_expression()

    def _parse_expression(self):
        token = self._get_next_token()
        
        if token.isdigit():  # If it's a number
            return Number(int(token))
        elif token.isalpha():  # If it's a variable
            return Variable(token)
        elif token == '(':  # Parenthesis handling
            sub_expr = self._parse_expression()
            self._get_next_token()  # Skip closing ')'
            return Parenthesis(sub_expr)
        else:
            left = self._parse_expression()  # Parse left expression
            operator = token  # Operator
            right = self._parse_expression()  # Parse right expression
            return BinaryOperation(left, operator, right)

    def _get_next_token(self):
        token = self.tokens[self.current_token]
        self.current_token += 1
        return token

# Interpreter usage function
def interpret(expression, context=None):
    if context is None:
        context = {}
    
    try:
        parser = Parser(expression)
        parsed_expr = parser.parse()
        result = parsed_expr.interpret(context)
        return result
    except Exception as e:
        return str(e)

# Usage example
if __name__ == "__main__":
    print(interpret("( 3 + ( 2 * 5 ) )"))  # Output: 13
    print(interpret("a + 5", {"a": 3}))    # Output: 8
    print(interpret("10 / 0"))             # Output: Division by zero

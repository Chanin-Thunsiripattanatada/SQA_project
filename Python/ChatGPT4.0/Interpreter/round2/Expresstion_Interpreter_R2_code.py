import operator

class Expression:
    def interpret(self, context):
        raise NotImplementedError()

class Number(Expression):
    def __init__(self, value):
        self.value = value

    def interpret(self, context):
        return self.value

class Variable(Expression):
    def __init__(self, name):
        self.name = name

    def interpret(self, context):
        if self.name not in context:
            raise ValueError(f"Undefined variable: {self.name}")
        return context[self.name]

class BinaryOperation(Expression):
    def __init__(self, left, right, operation):
        self.left = left
        self.right = right
        self.operation = operation

    def interpret(self, context):
        left_value = self.left.interpret(context)
        right_value = self.right.interpret(context)
        try:
            return self.operation(left_value, right_value)
        except ZeroDivisionError:
            raise ValueError("Division by zero")

class Add(BinaryOperation):
    def __init__(self, left, right):
        super().__init__(left, right, operator.add)

class Subtract(BinaryOperation):
    def __init__(self, left, right):
        super().__init__(left, right, operator.sub)

class Multiply(BinaryOperation):
    def __init__(self, left, right):
        super().__init__(left, right, operator.mul)

class Divide(BinaryOperation):
    def __init__(self, left, right):
        super().__init__(left, right, operator.truediv)

class Parser:
    def __init__(self, expression):
        self.expression = expression
        self.index = 0

    def parse(self):
        tokens = self.tokenize(self.expression)
        return self.parse_expression(tokens)

    def tokenize(self, expr):
        import re
        return re.findall(r"\d+|[+*/()-]|[a-zA-Z_]\w*", expr)

    def parse_expression(self, tokens):
        return self.parse_term(tokens)

    def parse_term(self, tokens):
        left = self.parse_factor(tokens)

        while self.index < len(tokens) and tokens[self.index] in ('+', '-'):
            operator_token = tokens[self.index]
            self.index += 1
            right = self.parse_factor(tokens)

            if operator_token == '+':
                left = Add(left, right)
            elif operator_token == '-':
                left = Subtract(left, right)

        return left

    def parse_factor(self, tokens):
        left = self.parse_primary(tokens)

        while self.index < len(tokens) and tokens[self.index] in ('*', '/'):
            operator_token = tokens[self.index]
            self.index += 1
            right = self.parse_primary(tokens)

            if operator_token == '*':
                left = Multiply(left, right)
            elif operator_token == '/':
                left = Divide(left, right)

        return left

    def parse_primary(self, tokens):
        token = tokens[self.index]
        self.index += 1

        if token.isdigit():
            return Number(int(token))
        elif token.isalpha():
            return Variable(token)
        elif token == '(':
            expr = self.parse_expression(tokens)
            if tokens[self.index] != ')':
                raise ValueError("Mismatched parentheses")
            self.index += 1
            return expr
        else:
            raise ValueError(f"Unexpected token: {token}")

class Evaluator:
    def __init__(self, expression, variables=None):
        if variables is None:
            variables = {}
        self.variables = variables
        self.expression = expression

    def evaluate(self):
        parser = Parser(self.expression)
        expr_tree = parser.parse()
        return expr_tree.interpret(self.variables)

# if __name__ == "__main__":
#     expression1 = "(3 + 5) * 2"
#     evaluator1 = Evaluator(expression1)
#     print(f"{expression1} = {evaluator1.evaluate()}")

#     expression2 = "x * (7 + y)"
#     variables = {"x": 4, "y": 3}
#     evaluator2 = Evaluator(expression2, variables)
#     print(f"{expression2} with {variables} = {evaluator2.evaluate()}")

#     try:
#         expression3 = "1 / (2 - 2)"
#         evaluator3 = Evaluator(expression3)
#         print(evaluator3.evaluate())
#     except ValueError as e:
#         print(f"Error: {e}")
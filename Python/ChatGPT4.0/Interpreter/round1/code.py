class Expression:
    def interpret(self, context):
        pass


class NumberExpression(Expression):
    def __init__(self, number):
        self.number = number

    def interpret(self, context):
        return self.number


class AddExpression(Expression):
    def __init__(self, left_expr, right_expr):
        self.left_expr = left_expr
        self.right_expr = right_expr

    def interpret(self, context):
        return self.left_expr.interpret(context) + self.right_expr.interpret(context)


class SubtractExpression(Expression):
    def __init__(self, left_expr, right_expr):
        self.left_expr = left_expr
        self.right_expr = right_expr

    def interpret(self, context):
        return self.left_expr.interpret(context) - self.right_expr.interpret(context)


class MultiplyExpression(Expression):
    def __init__(self, left_expr, right_expr):
        self.left_expr = left_expr
        self.right_expr = right_expr

    def interpret(self, context):
        return self.left_expr.interpret(context) * self.right_expr.interpret(context)


class DivideExpression(Expression):
    def __init__(self, left_expr, right_expr):
        self.left_expr = left_expr
        self.right_expr = right_expr

    def interpret(self, context):
        denominator = self.right_expr.interpret(context)
        if denominator == 0:
            raise ValueError("Cannot divide by zero")
        return self.left_expr.interpret(context) / denominator

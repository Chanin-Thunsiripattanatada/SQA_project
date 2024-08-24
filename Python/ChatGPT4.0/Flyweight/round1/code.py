class Flyweight:
    def __init__(self, shared_state):
        self.shared_state = shared_state

    def operation(self, unique_state):
        return f"Flyweight with shared ({self.shared_state}) and unique ({unique_state}) states."


class FlyweightFactory:
    _flyweights = {}

    @staticmethod
    def get_flyweight(shared_state):
        key = str(shared_state)
        if key not in FlyweightFactory._flyweights:
            FlyweightFactory._flyweights[key] = Flyweight(shared_state)
        return FlyweightFactory._flyweights[key]

    @staticmethod
    def list_flyweights():
        return list(FlyweightFactory._flyweights.keys())

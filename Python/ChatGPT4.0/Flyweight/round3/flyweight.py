from typing import Dict

class Flyweight:
    def __init__(self, shared_state: str):
        self.shared_state = shared_state

    def operation(self, unique_state: str) -> str:
        return f"Flyweight: Displaying shared ({self.shared_state}) and unique ({unique_state}) state."


class FlyweightFactory:
    def __init__(self):
        self._flyweights: Dict[str, Flyweight] = {}

    def get_flyweight(self, shared_state: str) -> Flyweight:
        if shared_state not in self._flyweights:
            self._flyweights[shared_state] = Flyweight(shared_state)
        return self._flyweights[shared_state]

    def list_flyweights(self) -> Dict[str, Flyweight]:
        return self._flyweights


# Example usage:
# factory = FlyweightFactory()
# flyweight1 = factory.get_flyweight("shared_state_1")
# flyweight2 = factory.get_flyweight("shared_state_1")
# assert flyweight1 is flyweight2
# print(flyweight1.operation("unique_state_1"))

# flyweight.py

from typing import Dict

class Flyweight:
    def operation(self, extrinsic_state: str) -> None:
        raise NotImplementedError("Subclasses should implement this method")

class ConcreteFlyweight(Flyweight):
    def __init__(self, intrinsic_state: str) -> None:
        self._intrinsic_state = intrinsic_state

    def operation(self, extrinsic_state: str) -> None:
        print(f"ConcreteFlyweight: Intrinsic state = {self._intrinsic_state}, Extrinsic state = {extrinsic_state}")

class FlyweightFactory:
    def __init__(self) -> None:
        self._flyweights: Dict[str, Flyweight] = {}

    def get_flyweight(self, intrinsic_state: str) -> Flyweight:
        if intrinsic_state not in self._flyweights:
            self._flyweights[intrinsic_state] = ConcreteFlyweight(intrinsic_state)
        return self._flyweights[intrinsic_state]


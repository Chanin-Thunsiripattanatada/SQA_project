import pytest

from code import Flyweight, FlyweightFactory

def test_flyweight_operation():
    flyweight = Flyweight("shared_state")
    result = flyweight.operation("unique_state")
    assert result == "Flyweight: Displaying shared (shared_state) and unique (unique_state) state."

def test_flyweight_factory_creates_new_flyweight():
    factory = FlyweightFactory()
    flyweight = factory.get_flyweight("shared_state_1")
    assert flyweight.shared_state == "shared_state_1"

def test_flyweight_factory_reuses_existing_flyweight():
    factory = FlyweightFactory()
    flyweight1 = factory.get_flyweight("shared_state_1")
    flyweight2 = factory.get_flyweight("shared_state_1")
    assert flyweight1 is flyweight2

def test_flyweight_factory_lists_flyweights():
    factory = FlyweightFactory()
    factory.get_flyweight("shared_state_1")
    factory.get_flyweight("shared_state_2")
    flyweights = factory.list_flyweights()
    assert len(flyweights) == 2
    assert "shared_state_1" in flyweights
    assert "shared_state_2" in flyweights
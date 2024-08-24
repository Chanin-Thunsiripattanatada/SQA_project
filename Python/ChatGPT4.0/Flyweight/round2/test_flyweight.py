# test_flyweight.py

import pytest
from flyweight import FlyweightFactory, ConcreteFlyweight

def test_flyweight_factory():
    factory = FlyweightFactory()

    # Test that a new Flyweight is created and reused correctly
    flyweight1 = factory.get_flyweight("state1")
    flyweight2 = factory.get_flyweight("state1")
    assert flyweight1 is flyweight2  # Should be the same object

    flyweight3 = factory.get_flyweight("state2")
    assert flyweight1 is not flyweight3  # Should be different objects

def test_concrete_flyweight_operation(capfd):
    factory = FlyweightFactory()
    flyweight = factory.get_flyweight("state1")
    
    flyweight.operation("extrinsic1")
    
    captured = capfd.readouterr()
    assert "ConcreteFlyweight: Intrinsic state = state1, Extrinsic state = extrinsic1" in captured.out

def test_flyweight_creation():
    factory = FlyweightFactory()
    
    # Test creation of ConcreteFlyweight instances
    flyweight1 = factory.get_flyweight("state1")
    assert isinstance(flyweight1, ConcreteFlyweight)
    
    flyweight2 = factory.get_flyweight("state2")
    assert isinstance(flyweight2, ConcreteFlyweight)
    
    assert flyweight1 is not flyweight2  # Different states should have different instances

if __name__ == "__main__":
    pytest.main()

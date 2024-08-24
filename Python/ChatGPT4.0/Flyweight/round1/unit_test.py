import pytest
from code import FlyweightFactory

def test_flyweight_creation():
    factory = FlyweightFactory()

    # Create flyweights
    flyweight1 = factory.get_flyweight("shared_state_1")
    flyweight2 = factory.get_flyweight("shared_state_1")
    flyweight3 = factory.get_flyweight("shared_state_2")

    # Check if flyweight instances with the same shared state are the same
    assert flyweight1 is flyweight2, "Flyweights with the same shared state should be the same instance."
    assert flyweight1 is not flyweight3, "Flyweights with different shared states should be different instances."

    # Test the operation method
    assert flyweight1.operation("unique_state_1") == "Flyweight with shared (shared_state_1) and unique (unique_state_1) states."
    assert flyweight3.operation("unique_state_3") == "Flyweight with shared (shared_state_2) and unique (unique_state_3) states."

def test_flyweight_list():
    factory = FlyweightFactory()

    # Create flyweights
    factory.get_flyweight("shared_state_1")
    factory.get_flyweight("shared_state_2")

    # List flyweights
    flyweights = factory.list_flyweights()
    
    # Check if flyweights are listed correctly
    assert "shared_state_1" in flyweights, "shared_state_1 should be listed."
    assert "shared_state_2" in flyweights, "shared_state_2 should be listed."
    assert len(flyweights) == 2, "There should be exactly two flyweights listed."

if __name__ == "__main__":
    pytest.main()

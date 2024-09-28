#from text_rendering_system
from Textrender_R1 import (
    CharacterFlyweight,
    FlyweightFactory,
    Font,
    TextRenderer,
)


def test_character_flyweight_rendering():
    flyweight = CharacterFlyweight("A")
    flyweight.set_font("Arial", 12)
    flyweight.set_color("black")
    # No assertion is needed here as we are checking for no exceptions raised
    flyweight.render(10, 10)


def test_flyweight_factory_creation_and_reuse():
    factory = FlyweightFactory()
    flyweight1 = factory.get_flyweight("A")
    flyweight2 = factory.get_flyweight("A")
    assert flyweight1 is flyweight2


def test_text_renderer_rendering():
    factory = FlyweightFactory()
    renderer = TextRenderer(factory)
    font = Font("Arial", 12)
    # No assertion is needed here as we are checking for no exceptions raised
    renderer.render_text("Hello", 10, 10, font, "black")
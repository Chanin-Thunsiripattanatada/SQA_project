# from  text_rendering_system
from Textrender_R3 import FontFactory, Text, DefaultTextRenderer
import pytest


@pytest.fixture
def font_factory():
    return FontFactory()


@pytest.fixture
def default_renderer():
    return DefaultTextRenderer()


def test_font_creation(font_factory):
    font = font_factory.get_font("Arial", 12)
    assert font.name == "Arial"
    assert font.size == 12
    assert not font.bold
    assert not font.italic
    assert not font.underline


def test_font_styles(font_factory):
    font = font_factory.get_font("Arial", 12, bold=True, italic=True, underline=True)
    assert font.bold
    assert font.italic
    assert font.underline


def test_font_reuse(font_factory):
    font1 = font_factory.get_font("Arial", 12)
    font2 = font_factory.get_font("Arial", 12)
    assert font1 is font2


def test_text_creation(font_factory):
    font = font_factory.get_font("Arial", 12)
    text = Text("Hello, world!", font, "black")
    assert text.text == "Hello, world!"
    assert text.font is font
    assert text.color == "black"
    assert text.alignment == "left"
    assert text.line_spacing == 1.0
    assert text.letter_spacing == 0


def test_text_rendering(font_factory, default_renderer):
    font = font_factory.get_font("Arial", 12)
    text = Text("Hello, world!", font, "black")
    rendered_text = default_renderer.render(text)
    assert rendered_text == "Rendered: 'Hello, world!' (Arial 12pt , black, left, 1.0, 0)"
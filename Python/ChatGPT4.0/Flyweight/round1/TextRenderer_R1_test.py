import pytest

from TextRenderer_R1 import TextRenderer

@pytest.fixture
def text_renderer():
    return TextRenderer()

def test_render_text_basic(text_renderer):
    result = text_renderer.render_text("Hello World!", "Sans-serif", 12, "Bold", "Black", "Left", 2, 1, "English")
    assert result == {
        "text": "Hello World!",
        "font_family": "Sans-serif",
        "font_size": 12,
        "font_style": "Bold",
        "color": "Black",
        "alignment": "Left",
        "line_spacing": 2,
        "letter_spacing": 1,
        "language": "English"
    }

def test_render_text_multilingual(text_renderer):
    result = text_renderer.render_text("مرحبا بالعالم", "Serif", 14, "Italic", "Red", "Right", 3, 2, "Arabic")
    assert result == {
        "text": "مرحبا بالعالم",
        "font_family": "Serif",
        "font_size": 14,
        "font_style": "Italic",
        "color": "Red",
        "alignment": "Right",
        "line_spacing": 3,
        "letter_spacing": 2,
        "language": "Arabic"
    }

def test_render_text_special_characters(text_renderer):
    result = text_renderer.render_text("Hello 😊", "Monospace", 10, "Normal", "Blue", "Center", 1, 0, "Unicode")
    assert result == {
        "text": "Hello 😊",
        "font_family": "Monospace",
        "font_size": 10,
        "font_style": "Normal",
        "color": "Blue",
        "alignment": "Center",
        "line_spacing": 1,
        "letter_spacing": 0,
        "language": "Unicode"
    }

def test_font_factory_reuse(text_renderer):
    # Testing reuse of the same font
    font1 = text_renderer.font_factory.get_font("Sans-serif", 12, "Bold", "Black")
    font2 = text_renderer.font_factory.get_font("Sans-serif", 12, "Bold", "Black")
    assert font1 is font2

def test_font_factory_new_instance(text_renderer):
    # Testing a new instance with different properties
    font1 = text_renderer.font_factory.get_font("Sans-serif", 12, "Bold", "Black")
    font2 = text_renderer.font_factory.get_font("Sans-serif", 14, "Bold", "Black")
    assert font1 is not font2

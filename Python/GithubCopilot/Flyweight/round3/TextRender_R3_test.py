import pytest
from TextRender_R3 import FontFactory, TextStyleFactory, Text, TextRenderer

def test_text_rendering():
    font = FontFactory.get_font("Arial", 12, "Bold")
    style = TextStyleFactory.get_style("Red", "Center", 1.5, 0.1)
    text = Text("Hello, World!", font, style)
    renderer = TextRenderer()
    result = renderer.render(text)
    assert result == "Rendering 'Hello, World!' with font Arial, size 12, style Bold, color Red, alignment Center, line spacing 1.5, letter spacing 0.1"

def test_font_factory():
    font1 = FontFactory.get_font("Arial", 12, "Bold")
    font2 = FontFactory.get_font("Arial", 12, "Bold")
    assert font1 is font2

def test_text_style_factory():
    style1 = TextStyleFactory.get_style("Red", "Center", 1.5, 0.1)
    style2 = TextStyleFactory.get_style("Red", "Center", 1.5, 0.1)
    assert style1 is style2

def test_multilingual_support():
    font = FontFactory.get_font("Arial", 12, "Regular")
    style = TextStyleFactory.get_style("Black", "Left", 1.0, 0.0)
    text = Text("สวัสดี", font, style)
    renderer = TextRenderer()
    result = renderer.render(text)
    assert result == "Rendering 'สวัสดี' with font Arial, size 12, style Regular, color Black, alignment Left, line spacing 1.0, letter spacing 0.0"

def test_special_characters():
    font = FontFactory.get_font("Arial", 12, "Regular")
    style = TextStyleFactory.get_style("Black", "Left", 1.0, 0.0)
    text = Text("Hello, 😊", font, style)
    renderer = TextRenderer()
    result = renderer.render(text)
    assert result == "Rendering 'Hello, 😊' with font Arial, size 12, style Regular, color Black, alignment Left, line spacing 1.0, letter spacing 0.0"

if __name__ == "__main__":
    pytest.main()
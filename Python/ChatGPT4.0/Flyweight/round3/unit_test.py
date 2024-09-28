import pytest
from text_rendering_system import FlyweightFactory, TextRenderer

@pytest.fixture
def factory():
    return FlyweightFactory()

@pytest.fixture
def renderer(factory):
    return TextRenderer(factory)

def test_font_management(factory):
    font1 = factory.get_font("Sans-serif", 12, bold=True)
    font2 = factory.get_font("Sans-serif", 12, bold=True)
    font3 = factory.get_font("Serif", 14, italic=True)
    assert font1 is font2  # Flyweight: should return the same object
    assert font1 is not font3

def test_color_management(factory):
    color1 = factory.get_color("#000000")
    color2 = factory.get_color("#000000")
    color3 = factory.get_color("#FF0000")
    assert color1 is color2  # Flyweight: should return the same object
    assert color1 is not color3

def test_text_rendering(renderer):
    renderer.add_text("Hello World", "Sans-serif", 12, "#000000", bold=True, alignment="center", line_spacing=2)
    renderer.add_text("This is a test", "Serif", 14, "#FF0000", italic=True, alignment="left")
    renderer.add_text("Multilingual: こんにちは", "Monospace", 12, "#00FF00", alignment="justify")
    output = renderer.render()
    assert "Rendering 'Hello World' with Font(Sans-serif, 12, bold=True, italic=False, underline=False)" in output
    assert "Rendering 'This is a test' with Font(Serif, 14, bold=False, italic=True, underline=False)" in output
    assert "Rendering 'Multilingual: こんにちは' with Font(Monospace, 12, bold=False, italic=False, underline=False)" in output

def test_multilingual_support(renderer):
    renderer.add_text("こんにちは", "Monospace", 12, "#00FF00", alignment="justify")
    renderer.add_text("مرحبا", "Sans-serif", 12, "#000000", alignment="right")
    output = renderer.render()
    assert "Rendering 'こんにちは' with Font(Monospace, 12" in output
    assert "Rendering 'مرحبا' with Font(Sans-serif, 12" in output

def test_alignment(renderer):
    renderer.add_text("Left aligned", "Sans-serif", 12, "#000000", alignment="left")
    renderer.add_text("Center aligned", "Serif", 14, "#FF0000", alignment="center")
    renderer.add_text("Right aligned", "Monospace", 16, "#00FF00", alignment="right")
    renderer.add_text("Justified text", "Sans-serif", 12, "#000000", alignment="justify")
    output = renderer.render()
    assert "with left alignment" in output
    assert "with center alignment" in output
    assert "with right alignment" in output
    assert "with justify alignment" in output

def test_line_spacing(renderer):
    renderer.add_text("Text with line spacing", "Sans-serif", 12, "#000000", line_spacing=2)
    output = renderer.render()
    assert "line_spacing=2" in output

def test_letter_spacing(renderer):
    renderer.add_text("Text with letter spacing", "Sans-serif", 12, "#000000", letter_spacing=1)
    output = renderer.render()
    assert "letter_spacing=1" in output

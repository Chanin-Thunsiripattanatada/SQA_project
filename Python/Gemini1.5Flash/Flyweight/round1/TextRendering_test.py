#unit_test
import pytest
from TextRendering import FontFactory, TextRenderer, TextStyle

@pytest.fixture
def font_factory():
    return FontFactory()

@pytest.fixture
def text_renderer(font_factory):
    return TextRenderer(font_factory)

def test_render_sans_serif(text_renderer, font_factory):
    style = TextStyle(font_factory.get_font("sans-serif"), 16, "black", "left")
    assert text_renderer.render("Hello, World!", style) == "<span style='font-size:16px; color:black; text-align:left; line-height:1'><font face='sans-serif'>Hello, World!</font></span>"

def test_render_serif(text_renderer, font_factory):
    style = TextStyle(font_factory.get_font("serif"), 12, "red", "center")
    assert text_renderer.render("This is a serif font.", style) == "<span style='font-size:12px; color:red; text-align:center; line-height:1'><font face='serif'>This is a serif font.</font></span>"

def test_render_monospace(text_renderer, font_factory):
    style = TextStyle(font_factory.get_font("monospace"), 14, "blue", "right")
    assert text_renderer.render("Code example:", style) == "<span style='font-size:14px; color:blue; text-align:right; line-height:1'><font face='monospace'>Code example:</font></span>"

def test_render_unknown_font(text_renderer, font_factory):
    style = TextStyle(font_factory.get_font("unknown"), 16, "black", "left")
    assert text_renderer.render("This is unknown font.", style) == "<span style='font-size:16px; color:black; text-align:left; line-height:1'>This is unknown font.</span>"

def test_render_with_line_spacing(text_renderer, font_factory):
    style = TextStyle(font_factory.get_font("serif"), 12, "red", "center", line_spacing=2)
    assert text_renderer.render("Line spacing example.", style) == "<span style='font-size:12px; color:red; text-align:center; line-height:2'><font face='serif'>Line spacing example.</font></span>"

if __name__ == "__main__":
    pytest.main()

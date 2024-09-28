import pytest
from TextRendering_R2 import TextRenderer

@pytest.fixture
def renderer():
    return TextRenderer()

def test_render_text_sans_serif(renderer):
    text = "Hello, world!"
    result = renderer.render_text(text, "sans-serif", 12, "black", "left", 1, 0)
    assert result == "<span style='font-family:<font size='12'>Sans-serif: </font>; color:black; text-align:left; line-height:1; letter-spacing:0'>Hello, world!</span>"

def test_render_text_serif(renderer):
    text = "Hello, world!"
    result = renderer.render_text(text, "serif", 14, "red", "center", 1.5, 2)
    assert result == "<span style='font-family:<font size='14'>Serif: </font>; color:red; text-align:center; line-height:1.5; letter-spacing:2'>Hello, world!</span>"

def test_render_text_monospace(renderer):
    text = "Hello, world!"
    result = renderer.render_text(text, "monospace", 16, "blue", "right", 2, -1)
    assert result == "<span style='font-family:<font size='16'>Monospace: </font>; color:blue; text-align:right; line-height:2; letter-spacing:-1'>Hello, world!</span>"

def test_render_text_reuse_formatter(renderer):
    text1 = "Hello, world!"
    text2 = "Goodbye, world!"
    result1 = renderer.render_text(text1, "sans-serif", 12, "black", "left", 1, 0)
    result2 = renderer.render_text(text2, "sans-serif", 12, "black", "left", 1, 0)
    assert result1 == result2

# Branch and statement coverage
def test_get_formatter_cache(renderer):
    formatter1 = renderer.get_formatter("sans-serif", "black", "left", 1, 0)
    formatter2 = renderer.get_formatter("sans-serif", "black", "left", 1, 0)
    assert formatter1 == formatter2

def test_get_formatter_new(renderer):
    formatter1 = renderer.get_formatter("sans-serif", "black", "left", 1, 0)
    formatter2 = renderer.get_formatter("serif", "red", "center", 1.5, 2)
    assert formatter1 != formatter2

if __name__ == "__main__":
    pytest.main()
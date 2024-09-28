# test_text_rendering_system.py
import pytest
from TextRender_R1 import FontFactory, Text, TextRenderingSystem
#(AI)from font import FontFactory, Text
#(AI)from text_rendering_system import TextRenderingSystem

def test_font_factory():
    font1 = FontFactory.get_font("Sans-serif", 12, "Bold")
    font2 = FontFactory.get_font("Sans-serif", 12, "Bold")
    assert font1 is font2

def test_text_rendering():
    font = FontFactory.get_font("Serif", 14, "Italic")
    text = Text("Hello World", font, "red", "center", 1.5, 0.1)
    assert text.render() == "Rendering 'Hello World' with font Serif, size 14, style Italic, color red, alignment center, line spacing 1.5, kerning 0.1"

def test_text_rendering_system():
    trs = TextRenderingSystem()
    trs.add_text("Hello", "Sans-serif", 12, "Bold", "black", "left", 1.0, 0.0)
    trs.add_text("World", "Sans-serif", 12, "Bold", "black", "left", 1.0, 0.0)
    rendered_texts = trs.render_all()
    assert len(rendered_texts) == 2
    assert rendered_texts[0] == "Rendering 'Hello' with font Sans-serif, size 12, style Bold, color black, alignment left, line spacing 1.0, kerning 0.0"
    assert rendered_texts[1] == "Rendering 'World' with font Sans-serif, size 12, style Bold, color black, alignment left, line spacing 1.0, kerning 0.0"

if __name__ == "__main__":
    pytest.main()
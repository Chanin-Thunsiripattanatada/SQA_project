import pytest
from TextRender_R2 import Text

def test_text_rendering():
    text = Text("Hello World", "Sans-serif", 12, "Bold", "Black", "Left", 1.5, 1.0)
    assert text.render() == "Text: 'Hello World', Font: Sans-serif, Size: 12, Style: Bold, Color: Black, Alignment: Left, Line Spacing: 1.5, Kerning: 1.0"

def test_multilingual_support():
    text = Text("こんにちは", "Serif", 14, "Italic", "Red", "Center", 1.2, 1.0)
    assert text.render() == "Text: 'こんにちは', Font: Serif, Size: 14, Style: Italic, Color: Red, Alignment: Center, Line Spacing: 1.2, Kerning: 1.0"

def test_rich_text_support():
    text = Text("Bold and Italic", "Monospace", 10, "Bold Italic", "Blue", "Right", 1.0, 1.0)
    assert text.render() == "Text: 'Bold and Italic', Font: Monospace, Size: 10, Style: Bold Italic, Color: Blue, Alignment: Right, Line Spacing: 1.0, Kerning: 1.0"

def test_text_wrapping():
    text = Text("This is a long text that should wrap around", "Sans-serif", 12, "Normal", "Green", "Justify", 1.5, 1.0)
    assert text.render() == "Text: 'This is a long text that should wrap around', Font: Sans-serif, Size: 12, Style: Normal, Color: Green, Alignment: Justify, Line Spacing: 1.5, Kerning: 1.0"

def test_special_characters():
    text = Text("Math symbols: ∑, ∫, ∆", "Serif", 16, "Normal", "Black", "Left", 1.0, 1.0)
    assert text.render() == "Text: 'Math symbols: ∑, ∫, ∆', Font: Serif, Size: 16, Style: Normal, Color: Black, Alignment: Left, Line Spacing: 1.0, Kerning: 1.0"

if __name__ == "__main__":
    pytest.main()
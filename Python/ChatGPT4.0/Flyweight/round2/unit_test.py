import pytest

from text_rendering import FontFactory, TextRenderer, MultilingualText, UnicodeText


def test_font_factory():
    font1 = FontFactory.get_font("Serif", 12, "Bold", False)
    font2 = FontFactory.get_font("Serif", 12, "Bold", False)
    assert font1 is font2  # Flyweight pattern ensures reusing existing font objects


def test_text_rendering():
    renderer = TextRenderer()
    renderer.add_text("Hello World", "Sans-serif", 14, "Italic", "red", "left", 1.0, 1.5, False)
    output = renderer.render()
    assert len(output) == 1
    assert "Rendering 'Hello World'" in output[0]


def test_multilingual_text():
    font = FontFactory.get_font("Sans-serif", 16, "Regular", True)
    multilingual_text = MultilingualText("مرحبا", font, "green", "right", 1.0, 2.0)
    output = multilingual_text.render()
    assert "[Multilingual]" in output


def test_unicode_text():
    font = FontFactory.get_font("Monospace", 10, "Bold", True)
    unicode_text = UnicodeText("𝜋 ≈ 3.14", font, "blue", "center", 1.0, 1.5)
    output = unicode_text.render()
    assert "[Unicode]" in output


if __name__ == "__main__":
    pytest.main()

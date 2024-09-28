from typing import Dict, Tuple


class Font:
    def __init__(self, family: str, size: int, style: str, multilingual_support: bool):
        self.family = family
        self.size = size
        self.style = style
        self.multilingual_support = multilingual_support

    def __repr__(self):
        return f"Font(family={self.family}, size={self.size}, style={self.style}, multilingual={self.multilingual_support})"


class FontFactory:
    _fonts: Dict[Tuple[str, int, str], Font] = {}

    @classmethod
    def get_font(cls, family: str, size: int, style: str, multilingual_support: bool):
        key = (family, size, style)
        if key not in cls._fonts:
            cls._fonts[key] = Font(family, size, style, multilingual_support)
        return cls._fonts[key]


class Text:
    def __init__(self, content: str, font: Font, color: str, alignment: str, kerning: float, line_spacing: float):
        self.content = content
        self.font = font
        self.color = color
        self.alignment = alignment
        self.kerning = kerning
        self.line_spacing = line_spacing

    def render(self):
        return (f"Rendering '{self.content}' with font={self.font}, color={self.color}, "
                f"alignment={self.alignment}, kerning={self.kerning}, line_spacing={self.line_spacing}")


# Text Rendering System
class TextRenderer:
    def __init__(self):
        self.text_elements = []

    def add_text(self, content: str, font_family: str, font_size: int, font_style: str, color: str,
                 alignment: str, kerning: float, line_spacing: float, multilingual_support: bool):
        font = FontFactory.get_font(font_family, font_size, font_style, multilingual_support)
        text = Text(content, font, color, alignment, kerning, line_spacing)
        self.text_elements.append(text)

    def render(self):
        return [text.render() for text in self.text_elements]


# Supporting Multilingual (e.g., Right-to-left for Arabic)
class MultilingualText(Text):
    def render(self):
        if self.font.multilingual_support:
            return f"[Multilingual] {super().render()}"
        return super().render()


# Special Unicode Character Support
class UnicodeText(Text):
    def render(self):
        return f"[Unicode] {super().render()}"


# Testing the Flyweight pattern with pytest


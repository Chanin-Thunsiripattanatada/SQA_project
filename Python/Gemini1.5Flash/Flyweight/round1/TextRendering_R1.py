from abc import ABC, abstractmethod
from typing import Dict

class Font(ABC):
    @abstractmethod
    def render(self, text: str) -> str:
        pass

class SansSerifFont(Font):
    def render(self, text: str) -> str:
        return f"<font face='sans-serif'>{text}</feont>"

class SerifFont(Font):
    def render(self, text: str) -> str:
        return f"<font face='serif'>{text}</font>"

class MonospaceFont(Font):
    def render(self, text: str) -> str:
        return f"<font face='monospace'>{text}</font>"

class FontFactory:
    _fonts: Dict[str, Font] = {}

    def __init__(self):
        self._fonts = {
            "sans-serif": SansSerifFont(),
            "serif": SerifFont(),
            "monospace": MonospaceFont()
        }

    def get_font(self, font_name: str) -> Font:
        return self._fonts.get(font_name)

class TextStyle:
    def __init__(self, font: Font, font_size: int, color: str, alignment: str, line_spacing: int = 1):
        self.font = font
        self.font_size = font_size
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing

class TextRenderer:
    def __init__(self, font_factory: FontFactory):
        self.font_factory = font_factory

    def render(self, text: str, style: TextStyle) -> str:
        font = self.font_factory.get_font(style.font)
        if font:
            return f"<span style='font-size:{style.font_size}px; color:{style.color}; text-align:{style.alignment}; line-height:{style.line_spacing}'>{font.render(text)}</span>"
        else:
            return f"<span style='font-size:{style.font_size}px; color:{style.color}; text-align:{style.alignment}; line-height:{style.line_spacing}'>{text}</span>"
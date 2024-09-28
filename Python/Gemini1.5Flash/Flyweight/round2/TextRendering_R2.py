from abc import ABC, abstractmethod
from typing import Dict, Tuple, List

class Font(ABC):
    @abstractmethod
    def render(self, text: str, size: int) -> str:
        pass

class SansSerifFont(Font):
    def render(self, text: str, size: int) -> str:
        return f"<font size='{size}'>Sans-serif: {text}</font>"

class SerifFont(Font):
    def render(self, text: str, size: int) -> str:
        return f"<font size='{size}'>Serif: {text}</font>"

class MonospaceFont(Font):
    def render(self, text: str, size: int) -> str:
        return f"<font size='{size}'>Monospace: {text}</font>"

class TextFormatter:
    def __init__(self, font: Font, color: str, alignment: str, line_spacing: int, kerning: int):
        self.font = font
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.kerning = kerning

    def format_text(self, text: str, size: int) -> str:
        return f"<span style='font-family:{self.font.render('', size)}; color:{self.color}; text-align:{self.alignment}; line-height:{self.line_spacing}; letter-spacing:{self.kerning}'>{text}</span>"

class TextRenderer:
    def __init__(self):
        self.font_factory = {
            "sans-serif": SansSerifFont(),
            "serif": SerifFont(),
            "monospace": MonospaceFont(),
        }
        self.text_formatters: Dict[Tuple[str, str, str, int, int], TextFormatter] = {}

    def get_formatter(self, font_name: str, color: str, alignment: str, line_spacing: int, kerning: int) -> TextFormatter:
        key = (font_name, color, alignment, line_spacing, kerning)
        if key not in self.text_formatters:
            self.text_formatters[key] = TextFormatter(self.font_factory[font_name], color, alignment, line_spacing, kerning)
        return self.text_formatters[key]

    def render_text(self, text: str, font_name: str, size: int, color: str, alignment: str, line_spacing: int, kerning: int) -> str:
        formatter = self.get_formatter(font_name, color, alignment, line_spacing, kerning)
        return formatter.format_text(text, size)
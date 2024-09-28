from abc import ABC, abstractmethod
from typing import Dict, Optional

class Font(ABC):
    """Abstract base class for fonts."""

    @abstractmethod
    def render(self, text: str) -> str:
        """Renders the given text with the font's specific properties."""
        pass

class SansSerifFont(Font):
    """Represents a sans-serif font."""

    def __init__(self, size: int = 12, bold: bool = False, italic: bool = False) -> None:
        self.size = size
        self.bold = bold
        self.italic = italic

    def render(self, text: str) -> str:
        """Renders the text with sans-serif style."""
        return f"<font size='{self.size}' {'' if not self.bold else 'weight="bold"'} {'' if not self.italic else 'style="italic"'} >{text}</font>"

class SerifFont(Font):
    """Represents a serif font."""

    def __init__(self, size: int = 12, bold: bool = False, italic: bool = False) -> None:
        self.size = size
        self.bold = bold
        self.italic = italic

    def render(self, text: str) -> str:
        """Renders the text with serif style."""
        return f"<font size='{self.size}' {'' if not self.bold else 'weight="bold"'} {'' if not self.italic else 'style="italic"'} >{text}</font>"

class MonospaceFont(Font):
    """Represents a monospace font."""

    def __init__(self, size: int = 12) -> None:
        self.size = size

    def render(self, text: str) -> str:
        """Renders the text with monospace style."""
        return f"<font size='{self.size}' family='monospace'>{text}</font>"

class FontFactory:
    """Factory class for creating font objects."""

    _fonts: Dict[str, Font] = {}

    def get_font(self, font_type: str, **kwargs) -> Font:
        """Retrieves or creates a font object."""
        font_key = f"{font_type}-{kwargs}"
        if font_key not in self._fonts:
            if font_type == "sans-serif":
                self._fonts[font_key] = SansSerifFont(**kwargs)
            elif font_type == "serif":
                self._fonts[font_key] = SerifFont(**kwargs)
            elif font_type == "monospace":
                self._fonts[font_key] = MonospaceFont(**kwargs)
            else:
                raise ValueError(f"Invalid font type: {font_type}")
        return self._fonts[font_key]

class TextRenderer:
    """Text rendering system."""

    def __init__(self, font_factory: FontFactory, color: str = "black", alignment: str = "left", line_spacing: int = 1, letter_spacing: int = 0) -> None:
        self.font_factory = font_factory
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.letter_spacing = letter_spacing

    def render(self, text: str, font_type: str, **kwargs) -> str:
        """Renders the given text with specified font and formatting."""
        font: Font = self.font_factory.get_font(font_type, **kwargs)
        rendered_text = font.render(text)
        return f"<span style='color: {self.color}; text-align: {self.alignment}; line-height: {self.line_spacing}; letter-spacing: {self.letter_spacing}'>{rendered_text}</span>"

class TextRendererWithWrap(TextRenderer):
    """Text renderer with wrapping capabilities."""

    def __init__(self, font_factory: FontFactory, width: int, **kwargs) -> None:
        super().__init__(font_factory, **kwargs)
        self.width = width

    def render(self, text: str, font_type: str, **kwargs) -> str:
        """Renders the text with wrapping."""
        font: Font = self.font_factory.get_font(font_type, **kwargs)
        rendered_text = font.render(text)
        wrapped_text = self._wrap_text(rendered_text)
        return f"<span style='color: {self.color}; text-align: {self.alignment}; line-height: {self.line_spacing}; letter-spacing: {self.letter_spacing}'>{wrapped_text}</span>"

    def _wrap_text(self, text: str) -> str:
        """Wraps the text to fit within the given width."""
        words = text.split()
        lines: list[str] = []
        current_line: str = ""
        for word in words:
            if len(current_line) + len(word) > self.width:
                lines.append(current_line)
                current_line = word
            else:
                current_line += f" {word}" if current_line else word
        if current_line:
            lines.append(current_line)
        return "\n".join(lines)

class TextRendererWithRichText(TextRendererWithWrap):
    """Text renderer with rich text support."""

    def render(self, text: str, font_type: str, **kwargs) -> str:
        """Renders the text with rich text formatting."""
        font: Font = self.font_factory.get_font(font_type, **kwargs)
        rendered_text = font.render(text)
        wrapped_text = self._wrap_text(rendered_text)
        return f"<span style='color: {self.color}; text-align: {self.alignment}; line-height: {self.line_spacing}; letter-spacing: {self.letter_spacing}'>{wrapped_text}</span>"

    def _wrap_text(self, text: str) -> str:
        """Wraps the text and preserves rich text formatting."""
        words = text.split()
        lines: list[str] = []
        current_line: str = ""
        for word in words:
            if len(current_line) + len(word) > self.width:
                lines.append(current_line)
                current_line = word
            else:
                current_line += f" {word}" if current_line else word
        if current_line:
            lines.append(current_line)
        return "\n".join(lines)
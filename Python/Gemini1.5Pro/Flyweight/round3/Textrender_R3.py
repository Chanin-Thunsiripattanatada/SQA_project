from abc import ABC, abstractmethod

class Font:
    def __init__(self, name, size, bold=False, italic=False, underline=False):
        self.name = name
        self.size = size
        self.bold = bold
        self.italic = italic
        self.underline = underline

    def __str__(self):
        style = ""
        if self.bold:
            style += "Bold"
        if self.italic:
            style += "Italic"
        if self.underline:
            style += "Underline"
        return f"{self.name} {self.size}pt {style}"


class FontFactory:
    _fonts = {}

    @classmethod
    def get_font(cls, name, size, bold=False, italic=False, underline=False):
        font_key = (name, size, bold, italic, underline)
        if font_key not in cls._fonts:
            cls._fonts[font_key] = Font(name, size, bold, italic, underline)
        return cls._fonts[font_key]


class Text:
    def __init__(self, text, font: Font, color, alignment="left", line_spacing=1.0, letter_spacing=0):
        self.text = text
        self.font = font
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.letter_spacing = letter_spacing

    def __str__(self):
        return f"'{self.text}' ({self.font}, {self.color}, {self.alignment}, {self.line_spacing}, {self.letter_spacing})"


class TextRenderer(ABC):
    @abstractmethod
    def render(self, text: Text):
        pass


class DefaultTextRenderer(TextRenderer):
    def render(self, text: Text):
        return f"Rendered: {text}"


if __name__ == "__main__":
    font_factory = FontFactory()
    font1 = font_factory.get_font("Arial", 12)
    font2 = font_factory.get_font("Arial", 12, bold=True)
    text1 = Text("Hello, world!", font1, "black")
    text2 = Text("This is a test.", font2, "red", alignment="center")
    renderer = DefaultTextRenderer()
    print(renderer.render(text1))
    print(renderer.render(text2))
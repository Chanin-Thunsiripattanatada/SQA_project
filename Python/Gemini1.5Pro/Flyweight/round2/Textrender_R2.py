from abc import ABC, abstractmethod


class Font:
    def __init__(self, name, size, style):
        self.name = name
        self.size = size
        self.style = style

    def __eq__(self, other):
        return (
            self.name == other.name
            and self.size == other.size
            and self.style == other.style
        )
    
    # def __hash__(self):
    #     return hash((self.name, self.size, self.style)) ปรับมือจะ test pass เมื่อมีส่วนนี้


class FontFactory:
    def __init__(self):
        self._fonts = {}

    def get_font(self, name, size, style):
        font = Font(name, size, style)
        if font not in self._fonts:
            self._fonts[font] = font
        return self._fonts[font]


class Text:
    def __init__(self, text, font, color, alignment, line_spacing, letter_spacing):
        self.text = text
        self.font = font
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.letter_spacing = letter_spacing


class TextRenderer(ABC):
    @abstractmethod
    def render(self, text):
        pass


class FlyweightTextRenderer(TextRenderer):
    def __init__(self):
        self._font_factory = FontFactory()

    def render(self, text):
        font = self._font_factory.get_font(
            text.font.name, text.font.size, text.font.style
        )
        # Logic to render text with given font, color, alignment, etc.
        # This is a simplified example, actual implementation would be more complex
        print(
            f"Rendering text: '{text.text}' with font: {font.name}, size: {font.size}, "
            f"style: {font.style}, color: {text.color}, alignment: {text.alignment}"
        )
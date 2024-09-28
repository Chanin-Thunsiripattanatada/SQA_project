class Font:
    def __init__(self, name, size, style):
        self.name = name
        self.size = size
        self.style = style

class TextStyle:
    def __init__(self, color, alignment, line_spacing, letter_spacing):
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.letter_spacing = letter_spacing

class FontFactory:
    _fonts = {}

    @classmethod
    def get_font(cls, name, size, style):
        key = (name, size, style)
        if key not in cls._fonts:
            cls._fonts[key] = Font(name, size, style)
        return cls._fonts[key]

class TextStyleFactory:
    _styles = {}

    @classmethod
    def get_style(cls, color, alignment, line_spacing, letter_spacing):
        key = (color, alignment, line_spacing, letter_spacing)
        if key not in cls._styles:
            cls._styles[key] = TextStyle(color, alignment, line_spacing, letter_spacing)
        return cls._styles[key]

class Text:
    def __init__(self, content, font, style):
        self.content = content
        self.font = font
        self.style = style

class TextRenderer:
    def render(self, text):
        # Simulate rendering process
        return f"Rendering '{text.content}' with font {text.font.name}, size {text.font.size}, style {text.font.style}, color {text.style.color}, alignment {text.style.alignment}, line spacing {text.style.line_spacing}, letter spacing {text.style.letter_spacing}"

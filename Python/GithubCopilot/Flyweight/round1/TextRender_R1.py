# font.py
class Font:
    def __init__(self, font_type, size, style):
        self.font_type = font_type
        self.size = size
        self.style = style

class FontFactory:
    _fonts = {}

    @classmethod
    def get_font(cls, font_type, size, style):
        key = (font_type, size, style)
        if key not in cls._fonts:
            cls._fonts[key] = Font(font_type, size, style)
        return cls._fonts[key]

class Text:
    def __init__(self, content, font, color, alignment, line_spacing, kerning):
        self.content = content
        self.font = font
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.kerning = kerning

    def render(self):
        # Simulate rendering process
        return f"Rendering '{self.content}' with font {self.font.font_type}, size {self.font.size}, style {self.font.style}, color {self.color}, alignment {self.alignment}, line spacing {self.line_spacing}, kerning {self.kerning}"

# text_rendering_system.py
class TextRenderingSystem:
    def __init__(self):
        self.texts = []

    def add_text(self, content, font_type, size, style, color, alignment, line_spacing, kerning):
        font = FontFactory.get_font(font_type, size, style)
        text = Text(content, font, color, alignment, line_spacing, kerning)
        self.texts.append(text)

    def render_all(self):
        return [text.render() for text in self.texts]
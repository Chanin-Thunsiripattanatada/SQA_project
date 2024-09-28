class Font:
    def __init__(self, font_type, size, style):
        self.font_type = font_type
        self.size = size
        self.style = style

    def display(self, text, color, alignment, line_spacing, kerning):
        # Simulate text rendering with given properties
        return f"Text: '{text}', Font: {self.font_type}, Size: {self.size}, Style: {self.style}, Color: {color}, Alignment: {alignment}, Line Spacing: {line_spacing}, Kerning: {kerning}"

class FontFactory:
    _fonts = {}

    @classmethod
    def get_font(cls, font_type, size, style):
        key = (font_type, size, style)
        if key not in cls._fonts:
            cls._fonts[key] = Font(font_type, size, style)
        return cls._fonts[key]

class Text:
    def __init__(self, content, font_type, size, style, color, alignment, line_spacing, kerning):
        self.content = content
        self.font = FontFactory.get_font(font_type, size, style)
        self.color = color
        self.alignment = alignment
        self.line_spacing = line_spacing
        self.kerning = kerning

    def render(self):
        return self.font.display(self.content, self.color, self.alignment, self.line_spacing, self.kerning)
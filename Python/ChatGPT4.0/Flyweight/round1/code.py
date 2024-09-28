from typing import Dict


class FontFlyweight:
    def __init__(self, font_family: str, font_size: int, font_style: str, color: str):
        self.font_family = font_family
        self.font_size = font_size
        self.font_style = font_style
        self.color = color

    def render(self, text: str, alignment: str, line_spacing: int, letter_spacing: int, language: str):
        print(f"Rendering '{text}' with font: {self.font_family}, size: {self.font_size}, style: {self.font_style}, color: {self.color}")
        print(f"Alignment: {alignment}, Line Spacing: {line_spacing}, Letter Spacing: {letter_spacing}, Language: {language}")
        # Additional rich text support, text wrapping, and special character handling can be added here
        return {
            "text": text,
            "font_family": self.font_family,
            "font_size": self.font_size,
            "font_style": self.font_style,
            "color": self.color,
            "alignment": alignment,
            "line_spacing": line_spacing,
            "letter_spacing": letter_spacing,
            "language": language
        }


class FontFactory:
    _fonts: Dict[str, FontFlyweight] = {}

    @classmethod
    def get_font(cls, font_family: str, font_size: int, font_style: str, color: str) -> FontFlyweight:
        key = f"{font_family}_{font_size}_{font_style}_{color}"
        if key not in cls._fonts:
            cls._fonts[key] = FontFlyweight(font_family, font_size, font_style, color)
        return cls._fonts[key]


class TextRenderer:
    def __init__(self):
        self.font_factory = FontFactory()

    def render_text(self, text: str, font_family: str, font_size: int, font_style: str, color: str, alignment: str, line_spacing: int, letter_spacing: int, language: str):
        font = self.font_factory.get_font(font_family, font_size, font_style, color)
        return font.render(text, alignment, line_spacing, letter_spacing, language)


# Example usage
renderer = TextRenderer()
renderer.render_text("Hello World!", "Sans-serif", 12, "Bold", "Black", "Left", 2, 1, "English")
renderer.render_text("สวัสดีโลก", "Serif", 14, "Italic", "Red", "Right", 3, 2, "Thai")

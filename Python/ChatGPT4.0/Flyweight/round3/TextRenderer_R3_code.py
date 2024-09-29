from typing import Dict, Tuple

# Flyweight classes
class Font:
    def __init__(self, name: str, size: int, bold: bool = False, italic: bool = False, underline: bool = False):
        self.name = name
        self.size = size
        self.bold = bold
        self.italic = italic
        self.underline = underline

    def __repr__(self):
        return f"Font({self.name}, {self.size}, bold={self.bold}, italic={self.italic}, underline={self.underline})"

class Color:
    def __init__(self, color_code: str):
        self.color_code = color_code

    def __repr__(self):
        return f"Color({self.color_code})"

# Flyweight Factory to manage shared fonts and colors
class FlyweightFactory:
    def __init__(self):
        self._fonts: Dict[Tuple[str, int, bool, bool, bool], Font] = {}
        self._colors: Dict[str, Color] = {}

    def get_font(self, name: str, size: int, bold: bool = False, italic: bool = False, underline: bool = False) -> Font:
        key = (name, size, bold, italic, underline)
        if key not in self._fonts:
            self._fonts[key] = Font(name, size, bold, italic, underline)
        return self._fonts[key]

    def get_color(self, color_code: str) -> Color:
        if color_code not in self._colors:
            self._colors[color_code] = Color(color_code)
        return self._colors[color_code]

# Text Rendering system
class TextRenderer:
    def __init__(self, factory: FlyweightFactory):
        self.factory = factory
        self.text_objects = []

    def add_text(self, text: str, font_name: str, size: int, color_code: str, bold: bool = False, italic: bool = False, underline: bool = False, alignment: str = "left", line_spacing: int = 1, letter_spacing: int = 0):
        font = self.factory.get_font(font_name, size, bold, italic, underline)
        color = self.factory.get_color(color_code)
        self.text_objects.append({
            "text": text,
            "font": font,
            "color": color,
            "alignment": alignment,
            "line_spacing": line_spacing,
            "letter_spacing": letter_spacing
        })

    def render(self):
        rendered_output = []
        for obj in self.text_objects:
            rendered_output.append(f"Rendering '{obj['text']}' with {obj['font']} in {obj['color']} with {obj['alignment']} alignment.")
        return "\n".join(rendered_output)

# Usage example
if __name__ == "__main__":
    factory = FlyweightFactory()
    renderer = TextRenderer(factory)

    renderer.add_text("Hello World", "Sans-serif", 12, "#000000", bold=True, alignment="center", line_spacing=2)
    renderer.add_text("This is a test", "Serif", 14, "#FF0000", italic=True, alignment="left")
    renderer.add_text("Multilingual: こんにちは", "Monospace", 12, "#00FF00", alignment="justify")
    print(renderer.render())

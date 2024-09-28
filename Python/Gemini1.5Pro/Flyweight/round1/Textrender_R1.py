from abc import ABC, abstractmethod


class Flyweight(ABC):
    def __init__(self):
        self._font = None
        self._size = None
        self._color = None

    @abstractmethod
    def render(self, text: str, x: int, y: int):
        pass

    def set_font(self, family: str, size: int):
        self._font = Font(family, size)

    def set_color(self, color: str):
        self._color = color


class Font:
    def __init__(self, family: str, size: int):
        self.family = family
        self.size = size


class CharacterFlyweight(Flyweight):
    def __init__(self, char: str):
        super().__init__()
        self._char = char

    def render(self, x: int, y: int):
        print(
            f"Rendering character '{self._char}' with {self._font.family} {self._font.size}pt font in {self._color} at ({x}, {y})"
        )


class FlyweightFactory:
    def __init__(self):
        self._flyweights = {}

    def get_flyweight(self, char: str) -> Flyweight:
        if char not in self._flyweights:
            self._flyweights[char] = CharacterFlyweight(char)
        return self._flyweights[char]


class TextRenderer:
    def __init__(self, factory: FlyweightFactory):
        self._factory = factory

    def render_text(self, text: str, x: int, y: int, font: Font, color: str):
        for char in text:
            flyweight = self._factory.get_flyweight(char)
            flyweight.set_font(font.family, font.size)
            flyweight.set_color(color)
            flyweight.render(x, y)
            x += font.size


# Example usage
factory = FlyweightFactory()
renderer = TextRenderer(factory)

font = Font("Arial", 12)
renderer.render_text("Hello, World!", 10, 10, font, "black")
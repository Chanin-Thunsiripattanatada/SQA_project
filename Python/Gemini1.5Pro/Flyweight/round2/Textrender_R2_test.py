import pytest

#from text_rendering_system
from Textrender_R2 import (
    FlyweightTextRenderer,
    Text,
    Font,
)


@pytest.fixture
def text_renderer():
    return FlyweightTextRenderer()


@pytest.mark.parametrize(
    "text1, text2, expected_result",
    [
        (
            Text("Hello", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            Text("Hello", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            True,
        ),
        (
            Text("Hello", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            Text("World", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            False,
        ),
        (
            Text("Hello", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            Text("Hello", Font("Times New Roman", 12, "normal"), "black", "left", 1.0, 1.0),
            False,
        ),
        (
            Text("Hello", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            Text("Hello", Font("Arial", 14, "normal"), "black", "left", 1.0, 1.0),
            False,
        ),
        (
            Text("Hello", Font("Arial", 12, "normal"), "black", "left", 1.0, 1.0),
            Text("Hello", Font("Arial", 12, "bold"), "black", "left", 1.0, 1.0),
            False,
        ),
    ],
)
def test_flyweight_text_renderer(text_renderer, text1, text2, expected_result, capsys):
    text_renderer.render(text1)
    captured1 = capsys.readouterr()

    text_renderer.render(text2)
    captured2 = capsys.readouterr()

    if expected_result:
        assert captured1.out == captured2.out
    else:
        assert captured1.out != captured2.out
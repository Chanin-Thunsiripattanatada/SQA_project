from TextRendering_R3 import FontFactory,TextRenderer,SansSerifFont,SerifFont,MonospaceFont,TextRendererWithRichText,TextRendererWithWrap

def test_font_factory():
    font_factory = FontFactory()
    assert isinstance(font_factory.get_font("sans-serif"), SansSerifFont)
    assert isinstance(font_factory.get_font("serif"), SerifFont)
    assert isinstance(font_factory.get_font("monospace"), MonospaceFont)

def test_font_rendering():
    font_factory = FontFactory()
    renderer = TextRenderer(font_factory)
    assert renderer.render("Hello, world!", "sans-serif") == "<span style='color: black; text-align: left; line-height: 1; letter-spacing: 0'><font size='12' >Hello, world!</font></span>"
    assert renderer.render("Hello, world!", "serif", size=16, bold=True) == "<span style='color: black; text-align: left; line-height: 1; letter-spacing: 0'><font size='16' weight='bold' >Hello, world!</font></span>"
    assert renderer.render("Hello, world!", "monospace") == "<span style='color: black; text-align: left; line-height: 1; letter-spacing: 0'><font size='12' family='monospace'>Hello, world!</font></span>"

def test_text_renderer_with_wrap():
    font_factory = FontFactory()
    renderer = TextRendererWithWrap(font_factory, width=10)
    assert renderer.render("Hello, world!", "sans-serif") == "<span style='color: black; text-align: left; line-height: 1; letter-spacing: 0'><font size='12' >Hello,</font>\n<font size='12' >world!</font></span>"

def test_text_renderer_with_rich_text():
    font_factory = FontFactory()
    renderer = TextRendererWithRichText(font_factory, width=10)
    assert renderer.render("<b>Hello</b>, <i>world</i>!", "sans-serif") == "<span style='color: black; text-align: left; line-height: 1; letter-spacing: 0'><font size='12' ><b>Hello</b>,</font>\n<font size='12' ><i>world</i>!</font></span>"

if __name__ == "__main__":
    font_factory = FontFactory()
    renderer = TextRendererWithRichText(font_factory, width=20, color="blue", alignment="center")
    text = "This is a sample text with <b>bold</b> and <i>italic</i> formatting."
    print(renderer.render(text, "serif", size=14, bold=True))


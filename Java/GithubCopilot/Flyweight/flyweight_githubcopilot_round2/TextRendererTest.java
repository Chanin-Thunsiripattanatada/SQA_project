package flyweight_githubcopilot_round2;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TextRendererTest {

    @Test
    public void testRenderText() {
        TextRenderer renderer = new TextRenderer();
        TextStyle font = TextStyleFactory.getFont("Arial", 12, true, false, false);
        TextStyle color = TextStyleFactory.getColor("Red");
        TextStyle alignment = TextStyleFactory.getAlignment("Center");

        renderer.renderText("Hello, World!", font, color, alignment);
    }

    @Test
    public void testFontReuse() {
        TextStyle font1 = TextStyleFactory.getFont("Arial", 12, true, false, false);
        TextStyle font2 = TextStyleFactory.getFont("Arial", 12, true, false, false);

        assertSame(font1, font2);
    }

    @Test
    public void testColorReuse() {
        TextStyle color1 = TextStyleFactory.getColor("Red");
        TextStyle color2 = TextStyleFactory.getColor("Red");

        assertSame(color1, color2);
    }

    @Test
    public void testAlignmentReuse() {
        TextStyle alignment1 = TextStyleFactory.getAlignment("Center");
        TextStyle alignment2 = TextStyleFactory.getAlignment("Center");

        assertSame(alignment1, alignment2);
    }
}
package round2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlyweightTextRenderingTest {

    @Test
    void testFontFactory() {
        Font font1 = FontFactory.getFont("Arial", 12, false, false);
        Font font2 = FontFactory.getFont("Arial", 12, false, false);
        assertSame(font1, font2); // Ensure the same font object is returned

        Font font3 = FontFactory.getFont("Times New Roman", 14, true, false);
        assertNotSame(font1, font3); // Different font properties should result in different objects
    }

    @Test
    void testTextRenderer() {
        TextRenderer renderer1 = TextRendererFactory.getRenderer();
        TextRenderer renderer2 = TextRendererFactory.getRenderer();
        assertSame(renderer1, renderer2); // Ensure the same renderer object is returned
    }

    @Test
    void testTextRendering() {
        TextRenderer renderer = TextRendererFactory.getRenderer();
        TextFormat format = new TextFormat("Arial", 12, true, false, Color.BLUE);
        renderer.render("Hello, Flyweight!", 10, 20, Color.BLUE, format); 
        // Output should reflect the provided text, position, color, and format
    }

    // Additional tests for branch and statement coverage:

    @Test
    void testTextFormatToString() {
        TextFormat format = new TextFormat("Arial", 12, true, false, Color.BLUE);
        assertEquals("Font: Arial, Size: 12, Bold: true, Italic: false, Color: java.awt.Color[r=0,g=0,b=255]", format.toString());
    }

    @Test
    void testFontFactoryWithDifferentStyles() {
        Font font1 = FontFactory.getFont("Arial", 12, true, false);
        Font font2 = FontFactory.getFont("Arial", 12, false, true);
        Font font3 = FontFactory.getFont("Arial", 12, true, true);
        assertNotSame(font1, font2);
        assertNotSame(font1, font3);
        assertNotSame(font2, font3);
    }
}
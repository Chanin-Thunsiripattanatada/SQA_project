
import org.junit.jupiter.api.Test;

import TextRenderingSystem.TextRenderer;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;


class TextRenderingSystemTest {

    @Test
    void testRender() {
        TextRenderer renderer = new TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem.TextAlignment.LEFT);
        renderer.render();
        assertEquals("Hello, World!", renderer.text);
        assertEquals(new Font("Arial", Font.BOLD, 12), renderer.font);
        assertEquals(Color.BLACK, renderer.color);
        assertEquals(TextRenderingSystem.TextAlignment.LEFT, renderer.alignment);
    }

    @Test
    void testSetLineSpacing() {
        TextRenderer renderer = new TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem.TextAlignment.LEFT);
        renderer.setLineSpacing(1.5f);
        assertEquals(1.5f, renderer.lineSpacing);
    }

    @Test
    void testSetLetterSpacing() {
        TextRenderer renderer = new TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem.TextAlignment.LEFT);
        renderer.setLetterSpacing(0.5f);
        assertEquals(0.5f, renderer.letterSpacing);
    }

    @Test
    void testGetFont() {
        TextRenderer renderer = new TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem.TextAlignment.LEFT);
        assertEquals(new Font("Arial", Font.BOLD, 12), renderer.getFont());
    }

    @Test
    void testFontCache() {
        TextRenderer renderer1 = new TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem.TextAlignment.LEFT);
        TextRenderer renderer2 = new TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem.TextAlignment.LEFT);
        assertSame(renderer1.getFont(), renderer2.getFont());
    }
}

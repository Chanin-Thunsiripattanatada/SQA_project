package round1;

import org.junit.jupiter.api.Test;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class TextRenderingSystem_R1Test {

    @Test
    void testRender() {
        TextRenderingSystem_R1.TextRenderer renderer = new TextRenderingSystem_R1.TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem_R1.TextAlignment.LEFT);
        renderer.render();
        assertEquals("Hello, World!", renderer.text);
        assertEquals(new Font("Arial", Font.BOLD, 12), renderer.font);
        assertEquals(Color.BLACK, renderer.color);
        assertEquals(TextRenderingSystem_R1.TextAlignment.LEFT, renderer.alignment);
    }

    @Test
    void testSetLineSpacing() {
        TextRenderingSystem_R1.TextRenderer renderer = new TextRenderingSystem_R1.TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem_R1.TextAlignment.LEFT);
        renderer.setLineSpacing(1.5f);
        assertEquals(1.5f, renderer.lineSpacing, 0.001f);
    }

    @Test
    void testSetLetterSpacing() {
        TextRenderingSystem_R1.TextRenderer renderer = new TextRenderingSystem_R1.TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem_R1.TextAlignment.LEFT);
        renderer.setLetterSpacing(0.5f);
        assertEquals(0.5f, renderer.letterSpacing, 0.001f);
    }

    @Test
    void testGetFont() {
        TextRenderingSystem_R1.TextRenderer renderer = new TextRenderingSystem_R1.TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem_R1.TextAlignment.LEFT);
        assertEquals(new Font("Arial", Font.BOLD, 12), renderer.getFont());
    }

    @Test
    void testFontCache() {
        TextRenderingSystem_R1.TextRenderer renderer1 = new TextRenderingSystem_R1.TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem_R1.TextAlignment.LEFT);
        TextRenderingSystem_R1.TextRenderer renderer2 = new TextRenderingSystem_R1.TextRenderer("Hello, World!", new Font("Arial", Font.BOLD, 12), Color.BLACK, TextRenderingSystem_R1.TextAlignment.LEFT);
        assertSame(renderer1.getFont(), renderer2.getFont());
    }
}

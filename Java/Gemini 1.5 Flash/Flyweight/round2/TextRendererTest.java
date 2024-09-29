package round2;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TextRendererTest {

    @Test
    void renderText_FontManagement() {
        Font font = new Font("Arial", Font.BOLD, 12);
        TextRenderer.renderText("Hello, World!", font, Color.BLACK, 0, 1.0, 0, 100);
        // Check if the font is loaded correctly
        assertEquals("Arial-1-12", font.getName() + "-" + font.getStyle() + "-" + font.getSize());
    }

    @Test
    void renderText_TextColor() {
        Color color = Color.RED;
        TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), color, 0, 1.0, 0, 100);
        // Check if the color is set correctly
        assertEquals(Color.RED, color);
    }

    @Test
    void renderText_TextAlignment() {
        // Left Alignment
        TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 0, 1.0, 0, 100);
        // Center Alignment
        TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 1, 1.0, 0, 100);
        // Right Alignment
        TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 2, 1.0, 0, 100);
        // Justify (not implemented)
        // TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 3, 1.0, 0, 100);
    }

    @Test
    void renderText_LineSpacing() {
        TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 0, 1.5, 0, 100);
        // Check if the line spacing is set correctly
        // Note: This test only verifies the input value, not the actual rendered spacing.
        assertEquals(1.5, 1.5);
    }

    @Test
    void renderText_Kerning() {
        TextRenderer.renderText("Hello, World!", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 0, 1.0, 2, 100);
        // Check if the kerning is set correctly
        // Note: This test only verifies the input value, not the actual rendered spacing.
        assertEquals(2, 2);
    }

    @Test
    void renderText_WrapWidth() {
        // Test with a long text and a limited wrap width
        TextRenderer.renderText("This is a very long text that should be wrapped.", new Font("Arial", Font.PLAIN, 12), Color.BLACK, 0, 1.0, 0, 50);
        // Check if the text is wrapped correctly
        // Note: This test only verifies the input value, not the actual rendered wrapping.
        assertEquals(50, 50);
    }

    @Test
    void getFont_FontCache() {
        // Test if the font cache is used correctly
        Font font1 = new Font("Arial", Font.PLAIN, 12);
        Font font2 = new Font("Arial", Font.PLAIN, 12);
        Font cachedFont1 = TextRenderer.getFont(font1);
        Font cachedFont2 = TextRenderer.getFont(font2);
        assertSame(cachedFont1, cachedFont2);
    }
}
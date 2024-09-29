package round3;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TextRenderingSystemTest {

    @Test
    void testTextRendering() {
        TextRenderingSystem textRenderingSystem = new TextRenderingSystem();

        // Sample text and format
        String text = "Hello, World!";
        String fontFamily = "Arial";
        int fontSize = 12;
        boolean bold = true;
        boolean italic = false;
        boolean underline = true;
        Color color = Color.RED;

        // Create a Graphics object (for testing purposes, we'll use a stub)
        Graphics g = new Graphics() {
            @Override
            public void drawLine(int x1, int y1, int x2, int y2) {
                // Verify underline drawing
                assertTrue(x1 < x2); // Assuming text is drawn left to right
            }

            @Override
            public void drawString(String str, int x, int y) {
                // Verify text content and position
                assertEquals(text, str);
                assertTrue(x >= 0);
                assertTrue(y >= 0);
            }

            // Other Graphics methods (not used in this test) can be left empty
            @Override
            public void setColor(Color c) {}
            // ... other methods
            @Override
            public void setFont(Font font) {
                // Verify font settings
                assertEquals(fontFamily, font.getName());
                assertEquals(fontSize, font.getSize());
                assertEquals(Font.BOLD, font.getStyle());
            }
            // ... other methods
        };

        // Render the text
        textRenderingSystem.renderText(text, fontFamily, fontSize, bold, italic, underline, color, 10, 20, g);
    }
}
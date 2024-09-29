// JUnit Test
package flyweight_gemini_flash_round1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

class TextRenderingSystemTest {

    @Test
    void testSetFont() {
        TextRenderingSystem system = new TextRenderingSystem("Hello");
        system.setFont("Serif");
        assertEquals(new Font("Times New Roman", Font.PLAIN, 12), system.font);

        system.setFont("UnknownFont");
        assertEquals(new Font("Arial", Font.PLAIN, 12), system.font); // Default to Sans-serif
    }

    @Test
    void testSetFontSize() {
        TextRenderingSystem system = new TextRenderingSystem("Hello");
        system.setFontSize(16);
        assertEquals(16, system.fontSize);
        assertEquals(new Font("Arial", Font.PLAIN, 16), system.font);
    }

    @Test
    void testSetColor() {
        TextRenderingSystem system = new TextRenderingSystem("Hello");
        system.setColor(Color.RED);
        assertEquals(Color.RED, system.color);
    }

    @Test
    void testSetAlignment() {
        TextRenderingSystem system = new TextRenderingSystem("Hello");
        system.setAlignment(TextRenderingSystem.TextAlign.CENTER);
        assertEquals(TextRenderingSystem.TextAlign.CENTER, system.alignment);
    }

    @Test
    void testSetLineSpacing() {
        TextRenderingSystem system = new TextRenderingSystem("Hello");
        system.setLineSpacing(5);
        assertEquals(5, system.lineSpacing);
    }

    @Test
    void testSetLetterSpacing() {
        TextRenderingSystem system = new TextRenderingSystem("Hello");
        system.setLetterSpacing(2);
        assertEquals(2, system.letterSpacing);
    }

    @Test
    void testRender() {
        // Mock Graphics2D object for testing
        Graphics2D g2d = new MockGraphics2D();
        TextRenderingSystem system = new TextRenderingSystem("Hello");

        system.render(g2d, 10, 20);
        // Assertions for font, color, alignment, etc. can be added here, but require a more complex mock
    }

    static class MockGraphics2D extends Graphics2D {
        // Implement necessary methods for testing.
        // Example: 
        @Override
        public void drawString(String str, int x, int y) {
            // Check if the string is drawn at the expected coordinates
            // Implement assertions or logic for testing
        }
        // ... other methods required for testing
    }
}
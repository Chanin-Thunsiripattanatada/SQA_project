package flyweight_gemini_flash_round3;
// JUnit Test Cases
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TextRendererTest {

    @Test
    public void testRenderText_ValidInput() {
        // Valid inputs
        Font font = new Font("Arial", Font.PLAIN, 12);
        Color color = Color.BLACK;
        int alignment = 0; // Left Alignment
        double lineSpacing = 1.5;
        double letterSpacing = 0.5;
        String language = "en";
        boolean richText = false;

        assertDoesNotThrow(() -> TextRenderer.renderText("Hello World!", font, color, alignment, lineSpacing, letterSpacing, language, richText));
    }

    @Test
    public void testRenderText_InvalidAlignment() {
        // Invalid alignment
        Font font = new Font("Arial", Font.PLAIN, 12);
        Color color = Color.BLACK;
        int alignment = -1; // Invalid Alignment
        double lineSpacing = 1.5;
        double letterSpacing = 0.5;
        String language = "en";
        boolean richText = false;

        assertThrows(IllegalArgumentException.class, () -> TextRenderer.renderText("Hello World!", font, color, alignment, lineSpacing, letterSpacing, language, richText));
    }

    @Test
    public void testRenderText_InvalidLineSpacing() {
        // Invalid line spacing
        Font font = new Font("Arial", Font.PLAIN, 12);
        Color color = Color.BLACK;
        int alignment = 0;
        double lineSpacing = 0.5; // Invalid Line Spacing
        double letterSpacing = 0.5;
        String language = "en";
        boolean richText = false;

        assertThrows(IllegalArgumentException.class, () -> TextRenderer.renderText("Hello World!", font, color, alignment, lineSpacing, letterSpacing, language, richText));
    }

    @Test
    public void testRenderText_InvalidLetterSpacing() {
        // Invalid letter spacing
        Font font = new Font("Arial", Font.PLAIN, 12);
        Color color = Color.BLACK;
        int alignment = 0;
        double lineSpacing = 1.5;
        double letterSpacing = -0.5; // Invalid Letter Spacing
        String language = "en";
        boolean richText = false;

        assertThrows(IllegalArgumentException.class, () -> TextRenderer.renderText("Hello World!", font, color, alignment, lineSpacing, letterSpacing, language, richText));
    }

    @Test
    public void testRenderText_InvalidLanguage() {
        // Invalid language
        Font font = new Font("Arial", Font.PLAIN, 12);
        Color color = Color.BLACK;
        int alignment = 0;
        double lineSpacing = 1.5;
        double letterSpacing = 0.5;
        String language = "invalid"; // Invalid Language
        boolean richText = false;

        assertThrows(IllegalArgumentException.class, () -> TextRenderer.renderText("Hello World!", font, color, alignment, lineSpacing, letterSpacing, language, richText));
    }

    // Add more test cases for different scenarios
}
package flyweight_chatgpt4o_round3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextRenderingSystemTest {

    private TextRenderingSystem system;

    @BeforeEach
    public void setUp() {
        system = new TextRenderingSystem();
    }

    @Test
    public void testRenderTextWithDifferentFonts() {
        system.renderText("Hello World", "Serif", false, false, false, 14, "Black", "Left", 1.5f, 0.0f);
        system.renderText("Hello World", "Sans-serif", true, false, false, 16, "Red", "Right", 1.2f, 0.2f);
        system.renderText("你好，世界", "Monospace", false, true, false, 18, "Blue", "Center", 2.0f, 0.3f);
    }

    @Test
    public void testRenderTextWithSameFontInstance() {
        FontFlyweight font1 = FontFactory.getFont("Serif", false, false, false);
        FontFlyweight font2 = FontFactory.getFont("Serif", false, false, false);

        assertSame(font1, font2);  // Flyweight ensures the same instance is reused.
    }

    @Test
    public void testRenderTextWithDifferentFontAttributes() {
        FontFlyweight font1 = FontFactory.getFont("Sans-serif", true, false, false);
        FontFlyweight font2 = FontFactory.getFont("Sans-serif", false, true, false);

        assertNotSame(font1, font2);  // Different font attributes, different instances.
    }

    @Test
    public void testRenderTextAlignment() {
        system.renderText("Aligned Left", "Serif", false, false, false, 12, "Green", "Left", 1.0f, 0.1f);
        system.renderText("Aligned Center", "Serif", false, false, false, 12, "Green", "Center", 1.0f, 0.1f);
        system.renderText("Aligned Right", "Serif", false, false, false, 12, "Green", "Right", 1.0f, 0.1f);
        system.renderText("Justified", "Serif", false, false, false, 12, "Green", "Justify", 1.0f, 0.1f);
    }

    @Test
    public void testRenderWithSpecialCharactersAndUnicode() {
        system.renderText("Math Symbols: ∑, √, ∫", "Sans-serif", false, false, false, 12, "Black", "Left", 1.0f, 0.1f);
        system.renderText("Emoji: 😀, 🧡, 👍", "Sans-serif", false, false, false, 12, "Black", "Left", 1.0f, 0.1f);
    }
}

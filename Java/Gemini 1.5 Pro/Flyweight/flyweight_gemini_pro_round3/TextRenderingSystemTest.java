package flyweight_gemini_pro_round3;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FlyweightTextRenderingTest {

    @Test
    void testTextRendering() {
        // Create a TextFormatFactory
        TextFormatFactory factory = new TextFormatFactory();

        // Create some TextFormat objects
        TextFormat format1 = factory.getTextFormat("Arial", 12, false, false, false, Color.BLACK);
        TextFormat format2 = factory.getTextFormat("Arial", 12, true, false, false, Color.BLACK);
        TextFormat format3 = factory.getTextFormat("Times New Roman", 14, false, true, false, Color.RED);

        // Check that the factory returns the same object for the same format
        assertSame(format1, factory.getTextFormat("Arial", 12, false, false, false, Color.BLACK));

        // Check that the format objects have the correct properties
        assertEquals("Arial", ((ConcreteTextFormat) format1).fontFamily);
        assertEquals(12, ((ConcreteTextFormat) format1).fontSize);
        assertFalse(((ConcreteTextFormat) format1).isBold);
        assertFalse(((ConcreteTextFormat) format1).isItalic);
        assertFalse(((ConcreteTextFormat) format1).isUnderline);
        assertEquals(Color.BLACK, ((ConcreteTextFormat) format1).color);

        // Check that the format objects draw text correctly
        // (You will need to use a mocking framework to mock the Graphics object)
    }

    // Add more tests to achieve 100% branch and statement coverage
}
package flyweight_chatgpt4o_round2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlyweightPatternTest {

    private TextFormatFactory factory;

    @BeforeEach
    void setUp() {
        factory = new TextFormatFactory();
    }

    @Test
    void testFlyweightCreationAndReusability() {
        TextFormat format1 = factory.getTextFormat("Serif", 12, true, false, false, "Left", "EN");
        TextFormat format2 = factory.getTextFormat("Serif", 12, true, false, false, "Left", "EN");
        
        assertSame(format1, format2, "The flyweight object should be reused.");
    }

    @Test
    void testFlyweightDifferentFormats() {
        TextFormat format1 = factory.getTextFormat("Serif", 12, true, false, false, "Left", "EN");
        TextFormat format2 = factory.getTextFormat("Sans-serif", 14, false, true, false, "Center", "FR");
        
        assertNotSame(format1, format2, "Different text formats should not be reused.");
    }

    @Test
    void testRenderTextWithContext() {
        TextFormat format = factory.getTextFormat("Serif", 12, true, false, false, "Left", "EN");
        TextContext context = new TextContext("Black", 1.5f, 0.1f);

        format.renderText("Test Text", context);
        
        assertDoesNotThrow(() -> format.renderText("Another Text", context), "Rendering text should not throw any exception.");
    }

    @Test
    void testContextAttributes() {
        TextContext context = new TextContext("Red", 2.0f, 0.2f);

        assertEquals("Red", context.getColor());
        assertEquals(2.0f, context.getLineSpacing());
        assertEquals(0.2f, context.getKerning());
    }
}


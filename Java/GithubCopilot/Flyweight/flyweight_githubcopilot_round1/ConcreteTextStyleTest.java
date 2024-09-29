package flyweight_githubcopilot_round1;
// TextStyleFactoryTest.java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TextStyleFactoryTest {
    @Test
    public void testGetTextStyle() {
        TextStyle textStyle1 = TextStyleFactory.getTextStyle("Sans-serif-12-Bold");
        assertNotNull(textStyle1);

        TextStyle textStyle2 = TextStyleFactory.getTextStyle("Sans-serif-12-Bold");
        assertSame(textStyle1, textStyle2);

        TextStyle textStyle3 = TextStyleFactory.getTextStyle("Serif-14-Italic");
        assertNotNull(textStyle3);
        assertNotSame(textStyle1, textStyle3);
    }
}

class ConcreteTextStyleTest {
    @Test
    public void testSetFont() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setFont("Sans-serif", 12, true, false, false);
        // Add assertions to verify the font properties
        assertEquals("Sans-serif", textStyle.getFontType());
        assertEquals(12, textStyle.getFontSize());
        assertTrue(textStyle.isBold());
        assertFalse(textStyle.isItalic());
        assertFalse(textStyle.isUnderline());
    }

    @Test
    public void testSetColor() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setColor("Black");
        // Add assertions to verify the color property
        assertEquals("Black", textStyle.getColor());
    }

    @Test
    public void testSetAlignment() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setAlignment("Left");
        // Add assertions to verify the alignment property
        assertEquals("Left", textStyle.getAlignment());
    }

    @Test
    public void testSetLineSpacing() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setLineSpacing(1.5);
        // Add assertions to verify the line spacing property
        assertEquals(1.5, textStyle.getLineSpacing());
    }

    @Test
    public void testSetKerning() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setKerning(0.1);
        // Add assertions to verify the kerning property
        assertEquals(0.1, textStyle.getKerning());
    }

    @Test
    public void testSetMultilingualSupport() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setMultilingualSupport(true);
        // Add assertions to verify the multilingual support property
        assertTrue(textStyle.isMultilingualSupport());
    }

    @Test
    public void testSetRichTextSupport() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setRichTextSupport(true);
        // Add assertions to verify the rich text support property
        assertTrue(textStyle.isRichTextSupport());
    }

    @Test
    public void testSetTextWrapping() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setTextWrapping(true);
        // Add assertions to verify the text wrapping property
        assertTrue(textStyle.isTextWrapping());
    }

    @Test
    public void testSetSpecialCharactersSupport() {
        ConcreteTextStyle textStyle = new ConcreteTextStyle();
        textStyle.setSpecialCharactersSupport(true);
        // Add assertions to verify the special characters support property
        assertTrue(textStyle.isSpecialCharactersSupport());
    }
}
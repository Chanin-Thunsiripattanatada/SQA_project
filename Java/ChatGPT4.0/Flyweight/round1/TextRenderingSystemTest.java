import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextRenderingSystemTest {

    private TextRenderingSystem textRenderingSystem;

    @BeforeEach
    public void setUp() {
        textRenderingSystem = new TextRenderingSystem();
    }

    @Test
    public void testRenderTextWithRegularFont() {
        TextProperties properties = new TextProperties("black", "left", 1.5, 0.2, true, true);
        assertDoesNotThrow(() -> textRenderingSystem.renderText("Hello World!", "Sans-serif", 12, false, false, false, properties));
    }

    @Test
    public void testRenderTextWithBoldFont() {
        TextProperties properties = new TextProperties("red", "center", 1.8, 0.1, true, false);
        assertDoesNotThrow(() -> textRenderingSystem.renderText("Welcome", "Serif", 16, true, false, false, properties));
    }

    @Test
    public void testFlyweightFactoryReuse() {
        FontFlyweightFactory factory = new FontFlyweightFactory();
        FontFlyweight flyweight1 = factory.getFontFlyweight("Sans-serif", 12, false, false, false);
        FontFlyweight flyweight2 = factory.getFontFlyweight("Sans-serif", 12, false, false, false);

        assertSame(flyweight1, flyweight2, "The same flyweight object should be reused");
    }

    @Test
    public void testFlyweightFactoryCreateNew() {
        FontFlyweightFactory factory = new FontFlyweightFactory();
        FontFlyweight flyweight1 = factory.getFontFlyweight("Sans-serif", 12, false, false, false);
        FontFlyweight flyweight2 = factory.getFontFlyweight("Serif", 14, true, false, false);

        assertNotSame(flyweight1, flyweight2, "Different flyweights should be created for different font properties");
    }
}

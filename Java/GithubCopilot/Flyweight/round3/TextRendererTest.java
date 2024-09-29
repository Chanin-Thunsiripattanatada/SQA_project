package round3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class TextRendererTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testTextRendering() {
        TextRenderer renderer = new TextRenderer();
        TextStyle font = TextStyleFactory.getFont("Sans-serif", 12, false, false, false);
        TextStyle color = TextStyleFactory.getColor("Red");
        TextStyle alignment = TextStyleFactory.getAlignment("Center");
        TextStyle lineSpacing = TextStyleFactory.getLineSpacing(1.5);
        TextStyle kerning = TextStyleFactory.getKerning(0.1);

        renderer.renderText("Hello World", font, color, alignment, lineSpacing, kerning);

        String expectedOutput = "Applying Font: Sans-serif, Size: 12, Bold: false, Italic: false, Underline: false to text: Hello World\n" +
                                "Applying Color: Red to text: Hello World\n" +
                                "Applying Alignment: Center to text: Hello World\n" +
                                "Applying Line Spacing: 1.5 to text: Hello World\n" +
                                "Applying Kerning: 0.1 to text: Hello World\n" +
                                "Rendering text: Hello World\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testTextStyleFactory() {
        TextStyle font1 = TextStyleFactory.getFont("Sans-serif", 12, false, false, false);
        TextStyle font2 = TextStyleFactory.getFont("Sans-serif", 12, false, false, false);
        assertSame(font1, font2);

        TextStyle color1 = TextStyleFactory.getColor("Red");
        TextStyle color2 = TextStyleFactory.getColor("Red");
        assertSame(color1, color2);

        TextStyle alignment1 = TextStyleFactory.getAlignment("Center");
        TextStyle alignment2 = TextStyleFactory.getAlignment("Center");
        assertSame(alignment1, alignment2);

        TextStyle lineSpacing1 = TextStyleFactory.getLineSpacing(1.5);
        TextStyle lineSpacing2 = TextStyleFactory.getLineSpacing(1.5);
        assertSame(lineSpacing1, lineSpacing2);

        TextStyle kerning1 = TextStyleFactory.getKerning(0.1);
        TextStyle kerning2 = TextStyleFactory.getKerning(0.1);
        assertSame(kerning1, kerning2);
    }
}
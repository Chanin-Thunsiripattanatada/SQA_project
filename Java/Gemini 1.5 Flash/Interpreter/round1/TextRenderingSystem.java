import java.util.HashMap;
import java.util.Map;

// Flyweight Interface
interface TextFlyweight {
    void render(String text, TextProperties properties);
}

// Concrete Flyweight Class
class FontFlyweight implements TextFlyweight {
    private final String fontFamily;
    private final int fontSize;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;

    public FontFlyweight(String fontFamily, int fontSize, boolean bold, boolean italic, boolean underline) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
    }

    @Override
    public void render(String text, TextProperties properties) {
        // Simulate rendering
        System.out.println("Rendering Text: " + text);
        System.out.println("Font: " + fontFamily + ", Size: " + fontSize + ", Bold: " + bold + ", Italic: " + italic + ", Underline: " + underline);
        System.out.println("Text Color: " + properties.color);
        System.out.println("Text Alignment: " + properties.alignment);
        System.out.println("Line Spacing: " + properties.lineSpacing + ", Kerning: " + properties.kerning);
        System.out.println("Multilingual Support: " + properties.multilingualSupport);
        System.out.println("Rich Text: " + properties.richText);
        System.out.println();
    }
}

// Flyweight Factory
class FontFlyweightFactory {
    private final Map<String, FontFlyweight> fontPool = new HashMap<>();

    public FontFlyweight getFontFlyweight(String fontFamily, int fontSize, boolean bold, boolean italic, boolean underline) {
        String key = fontFamily + fontSize + bold + italic + underline;
        if (!fontPool.containsKey(key)) {
            fontPool.put(key, new FontFlyweight(fontFamily, fontSize, bold, italic, underline));
        }
        return fontPool.get(key);
    }
}

// Extrinsic Properties for Text Rendering
class TextProperties {
    String color;
    String alignment;
    double lineSpacing;
    double kerning;
    boolean multilingualSupport;
    boolean richText;

    public TextProperties(String color, String alignment, double lineSpacing, double kerning, boolean multilingualSupport, boolean richText) {
        this.color = color;
        this.alignment = alignment;
        this.lineSpacing = lineSpacing;
        this.kerning = kerning;
        this.multilingualSupport = multilingualSupport;
        this.richText = richText;
    }
}

// Client Class
public class TextRenderingSystem {
    private final FontFlyweightFactory fontFactory = new FontFlyweightFactory();

    public void renderText(String text, String fontFamily, int fontSize, boolean bold, boolean italic, boolean underline, TextProperties properties) {
        FontFlyweight fontFlyweight = fontFactory.getFontFlyweight(fontFamily, fontSize, bold, italic, underline);
        fontFlyweight.render(text, properties);
    }

    public static void main(String[] args) {
        TextRenderingSystem system = new TextRenderingSystem();

        // Render a simple text
        TextProperties properties = new TextProperties("black", "left", 1.5, 0.2, true, true);
        system.renderText("Hello World!", "Sans-serif", 12, false, false, false, properties);

        // Render another text with different properties
        TextProperties properties2 = new TextProperties("red", "center", 1.8, 0.1, true, false);
        system.renderText("Welcome to the Text Rendering System", "Serif", 16, true, false, false, properties2);
    }
}
package flyweight_chatgpt4o_round2;

import java.util.HashMap;
import java.util.Map;

// Flyweight Interface
interface TextFormat {
    void renderText(String text, TextContext context);
}

// Concrete Flyweight
class ConcreteTextFormat implements TextFormat {
    private final String font;
    private final int fontSize;
    private final boolean bold;
    private final boolean italic;
    private final boolean underline;
    private final String alignment;
    private final String language;

    public ConcreteTextFormat(String font, int fontSize, boolean bold, boolean italic, boolean underline, String alignment, String language) {
        this.font = font;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.alignment = alignment;
        this.language = language;
    }

    @Override
    public void renderText(String text, TextContext context) {
        // Render text with formatting options
        System.out.println("Rendering text: " + text);
        System.out.println("Font: " + font + " | Size: " + fontSize + " | Bold: " + bold + " | Italic: " + italic + " | Underline: " + underline);
        System.out.println("Alignment: " + alignment + " | Color: " + context.getColor() + " | Language: " + language);
        System.out.println("Line Spacing: " + context.getLineSpacing() + " | Kerning: " + context.getKerning());
    }
}

// Flyweight Factory
class TextFormatFactory {
    private final Map<String, TextFormat> formats = new HashMap<>();

    public TextFormat getTextFormat(String font, int fontSize, boolean bold, boolean italic, boolean underline, String alignment, String language) {
        String key = font + fontSize + bold + italic + underline + alignment + language;
        if (!formats.containsKey(key)) {
            formats.put(key, new ConcreteTextFormat(font, fontSize, bold, italic, underline, alignment, language));
        }
        return formats.get(key);
    }
}

// Context class for extrinsic state
class TextContext {
    private final String color;
    private final float lineSpacing;
    private final float kerning;

    public TextContext(String color, float lineSpacing, float kerning) {
        this.color = color;
        this.lineSpacing = lineSpacing;
        this.kerning = kerning;
    }

    public String getColor() {
        return color;
    }

    public float getLineSpacing() {
        return lineSpacing;
    }

    public float getKerning() {
        return kerning;
    }
}

// Client Code Example
public class TextRenderingSystem {
    public static void main(String[] args) {
        TextFormatFactory formatFactory = new TextFormatFactory();
        
        TextFormat format1 = formatFactory.getTextFormat("Serif", 12, true, false, false, "Left", "EN");
        TextContext context1 = new TextContext("Black", 1.5f, 0.1f);
        format1.renderText("Hello World!", context1);
        
        TextFormat format2 = formatFactory.getTextFormat("Serif", 12, true, false, false, "Left", "EN");
        TextContext context2 = new TextContext("Red", 2.0f, 0.2f);
        format2.renderText("Bonjour!", context2);
    }
}

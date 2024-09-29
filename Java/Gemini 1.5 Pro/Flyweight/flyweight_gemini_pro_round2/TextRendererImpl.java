package flyweight_gemini_pro_round2;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


// Flyweight Factory for managing font objects
class FontFactory {
    private static final Map<String, Font> fontCache = new HashMap<>();

    public static Font getFont(String fontFamily, int fontSize, boolean isBold, boolean isItalic) {
        String fontKey = fontFamily + "|" + fontSize + "|" + isBold + "|" + isItalic;
        if (!fontCache.containsKey(fontKey)) {
            int fontStyle = Font.PLAIN;
            if (isBold) {
                fontStyle |= Font.BOLD;
            }
            if (isItalic) {
                fontStyle |= Font.ITALIC;
            }
            fontCache.put(fontKey, new Font(fontFamily, fontStyle, fontSize));
        }
        return fontCache.get(fontKey);
    }
}

// Flyweight interface for text rendering
interface TextRenderer {
    void render(String text, int x, int y, Color color, TextFormat format);
}

// Concrete Flyweight class for text rendering
class TextRendererImpl implements TextRenderer {
    @Override
    public void render(String text, int x, int y, Color color, TextFormat format) {
        // Simulate text rendering using System.out.println
        System.out.println("Rendering text: \"" + text + "\" at (" + x + ", " + y + ")"
                + " with color: " + color
                + " and format: " + format);
    }
}

// Flyweight Factory for managing TextRenderer objects
class TextRendererFactory {
    private static final TextRenderer renderer = new TextRendererImpl();

    public static TextRenderer getRenderer() {
        return renderer;
    }
}

// Immutable TextFormat class to hold text formatting properties
class TextFormat {
    private final String fontFamily;
    private final int fontSize;
    private final boolean isBold;
    private final boolean isItalic;
    private final Color color;
    // Add other formatting properties here

    public TextFormat(String fontFamily, int fontSize, boolean isBold, boolean isItalic, Color color) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.color = color;
    }

    public Font getFont() {
        return FontFactory.getFont(fontFamily, fontSize, isBold, isItalic);
    }

    // Getters for other formatting properties

    @Override
    public String toString() {
        return "Font: " + fontFamily + ", Size: " + fontSize + ", Bold: " + isBold + ", Italic: " + isItalic + ", Color: " + color;
    }
}
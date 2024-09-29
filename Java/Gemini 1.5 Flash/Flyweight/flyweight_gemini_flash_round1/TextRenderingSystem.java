package flyweight_gemini_flash_round1;
import java.awt.*;
import java.util.HashMap;

// Flyweight Factory for managing Font objects
class FontFactory {
    private static final HashMap<String, Font> fontCache = new HashMap<>();

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

// Flyweight interface for text formatting options
interface TextFormat {
    void apply(Graphics g, String text, int x, int y);
}

// Concrete Flyweight for basic text formatting
class BasicTextFormat implements TextFormat {
    private final Font font;
    private final Color color;

    public BasicTextFormat(Font font, Color color) {
        this.font = font;
        this.color = color;
    }

    @Override
    public void apply(Graphics g, String text, int x, int y) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, x, y);
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }
    
}

// Context class holding extrinsic state and referencing Flyweight
class TextRenderer {
    private TextFormat textFormat;

    public void setTextFormat(String fontFamily, int fontSize, boolean isBold, boolean isItalic, Color color) {
        Font font = FontFactory.getFont(fontFamily, fontSize, isBold, isItalic);
        this.textFormat = new BasicTextFormat(font, color);
    }

    public void renderText(Graphics g, String text, int x, int y) {
        textFormat.apply(g, text, x, y);
    }
}
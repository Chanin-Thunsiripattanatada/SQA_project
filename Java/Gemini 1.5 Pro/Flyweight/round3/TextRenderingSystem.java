package round3;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Flyweight interface for text formats
interface TextFormat {
    void drawText(String text, int x, int y, Graphics g);
}

// Concrete Flyweight representing a specific text format
class ConcreteTextFormat implements TextFormat {
    private String fontFamily;
    private int fontSize;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private Color color;

    public ConcreteTextFormat(String fontFamily, int fontSize, boolean bold, boolean italic, boolean underline, Color color) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.color = color;
    }

    @Override
    public void drawText(String text, int x, int y, Graphics g) {
        g.setColor(color);
        g.setFont(new Font(fontFamily, getFontStyle(), fontSize));
        if (underline) {
            FontMetrics fm = g.getFontMetrics();
            int lineY = y + fm.getAscent();
            g.drawLine(x, lineY + 2, x + fm.stringWidth(text), lineY + 2);
        }
        g.drawString(text, x, y);
    }

    private int getFontStyle() {
        int style = Font.PLAIN;
        if (bold) {
            style = style | Font.BOLD;
        }
        if (italic) {
            style = style | Font.ITALIC;
        }
        return style;
    }
}

// Flyweight Factory for managing text formats
class TextFormatFactory {
    private static final Map<String, TextFormat> textFormatMap = new HashMap<>();

    public static TextFormat getTextFormat(String fontFamily, int fontSize, boolean bold, boolean italic, boolean underline, Color color) {
        String key = fontFamily + "|" + fontSize + "|" + bold + "|" + italic + "|" + underline + "|" + color.getRGB();
        if (!textFormatMap.containsKey(key)) {
            textFormatMap.put(key, new ConcreteTextFormat(fontFamily, fontSize, bold, italic, underline, color));
        }
        return textFormatMap.get(key);
    }
}

// Client code using the Flyweight pattern
public class TextRenderingSystem {
    private TextFormatFactory textFormatFactory;

    public TextRenderingSystem() {
        this.textFormatFactory = new TextFormatFactory();
    }

    public void renderText(String text, String fontFamily, int fontSize, boolean bold, boolean italic, boolean underline, Color color, int x, int y, Graphics g) {
        TextFormat textFormat = textFormatFactory.getTextFormat(fontFamily, fontSize, bold, italic, underline, color);
        textFormat.drawText(text, x, y, g);
    }
}
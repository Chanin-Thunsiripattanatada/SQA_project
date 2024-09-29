package flyweight_gemini_pro_round1;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface TextFormat {
    void display(String text, int x, int y, Graphics g);
}

// Concrete Flyweight class
class ConcreteTextFormat implements TextFormat {
    private String fontFamily;
    private int fontSize;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;
    private Color color;

    public ConcreteTextFormat(String fontFamily, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline, Color color) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderline = isUnderline;
        this.color = color;
    }

    @Override
    public void display(String text, int x, int y, Graphics g) {
        g.setColor(color);
        g.setFont(new Font(fontFamily, getFontStyle(), fontSize));
        g.drawString(text, x, y);
    }

    private int getFontStyle() {
        int style = Font.PLAIN;
        if (isBold) {
            style |= Font.BOLD;
        }
        if (isItalic) {
            style |= Font.ITALIC;
        }
        return style;
    }
}

// Flyweight factory
class TextFormatFactory {
    private static final Map<String, TextFormat> textFormatMap = new HashMap<>();

    public static TextFormat getTextFormat(String fontFamily, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline, Color color) {
        String key = fontFamily + "|" + fontSize + "|" + isBold + "|" + isItalic + "|" + isUnderline + "|" + color.getRGB();
        if (!textFormatMap.containsKey(key)) {
            textFormatMap.put(key, new ConcreteTextFormat(fontFamily, fontSize, isBold, isItalic, isUnderline, color));
        }
        return textFormatMap.get(key);
    }
}

// Context class
class TextRenderer {
    private TextFormat textFormat;

    public void setTextFormat(String fontFamily, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline, Color color) {
        this.textFormat = TextFormatFactory.getTextFormat(fontFamily, fontSize, isBold, isItalic, isUnderline, color);
    }

    public void renderText(String text, int x, int y, Graphics g) {
        textFormat.display(text, x, y, g);
    }
}
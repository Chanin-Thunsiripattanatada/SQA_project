package round2;
import java.util.HashMap;
import java.util.Map;

// Flyweight Interface
interface TextStyle {
    void applyStyle(String text);
}

// Concrete Flyweight Classes
class Font implements TextStyle {
    private String fontName;
    private int fontSize;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;

    public Font(String fontName, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline) {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderline = isUnderline;
    }

    @Override
    public void applyStyle(String text) {
        // Apply font style to text
        System.out.println("Applying font: " + fontName + ", size: " + fontSize + ", bold: " + isBold + ", italic: " + isItalic + ", underline: " + isUnderline);
    }
}

class Color implements TextStyle {
    private String color;

    public Color(String color) {
        this.color = color;
    }

    @Override
    public void applyStyle(String text) {
        // Apply color to text
        System.out.println("Applying color: " + color);
    }
}

class Alignment implements TextStyle {
    private String alignment;

    public Alignment(String alignment) {
        this.alignment = alignment;
    }

    @Override
    public void applyStyle(String text) {
        // Apply alignment to text
        System.out.println("Applying alignment: " + alignment);
    }
}

// Flyweight Factory
class TextStyleFactory {
    private static final Map<String, TextStyle> styles = new HashMap<>();

    public static TextStyle getFont(String fontName, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline) {
        String key = fontName + fontSize + isBold + isItalic + isUnderline;
        if (!styles.containsKey(key)) {
            styles.put(key, new Font(fontName, fontSize, isBold, isItalic, isUnderline));
        }
        return styles.get(key);
    }

    public static TextStyle getColor(String color) {
        if (!styles.containsKey(color)) {
            styles.put(color, new Color(color));
        }
        return styles.get(color);
    }

    public static TextStyle getAlignment(String alignment) {
        if (!styles.containsKey(alignment)) {
            styles.put(alignment, new Alignment(alignment));
        }
        return styles.get(alignment);
    }
}

// Client Class
class TextRenderer {
    public void renderText(String text, TextStyle... styles) {
        for (TextStyle style : styles) {
            style.applyStyle(text);
        }
        System.out.println("Rendering text: " + text);
    }

    public static void main(String[] args) {
        TextRenderer renderer = new TextRenderer();
        TextStyle font = TextStyleFactory.getFont("Arial", 12, true, false, false);
        TextStyle color = TextStyleFactory.getColor("Red");
        TextStyle alignment = TextStyleFactory.getAlignment("Center");

        renderer.renderText("Hello, World!", font, color, alignment);
    }
}
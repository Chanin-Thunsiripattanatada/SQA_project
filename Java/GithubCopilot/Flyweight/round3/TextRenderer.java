package round3;
import java.util.HashMap;
import java.util.Map;

// Flyweight Interface
interface TextStyle {
    void applyStyle(String text);
}

// Concrete Flyweight Classes
class Font implements TextStyle {
    private String fontType;
    private int fontSize;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;

    public Font(String fontType, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline) {
        this.fontType = fontType;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderline = isUnderline;
    }

    @Override
    public void applyStyle(String text) {
        // Apply font style to text
        System.out.println("Applying Font: " + fontType + ", Size: " + fontSize + ", Bold: " + isBold + ", Italic: " + isItalic + ", Underline: " + isUnderline + " to text: " + text);
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
        System.out.println("Applying Color: " + color + " to text: " + text);
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
        System.out.println("Applying Alignment: " + alignment + " to text: " + text);
    }
}

class LineSpacing implements TextStyle {
    private double spacing;

    public LineSpacing(double spacing) {
        this.spacing = spacing;
    }

    @Override
    public void applyStyle(String text) {
        // Apply line spacing to text
        System.out.println("Applying Line Spacing: " + spacing + " to text: " + text);
    }
}

class Kerning implements TextStyle {
    private double kerning;

    public Kerning(double kerning) {
        this.kerning = kerning;
    }

    @Override
    public void applyStyle(String text) {
        // Apply kerning to text
        System.out.println("Applying Kerning: " + kerning + " to text: " + text);
    }
}

// Flyweight Factory
class TextStyleFactory {
    private static final Map<String, TextStyle> styles = new HashMap<>();

    public static TextStyle getFont(String fontType, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline) {
        String key = fontType + fontSize + isBold + isItalic + isUnderline;
        if (!styles.containsKey(key)) {
            styles.put(key, new Font(fontType, fontSize, isBold, isItalic, isUnderline));
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

    public static TextStyle getLineSpacing(double spacing) {
        String key = "LineSpacing" + spacing;
        if (!styles.containsKey(key)) {
            styles.put(key, new LineSpacing(spacing));
        }
        return styles.get(key);
    }

    public static TextStyle getKerning(double kerning) {
        String key = "Kerning" + kerning;
        if (!styles.containsKey(key)) {
            styles.put(key, new Kerning(kerning));
        }
        return styles.get(key);
    }
}

// Client Code
class TextRenderer {
    public void renderText(String text, TextStyle... styles) {
        for (TextStyle style : styles) {
            style.applyStyle(text);
        }
        // Render the text with applied styles
        System.out.println("Rendering text: " + text);
    }
}

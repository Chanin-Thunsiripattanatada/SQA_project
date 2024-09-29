package flyweight_chatgpt4o_round3;

// TextRenderingSystem.java
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

interface FontFlyweight {
    void renderText(String text, int fontSize, String color, String alignment, float lineSpacing, float letterSpacing);
}

class ConcreteFont implements FontFlyweight {
    private final String fontName;
    private final boolean isBold;
    private final boolean isItalic;
    private final boolean isUnderline;

    public ConcreteFont(String fontName, boolean isBold, boolean isItalic, boolean isUnderline) {
        this.fontName = fontName;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderline = isUnderline;
    }

    @Override
    public void renderText(String text, int fontSize, String color, String alignment, float lineSpacing, float letterSpacing) {
        System.out.println("Rendering text: '" + text + "' with font: " + fontName +
                " [Bold: " + isBold + ", Italic: " + isItalic + ", Underline: " + isUnderline + "]" +
                " in size: " + fontSize + ", color: " + color + ", alignment: " + alignment +
                ", lineSpacing: " + lineSpacing + ", letterSpacing: " + letterSpacing);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcreteFont that = (ConcreteFont) o;
        return isBold == that.isBold && isItalic == that.isItalic && isUnderline == that.isUnderline && fontName.equals(that.fontName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fontName, isBold, isItalic, isUnderline);
    }
}

class FontFactory {
    private static final Map<String, FontFlyweight> fontPool = new HashMap<>();

    public static FontFlyweight getFont(String fontName, boolean isBold, boolean isItalic, boolean isUnderline) {
        String key = fontName + "-" + isBold + "-" + isItalic + "-" + isUnderline;
        if (!fontPool.containsKey(key)) {
            fontPool.put(key, new ConcreteFont(fontName, isBold, isItalic, isUnderline));
        }
        return fontPool.get(key);
    }
}

public class TextRenderingSystem {
    public void renderText(String text, String fontName, boolean isBold, boolean isItalic, boolean isUnderline, 
                           int fontSize, String color, String alignment, float lineSpacing, float letterSpacing) {
        FontFlyweight font = FontFactory.getFont(fontName, isBold, isItalic, isUnderline);
        font.renderText(text, fontSize, color, alignment, lineSpacing, letterSpacing);
    }

    public static void main(String[] args) {
        TextRenderingSystem system = new TextRenderingSystem();
        system.renderText("Hello World", "Sans-serif", true, false, false, 16, "Red", "Center", 1.5f, 0.0f);
        system.renderText("你好，世界", "Serif", false, true, false, 18, "Blue", "Left", 2.0f, 0.3f);
    }
}


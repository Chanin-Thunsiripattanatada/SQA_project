package round1;
import java.util.HashMap;
import java.util.Map;

// TextStyle.java
interface TextStyle {
    void setFont(String fontType, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline);
    void setColor(String color);
    void setAlignment(String alignment);
    void setLineSpacing(double spacing);
    void setKerning(double kerning);
    void setMultilingualSupport(boolean support);
    void setRichTextSupport(boolean support);
    void setTextWrapping(boolean wrapping);
    void setSpecialCharactersSupport(boolean support);
}

// ConcreteTextStyle.java
class ConcreteTextStyle implements TextStyle {
    private String fontType;
    private int fontSize;
    private boolean isBold;
    private boolean isItalic;
    private boolean isUnderline;
    private String color;
    private String alignment;
    private double lineSpacing;
    private double kerning;
    private boolean multilingualSupport;
    private boolean richTextSupport;
    private boolean textWrapping;
    private boolean specialCharactersSupport;

    @Override
    public void setFont(String fontType, int fontSize, boolean isBold, boolean isItalic, boolean isUnderline) {
        this.fontType = fontType;
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isItalic = isItalic;
        this.isUnderline = isUnderline;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    @Override
    public void setLineSpacing(double spacing) {
        this.lineSpacing = spacing;
    }

    @Override
    public void setKerning(double kerning) {
        this.kerning = kerning;
    }

    @Override
    public void setMultilingualSupport(boolean support) {
        this.multilingualSupport = support;
    }

    @Override
    public void setRichTextSupport(boolean support) {
        this.richTextSupport = support;
    }

    @Override
    public void setTextWrapping(boolean wrapping) {
        this.textWrapping = wrapping;
    }

    @Override
    public void setSpecialCharactersSupport(boolean support) {
        this.specialCharactersSupport = support;
    }

    // Getter methods for testing purposes
    public String getFontType() {
        return fontType;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public boolean isUnderline() {
        return isUnderline;
    }

    public String getColor() {
        return color;
    }

    public String getAlignment() {
        return alignment;
    }

    public double getLineSpacing() {
        return lineSpacing;
    }

    public double getKerning() {
        return kerning;
    }

    public boolean isMultilingualSupport() {
        return multilingualSupport;
    }

    public boolean isRichTextSupport() {
        return richTextSupport;
    }

    public boolean isTextWrapping() {
        return textWrapping;
    }

    public boolean isSpecialCharactersSupport() {
        return specialCharactersSupport;
    }
}

// TextStyleFactory.java
class TextStyleFactory {
    private static final Map<String, TextStyle> textStyleMap = new HashMap<>();

    public static TextStyle getTextStyle(String key) {
        TextStyle textStyle = textStyleMap.get(key);
        if (textStyle == null) {
            textStyle = new ConcreteTextStyle();
            textStyleMap.put(key, textStyle);
        }
        return textStyle;
    }
}

// Text.java
class Text {
    private String content;
    private TextStyle textStyle;

    public Text(String content, TextStyle textStyle) {
        this.content = content;
        this.textStyle = textStyle;
    }

    public void render() {
        // Render the text using the textStyle
        System.out.println("Rendering text: " + content);
    }
}

// TextRenderingSystem.java
public class TextRenderingSystem {
    public static void main(String[] args) {
        TextStyle textStyle1 = TextStyleFactory.getTextStyle("Sans-serif-12-Bold");
        textStyle1.setFont("Sans-serif", 12, true, false, false);
        textStyle1.setColor("Black");
        textStyle1.setAlignment("Left");

        Text text1 = new Text("Hello, World!", textStyle1);
        text1.render();

        TextStyle textStyle2 = TextStyleFactory.getTextStyle("Serif-14-Italic");
        textStyle2.setFont("Serif", 14, false, true, false);
        textStyle2.setColor("Red");
        textStyle2.setAlignment("Center");

        Text text2 = new Text("Flyweight Pattern", textStyle2);
        text2.render();
    }
}
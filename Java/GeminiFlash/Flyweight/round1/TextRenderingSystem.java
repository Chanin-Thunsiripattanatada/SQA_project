import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TextRenderingSystem {

    private static final Map<String, Font> FONT_CACHE = new HashMap<>();

    public static void main(String[] args) {
        // Example usage
        TextRenderer renderer = new TextRenderer(
                "Hello, World!",
                new Font("Arial", Font.BOLD, 12),
                Color.BLACK,
                TextAlignment.LEFT
        );
        renderer.render();
    }

    public static class TextRenderer {
        public String text;
        public Font font;
        public Color color;
        public TextAlignment alignment;
        public float lineSpacing;
        public float letterSpacing;

        public TextRenderer(String text, Font font, Color color, TextAlignment alignment) {
            this.text = text;
            this.font = font;
            this.color = color;
            this.alignment = alignment;
            this.lineSpacing = 1.0f;
            this.letterSpacing = 0.0f;
        }
      
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public TextAlignment getAlignment() {
            return alignment;
        }

        public void setAlignment(TextAlignment alignment) {
            this.alignment = alignment;
        }

        public float getLineSpacing() {
            return lineSpacing;
        }

        public float getLetterSpacing() {
            return letterSpacing;
        }

        public TextRenderer setLineSpacing(float lineSpacing) {
            this.lineSpacing = lineSpacing;
            return this;
        }

        public TextRenderer setLetterSpacing(float letterSpacing) {
            this.letterSpacing = letterSpacing;
            return this;
        }

        public void render() {
            // Simulated rendering logic
            System.out.println("Rendering text: " + text);
            System.out.println("Font: " + font.getName() + " - " + font.getStyle() + " - " + font.getSize());
            System.out.println("Color: " + color);
            System.out.println("Alignment: " + alignment);
            System.out.println("Line Spacing: " + lineSpacing);
            System.out.println("Letter Spacing: " + letterSpacing);
        }

        public Font getFont() {
            String key = font.getName() + "-" + font.getStyle() + "-" + font.getSize();
            if (!FONT_CACHE.containsKey(key)) {
                FONT_CACHE.put(key, font);
            }
            return FONT_CACHE.get(key);
        }
    }

    public enum TextAlignment {
        LEFT,
        CENTER,
        RIGHT,
        JUSTIFY
    }
}

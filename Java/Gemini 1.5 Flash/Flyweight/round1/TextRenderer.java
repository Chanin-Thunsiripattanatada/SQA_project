package round1;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TextRenderer {

    private static final Map<String, Font> FONTS = new HashMap<>();

    static {
        FONTS.put("Sans-serif", new Font("Arial", Font.PLAIN, 12));
        FONTS.put("Serif", new Font("Times New Roman", Font.PLAIN, 12));
        FONTS.put("Monospace", new Font("Courier New", Font.PLAIN, 12));
    }

    public static class TextRenderingContext {
        private Font font;
        private Color color;
        private int fontSize;
        private int style;
        private int alignment;
        private int lineSpacing;
        private int letterSpacing;

        public TextRenderingContext(String fontName, int fontSize, Color color) {
            this.font = FONTS.getOrDefault(fontName, FONTS.get("Sans-serif"));
            this.fontSize = fontSize;
            this.color = color;
            this.style = Font.PLAIN;
            this.alignment = Graphics.LEFT_ALIGNMENT;
            this.lineSpacing = 0;
            this.letterSpacing = 0;
        }

        public TextRenderingContext setStyle(int style) {
            this.style = style;
            return this;
        }

        public TextRenderingContext setAlignment(int alignment) {
            this.alignment = alignment;
            return this;
        }

        public TextRenderingContext setLineSpacing(int lineSpacing) {
            this.lineSpacing = lineSpacing;
            return this;
        }

        public TextRenderingContext setLetterSpacing(int letterSpacing) {
            this.letterSpacing = letterSpacing;
            return this;
        }
    }

    public static void renderText(String text, TextRenderingContext context, Graphics g) {
        Font currentFont = context.font.deriveFont(context.style, context.fontSize);
        g.setFont(currentFont);
        g.setColor(context.color);

        // Handle alignment
        int alignment = context.alignment;
        if (alignment == Graphics.CENTER_ALIGNMENT) {
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            g.drawString(text, (g.getClipBounds().width - textWidth) / 2, g.getClipBounds().y);
        } else if (alignment == Graphics.RIGHT_ALIGNMENT) {
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            g.drawString(text, g.getClipBounds().width - textWidth, g.getClipBounds().y);
        } else {
            g.drawString(text, g.getClipBounds().x, g.getClipBounds().y);
        }
    }
}
package flyweight_gemini_flash_round1;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TextRenderingSystem {

    private static final Map<String, Font> FONTS = new HashMap<>();

    static {
        FONTS.put("Sans-serif", new Font("Arial", Font.PLAIN, 12));
        FONTS.put("Serif", new Font("Times New Roman", Font.PLAIN, 12));
        FONTS.put("Monospace", new Font("Courier New", Font.PLAIN, 12));
    }

    private String text;
    private Font font;
    private Color color;
    private int fontSize;
    private int lineSpacing;
    private int letterSpacing;
    private TextAlign alignment;

    public TextRenderingSystem(String text) {
        this.text = text;
        this.font = FONTS.get("Sans-serif");
        this.color = Color.BLACK;
        this.fontSize = 12;
        this.lineSpacing = 0;
        this.letterSpacing = 0;
        this.alignment = TextAlign.LEFT;
    }

    public void setFont(String fontName) {
        if (FONTS.containsKey(fontName)) {
            this.font = FONTS.get(fontName);
        } else {
            this.font = FONTS.get("Sans-serif"); // Default to Sans-serif if font is not found
        }
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        this.font = this.font.deriveFont(fontSize);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAlignment(TextAlign alignment) {
        this.alignment = alignment;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public void setLetterSpacing(int letterSpacing) {
        this.letterSpacing = letterSpacing;
    }

    public void render(Graphics2D g2d, int x, int y) {
        g2d.setColor(color);
        g2d.setFont(font);

        // Handle letter spacing
        if (letterSpacing != 0) {
            g2d.drawString(text, x, y);
            return;
        }

        // Handle alignment
        switch (alignment) {
            case CENTER:
                x -= g2d.getFontMetrics().stringWidth(text) / 2;
                break;
            case RIGHT:
                x -= g2d.getFontMetrics().stringWidth(text);
                break;
            case JUSTIFY:
                // Implement justify logic for text rendering
                break;
            default:
                break;
        }

        // Draw the text
        g2d.drawString(text, x, y);
    }

    public enum TextAlign {
        LEFT, CENTER, RIGHT, JUSTIFY
    }
}
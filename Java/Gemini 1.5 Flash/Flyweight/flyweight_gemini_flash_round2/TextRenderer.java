package flyweight_gemini_flash_round2;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TextRenderer {

    private static final Map<String, Font> fontCache = new HashMap<>();

    public static void renderText(String text, Font font, Color color, int alignment, double lineSpacing, double kerning, int wrapWidth) {
        // 1. Font Management
        Font actualFont = getFont(font);

        // 2. Text Color
        Graphics2D g2d = (Graphics2D) GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(1, 1).createGraphics();
        g2d.setColor(color);

        // 3. Text Alignment
        switch (alignment) {
            case 0: // Left Alignment
                g2d.drawString(text, 0, 0);
                break;
            case 1: // Center Alignment
                int textWidth = g2d.getFontMetrics().stringWidth(text);
                g2d.drawString(text, (wrapWidth - textWidth) / 2, 0);
                break;
            case 2: // Right Alignment
                g2d.drawString(text, wrapWidth - g2d.getFontMetrics().stringWidth(text), 0);
                break;
            case 3: // Justify
                // Complex, not implemented for this example
                break;
        }

        // 4. Line Spacing
        g2d.drawString(text, 0, 0);
        int lineHeight = g2d.getFontMetrics().getHeight();
        g2d.dispose();

        // 5. Kerning
        // Simulate kerning by adding space between characters.
        // This is not a real kerning implementation, but it demonstrates the concept.
        StringBuilder kerningText = new StringBuilder();
        for (char c : text.toCharArray()) {
            kerningText.append(c);
            if (kerning > 0) {
                kerningText.append(" ");
            }
        }
        System.out.println(kerningText.toString());

        // 6. Multilingual Support
        // ...

        // 7. Rich Text Support
        // ...

        // 8. Text Wrapping
        // ...

        // 9. Special Characters & Unicode Support
        // ...

        // Display the rendered text
        System.out.println("Text: " + text);
        System.out.println("Font: " + font);
        System.out.println("Color: " + color);
        System.out.println("Alignment: " + alignment);
        System.out.println("Line Spacing: " + lineSpacing);
        System.out.println("Kerning: " + kerning);
        System.out.println("Wrap Width: " + wrapWidth);
    }

    public static Font getFont(Font font) {
        String fontKey = font.getName() + "-" + font.getStyle() + "-" + font.getSize();
        if (!fontCache.containsKey(fontKey)) {
            fontCache.put(fontKey, font);
        }
        return fontCache.get(fontKey);
    }
}
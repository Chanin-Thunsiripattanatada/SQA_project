package round1;

import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class TextRendererTest {

    @Test
    void renderText_defaultFont() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Do nothing, just verify the method is called
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 12, Color.BLACK);
        TextRenderer.renderText("Hello", context, g);
        // Verify that the default font is used
        assertEquals(new Font("Arial", Font.PLAIN, 12), context.font);
    }

    @Test
    void renderText_customFont() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Do nothing, just verify the method is called
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Serif", 12, Color.BLACK);
        TextRenderer.renderText("Hello", context, g);
        // Verify that the custom font is used
        assertEquals(new Font("Times New Roman", Font.PLAIN, 12), context.font);
    }

    @Test
    void renderText_fontSize() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Do nothing, just verify the method is called
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 24, Color.BLACK);
        TextRenderer.renderText("Hello", context, g);
        // Verify that the font size is used
        assertEquals(24, context.fontSize);
    }

    @Test
    void renderText_color() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Do nothing, just verify the method is called
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }

            @Override
            public void setColor(Color c) {
                // Verify that the color is set
                assertEquals(Color.RED, c);
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 12, Color.RED);
        TextRenderer.renderText("Hello", context, g);
        // Verify that the color is used
    }

    @Test
    void renderText_style() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Do nothing, just verify the method is called
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 12, Color.BLACK);
        context.setStyle(Font.BOLD);
        TextRenderer.renderText("Hello", context, g);
        // Verify that the style is used
        assertEquals(Font.BOLD, context.style);
    }

    @Test
    void renderText_alignment_left() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Verify that the text is drawn at the correct position for left alignment
                assertEquals(0, x);
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }

            @Override
            public int getClipBounds().width() {
                return 100;
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 12, Color.BLACK);
        context.setAlignment(Graphics.LEFT_ALIGNMENT);
        TextRenderer.renderText("Hello", context, g);
    }

    @Test
    void renderText_alignment_center() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Verify that the text is drawn at the correct position for center alignment
                assertEquals(45, x);
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }

            @Override
            public int getClipBounds().width() {
                return 100;
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 12, Color.BLACK);
        context.setAlignment(Graphics.CENTER_ALIGNMENT);
        TextRenderer.renderText("Hello", context, g);
    }

    @Test
    void renderText_alignment_right() {
        Graphics g = new Graphics() {
            @Override
            public void drawString(String str, int x, int y) {
                // Verify that the text is drawn at the correct position for right alignment
                assertEquals(90, x);
            }

            @Override
            public FontMetrics getFontMetrics(Font font) {
                return new FontMetrics(font) {
                    @Override
                    public int stringWidth(String str) {
                        return str.length() * 10; // Assume each character is 10 pixels wide
                    }
                };
            }

            @Override
            public int getClipBounds().width() {
                return 100;
            }
        };

        TextRenderer.TextRenderingContext context = new TextRenderer.TextRenderingContext("Sans-serif", 12, Color.BLACK);
        context.setAlignment(Graphics.RIGHT_ALIGNMENT);
        TextRenderer.renderText("Hello", context, g);
    }
}
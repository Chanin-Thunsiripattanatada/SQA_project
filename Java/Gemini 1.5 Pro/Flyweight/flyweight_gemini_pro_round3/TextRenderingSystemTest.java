package flyweight_gemini_pro_round3;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import static org.junit.jupiter.api.Assertions.*;

class FlyweightPatternTest {

    @Test
    void testGetTextFormatFromCache() {
        TextFormat format1 = TextFormatFactory.getTextFormat("Arial", 12, true, false, false, Color.BLACK);
        TextFormat format2 = TextFormatFactory.getTextFormat("Arial", 12, true, false, false, Color.BLACK);
        assertSame(format1, format2, "TextFormat objects should be the same (from cache)");
    }

    @Test
    void testGetTextFormatNewInstance() {
        TextFormat format1 = TextFormatFactory.getTextFormat("Arial", 12, true, false, false, Color.BLACK);
        TextFormat format2 = TextFormatFactory.getTextFormat("Times New Roman", 14, false, true, false, Color.RED);
        assertNotSame(format1, format2, "TextFormat objects should be different (new instance created)");
    }

    @Test
    void testConcreteTextFormat() {
        Color color = Color.BLUE;
        ConcreteTextFormat format = new ConcreteTextFormat("Courier New", 16, true, true, false, color);

        assertEquals("Courier New", format.fontFamily, "Font family should be set correctly");
        assertEquals(16, format.fontSize, "Font size should be set correctly");
        assertTrue(format.isBold, "Bold should be set correctly");
        assertTrue(format.isItalic, "Italic should be set correctly");
        assertFalse(format.isUnderline, "Underline should be set correctly"); 
        assertEquals(color, format.color, "Color should be set correctly");
    }

    @Test
    void testDrawText() {
        TextFormat format = TextFormatFactory.getTextFormat("Verdana", 14, false, false, false, Color.GREEN);
        DummyGraphics g = new DummyGraphics();
        format.drawText("Hello, Flyweight!", 100, 150, g); 

        assertEquals("Verdana", g.getFont().getFamily(), "Font family should be applied correctly");
        assertEquals(14, g.getFont().getSize(), "Font size should be applied correctly");
        assertFalse(g.getFont().isBold(), "Bold should be applied correctly");
        assertFalse(g.getFont().isItalic(), "Italic should be applied correctly");
        assertEquals(Color.GREEN, g.getColor(), "Color should be applied correctly");
    }

    // Dummy Graphics class for testing
    static class DummyGraphics extends Graphics {
        private Font font;
        private Color color;

        @Override
        public void drawString(String str, int x, int y) {
            // Simulate drawing by capturing font and color 
            this.font = getFont();
            this.color = getColor();
        }

        @Override
        public void setFont(Font font) {
            this.font = font;
        }

        @Override 
        public Font getFont() {
            return font;
        }

        @Override
        public void setColor(Color c) {
            this.color = c;
        }

        @Override
        public Color getColor() {
            return color;
        }

        @Override
        public Graphics create() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }

        @Override
        public void translate(int x, int y) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'translate'");
        }

        @Override
        public void setPaintMode() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setPaintMode'");
        }

        @Override
        public void setXORMode(Color c1) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setXORMode'");
        }

        @Override
        public FontMetrics getFontMetrics(Font f) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getFontMetrics'");
        }

        @Override
        public Rectangle getClipBounds() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getClipBounds'");
        }

        @Override
        public void clipRect(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'clipRect'");
        }

        @Override
        public void setClip(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setClip'");
        }

        @Override
        public Shape getClip() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getClip'");
        }

        @Override
        public void setClip(Shape clip) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'setClip'");
        }

        @Override
        public void copyArea(int x, int y, int width, int height, int dx, int dy) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'copyArea'");
        }

        @Override
        public void drawLine(int x1, int y1, int x2, int y2) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawLine'");
        }

        @Override
        public void fillRect(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fillRect'");
        }

        @Override
        public void clearRect(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'clearRect'");
        }

        @Override
        public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawRoundRect'");
        }

        @Override
        public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fillRoundRect'");
        }

        @Override
        public void drawOval(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawOval'");
        }

        @Override
        public void fillOval(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fillOval'");
        }

        @Override
        public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawArc'");
        }

        @Override
        public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fillArc'");
        }

        @Override
        public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawPolyline'");
        }

        @Override
        public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawPolygon'");
        }

        @Override
        public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'fillPolygon'");
        }

        @Override
        public void drawString(AttributedCharacterIterator iterator, int x, int y) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawString'");
        }

        @Override
        public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawImage'");
        }

        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawImage'");
        }

        @Override
        public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawImage'");
        }

        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor,
                ImageObserver observer) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawImage'");
        }

        @Override
        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
                ImageObserver observer) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawImage'");
        }

        @Override
        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
                Color bgcolor, ImageObserver observer) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'drawImage'");
        }

        @Override
        public void dispose() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'dispose'");
        }

        // ... [Other overridden methods - same as before] ... 
    }
}
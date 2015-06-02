package projektkurs.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.entity.Entity;
import projektkurs.lib.Integers;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.render.Sprite;

/**
 * Renderhilfen.
 */
public final class RenderUtil {

    /**
     * Die Standard-Hintergrundfarbe.
     */
    private static final int DEFAULT_BACKGROUND_COLOR = 0xFFFFFF;
    /**
     * Tooltip-Farbe.
     */
    private static final int TOOLTIP_COLOR = 0x943F3F;

    /**
     * Malt den Rand.
     *
     * @param screen
     *            Screen
     */
    public static void drawBorder(Screen screen) {
        for (int y = 0; y < Integers.WINDOW_HUD_Y; y++) {
            for (int x = 0; x < Integers.windowX; x++) {
                screen.setPixel(0, x, y);
            }
        }
        for (int y = Integers.WINDOW_HUD_Y; y < Integers.windowY - Integers.WINDOW_HUD_Y; y++) {
            for (int x = 0; x < Integers.WINDOW_HUD_X; x++) {
                screen.setPixel(0, x, y);
            }
        }
        for (int y = Integers.WINDOW_HUD_Y; y < Integers.windowY - Integers.WINDOW_HUD_Y; y++) {
            for (int x = Integers.windowX - Integers.WINDOW_HUD_X; x < Integers.windowX; x++) {
                screen.setPixel(0, x, y);
            }
        }
        for (int y = Integers.windowY - Integers.WINDOW_HUD_Y; y < Integers.windowY; y++) {
            for (int x = 0; x < Integers.windowX; x++) {
                screen.setPixel(0, x, y);
            }
        }
    }

    /**
     * Malt ein Rasterbild in eine CutScene.
     *
     * @param screen
     *            Screen
     * @param sprite
     *            Sprite
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     */
    public static void drawCutSceneRaster(Screen screen, Sprite sprite, int x, int y) {
        drawSprite(screen, sprite, (x - CutSceneManager.getCutSceneRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, (y - CutSceneManager.getCutSceneRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y);
    }

    /**
     * Rendert den Standardhintergrund.
     *
     * @param screen
     *            Screen
     */
    public static void drawDefaultBackground(Screen screen) {
        screen.setColor(DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * Malt einen Entity aufs Spielfeld.
     *
     * @param screen
     *            Screen
     * @param e
     *            Entity
     */
    public static void drawDefaultEntity(Screen screen, Entity e) {
        drawSprite(screen, e.getSprite(), e.getRenderX(), e.getRenderY());
    }

    /**
     * Malt ein Rasterbild aufs Spielfeld.
     *
     * @param screen
     *            Screen
     * @param sprite
     *            Sprite
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     */
    public static void drawDefaultRaster(Screen screen, Sprite sprite, int x, int y) {
        drawSprite(screen, sprite, (x - Main.getRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, (y - Main.getRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y);
    }

    /**
     * Malt ein gefuelltes Rechteck in schwarz.
     *
     * @param screen
     *            Screen
     * @param x
     *            X-Koordinate der linken oberen Ecke
     * @param y
     *            Y-Koordinate der linken oberen Ecke
     * @param width
     *            Breite
     * @param height
     *            Hoehe
     */
    public static void drawFilledRectangle(Screen screen, int x, int y, int width, int height) {
        drawFilledRectangle(screen, x, y, width, height, 0);
    }

    /**
     * Malt ein gefuelltes Rechteck.
     *
     * @param screen
     *            Screen
     * @param x
     *            X-Koordinate der linken oberen Ecke
     * @param y
     *            Y-Koordinate der linken oberen Ecke
     * @param width
     *            Breite
     * @param height
     *            Hoehe
     * @param color
     *            Farbe
     */
    public static void drawFilledRectangle(Screen screen, int x, int y, int width, int height, int color) {
        if (!isTransparent(color)) {
            for (int yy = y; yy < y + height; yy++) {
                for (int xx = x; xx < x + width; xx++) {
                    screen.setPixel(color, xx, yy);
                }
            }
        }
    }

    /**
     * Malt ein BufferedImage auf ein Graphics2D-Objekt.
     *
     * @param g
     *            Graphics2D
     * @param image
     *            BufferedImage
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public static void drawImage(Graphics2D g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    /**
     * Malt ein BufferedImage auf ein Graphics2D-Objekt.
     *
     * @param g
     *            Graphics2D
     * @param image
     *            BufferedImage
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param width
     *            Breite
     * @param height
     *            Hoehe
     */
    public static void drawImage(Graphics2D g, BufferedImage image, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    /**
     * Malt eine schwarze Linie von Punkt A bis Punkt B.
     *
     * @param screen
     *            Screen
     * @param startX
     *            X-Koordinate
     * @param startY
     *            Y-Koordinate
     * @param endX
     *            X-Koordinate
     * @param endY
     *            Y-Koordinate
     */
    public static void drawLine(Screen screen, int startX, int startY, int endX, int endY) {
        drawLine(screen, startX, startY, endX, endY, 0);
    }

    /**
     * Malt eine Linie in der gegebenen Farbe von Punkt A bis Punkt B.
     *
     * @param screen
     *            Screen
     * @param startX
     *            X-Koordinate A
     * @param startY
     *            Y-Koordinate A
     * @param endX
     *            X-Koordinate B
     * @param endY
     *            Y-Koordinate B
     * @param color
     *            Farbe
     */
    public static void drawLine(Screen screen, int startX, int startY, int endX, int endY, int color) {
        if (!isTransparent(color)) {
            if (startX < endX) {
                double m = (endY - startY) / (double) (endX - startX);
                for (int x = 0; x < endX - startX; x++) {
                    screen.setPixel(color, x + startX, MathUtil.round(m * x) + startY);
                }
            } else if (endX < startX) {
                double m = (startY - endY) / (double) (startX - endX);
                for (int x = 0; x < startX - endX; x++) {
                    screen.setPixel(color, x + endX, MathUtil.round(m * x) + startY);
                }
            } else {
                if (startY < endY) {
                    for (int y = startY; y < endY; y++) {
                        screen.setPixel(color, startX, y);
                    }
                } else if (endY < startY) {
                    for (int y = endY; y < startY; y++) {
                        screen.setPixel(color, startX, y);
                    }
                } else {
                    screen.setPixel(color, startX, startY);
                }
            }
        }
    }

    /**
     * Malt ein schwarzes Rechteck an die gegebene Koordinate.
     *
     * @param screen
     *            Screen
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param width
     *            Breite
     * @param height
     *            Hoehe
     */
    public static void drawRectangle(Screen screen, int x, int y, int width, int height) {
        drawRectangle(screen, x, y, width, height, 0);
    }

    /**
     * Malt ein Rechteck mit der gegebenen Farbe an die gegebene Koordinate.
     *
     * @param screen
     *            Screen
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param width
     *            Breite
     * @param height
     *            Hoehe
     * @param color
     *            Farbe
     */
    public static void drawRectangle(Screen screen, int x, int y, int width, int height, int color) {
        if (!isTransparent(color)) {
            drawLine(screen, x, y, x + width, y, color);
            drawLine(screen, x, y + height, x + width, y + height, color);
            drawLine(screen, x, y, x, y + height, color);
            drawLine(screen, x + width, y, x + width, y + height, color);
        }
    }

    /**
     * Malt einen Sprite an die gegebenen Koordinaten.
     *
     * @param screen
     *            Screen
     * @param sprite
     *            Sprite
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public static void drawSprite(Screen screen, Sprite sprite, int x, int y) {
        int pixel;
        for (int yy = 0; yy < sprite.getSizeY(); yy++) {
            for (int xx = 0; xx < sprite.getSizeX(); xx++) {
                pixel = sprite.getPixel(xx, yy);
                if (!isTransparent(pixel)) {
                    screen.setPixel(pixel, x + xx, y + yy);
                }
            }
        }
    }

    /**
     * Rendert einen Tooltip an die gegebene Position.
     *
     * @param g
     *            Graphics2D
     * @param str
     *            Tooltip
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public static void drawTooltip(Screen screen, List<String> tooltip, int x, int y) {
        int height = 0;
        int width = 0;
        for (String string : tooltip) {
            height += Font.getStringHeight(string) + 2;
            width = Math.max(width, Font.getStringWidth(string) + 4);
        }
        drawFilledRectangle(screen, x, y - height, width, height, TOOLTIP_COLOR);
        drawRectangle(screen, x, y - height, width, height);
        int offsetY = 0;
        for (String string : tooltip) {
            Font.drawString(screen, string, x - 7, y - height - 1 + offsetY);
            offsetY += Font.getStringHeight(string) + 2;
        }
    }

    public static int getBlue(int color) {
        return color & 255;
    }

    public static double getBlueD(int color) {
        return getBlue(color) / 255D;
    }

    public static int getColor(double r, double g, double b) {
        return getColor(MathUtil.floorMul(r, 255), MathUtil.floorMul(g, 255), MathUtil.floorMul(b, 255));
    }

    public static int getColor(int r, int g, int b) {
        int color = 0;
        color |= r << 16 & 0xFF0000;
        color |= g << 8 & 0xFF00;
        color |= b & 0xFF;
        return color;
    }

    public static int getGreen(int color) {
        return color >> 8 & 255;
    }

    public static double getGreenD(int color) {
        return getGreen(color) / 255D;
    }

    public static int getRed(int color) {
        return color >> 16 & 255;
    }

    public static double getRedD(int color) {
        return getRed(color) / 255D;
    }

    public static int interpolate(int colA, int colB, double fraction) {

        fraction = MathUtil.clamp(fraction, 0, 1);

        double red1 = getRedD(colA);
        double green1 = getGreenD(colA);
        double blue1 = getBlueD(colA);

        double deltaRed = getRedD(colB) - red1;
        double deltaGreen = getGreenD(colB) - green1;
        double deltaBlue = getBlueD(colB) - blue1;

        double red = red1 + deltaRed * fraction;
        double green = green1 + deltaGreen * fraction;
        double blue = blue1 + deltaBlue * fraction;

        return getColor(MathUtil.clamp(red, 0, 1), MathUtil.clamp(green, 0, 1), MathUtil.clamp(blue, 0, 1));
    }

    public static int interpolate(int colA, int colB, int colC, int colD, double fractionX, double fractionY) {
        return interpolate(interpolate(colA, colB, fractionX), interpolate(colC, colD, fractionX), fractionY);
    }

    public static boolean isTransparent(int color) {
        return (color & 0xFFFFFF) == Integers.TRANSPARENCY;
    }

    /**
     * Nicht instanziierbar.
     */
    private RenderUtil() {
    }
}

package projektkurs.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.entity.Entity;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.render.Screen;
import projektkurs.render.Sprite;
import projektkurs.util.Init.State;

/**
 * Renderhilfen.
 */
public final class RenderUtil {

    /**
     * Tooltip-Farbe.
     */
    public static final Color TOOLTIP_COLOR = new Color(0x94, 0x3F, 0x3F);
    /**
     * Die Standard-Hintergrundfarbe.
     */
    private static final int DEFAULT_BACKGROUND_COLOR = 0xFFFFFF;
    /**
     * Alle benutzten Schriften.
     */
    private static final Font[] FONTS = new Font[Integers.MAX_FONT_SIZE];

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
            for (int x = 0; x < Integers.WINDOW_HUD_X; x++) {
                screen.setPixel(0, x, y);
            }
        }
    }

    /**
     * Schreibt einen zentrierten String an die gegebenen Koordinaten.
     *
     * @param screen
     *            Screen
     * @param s
     *            String
     * @param centerX
     *            X-Koordinate des Mittelpunktes
     * @param centerY
     *            Y-Koordinate des Mittelpunktes
     */
    public static void drawCenteredString(Screen screen, String s, int centerX, int centerY) {
        // screen.drawGlyphVector(screen.getFont().createGlyphVector(screen.getFontRenderContext(), s), centerX - MathUtil.floorDiv(screen.getFontMetrics().stringWidth(s), 2), centerY + MathUtil.ceilDiv(screen.getFontMetrics().getHeight(), 4));
    }

    /**
     * Schreibt einen zentrierten String an die gegebenen Koordinaten.
     *
     * @param g
     *            Graphics2D
     * @param s
     *            String
     * @param centerX
     *            X-Koordinate des Mittelpunktes
     * @param centerY
     *            Y-Koordinate des Mittelpunktes
     * @param size
     *            die Groeße der Schrift
     */
    public static void drawCenteredString(Screen screen, String s, int centerX, int centerY, int size) {
        // Font oldfont = g.getFont();
        // g.setFont(FONTS[MathUtil.clampToArray(size, FONTS.length)]);
        // drawCenteredString(g, s, centerX, centerY);
        // g.setFont(oldfont);
    }

    /**
     * Schreibt einen zentrierten String in ein gegebenes Rechteck.
     *
     * @param screen
     *            Screen
     * @param s
     *            String
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     */
    public static void drawCenteredStringInRect(Screen screen, String s, int posX, int posY, int sizeX, int sizeY) {
        // Font oldfont = screen.getFont();
        // int size = Integers.DEFAULT_FONT_SIZE;
        // screen.setFont(FONTS[MathUtil.clampToArray(size, FONTS.length)]);
        // while (size > 1 && (screen.getFontMetrics().stringWidth(s) >= sizeX || screen.getFontMetrics().getHeight() >= sizeY)) {
        // screen.setFont(FONTS[MathUtil.clampToArray(--size, FONTS.length)]);
        // }
        // drawCenteredString(screen, s, posX + MathUtil.roundDiv(sizeX, 2), posY + MathUtil.roundDiv(sizeY, 2));
        // screen.setFont(oldfont);
    }

    /**
     * Schreibt einen zentrierten String in ein gegebenes Rechteck.
     *
     * @param g
     *            Graphics2D
     * @param s
     *            String
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     * @param size
     *            gewuenschte Schriftgroeße
     */
    public static void drawCenteredStringInRect(Screen screen, String s, int posX, int posY, int sizeX, int sizeY, int size) {
        // Font oldfont = g.getFont();
        // g.setFont(FONTS[MathUtil.clampToArray(size, FONTS.length)]);
        // while (size > 1 && (g.getFontMetrics().stringWidth(s) >= sizeX || g.getFontMetrics().getHeight() >= sizeY)) {
        // g.setFont(FONTS[MathUtil.clampToArray(--size, FONTS.length)]);
        // }
        // drawCenteredString(g, s, posX + MathUtil.roundDiv(sizeX, 2), posY + MathUtil.roundDiv(sizeY, 2));
        // g.setFont(oldfont);
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
     * Malt ein gefuelltes Rechteck.
     *
     * @param g
     *            Graphics2D
     * @param c
     *            Farbe
     * @param x
     *            X-Koordinate der linken oberen Ecke
     * @param y
     *            Y-Koordinate der linken oberen Ecke
     * @param width
     *            Breite
     * @param height
     *            Hoehe
     */
    public static void drawFilledRectangle(Screen screen, int color, int x, int y, int width, int height) {
        if (color != Integers.TRANSPARENCY) {
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
                if (pixel != Integers.TRANSPARENCY) {
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
    public static void drawTooltip(Screen screen, String str, int x, int y) {
        // Color oldColor = g.getColor();
        // Font oldfont = g.getFont();
        // g.setFont(FONTS[Integers.DEFAULT_FONT_SIZE]);
        // int height = g.getFontMetrics().getHeight();
        // int width = g.getFontMetrics().stringWidth(str);
        // g.setColor(TOOLTIP_COLOR);
        // g.fillRoundRect(x, y - height, width + 4, height, MathUtil.ceilDiv(width, 2), MathUtil.ceilDiv(height, 2));
        // g.setColor(Color.BLACK);
        // g.drawRoundRect(x, y - height, width + 4, height, MathUtil.ceilDiv(width, 2), MathUtil.ceilDiv(height, 2));
        // RenderUtil.drawCenteredStringInRect(g, str, x, y - height, width, height);
        // g.setFont(oldfont);
        // g.setColor(oldColor);
    }

    /**
     * Initialisierung.
     */
    @Init(state = State.POST)
    public static void init() {
        for (int i = 0; i < FONTS.length; i++) {
            FONTS[i] = new Font(Strings.NAME, Font.PLAIN, i);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private RenderUtil() {
    }
}

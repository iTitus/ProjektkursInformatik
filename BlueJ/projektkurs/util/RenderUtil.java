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
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(127, 127, 127, 156);
    /**
     * Alle benutzten Schriften.
     */
    private static final Font[] FONTS = new Font[Integers.MAX_FONT_SIZE];

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
     */
    public static void drawCenteredString(Graphics2D g, String s, int centerX, int centerY) {
        g.drawGlyphVector(g.getFont().createGlyphVector(g.getFontRenderContext(), s), centerX - MathUtil.floorDiv(g.getFontMetrics().stringWidth(s), 2), centerY + MathUtil.ceilDiv(g.getFontMetrics().getHeight(), 4));
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
     *            die Größe der Schrift
     */
    public static void drawCenteredString(Graphics2D g, String s, int centerX, int centerY, int size) {
        Font oldfont = g.getFont();
        g.setFont(FONTS[MathUtil.limitArray(size, FONTS.length)]);
        drawCenteredString(g, s, centerX, centerY);
        g.setFont(oldfont);
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
     *            Höhe
     */
    public static void drawCenteredStringInRect(Graphics2D g, String s, int posX, int posY, int sizeX, int sizeY) {
        Font oldfont = g.getFont();
        int size = Integers.DEFAULT_FONT_SIZE;
        g.setFont(FONTS[MathUtil.limitArray(size, FONTS.length)]);
        while (size > 1 && (g.getFontMetrics().stringWidth(s) >= sizeX || g.getFontMetrics().getHeight() >= sizeY)) {
            g.setFont(FONTS[MathUtil.limitArray(--size, FONTS.length)]);
        }
        drawCenteredString(g, s, posX + MathUtil.roundDiv(sizeX, 2), posY + MathUtil.roundDiv(sizeY, 2));
        g.setFont(oldfont);
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
     *            Höhe
     * @param size
     *            gewünschte Schriftgröße
     */
    public static void drawCenteredStringInRect(Graphics2D g, String s, int posX, int posY, int sizeX, int sizeY, int size) {
        Font oldfont = g.getFont();
        g.setFont(FONTS[MathUtil.limitArray(size, FONTS.length)]);
        while (size > 1 && (g.getFontMetrics().stringWidth(s) >= sizeX || g.getFontMetrics().getHeight() >= sizeY)) {
            g.setFont(FONTS[MathUtil.limitArray(--size, FONTS.length)]);
        }
        drawCenteredString(g, s, posX + MathUtil.roundDiv(sizeX, 2), posY + MathUtil.roundDiv(sizeY, 2));
        g.setFont(oldfont);
    }

    /**
     * Malt ein Rasterbild in eine CutScene.
     *
     * @param g
     *            Graphics2D
     * @param image
     *            Rasterbild
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     */
    public static void drawCutSceneRaster(Graphics2D g, BufferedImage image, int x, int y) {
        drawImage(g, image, (x - CutSceneManager.getCutSceneRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, (y - CutSceneManager.getCutSceneRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE, Integers.RASTER_SIZE);
    }

    /**
     * Rendert den Standardhintergrund.
     *
     * @param g
     *            Graphics2D Objekt
     */
    public static void drawDefaultBackground(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(DEFAULT_BACKGROUND_COLOR);
        g.fillRect(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.sightX * Integers.RASTER_SIZE, Integers.sightY * Integers.RASTER_SIZE);
        g.setColor(oldColor);
    }

    /**
     * Malt einen Entity aufs Spielfeld.
     *
     * @param g
     *            Graphics2D
     * @param e
     *            Entity
     */
    public static void drawDefaultEntity(Graphics2D g, Entity e) {
        drawImage(g, e.getImage(), e.getRenderX(), e.getRenderY(), e.getSizeX() * Integers.RASTER_SIZE, e.getSizeY() * Integers.RASTER_SIZE);
    }

    /**
     * Malt ein Rasterbild aufs Spielfeld.
     *
     * @param g
     *            Graphics2D
     * @param img
     *            Rasterbild
     * @param x
     *            X-Koordinate des Rasters
     * @param y
     *            Y-Koordinate des Rasters
     */
    public static void drawDefaultRaster(Graphics2D g, BufferedImage img, int x, int y) {
        drawImage(g, img, (x - Main.getRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, (y - Main.getRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE, Integers.RASTER_SIZE);
    }

    /**
     * Malt ein Bild an die gegebenen Koordinaten.
     *
     * @param g
     *            Graphics2D
     * @param img
     *            Bild
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public static void drawImage(Graphics2D g, BufferedImage img, int x, int y) {
        drawImage(g, img, x, y, img.getWidth(), img.getHeight());
    }

    /**
     * Malt ein Bild an die gegebenen Koordinaten.
     *
     * @param g
     *            Graphics2D
     * @param img
     *            Bild
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @param width
     *            Breite
     * @param height
     *            Höhe
     */
    public static void drawImage(Graphics2D g, BufferedImage img, int x, int y, int width, int height) {
        g.drawImage(img, x, y, width, height, null);
    }

    /**
     * Schreibt einen String an die gegebenen Koordinaten.
     *
     * @param g
     *            Graphics2D
     * @param string
     *            String
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     */
    public static void drawString(Graphics2D g, String string, int posX, int posY) {
        g.drawGlyphVector(g.getFont().createGlyphVector(g.getFontRenderContext(), string), posX, posY);
    }

    /**
     * Schreibt einen String an die gegebenen Koordinaten.
     *
     * @param g
     *            Graphics2D
     * @param string
     *            String
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param size
     *            Schriftgröße
     */
    public static void drawString(Graphics2D g, String string, int posX, int posY, int size) {
        Font oldfont = g.getFont();
        g.setFont(FONTS[MathUtil.limitArray(size, FONTS.length)]);
        g.drawGlyphVector(g.getFont().createGlyphVector(g.getFontRenderContext(), string), posX, posY);
        g.setFont(oldfont);
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
    public static void drawTooltip(Graphics2D g, String str, int x, int y) {
        Color oldColor = g.getColor();
        Font oldfont = g.getFont();
        g.setFont(FONTS[Integers.DEFAULT_FONT_SIZE]);
        int height = g.getFontMetrics().getHeight();
        int width = g.getFontMetrics().stringWidth(str);
        g.setColor(TOOLTIP_COLOR);
        g.fillRoundRect(x, y - height, width + 4, height, MathUtil.ceilDiv(width, 2), MathUtil.ceilDiv(height, 2));
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y - height, width + 4, height, MathUtil.ceilDiv(width, 2), MathUtil.ceilDiv(height, 2));
        RenderUtil.drawCenteredStringInRect(g, str, x, y - height, width, height);
        g.setFont(oldfont);
        g.setColor(oldColor);
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

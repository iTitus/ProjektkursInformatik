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

/**
 * Renderhilfen.
 */
public final class RenderUtil {

    /**
     * Tooltip-Farbe.
     */
    public static final Color  TOOLTIP_COLOR            = new Color(0x94, 0x3F, 0x3F);
    /**
     * Die Standard-Hintergrundfarbe.
     */
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(127, 127, 127, 156);

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
        g.drawGlyphVector(g.getFont().createGlyphVector(g.getFontRenderContext(), s), centerX - MathUtil.floorDiv(g.getFontMetrics().stringWidth(s), 2),
                centerY + MathUtil.ceilDiv(g.getFontMetrics().getHeight(), 4));
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
        g.setFont(new Font(Strings.NAME, Font.PLAIN, size));
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
        g.setFont(new Font(Strings.NAME, Font.PLAIN, 20));
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
        drawImage(g, image, (x - CutSceneManager.getCurrentCutSceneRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X,
                (y - CutSceneManager.getCurrentCutSceneRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE,
                Integers.RASTER_SIZE);
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
        drawImage(g, img, (x - Main.getRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, (y - Main.getRenderHelper().getSightY())
                * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE, Integers.RASTER_SIZE);
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
        int height = g.getFontMetrics().getHeight();
        int width = g.getFontMetrics().stringWidth(str);
        g.setColor(TOOLTIP_COLOR);
        g.fillRoundRect(x, y - height, width + 4, height, MathUtil.ceilDiv(width, 2), MathUtil.ceilDiv(height, 2));
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y - height, width + 4, height, MathUtil.ceilDiv(width, 2), MathUtil.ceilDiv(height, 2));
        g.setColor(oldColor);
        RenderUtil.drawString(g, str, x + 2, y - 3);
    }

    /**
     * Nicht instanziierbar.
     */
    private RenderUtil() {
    }
}

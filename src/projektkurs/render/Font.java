package projektkurs.render;

import java.util.Arrays;

import projektkurs.lib.SpriteSheets;
import projektkurs.lib.Sprites;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.MathUtil;

/**
 * Textrendering.
 */
public class Font {

    private static int[] carriagePerCharacter = new int[256];
    private static Sprite[] characters;

    private static SpriteSheet fontSheet;

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
        drawCenteredString(screen, s, centerX, centerY, 0);
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
     * @param color
     *            die Farbe der Schrift
     */
    public static void drawCenteredString(Screen screen, String s, int centerX, int centerY, int color) {
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
        drawCenteredStringInRect(screen, s, posX, posY, sizeX, sizeY, 0);
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
     * @param color
     *            Schriftfarbe
     */
    public static void drawCenteredStringInRect(Screen screen, String s, int posX, int posY, int sizeX, int sizeY, int color) {
        // Font oldfont = g.getFont();
        // g.setFont(FONTS[MathUtil.clampToArray(size, FONTS.length)]);
        // while (size > 1 && (g.getFontMetrics().stringWidth(s) >= sizeX || g.getFontMetrics().getHeight() >= sizeY)) {
        // g.setFont(FONTS[MathUtil.clampToArray(--size, FONTS.length)]);
        // }
        // drawCenteredString(g, s, posX + MathUtil.roundDiv(sizeX, 2), posY + MathUtil.roundDiv(sizeY, 2));
        // g.setFont(oldfont);
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
    public static void drawString(Screen screen, String string, int posX, int posY) {
        drawString(screen, string, posX, posY, 0);
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
     * @param color
     *            Farbe
     */
    public static void drawString(Screen screen, String string, int posX, int posY, int color) {
        if (string != null && string.length() > 0) {
            int offset = 0;
            for (int i = 0; i < string.length(); i++) {
                int character = string.charAt(i);
                if (MathUtil.isInArray(character, characters.length)) {
                    offset += carriagePerCharacter[character];
                    for (int yy = 0; yy < characters[character].getSizeY(); yy++) {
                        for (int xx = 0; xx < characters[character].getSizeX(); xx++) {
                            if (characters[character].getPixel(xx, yy) == 0xFF000000) {
                                screen.setPixel(color, posX + offset + xx, posY + yy);
                            }
                        }
                    }
                }
            }
        }
    }

    @Init(state = State.RESOURCES)
    public static void init() {

        fontSheet = new SpriteSheet("font", "font.png");
        SpriteSheets.registerSpriteSheet(fontSheet);

        characters = fontSheet.split(32, 32);
        for (Sprite sprite : characters) {
            Sprites.registerSprite(sprite);
        }

        Arrays.fill(carriagePerCharacter, 10);

    }

}

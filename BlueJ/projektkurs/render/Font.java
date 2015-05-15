package projektkurs.render;

import java.util.Arrays;

import projektkurs.lib.SpriteSheets;
import projektkurs.lib.Sprites;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Textrendering.
 */
public class Font {

    private static int[] carriagePerCharacter;
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
        drawString(screen, s, centerX - MathUtil.floorDiv(getStringWidth(s), 2), centerY - MathUtil.floorDiv(getStringHeight(s), 2) - 3, color);
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
        drawCenteredString(screen, s, posX + MathUtil.floorDiv(sizeX, 2), posY + MathUtil.floorDiv(sizeY, 2), color);
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
        if (!RenderUtil.isTransparent(color) && string != null && string.length() > 0) {
            int offset = 0;
            for (int i = 0; i < string.length(); i++) {
                int character = string.charAt(i);
                if (MathUtil.isInArray(character, characters.length)) {
                    offset += carriagePerCharacter[character];
                    for (int yy = 0; yy < characters[character].getSizeY(); yy++) {
                        for (int xx = 0; xx < characters[character].getSizeX(); xx++) {
                            if (characters[character].getPixel(xx, yy) == 0) {
                                screen.setPixel(color, posX + offset + xx, posY + yy);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Die Hoehe des Strings.
     *
     * @param s
     *            String
     * @return Hoehe des Strings
     */
    public static int getStringHeight(String s) {
        return 16;
    }

    /**
     * Die Breite des Strings.
     *
     * @param s
     *            String
     * @return Breite des Strings
     */
    public static int getStringWidth(String s) {
        int width = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (MathUtil.isInArray(c, characters.length)) {
                width += carriagePerCharacter[c];
            }
        }
        return width;
    }

    @Init(State.RESOURCES)
    public static void init() {

        fontSheet = new SpriteSheet("font", "font.png");
        SpriteSheets.registerSpriteSheet(fontSheet);

        characters = fontSheet.split(32, 32);
        for (Sprite sprite : characters) {
            Sprites.registerSprite(sprite);
        }

        carriagePerCharacter = new int[characters.length];
        Arrays.fill(carriagePerCharacter, 9);

    }

}

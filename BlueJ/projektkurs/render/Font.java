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

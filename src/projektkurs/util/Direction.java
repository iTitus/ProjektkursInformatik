package projektkurs.util;

/**
 * Alle Richtungen.
 */
public enum Direction {

    /**
     * Nach unten.
     */
    DOWN(0, 1),
    /**
     * Nach links-unten.
     */
    DOWN_LEFT(-1, 1),
    /**
     * Nach rechts-unten.
     */
    DOWN_RIGHT(1, 1),
    /**
     * Nach links.
     */
    LEFT(-1, 0),
    /**
     * Nach rechts.
     */
    RIGHT(1, 0),
    /**
     * Unbekannte Richtung.
     */
    UNKNOWN(0, 0),
    /**
     * Nach oben.
     */
    UP(0, -1),
    /**
     * Nach links-oben.
     */
    UP_LEFT(-1, -1),
    /**
     * Nach links-oben.
     */
    UP_RIGHT(1, -1);

    /**
     * Die Kardinalen-Richtungen: Nach oben, nach rechts, nach unten und nach links.
     */
    public static final Direction[] CARDINAL_DIRECTIONS = new Direction[] { UP, RIGHT, DOWN, LEFT };
    /**
     * Alle Richtungen, außer der ungültigen Richtung.
     */
    public static final Direction[] VALID_DIRECTIONS = new Direction[] { UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT };
    /**
     * Tabelle - wird benutzt um die Offset-Werte zu dekodieren.
     */
    private static final Direction[] TABLE = { UP_LEFT, UP, UP_RIGHT, LEFT, UNKNOWN, RIGHT, DOWN_LEFT, DOWN, DOWN_RIGHT };

    /**
     * Richtung für die gegebenen Offset-Werte.
     *
     * @param offsetX
     *            X-Offset
     * @param offsetY
     *            Y-Offset
     * @return Richtung für die gegebenen Offset-Werte
     */
    public static Direction getDirectionForOffset(int offsetX, int offsetY) {
        if (offsetX < -1 || offsetX > 1 || offsetY < -1 || offsetY > 1) {
            return UNKNOWN;
        }
        return TABLE[offsetX + 1 + (offsetY + 1) * 3];
    }

    /**
     * X-Offset.
     */
    private final int offsetX;
    /**
     * Y-Offset.
     */
    private final int offsetY;

    /**
     * Privater Konstruktor.
     *
     * @param offsetX
     *            X-Offset
     * @param offsetY
     *            Y-Offset
     */
    private Direction(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * X-Offset.
     *
     * @return X-Offset
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * Y-Offset.
     *
     * @return Y-Offset
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * Die entgegengesetzte Richtung.
     *
     * @return die entgegengesetzte Richtung
     */
    public Direction getOpposite() {
        return getDirectionForOffset(-offsetX, -offsetY);
    }

}

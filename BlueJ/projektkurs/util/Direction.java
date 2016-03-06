package projektkurs.util;

/**
 * Alle Richtungen.
 */
public enum Direction {

    /**
     * Nach unten.
     */
    DOWN(4, 0, 1),
    /**
     * Nach links-unten.
     */
    DOWN_LEFT(5, -1, 1),
    /**
     * Nach rechts-unten.
     */
    DOWN_RIGHT(3, 1, 1),
    /**
     * Nach links.
     */
    LEFT(6, -1, 0),
    /**
     * Nach rechts.
     */
    RIGHT(2, 1, 0),
    /**
     * Unbekannte Richtung.
     */
    UNKNOWN(-1, 0, 0),
    /**
     * Nach oben.
     */
    UP(0, 0, -1),
    /**
     * Nach links-oben.
     */
    UP_LEFT(7, -1, -1),
    /**
     * Nach links-oben.
     */
    UP_RIGHT(1, 1, -1);

    /**
     * Die Kardinalen-Richtungen: Nach oben, nach rechts, nach unten und nach links.
     */
    public static final Direction[] CARDINAL_DIRECTIONS = { UP, RIGHT, DOWN, LEFT };
    /**
     * Alle Richtungen, ausser der ungueltigen Richtung.
     */
    public static final Direction[] VALID_DIRECTIONS = { UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT };
    /**
     * Matrix - wird benutzt um die Offset-Werte zu dekodieren.
     */
    private static final Direction[] OFFSET_MATRIX = { UP_LEFT, UP, UP_RIGHT, LEFT, UNKNOWN, RIGHT, DOWN_LEFT, DOWN, DOWN_RIGHT };
    private static final Direction[][][] ROTATION_MATRIX = { { { UP_LEFT, UP_RIGHT }, { LEFT, RIGHT } }, { { UP, RIGHT }, { UP_LEFT, DOWN_RIGHT } }, { { UP_RIGHT, DOWN_RIGHT }, { UP, DOWN } }, { { RIGHT, DOWN }, { UP_RIGHT, DOWN_LEFT } }, { { DOWN_RIGHT, DOWN_LEFT }, { RIGHT, LEFT } },
            { { DOWN, LEFT }, { DOWN_RIGHT, UP_LEFT } }, { { DOWN_LEFT, UP_LEFT }, { DOWN, UP } }, { { LEFT, UP }, { DOWN_LEFT, UP_RIGHT } } };

    /**
     * Direction die zu dem gegebenen Index gehoert.
     * @param index
     * Index
     * @return Direction
     */
            public static Direction getDirectionForIndex(int index) {
        if (MathUtil.isInArray(index, VALID_DIRECTIONS.length)) {
            return VALID_DIRECTIONS[index];
        }
        return UNKNOWN;
    }

    /**
     * Richtung fuer die gegebenen Offset-Werte.
     * @param offsetX
     * X-Offset
     * @param offsetY
     * Y-Offset
     * @return Richtung fuer die gegebenen Offset-Werte
     */
    public static Direction getDirectionForOffset(int offsetX, int offsetY) {
        if (offsetX < -1 || offsetX > 1 || offsetY < -1 || offsetY > 1) {
            return UNKNOWN;
        }
        return OFFSET_MATRIX[offsetX + 1 + (offsetY + 1) * 3];
    }

    /**
     * Index.
     */
    private final int index;

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
     * @param offsetX
     * X-Offset
     * @param offsetY
     * Y-Offset
     */
    private Direction(int index, int offsetX, int offsetY) {
        this.index = index;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * Index.
     * @return Index
     */
    public int getIndex() {
        return index;
    }

    /**
     * X-Offset.
     * @return X-Offset
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * Y-Offset.
     * @return Y-Offset
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * Die entgegengesetzte Richtung.
     * @return die entgegengesetzte Richtung
     */
    public Direction getOpposite() {
        return getDirectionForOffset(-offsetX, -offsetY);
    }

    public Direction rotate(boolean cardinal, boolean clockwise) {
        if (index < 0) {
            return this;
        }
        return ROTATION_MATRIX[index][cardinal ? 1 : 0][clockwise ? 1 : 0];
    }
}

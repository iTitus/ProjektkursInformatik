package projektkurs.util;

/**
 * Alle Richtungen
 *
 */
public enum Direction {

	DOWN(0, 1), DOWN_LEFT(-1, 1), DOWN_RIGHT(1, 1), LEFT(-1, 0), RIGHT(1, 0), UNKNOWN(
			0, 0), UP(0, -1), UP_LEFT(-1, -1), UP_RIGHT(1, -1);

	public static final Direction[] CARDINAL_DIRECTIONS = new Direction[] { UP,
			RIGHT, DOWN, LEFT };
	public static final Direction[] VALID_DIRECTIONS = new Direction[] { UP,
			UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT };

	private static final Direction[][] TABLE = { { UP_LEFT, LEFT, DOWN_LEFT },
			{ UP, UNKNOWN, DOWN }, { UP_RIGHT, RIGHT, DOWN_RIGHT } };

	public static Direction getDirectionForOffset(int offsetX, int offsetY) {
		if (offsetX < -1 || offsetX > 1 || offsetY < -1 || offsetY > 1)
			return UNKNOWN;
		return TABLE[offsetX + 1][offsetY + 1];
	}

	private final int offsetX, offsetY;

	private Direction(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public Direction getOpposite() {
		return getDirectionForOffset(-offsetX, -offsetY);
	}

}

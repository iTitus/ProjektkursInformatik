package projektkurs.lib;

/**
 * Alle Richtungen
 *
 */
public enum Direction {

	DOWN(0, 1), DOWN_LEFT(-1, 1), DOWN_RIGHT(1, 1), LEFT(-1, 0), RIGHT(1, 0), UNKNOWN(
			0, 0), UP(0, -1), UP_LEFT(-1, -1), UP_RIGHT(1, -1);

	private static final Direction[][] table = { { UP_LEFT, LEFT, DOWN_LEFT },
			{ UP, UNKNOWN, DOWN }, { UP_RIGHT, RIGHT, DOWN_RIGHT } };

	public static Direction getDirectionForOffset(int offsetX, int offsetY) {
		if (offsetX < -1 || offsetX > 1 || offsetY < -1 || offsetY > 1)
			return UNKNOWN;
		return table[offsetX + 1][offsetY + 1];
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
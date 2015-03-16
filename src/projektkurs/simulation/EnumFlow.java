package projektkurs.simulation;

import projektkurs.util.Direction;

public enum EnumFlow {

    EAST(2, 1, 0, "E"), NONE(0, 0, 0, "None"), NORTH(1, 0, -1, "N"), OMNI_DIRECTIONAL(-1, 0, 0, "Omni"), SOUTH(3, 0, 1, "S"), WEST(4, -1, 0, "W");

    public static final EnumFlow[] VALID_FLOWS = { NORTH, EAST, SOUTH, WEST };
    private static final EnumFlow[] TABLE = { NONE, NORTH, NONE, WEST, NONE, EAST, NONE, SOUTH, NONE };

    public static EnumFlow getFlowForOffset(int offsetX, int offsetY) {
        if (offsetX < -1 || offsetX > 1 || offsetY < -1 || offsetY > 1) {
            return NONE;
        }
        return TABLE[offsetX + 1 + (offsetY + 1) * 3];
    }

    private final int index;
    private final String name;
    private final int offsetX;

    private final int offsetY;

    private EnumFlow(int index, int offsetX, int offsetY, String name) {
        this.index = index;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.name = name;
    }

    public Direction getDirection() {
        return Direction.getDirectionForOffset(offsetX, offsetY);
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public EnumFlow getOpposite() {
        return getFlowForOffset(-offsetX, -offsetY);
    }

}

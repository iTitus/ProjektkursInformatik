package projektkurs.simulation.logic.rule;

import projektkurs.render.Screen;
import projektkurs.simulation.logic.EnumConnectionType;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
import projektkurs.simulation.logic.gui.ElementLogicBoard;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;

public class SimpleWireRule extends Rule {

    /**
     * true: WE; false: NS
     */
    private final boolean orientation;

    public SimpleWireRule(boolean dir) {
        orientation = dir;
    }

    @Override
    public Rule copy() {
        return new SimpleWireRule(orientation);
    }

    @Override
    public int getColor(LogicBoard b, int x, int y) {
        switch (b.getFlow(x, y)) {
            case OMNI_DIRECTIONAL:
                return 0xCCCCCC;
            case NONE:
                return 0x000000;
            default:
                return 0x00FF00;
        }
    }

    @Override
    public EnumConnectionType getConnectionType(LogicBoard b, int x, int y, Direction direction) {
        if (orientation) {
            return direction == Direction.LEFT || direction == Direction.RIGHT ? EnumConnectionType.BOTH : EnumConnectionType.DISCONNECTED;
        }
        return direction == Direction.UP || direction == Direction.DOWN ? EnumConnectionType.BOTH : EnumConnectionType.DISCONNECTED;
    }

    @Override
    public String getName() {
        return "Wire: " + (orientation ? "WE" : "NS");
    }

    @Override
    public EnumFlow getNextFlow(LogicBoard b, int x, int y) {

        EnumFlow ret = EnumFlow.NONE;

        if (orientation) {
            EnumFlow i = b.getFlow(x + 1, y);
            EnumFlow j = b.getFlow(x - 1, y);
            if (j == EnumFlow.OMNI_DIRECTIONAL && i == EnumFlow.OMNI_DIRECTIONAL) {
                ret = EnumFlow.OMNI_DIRECTIONAL;
            } else if (j == EnumFlow.OMNI_DIRECTIONAL) {
                if (i == EnumFlow.WEST) {
                    ret = EnumFlow.OMNI_DIRECTIONAL;
                } else {
                    ret = EnumFlow.EAST;
                }
            } else if (i == EnumFlow.OMNI_DIRECTIONAL) {
                if (j == EnumFlow.EAST) {
                    ret = EnumFlow.OMNI_DIRECTIONAL;
                } else {
                    ret = EnumFlow.WEST;
                }
            } else if (i == EnumFlow.WEST && j == EnumFlow.EAST) {
                ret = EnumFlow.OMNI_DIRECTIONAL;
            } else if (i == EnumFlow.WEST) {
                ret = EnumFlow.WEST;
            } else if (j == EnumFlow.EAST) {
                ret = EnumFlow.EAST;
            }
        } else {
            EnumFlow k = b.getFlow(x, y + 1);
            EnumFlow l = b.getFlow(x, y - 1);
            if (l == EnumFlow.OMNI_DIRECTIONAL && k == EnumFlow.OMNI_DIRECTIONAL) {
                ret = EnumFlow.OMNI_DIRECTIONAL;
            } else if (l == EnumFlow.OMNI_DIRECTIONAL) {
                if (k == EnumFlow.NORTH) {
                    ret = EnumFlow.OMNI_DIRECTIONAL;
                } else {
                    ret = EnumFlow.SOUTH;
                }
            } else if (k == EnumFlow.OMNI_DIRECTIONAL) {
                if (l == EnumFlow.SOUTH) {
                    ret = EnumFlow.OMNI_DIRECTIONAL;
                } else {
                    ret = EnumFlow.NORTH;
                }
            } else if (k == EnumFlow.NORTH && l == EnumFlow.SOUTH) {
                ret = EnumFlow.OMNI_DIRECTIONAL;
            } else if (k == EnumFlow.NORTH) {
                ret = EnumFlow.NORTH;
            } else if (l == EnumFlow.SOUTH) {
                ret = EnumFlow.SOUTH;
            }
        }

        return ret;
    }

    @Override
    public void render(Screen screen, LogicBoard logicBoard, int x, int y, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, x * ElementLogicBoard.SIZE + offsetX + 6, y * ElementLogicBoard.SIZE + offsetY + 6, 4, 4, getColor(logicBoard, x, y));

        if (orientation) {
            EnumFlow flow = EnumFlow.EAST;
            EnumConnectionType type = logicBoard.getRule(x + flow.getOffsetX(), y + flow.getOffsetY()).getConnectionType(logicBoard, x + flow.getOffsetX(), y + flow.getOffsetY(), flow.getOpposite().getDirection());
            if (type != EnumConnectionType.DISCONNECTED) {
                RenderUtil.drawFilledRectangle(screen, x * ElementLogicBoard.SIZE + offsetX + 10, y * ElementLogicBoard.SIZE + offsetY + 6, 6, 4, getColor(logicBoard, x, y));
            }

            flow = EnumFlow.WEST;
            type = logicBoard.getRule(x + flow.getOffsetX(), y + flow.getOffsetY()).getConnectionType(logicBoard, x + flow.getOffsetX(), y + flow.getOffsetY(), flow.getOpposite().getDirection());
            if (type != EnumConnectionType.DISCONNECTED) {
                RenderUtil.drawFilledRectangle(screen, x * ElementLogicBoard.SIZE + offsetX + 0, y * ElementLogicBoard.SIZE + offsetY + 6, 6, 4, getColor(logicBoard, x, y));
            }
        } else {
            EnumFlow flow = EnumFlow.NORTH;
            EnumConnectionType type = logicBoard.getRule(x + flow.getOffsetX(), y + flow.getOffsetY()).getConnectionType(logicBoard, x + flow.getOffsetX(), y + flow.getOffsetY(), flow.getOpposite().getDirection());
            if (type != EnumConnectionType.DISCONNECTED) {
                RenderUtil.drawFilledRectangle(screen, x * ElementLogicBoard.SIZE + offsetX + 6, y * ElementLogicBoard.SIZE + offsetY + 0, 4, 6, getColor(logicBoard, x, y));
            }

            flow = EnumFlow.SOUTH;
            type = logicBoard.getRule(x + flow.getOffsetX(), y + flow.getOffsetY()).getConnectionType(logicBoard, x + flow.getOffsetX(), y + flow.getOffsetY(), flow.getOpposite().getDirection());
            if (type != EnumConnectionType.DISCONNECTED) {
                RenderUtil.drawFilledRectangle(screen, x * ElementLogicBoard.SIZE + offsetX + 6, y * ElementLogicBoard.SIZE + offsetY + 10, 4, 6, getColor(logicBoard, x, y));
            }
        }

    }

}

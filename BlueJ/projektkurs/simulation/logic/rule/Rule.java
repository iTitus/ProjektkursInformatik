package projektkurs.simulation.logic.rule;

import projektkurs.render.Screen;
import projektkurs.simulation.logic.EnumConnectionType;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.LogicBoard;
import projektkurs.simulation.logic.gui.ElementLogicBoard;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;

public abstract class Rule {

    public abstract Rule copy();

    public abstract int getColor(LogicBoard b, int x, int y);

    public EnumConnectionType getConnectionType(LogicBoard b, int x, int y, Direction direction) {
        return EnumConnectionType.BOTH;
    }

    public abstract String getName();

    public abstract EnumFlow getNextFlow(LogicBoard b, int x, int y);

    public void render(Screen screen, LogicBoard logicBoard, int x, int y, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, x * ElementLogicBoard.SIZE + offsetX, y * ElementLogicBoard.SIZE + offsetY, ElementLogicBoard.SIZE, ElementLogicBoard.SIZE, getColor(logicBoard, x, y));
    }

}

package projektkurs.simulation;

import projektkurs.gui.element.SimulationBoard;
import projektkurs.render.Screen;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;

public abstract class Rule {

    public abstract Rule copy();

    public abstract int getColor(Board b, int x, int y);

    public EnumConnectionType getConnectionType(Board b, int x, int y, Direction direction) {
        return EnumConnectionType.BOTH;
    }

    public abstract String getName();

    public abstract EnumFlow getNextFlow(Board b, int x, int y);

    public void render(Screen screen, Board board, int x, int y, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, x * SimulationBoard.SIZE + offsetX, y * SimulationBoard.SIZE + offsetY, SimulationBoard.SIZE, SimulationBoard.SIZE, getColor(board, x, y));
    }

}

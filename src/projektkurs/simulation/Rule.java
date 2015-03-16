package projektkurs.simulation;

import projektkurs.gui.element.SimulationBoard;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public abstract class Rule {

    public abstract Rule copy();

    public abstract int getColor(Board b, int x, int y);

    public abstract String getName();

    public abstract int nextInt(Board b, int x, int y);

    public void render(Screen screen, Board board, int x, int y, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, x * SimulationBoard.SIZE + offsetX, y * SimulationBoard.SIZE + offsetY, SimulationBoard.SIZE, SimulationBoard.SIZE, getColor(board, x, y));
    }

    public EnumConnectionType getConnectionType(Board b, int x, int y) {
        return EnumConnectionType.BOTH;
    }

}

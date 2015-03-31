package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.RenderUtil;

public class EmptySpace extends PacmanEntity {

    public EmptySpace(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawRectangle(screen, offsetX + ElementPacmanBoard.SIZE * x, offsetY + ElementPacmanBoard.SIZE * y, ElementPacmanBoard.SIZE, ElementPacmanBoard.SIZE);
    }

    @Override
    public boolean tryWalkOn(PacmanEntity e) {
        return true;
    }
}

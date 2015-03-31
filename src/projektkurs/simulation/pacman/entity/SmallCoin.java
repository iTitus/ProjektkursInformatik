package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.RenderUtil;

public class SmallCoin extends PacmanEntity {

    private static final int POINTS = 5;

    public SmallCoin(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public void onDeath() {
        board.increaseScore(POINTS);
        board.setPacmanEntity(new EmptySpace(board, x, y));
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawRectangle(screen, offsetX + ElementPacmanBoard.SIZE * x, offsetY + ElementPacmanBoard.SIZE * y, ElementPacmanBoard.SIZE, ElementPacmanBoard.SIZE);
        RenderUtil.drawRectangle(screen, offsetX + 7 + ElementPacmanBoard.SIZE * x, offsetY + 7 + ElementPacmanBoard.SIZE * y, 2, 2, 0xFFFF00);
    }

    @Override
    public boolean tryWalkOn(PacmanEntity e) {
        if (e instanceof Pacman) {
            onDeath();
        } else if (e instanceof Ghost) {
            ((Ghost) e).setGround(this);
        }
        return true;
    }
}

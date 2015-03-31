package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;

public class Obstacle extends PacmanEntity {

    public Obstacle(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
    }

    @Override
    public boolean tryWalkOn(PacmanEntity e) {
        return false;
    }
}

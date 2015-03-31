package projektkurs.simulation.pacman.entity;

import projektkurs.simulation.pacman.PacmanBoard;

public class Ghost_0 extends Ghost {

    public Ghost_0(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void update() {

    }

}

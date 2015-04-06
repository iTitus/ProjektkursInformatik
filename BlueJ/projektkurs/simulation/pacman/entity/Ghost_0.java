package projektkurs.simulation.pacman.entity;

import projektkurs.simulation.pacman.PacmanBoard;

public class Ghost_0 extends Ghost {

    public Ghost_0(PacmanBoard board, double x, double y) {
        super(board, x, y);
    }

    @Override
    public int getColor() {
        return 0xFF0000;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}

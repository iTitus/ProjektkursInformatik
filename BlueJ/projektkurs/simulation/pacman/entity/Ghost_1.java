package projektkurs.simulation.pacman.entity;

import projektkurs.simulation.pacman.PacmanBoard;

public class Ghost_1 extends Ghost {

    public Ghost_1(PacmanBoard board, double x, double y) {
        super(board, x, y);
    }

    @Override
    public int getColor() {
        return 0xFFB8FF;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}

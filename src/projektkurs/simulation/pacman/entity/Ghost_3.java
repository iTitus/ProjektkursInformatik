package projektkurs.simulation.pacman.entity;

import projektkurs.simulation.pacman.PacmanBoard;

public class Ghost_3 extends Ghost {

    public Ghost_3(PacmanBoard board, double x, double y) {
        super(board, x, y);
    }

    @Override
    public int getColor() {
        return 0xFFB851;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}

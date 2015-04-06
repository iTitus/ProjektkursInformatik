package projektkurs.simulation.pacman.entity;

import projektkurs.simulation.pacman.PacmanBoard;

public class Ghost_2 extends Ghost {

    public Ghost_2(PacmanBoard board, double x, double y) {
        super(board, x, y);
    }

    @Override
    public int getColor() {
        return 0x00FFFF;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}

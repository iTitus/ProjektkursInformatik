package projektkurs.simulation.pacman.entity;

import projektkurs.Main;
import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;

public class Pacman extends PacmanEntity {

    private Direction d = Direction.UNKNOWN;
    private PacmanEntity ground;

    public Pacman(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Direction getDirection() {
        return d;
    }

    public PacmanEntity getGround() {
        return ground;
    }

    @Override
    public void onDeath() {
        Main.closeGui();
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawRectangle(screen, offsetX + 1 + ElementPacmanBoard.SIZE * x, offsetY + 1 + ElementPacmanBoard.SIZE * y, ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, 0xFFFF00);
    }

    public void setDirection(Direction direction) {
        d = direction;
    }

    public void setGround(PacmanEntity ground) {
        this.ground = ground;
    }

    @Override
    public boolean tryWalkOn(PacmanEntity e) {
        if (board.isSuperMode()) {
            e.onDeath();
        } else {
            onDeath();
        }
        return false;
    }

    @Override
    public void update() {
        PacmanEntity e = board.getPacmanEntity(x + d.getOffsetX(), y + d.getOffsetY());
        if (e != null && e.tryWalkOn(this)) {
            setPosition(x + d.getOffsetX(), y + d.getOffsetY());
            board.setPacmanEntity(this);
            board.setPacmanEntity(ground);
            ground = e;
        }
    }

}

package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.RenderUtil;

public abstract class Ghost extends PacmanEntity {

    private static final int GHOST_POINTS = 100;
    private PacmanEntity ground = new EmptySpace(board, x, y);
    protected int targetX, targetY;

    public Ghost(PacmanBoard board, int x, int y) {
        super(board, x, y);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public abstract int getColor();

    public PacmanEntity getGround() {
        return ground;
    }

    @Override
    public int getPosX() {
        return x;
    }

    @Override
    public int getPosY() {
        return y;
    }

    @Override
    public void onDeath() {
        board.increaseScore(GHOST_POINTS * board.getMultiplicator());
        board.increaseMultiplicator();
        if (ground != null) {
            board.setPacmanEntity(ground);
        }
        targetX = board.getGhostSpawnX();
        targetY = board.getGhostSpawnY();
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawRectangle(screen, offsetX + 1 + ElementPacmanBoard.SIZE * x, offsetY + 1 + ElementPacmanBoard.SIZE * y, ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, getColor());
    }

    public void setGround(PacmanEntity ground) {
        this.ground = ground;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean tryWalkOn(PacmanEntity e) {
        if (board.isSuperMode()) {
            onDeath();
        } else {
            e.onDeath();
        }
        return false;
    }

    @Override
    public abstract void update();
}

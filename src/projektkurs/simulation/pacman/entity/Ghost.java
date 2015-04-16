package projektkurs.simulation.pacman.entity;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.GhostMode;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public abstract class Ghost extends PacmanEntity {

    public static final int POINTS = 100;
    private GhostMode mode = GhostMode.IDLE;
    protected int targetX, targetY;

    public Ghost(PacmanBoard board) {
        super(board, board.getGhostSpawnX(), board.getGhostSpawnY(), 1, 1);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public abstract void findTargetPosition();

    public abstract int getColor();

    public double getEffectiveSpeed() {
        return Pacman.SPEED * mode.getSpeedModifier();
    }

    public GhostMode getMode() {
        return mode;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void moveTarget(int dtargetX, int dtargetY) {
        targetX += dtargetX;
        targetY += dtargetY;
    }

    @Override
    public void onCollide(PacmanEntity e) {
        if (mode != GhostMode.RETURNING_HOME && e instanceof Pacman) {
            if (mode == GhostMode.FRIGHTENED) {
                setDead();
            } else {
                e.setDead();
            }
        }
    }

    @Override
    public void onDeath() {
        board.increaseScore(POINTS * board.getMultiplicator());
        board.increaseMultiplicator();
        targetX = board.getGhostSpawnX();
        targetY = board.getGhostSpawnY();
        mode = GhostMode.RETURNING_HOME;
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, offsetX + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * x), offsetY + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * y), ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, getColor());
    }

    @Override
    public void setDead() {
        onDeath();
    }

    public void setMode(GhostMode mode) {
        this.mode = mode;
    }

    public void setTarget(int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    @Override
    public void update() {
        if (mode != GhostMode.RETURNING_HOME) {
            findTargetPosition();
        } else if (Math.floor(x) == targetX && Math.floor(y) == targetY) {
            mode = GhostMode.IDLE;
        }
        // TODO Pathfinding
    }
}

package projektkurs.simulation.pacman.entity;

import java.util.List;

import projektkurs.Main;
import projektkurs.render.Screen;
import projektkurs.simulation.pacman.PacmanBoard;
import projektkurs.simulation.pacman.gui.ElementPacmanBoard;
import projektkurs.simulation.pacman.raster.PacmanRaster;
import projektkurs.util.Direction;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class Pacman extends PacmanEntity {

    private Direction direction = Direction.UNKNOWN;
    private Direction nextDirection = Direction.UNKNOWN;

    public Pacman(PacmanBoard board, double x, double y) {
        super(board, x, y);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    @Override
    public void onCollide(PacmanEntity e) {
        if (board.isSuperMode()) {
            e.onDeath();
        } else {
            onDeath();
        }
    }

    @Override
    public void onDeath() {
        Main.closeGui();
    }

    @Override
    public void render(Screen screen, int offsetX, int offsetY) {
        RenderUtil.drawFilledRectangle(screen, offsetX + 1 + MathUtil.round(ElementPacmanBoard.SIZE * x), offsetY + 1 + MathUtil.round(ElementPacmanBoard.SIZE * y), ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, 0xFFFF00);
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    public void update() {
        if (nextDirection != Direction.UNKNOWN) {
            System.out.println(nextDirection);
            int rX = MathUtil.round(x) + nextDirection.getOffsetX();
            int rY = MathUtil.round(y) + nextDirection.getOffsetY();
            PacmanRaster r = board.getPacmanRaster(rX, rY);
            System.out.println(rX + "|" + rY + " - " + r);
            if (!r.isSolid()) {
                direction = nextDirection;
            }
        }
        if (direction != Direction.UNKNOWN) {
            System.out.println("direction = " + direction);
            int rX = MathUtil.round(x) + direction.getOffsetX();
            int rY = MathUtil.round(y) + direction.getOffsetY();
            PacmanRaster r = board.getPacmanRaster(rX, rY);
            if (!r.isSolid()) {
                move(direction.getOffsetX() * MathUtil.inverse(ElementPacmanBoard.SIZE), direction.getOffsetY() * MathUtil.inverse(ElementPacmanBoard.SIZE));
                r.onWalkOn(board, rX, rY, this);
                List<PacmanEntity> entities = board.getPacmanEntities(rX, rY);
                if (entities != null) {
                    for (PacmanEntity e : entities) {
                        if (e != null) {
                            e.onCollide(this);
                        }
                    }
                }
            }
        }
    }

}

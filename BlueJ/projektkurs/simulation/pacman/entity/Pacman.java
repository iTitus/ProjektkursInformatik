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
        super(board, x, y, 1, 1);
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
        RenderUtil.drawFilledRectangle(screen, offsetX + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * x), offsetY + 1 + MathUtil.floor(ElementPacmanBoard.SIZE * y), ElementPacmanBoard.SIZE - 2, ElementPacmanBoard.SIZE - 2, 0xFFFF00);
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    public void update() {
        if (nextDirection != Direction.UNKNOWN) {
            int rX = MathUtil.floor(x + nextDirection.getOffsetX() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            int rY = MathUtil.floor(y + nextDirection.getOffsetY() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            PacmanRaster r = board.getPacmanRaster(rX, rY);
            int rX2 = MathUtil.floor(x + sizeX + nextDirection.getOffsetX() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            int rY2 = MathUtil.floor(y + sizeY + nextDirection.getOffsetY() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            PacmanRaster r2 = board.getPacmanRaster(rX2, rY2);
            if ((r == null || !r.isSolid()) && (r2 == null || !r2.isSolid())) {
                direction = nextDirection;
            }
            nextDirection = Direction.UNKNOWN;
        }
        if (direction != Direction.UNKNOWN) {
            int rX = MathUtil.floor(x + direction.getOffsetX() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            int rY = MathUtil.floor(y + direction.getOffsetY() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            PacmanRaster r = board.getPacmanRaster(rX, rY);
            int rX2 = MathUtil.floor(x + sizeX + nextDirection.getOffsetX() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            int rY2 = MathUtil.floor(y + sizeY + nextDirection.getOffsetY() * MathUtil.inverse(ElementPacmanBoard.SIZE));
            PacmanRaster r2 = board.getPacmanRaster(rX2, rY2);
            if ((r == null || !r.isSolid()) && (r2 == null || !r2.isSolid())) {
                move(direction.getOffsetX() * MathUtil.inverse(ElementPacmanBoard.SIZE), direction.getOffsetY() * MathUtil.inverse(ElementPacmanBoard.SIZE));
                r.onWalkOn(board, rX, rY, this);
                List<PacmanEntity> entities = board.getPacmanEntities(rX, rY, sizeX, sizeY);
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

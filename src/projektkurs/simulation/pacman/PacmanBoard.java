package projektkurs.simulation.pacman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.pacman.entity.Ghost_0;
import projektkurs.simulation.pacman.entity.Ghost_1;
import projektkurs.simulation.pacman.entity.Ghost_2;
import projektkurs.simulation.pacman.entity.Ghost_3;
import projektkurs.simulation.pacman.entity.Pacman;
import projektkurs.simulation.pacman.entity.PacmanEntity;
import projektkurs.simulation.pacman.entity.SmallCoin;
import projektkurs.simulation.pacman.raster.PacmanRaster;
import projektkurs.util.IUpdatable;

public class PacmanBoard implements IUpdatable {

    private final PacmanRaster[][] board;
    private final List<PacmanEntity> entities;
    private final Ghost_0 ghost0;
    private final Ghost_1 ghost1;
    private final Ghost_2 ghost2;
    private final Ghost_3 ghost3;
    private int multiplicator;
    private final Pacman pacman;
    private int score;
    private final int sizeX, sizeY;
    private boolean superMode;

    public PacmanBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        board = new PacmanRaster[sizeX][sizeY];
        entities = new ArrayList<PacmanEntity>();
        generateMaze();
        pacman = new Pacman(this, 1, 1);
        ghost0 = new Ghost_0(this);
        ghost1 = new Ghost_1(this);
        ghost2 = new Ghost_2(this);
        ghost3 = new Ghost_3(this);
        addPacmanEntity(pacman);
        addPacmanEntity(ghost0);
        addPacmanEntity(ghost1);
        addPacmanEntity(ghost2);
        addPacmanEntity(ghost3);
        addCoins();
    }

    public void activateSuperMode() {
        if (!superMode) {
            multiplicator = 1;
        }
        superMode = true;
    }

    public void addPacmanEntity(PacmanEntity e) {
        if (e != null && e.getPosX() >= 0 && e.getPosX() < sizeX && e.getPosY() >= 0 && e.getPosY() < sizeY && !entities.contains(e)) {
            entities.add(e);
        }
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public void deactivateSuperMode() {
        multiplicator = 0;
        superMode = false;
    }

    public void decreaseScore(int by) {
        score -= by;
    }

    public Ghost_0 getGhost0() {
        return ghost0;
    }

    public Ghost_1 getGhost1() {
        return ghost1;
    }

    public Ghost_2 getGhost2() {
        return ghost2;
    }

    public Ghost_3 getGhost3() {
        return ghost3;
    }

    public int getGhostSpawnX() {
        return 15;
    }

    public int getGhostSpawnY() {
        return 15;
    }

    public int getMultiplicator() {
        return multiplicator;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public List<PacmanEntity> getPacmanEntities(double x, double y) {
        return getPacmanEntities(x, y, 1, 1);
    }

    public List<PacmanEntity> getPacmanEntities(double x, double y, double width, double height) {
        List<PacmanEntity> list = new ArrayList<PacmanEntity>();
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            for (PacmanEntity e : entities) {
                if (e != null && e.isInside(x, y, width, height)) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    public PacmanRaster getPacmanRaster(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            return board[x][y];
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void increaseMultiplicator() {
        multiplicator = multiplicator << 1;
    }

    public void increaseScore(int by) {
        score += by;
    }

    public boolean isSuperMode() {
        return superMode;
    }

    public void render(Screen screen, int posX, int posY) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                PacmanRaster r = board[x][y];
                if (r != null) {
                    r.render(this, x, y, screen, posX, posY);
                }
            }
        }
        for (PacmanEntity e : entities) {
            if (e != null) {
                e.render(screen, posX, posY);
            }
        }
        Font.drawString(screen, String.format("X: %.2f | Y: %.2f | Dir: %s | Next Dir: %s | Score: %d", pacman.getPosX(), pacman.getPosY(), pacman.getDirection(), pacman.getNextDirection(), score), posX, posY, 0xFF0000);
    }

    public void setPacmanRaster(int x, int y, PacmanRaster raster) {
        if (raster != null && x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            board[x][y] = raster;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update() {
        for (Iterator<PacmanEntity> iterator = entities.iterator(); iterator.hasNext();) {
            PacmanEntity e = iterator.next();
            if (e != null) {
                if (e.canUpdate()) {
                    e.update();
                }
                if (e.isDead()) {
                    iterator.remove();
                }
            }
        }
    }

    private void addCoins() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (getPacmanRaster(x, y) == PacmanRaster.emptySpace) {
                    addPacmanEntity(new SmallCoin(this, x + 0.25, y + 0.25));
                }
            }
        }
    }

    private void generateMaze() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (x == 0 || y == 0 || x == sizeX - 1 || y == sizeY - 1) {
                    setPacmanRaster(x, y, PacmanRaster.obstacle);
                } else {
                    setPacmanRaster(x, y, PacmanRaster.emptySpace);
                }
            }
        }
        for (int y = 3; y < sizeY - 2; y++) {
            setPacmanRaster(2, y, PacmanRaster.obstacle);
        }
    }
}

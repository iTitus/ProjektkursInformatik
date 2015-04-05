package projektkurs.simulation.pacman;

import java.util.ArrayList;
import java.util.List;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.entity.Pacman;
import projektkurs.simulation.pacman.entity.PacmanEntity;
import projektkurs.simulation.pacman.raster.PacmanRaster;
import projektkurs.util.IUpdatable;

public class PacmanBoard implements IUpdatable {

    private final PacmanRaster[][] board;
    private final List<PacmanEntity> entities;
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
        pacman = new Pacman(this, 0, 0);
        addPacmanEntity(pacman);
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

    public int getGhostSpawnX() {
        return 0;
    }

    public int getGhostSpawnY() {
        return 0;
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
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            // TODO
        }
        return null;
    }

    public PacmanRaster getPacmanRaster(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            return board[x][y];
        }
        return PacmanRaster.emptySpace;
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
        for (PacmanEntity e : entities) {
            if (e != null && e.canUpdate()) {
                e.update();
            }
        }
    }

    private void generateMaze() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                setPacmanRaster(x, y, PacmanRaster.emptySpace);
            }
        }
    }
}

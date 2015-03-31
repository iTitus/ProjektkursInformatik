package projektkurs.simulation.pacman;

import projektkurs.render.Screen;
import projektkurs.simulation.pacman.entity.EmptySpace;
import projektkurs.simulation.pacman.entity.Pacman;
import projektkurs.simulation.pacman.entity.PacmanEntity;
import projektkurs.util.IUpdatable;

public class PacmanBoard implements IUpdatable {

    private final PacmanEntity[][] board;
    private int multiplicator;
    private final Pacman pacman;
    private int score;
    private final int sizeX, sizeY;
    private boolean superMode;

    public PacmanBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        board = new PacmanEntity[sizeX][sizeY];
        generateMaze();
        pacman = new Pacman(this, 0, 0);
        setPacmanEntity(pacman);
    }

    public void activateSuperMode() {
        if (!superMode) {
            multiplicator = 1;
        }
        superMode = true;
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

    public PacmanEntity getPacmanEntity(int x, int y) {
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

    public void makeDead(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            board[x][y].onDeath();
        }
    }

    public void render(Screen screen, int posX, int posY) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                PacmanEntity e = board[x][y];
                if (e != null) {
                    e.render(screen, posX, posY);
                }
            }
        }
    }

    public void setPacmanEntity(PacmanEntity e) {
        if (e != null && e.getPosX() >= 0 && e.getPosX() < sizeX && e.getPosY() >= 0 && e.getPosY() < sizeY) {
            board[e.getPosX()][e.getPosY()] = e;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                PacmanEntity e = board[x][y];
                if (e != null && e.canUpdate()) {
                    e.update();
                }
            }
        }
    }

    private void generateMaze() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                setPacmanEntity(new EmptySpace(this, x, y));
            }
        }
    }
}

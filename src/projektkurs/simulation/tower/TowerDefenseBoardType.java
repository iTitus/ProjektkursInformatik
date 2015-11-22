package projektkurs.simulation.tower;

import projektkurs.simulation.tower.raster.TowerRaster;
import projektkurs.simulation.tower.raster.logic.PathLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.Direction;

public enum TowerDefenseBoardType {

    STANDARD(100, 51, 10) {

        @Override
        public void generate(TowerDefenseBoard board) {
            for (int y = 0; y < board.getSizeY(); y++) {
                for (int x = 0; x < board.getSizeX(); x++) {
                    board.setTowerRaster(x, y, TowerRaster.grass);
                }
            }

            for (int x = 0; x < 48; x++) {
                board.setTowerRaster(x, 25, TowerRaster.path);
                TowerLogic logic = board.getTowerLogic(x, 25);
                if (logic instanceof PathLogic) {
                    ((PathLogic) logic).setDirection(Direction.RIGHT);
                }
            }

            board.setTowerRaster(48, 25, TowerRaster.path);
            TowerLogic logic = board.getTowerLogic(48, 25);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.UP);
            }
            board.setTowerRaster(48, 24, TowerRaster.path);
            logic = board.getTowerLogic(48, 24);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.UP);
            }
            board.setTowerRaster(48, 23, TowerRaster.path);
            logic = board.getTowerLogic(48, 23);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.RIGHT);
            }

            board.setTowerRaster(49, 23, TowerRaster.path);
            logic = board.getTowerLogic(49, 23);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.RIGHT);
            }
            board.setTowerRaster(50, 23, TowerRaster.path);
            logic = board.getTowerLogic(50, 23);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.RIGHT);
            }

            board.setTowerRaster(51, 23, TowerRaster.path);
            logic = board.getTowerLogic(51, 23);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.DOWN);
            }
            board.setTowerRaster(51, 24, TowerRaster.path);
            logic = board.getTowerLogic(51, 24);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.DOWN);
            }

            for (int x = 51; x < board.getSizeX(); x++) {
                board.setTowerRaster(x, 25, TowerRaster.path);
                logic = board.getTowerLogic(x, 25);
                if (logic instanceof PathLogic) {
                    ((PathLogic) logic).setDirection(Direction.RIGHT);
                }
            }

            board.setStart(0, 25);
            board.setEnd(board.getSizeX() - 1, 25);

            board.setTowerRaster(49, 24, TowerRaster.simpleShooter);
            board.setTowerRaster(50, 24, TowerRaster.simpleShooter);

        }

    };

    private final int sizeX, sizeY, credits;

    private TowerDefenseBoardType(int sizeX, int sizeY, int credits) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.credits = credits;
    }

    public abstract void generate(TowerDefenseBoard board);

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getStartingCredits() {
        return credits;
    }
}

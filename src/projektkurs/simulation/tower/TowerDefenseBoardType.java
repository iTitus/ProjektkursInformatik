package projektkurs.simulation.tower;

import projektkurs.simulation.tower.entity.Monster;
import projektkurs.simulation.tower.raster.TowerRaster;
import projektkurs.simulation.tower.raster.logic.PathLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.Direction;

public enum TowerDefenseBoardType {

    STANDARD(101, 51) {

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
                ((PathLogic) logic).setDirection(Direction.DOWN);
            }
            board.setTowerRaster(50, 24, TowerRaster.path);
            logic = board.getTowerLogic(50, 24);
            if (logic instanceof PathLogic) {
                ((PathLogic) logic).setDirection(Direction.DOWN);
            }

            for (int x = 50; x < board.getSizeX(); x++) {
                board.setTowerRaster(x, 25, TowerRaster.path);
                logic = board.getTowerLogic(x, 25);
                if (logic instanceof PathLogic) {
                    ((PathLogic) logic).setDirection(Direction.RIGHT);
                }
            }

            board.setStart(0, 25);
            board.setEnd(board.getSizeX() - 1, 25);

            Monster m = new Monster(board, 0.5, 0xFF0000);
            board.spawn(m);

        }

    };

    private final int sizeX, sizeY;

    private TowerDefenseBoardType(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public abstract void generate(TowerDefenseBoard board);
}

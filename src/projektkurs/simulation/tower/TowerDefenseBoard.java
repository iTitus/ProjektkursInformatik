package projektkurs.simulation.tower;

import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.entity.TowerEntity;
import projektkurs.simulation.tower.raster.TowerRaster;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.IUpdatable;

public class TowerDefenseBoard implements IUpdatable {

    private final int sizeX, sizeY;
    private final TowerRaster[][] board;
    private final Set<TowerLogic> towerLogics;
    private final Set<TowerEntity> towerEntities;

    public TowerDefenseBoard(TowerDefenseBoardType boardType) {
        this.sizeX = boardType.getSizeX();
        this.sizeY = boardType.getSizeY();
        this.board = new TowerRaster[this.sizeX][this.sizeY];
        this.towerLogics = new HashSet<TowerLogic>();
        this.towerEntities = new HashSet<TowerEntity>();
        boardType.generate(this);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void update() {
        for (TowerLogic towerLogic : towerLogics) {
            if (towerLogic != null && towerLogic.canUpdate()) {
                towerLogic.update();
            }
        }
        for (TowerEntity towerEntity : towerEntities) {
            if (towerEntity != null && towerEntity.canUpdate()) {
                towerEntity.update();
            }
        }
    }

    public void render(Screen screen, int posX, int posY) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                TowerRaster towerRaster = getTowerRaster(x, y);
                if (towerRaster != null) {
                    towerRaster.render(this, x, y, screen, posX, posY);
                }
            }
        }
        for (TowerEntity towerEntity : towerEntities) {
            if (towerEntity != null) {
                towerEntity.render(screen, posX, posY);
            }
        }
    }

    public TowerRaster getTowerRaster(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeX) {
            return null;
        }
        return board[x][y];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void onLeftClick(int x, int y, MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void onRightClick(int x, int y, MouseEvent e) {
        // TODO Auto-generated method stub

    }

}

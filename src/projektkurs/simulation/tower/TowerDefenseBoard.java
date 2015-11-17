package projektkurs.simulation.tower;

import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import projektkurs.render.Screen;
import projektkurs.simulation.tower.entity.TowerEntity;
import projektkurs.simulation.tower.raster.TowerRaster;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.IUpdatable;
import projektkurs.util.Rectangle;

public class TowerDefenseBoard implements IUpdatable {

    private final int sizeX, sizeY;
    private int startX, startY, life;
    private Rectangle end;
    private final TowerRaster[][] board;
    private final Set<TowerLogic> towerLogics;
    private final Set<TowerEntity> towerEntities;

    public TowerDefenseBoard(TowerDefenseBoardType boardType) {
        this.sizeX = boardType.getSizeX();
        this.sizeY = boardType.getSizeY();
        this.board = new TowerRaster[this.sizeX][this.sizeY];
        this.towerLogics = new HashSet<TowerLogic>();
        this.towerEntities = new HashSet<TowerEntity>();
        this.life = 100;
        this.end = new Rectangle(-1, -1, 1, 1);
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
        Iterator<TowerEntity> iterator = towerEntities.iterator();
        while (iterator.hasNext()) {
            TowerEntity towerEntity = iterator.next();
            if (towerEntity != null) {
                if (towerEntity.canUpdate()) {
                    towerEntity.update();
                }
                if (towerEntity.isDead()) {
                    iterator.remove();
                }
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
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
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

    public void setTowerRaster(int x, int y, TowerRaster towerRaster) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            removeTowerLogic(x, y);
            board[x][y] = towerRaster;
            addTowerLogic(x, y);
        }
    }

    public TowerLogic getTowerLogic(int x, int y) {
        if (x >= 0 && x < sizeX && y >= 0 && y < sizeY) {
            Iterator<TowerLogic> iterator = towerLogics.iterator();
            while (iterator.hasNext()) {
                TowerLogic towerLogic = iterator.next();
                if (towerLogic != null && towerLogic.getX() == x && towerLogic.getY() == y) {
                    return towerLogic;
                }
            }
        }
        return null;
    }

    private void addTowerLogic(int x, int y) {
        TowerLogic towerLogic = getTowerRaster(x, y).createLogic(this, x, y);
        if (towerLogic != null) {
            towerLogics.add(towerLogic);
        }
    }

    private void removeTowerLogic(int x, int y) {
        Iterator<TowerLogic> iterator = towerLogics.iterator();
        while (iterator.hasNext()) {
            TowerLogic towerLogic = iterator.next();
            if (towerLogic != null && towerLogic.getX() == x && towerLogic.getY() == y) {
                iterator.remove();
                return;
            }
        }
    }

    public void setStart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setEnd(double endX, double endY) {
        end.setPosition(endX, endY);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public Rectangle getEnd() {
        return end;
    }

    public void subtractLife() {
        life--;
    }

    public void spawn(TowerEntity towerEntity) {
        if (towerEntity != null) {
            towerEntities.add(towerEntity);
        }
    }

    public int getLife() {
        return life;
    }
}

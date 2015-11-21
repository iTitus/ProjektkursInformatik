package projektkurs.simulation.tower.gui;

import java.awt.event.MouseEvent;

import projektkurs.gui.element.Element;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.simulation.tower.TowerDefenseBoard;
import projektkurs.simulation.tower.TowerDefenseBoardType;
import projektkurs.simulation.tower.raster.TowerRaster;
import projektkurs.simulation.tower.raster.logic.TieredTowerLogic;
import projektkurs.simulation.tower.raster.logic.TowerLogic;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class ElementTowerDefenseBoard extends Element {

    public static final int SIDEBAR_WIDTH = 192;
    public static final int SIZE = 16;

    private final TowerDefenseBoard board;
    private int selectedX = -1, selectedY = -1;

    public ElementTowerDefenseBoard(int posX, int posY, TowerDefenseBoardType boardType, int id) {
        super(posX, posY, boardType.getSizeX() * SIZE + SIDEBAR_WIDTH, boardType.getSizeY() * SIZE, id, null);
        board = new TowerDefenseBoard(boardType);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void onLeftClick(int mouseX, int mouseY, MouseEvent e) {
        if (handleMenuLeftClick(mouseX, mouseY)) {
            return;
        }
        if (MathUtil.isInside(mouseX, mouseY, 1, 1, posX, posY, board.getSizeX() * SIZE, board.getSizeY() * SIZE)) {
            int x = (mouseX - posX) / SIZE;
            int y = (mouseY - posY) / SIZE;
            if (x == selectedX && y == selectedY) {
                return;
            }
            TowerRaster towerRaster = board.getTowerRaster(x, y);
            if (towerRaster != null) {
                if (towerRaster.isTower()) {
                    TowerLogic logic = board.getTowerLogic(x, y);
                    if (logic instanceof TieredTowerLogic && !logic.isInvalid()) {
                        selectedX = x;
                        selectedY = y;
                        return;
                    }
                } else if (towerRaster.canPlaceTower()) {
                    selectedX = x;
                    selectedY = y;
                    return;
                }
            }
            selectedX = -1;
            selectedY = -1;
        }
    }

    @Override
    public void onRightClick(int mouseX, int mouseY, MouseEvent e) {
        if (isInside(mouseX, mouseY)) {
            // NO-OP
        }
    }

    @Override
    public void render(Screen screen) {
        board.render(screen, posX, posY);
        RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
        renderMenu(screen);
    }

    @Override
    public void update() {
        if (board.canUpdate()) {
            board.update();
        }
    }

    private boolean handleMenuLeftClick(int mouseX, int mouseY) {
        if (selectedX < 0 || selectedY < 0) {
            return false;
        }
        TowerRaster towerRaster = board.getTowerRaster(selectedX, selectedY);
        if (towerRaster != null) {
            if (towerRaster.isTower()) {
                TowerLogic towerLogic = board.getTowerLogic(selectedX, selectedY);
                if (towerLogic instanceof TieredTowerLogic && !towerLogic.isInvalid()) {
                    return MathUtil.isInside(mouseX, mouseY, 1, 1, posX + (selectedX + 1) * SIZE, posY + (selectedY + 1) * SIZE, 128, 64);
                }
            } else {
                return MathUtil.isInside(mouseX, mouseY, 1, 1, posX + (selectedX + 1) * SIZE, posY + (selectedY + 1) * SIZE, 128, 64);
            }
        }
        return false;
    }

    private void renderMenu(Screen screen) {
        TowerRaster towerRaster = board.getTowerRaster(selectedX, selectedY);
        if (towerRaster != null) {
            if (towerRaster.isTower()) {
                TowerLogic towerLogic = board.getTowerLogic(selectedX, selectedY);
                if (towerLogic instanceof TieredTowerLogic && !towerLogic.isInvalid()) {
                    TieredTowerLogic tieredTowerLogic = (TieredTowerLogic) towerLogic;
                    RenderUtil.drawCircle(screen, (int) (posX + (selectedX + 0.5) * SIZE), (int) (posY + (selectedY + 0.5) * SIZE), tieredTowerLogic.getRange() * SIZE, 0xFF00FE);

                    int i = 0;
                    Font.drawString(screen, tieredTowerLogic.getName(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    i++;
                    Font.drawString(screen, "Tier: " + tieredTowerLogic.getTier(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    Font.drawString(screen, "Mode: " + tieredTowerLogic.getMode(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    i++;
                    Font.drawString(screen, "Damage: " + tieredTowerLogic.getDamage(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    Font.drawString(screen, "Frequency: " + tieredTowerLogic.getFrequency(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    Font.drawString(screen, "Range: " + tieredTowerLogic.getRange(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    Font.drawString(screen, "Shooting Speed: " + tieredTowerLogic.getSpeed(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);

                    if (tieredTowerLogic.getTier() < tieredTowerLogic.getMaxTier()) {
                        i++;
                        Font.drawString(screen, "Upgrade Cost: " + tieredTowerLogic.getUpgradeCost(), posX + sizeX - SIDEBAR_WIDTH, posY + 2 + 16 * i++);
                    }
                }
            }
            // Marker of selected raster
            RenderUtil.drawRectangle(screen, posX + selectedX * SIZE, posY + selectedY * SIZE, SIZE, SIZE);
        }
    }
}

package projektkurs.simulation.tower.gui;

import projektkurs.gui.Gui;
import projektkurs.simulation.tower.TowerDefenseBoardType;

public class GuiTowerDefense extends Gui {

    private ElementTowerDefenseBoard elementTowerDefenseBoard;

    @Override
    public void initGui() {
        super.initGui();
        elementTowerDefenseBoard = new ElementTowerDefenseBoard(64, 64, TowerDefenseBoardType.STANDARD, 0);
        addElement(elementTowerDefenseBoard);
    }

}

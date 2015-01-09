package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.lib.Integers;
import projektkurs.lib.Levels;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Das Hauptmenü.
 */
public class GuiMainMenu extends Gui implements IButtonListener {

    @Override
    public void initGui() {
        super.initGui();
        addElement(new Button(64, 64, 256, 64, 0, this, "Level0"));
        addElement(new Button(64, 128, 256, 64, 1, this, "Level1"));
        addElement(new Button(64, 192, 256, 64, 2, this, "button.exit"));
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        switch (button.getID()) {
            case 0:
                Main.startLevel(Levels.level0);
                break;
            case 1:
                Main.startLevel(Levels.level1);
                break;
            case 2:
                Main.exit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        RenderUtil.drawCenteredString(g, "MAIN MENU", MathUtil.floorDiv(Integers.windowX, 2), MathUtil.floorDiv(Integers.windowY, 2));
    }
}

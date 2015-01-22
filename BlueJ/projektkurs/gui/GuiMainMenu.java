package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.lib.Integers;
import projektkurs.lib.Levels;
import projektkurs.render.Screen;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Das Hauptmenue.
 */
public class GuiMainMenu extends Gui implements IButtonListener {

    @Override
    public void initGui() {
        super.initGui();
        addElement(new Button(64, 64, 0, this, "Level0"));
        addElement(new Button(64, 128, 1, this, "Level1"));
        addElement(new Button(64, 192, 2, this, "Options"));
        addElement(new Button(64, 256, 3, this, "button.exit"));
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
                Main.openGui(new GuiOption(this));
                break;
            case 3:
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
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
        RenderUtil.drawCenteredString(screen, "MAIN MENU", MathUtil.floorDiv(Integers.windowX, 2), MathUtil.floorDiv(Integers.windowY, 2));
    }
}

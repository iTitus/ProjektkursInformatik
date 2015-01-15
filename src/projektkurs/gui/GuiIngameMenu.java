package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

/**
 * Das Menü, das im Spiele aufgerufen werden kann.
 */
public class GuiIngameMenu extends Gui implements IButtonListener {

    @Override
    public void initGui() {
        super.initGui();
        addElement(new Button(64, 64, 256, 64, 0, this, "Options"));
        addElement(new Button(64, 128, 256, 64, 1, this, "Main Menu"));
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        switch (button.getID()) {
            case 0:
                Main.openGui(new GuiOption(this));
                break;
            case 1:
                Main.closeLevel();
                Main.openGui(new GuiMainMenu());
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
    }


}

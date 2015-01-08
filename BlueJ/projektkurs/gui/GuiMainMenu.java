package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.Element;
import projektkurs.gui.element.IButtonListener;
import projektkurs.lib.Integers;
import projektkurs.lib.Levels;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 *
 */
public class GuiMainMenu extends Gui implements IButtonListener {

    @Override
    public void initGui() {
        super.initGui();
        addElement(new Button(64, 64, 256, 64, 0, this, "Level"));
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        if (button.getID() == 0) {
            Main.startLevel(Levels.level0);
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        for (Element el : getGuiElements()) {
            el.onKeyTyped(keyChar, e);
        }
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        RenderUtil.drawCenteredString(g, "MAIN MENU", MathUtil.floorDiv(Integers.windowX, 2), MathUtil.floorDiv(Integers.windowY, 2));
    }
}

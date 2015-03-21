package projektkurs.simulation.ulam.gui;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;

/**
 *
 */
public class GuiUlam extends Gui {

    private ElementUlamBoard ulamElement1, ulamElement2;

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
        ulamElement1 = new ElementUlamBoard(64, 64, 800, 0, true);
        addElement(ulamElement1);

        ulamElement2 = new ElementUlamBoard(928, 64, 800, 1, false);
        addElement(ulamElement2);

        ulamElement1.start();
        ulamElement2.start();
    }

    @Override
    public void onGuiClosed() {
        ulamElement1.stop();
        ulamElement2.stop();
    }

    @Override
    public void render(Screen screen) {
        screen.setColor(0);
        super.render(screen);
    }

}

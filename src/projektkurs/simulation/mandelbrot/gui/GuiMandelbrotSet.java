package projektkurs.simulation.mandelbrot.gui;

import projektkurs.gui.Gui;
import projektkurs.lib.Integers;
import projektkurs.render.Screen;

public class GuiMandelbrotSet extends Gui {

    private final ElementMandelbrotSet elementMandelbrotSet;

    public GuiMandelbrotSet(double c_r, double c_i, boolean brot) {
        elementMandelbrotSet = new ElementMandelbrotSet(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.windowY - 2 * Integers.WINDOW_HUD_Y, 0, c_r, c_i, brot);
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(elementMandelbrotSet);
        elementMandelbrotSet.start();
    }

    @Override
    public void onGuiClosed() {
        elementMandelbrotSet.stop();
    }

    @Override
    public void render(Screen screen) {
        screen.setColor(0);
        super.render(screen);
    }

}

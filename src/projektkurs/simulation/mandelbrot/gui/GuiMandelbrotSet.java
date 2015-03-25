package projektkurs.simulation.mandelbrot.gui;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class GuiMandelbrotSet extends Gui {

    private ElementMandelbrotSet elementMandelbrotSet;

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void initGui() {
        super.initGui();
        elementMandelbrotSet = new ElementMandelbrotSet(64, 64, 1024, 768, 0, 2, 1);
        addElement(elementMandelbrotSet);
        elementMandelbrotSet.start();
    }

    @Override
    public void onGuiClosed() {
        elementMandelbrotSet.stop();
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}

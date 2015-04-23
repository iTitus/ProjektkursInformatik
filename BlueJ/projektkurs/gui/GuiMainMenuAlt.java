package projektkurs.gui;

import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

public class GuiMainMenuAlt extends Gui {

    @Override
    public void initGui() {
        super.initGui();

    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}

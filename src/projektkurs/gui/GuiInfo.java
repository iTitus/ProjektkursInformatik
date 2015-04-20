package projektkurs.gui;

import java.util.Collections;
import java.util.List;

import projektkurs.gui.element.TextView;
import projektkurs.lib.Integers;
import projektkurs.render.Screen;
import projektkurs.util.Pair;
import projektkurs.util.RenderUtil;

/**
 * Informations-GUI.
 */
public class GuiInfo extends Gui {

    private final Pair<String, List<String>> pair;

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     */
    public GuiInfo(Gui parent, Pair<String, List<String>> pair) {
        super(parent);
        this.pair = pair;

    }

    @Override
    public void initGui() {
        super.initGui();
        Collections.sort(pair.getValueB());
        addElement(new TextView(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.DEFAULT_BUTTON_HEIGHT, 0, pair.getValueA(), pair.getValueB()));
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}

package projektkurs.gui;

import java.util.ArrayList;
import java.util.Collections;

import projektkurs.gui.element.TextView;
import projektkurs.lib.Commands;
import projektkurs.lib.CutScenes;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Entities;
import projektkurs.lib.ExtraInformationen;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.Levels;
import projektkurs.lib.Raster;
import projektkurs.lib.Sounds;
import projektkurs.lib.SpriteSheets;
import projektkurs.lib.Sprites;
import projektkurs.render.Screen;
import projektkurs.util.MathUtil;
import projektkurs.util.Pair;
import projektkurs.util.RenderUtil;

/**
 * Informations-GUI.
 */
public class GuiInfo extends Gui {

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     */
    public GuiInfo(Gui parent) {
        super(parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        addInfos(Levels.getPair(), CutScenes.getPair(), Dialoge.getPair(), Raster.getPair(), ExtraInformationen.getPair(), Entities.getPair(), Items.getPair(), Commands.getPair(), Images.getPair(), SpriteSheets.getPair(), Sprites.getPair(), Sounds.getPair());
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

    /**
     * Fügt alle TextViews hinzu.
     *
     * @param texts
     *            Alle Texte
     */
    @SuppressWarnings("unchecked")
    private void addInfos(Pair<String, ArrayList<String>>... texts) {
        int x = Integers.WINDOW_HUD_X;
        int y = Integers.WINDOW_HUD_Y;
        int width = Integers.windowX - 2 * Integers.WINDOW_HUD_X;
        int height = MathUtil.floorDiv(Integers.windowY - 2 * Integers.WINDOW_HUD_Y, 10);
        for (int id = 0; id < texts.length; id++) {
            Collections.sort(texts[id].getValueB());
            addElement(new TextView(x, y + id * height, width, height, id, texts[id].getValueA(), texts[id].getValueB()));
        }
    }

}

package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import projektkurs.Main;
import projektkurs.gui.element.TextView;
import projektkurs.lib.Commands;
import projektkurs.lib.CutScenes;
import projektkurs.lib.Dialoge;
import projektkurs.lib.Entities;
import projektkurs.lib.ExtraInformationen;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.KeyBindings;
import projektkurs.lib.Levels;
import projektkurs.lib.Raster;
import projektkurs.lib.Sounds;
import projektkurs.util.RenderUtil;

/**
 * Informations-GUI.
 */
public class GuiInfo extends Gui {

    /**
     * Eltern-Gui.
     */
    private final Gui parent;

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     */
    public GuiInfo(Gui parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        super.initGui();
        int x = Integers.WINDOW_HUD_X;
        int y = Integers.WINDOW_HUD_Y;
        int factor = 64;
        int id = 0;
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.levels", new ArrayList<String>(Levels.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.cutscenes", new ArrayList<String>(CutScenes.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.dialogs", new ArrayList<String>(Dialoge.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.raster", new ArrayList<String>(Raster.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.extras", new ArrayList<String>(ExtraInformationen.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.entities", new ArrayList<String>(Entities.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.items", new ArrayList<String>(Items.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.commands", new ArrayList<String>(Commands.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.images", new ArrayList<String>(Images.MAPPINGS.keySet())));
        addElement(new TextView(x, y + id * factor, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, id++, "info.sounds", new ArrayList<String>(Sounds.MAPPINGS.keySet())));
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        super.onKeyTyped(keyChar, e);
        if (keyChar == KeyBindings.KEY_OPTION) {
            Main.openGui(parent);
        }
    }

    @Override
    public void render(Graphics2D g) {
        RenderUtil.drawDefaultBackground(g);
        super.render(g);
    }

}

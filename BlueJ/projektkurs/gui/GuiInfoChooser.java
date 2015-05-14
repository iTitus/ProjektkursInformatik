package projektkurs.gui;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.lib.Commands;
import projektkurs.lib.Configs;
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
import projektkurs.util.MathUtil;
import projektkurs.util.Pair;

@SuppressWarnings("deprecation")
public class GuiInfoChooser extends Gui implements IButtonListener {

	private final List<Pair<String, List<String>>> infos;

	public GuiInfoChooser(Gui parent) {
		super(parent);
		infos = new ArrayList<Pair<String, List<String>>>();
		infos.addAll(Arrays.asList(Levels.getPair(), CutScenes.getPair(), Dialoge.getPair(), Raster.getPair(), ExtraInformationen.getPair(), Entities.getPair(), Items.getPair(), Commands.getPair(), Images.getPair(), SpriteSheets.getPair(), Sprites.getPair(), Sounds.getPair(), Configs.getPair()));
	}

	@Override
	public void initGui() {
		super.initGui();
		int j = MathUtil.floorDiv(Integers.windowX - 2 * Integers.WINDOW_HUD_X, Integers.DEFAULT_BUTTON_WIDTH);
		for (int i = 0; i < infos.size(); i++) {
			addElement(new Button(Integers.WINDOW_HUD_X + i % j * Integers.DEFAULT_BUTTON_WIDTH, Integers.WINDOW_HUD_Y + i / j * Integers.DEFAULT_BUTTON_HEIGHT, i, this, infos.get(i).getValueA()));
		}

	}

	@Override
	public void onButtonLeftClick(Button button, MouseEvent e) {
		Main.openGui(new GuiInfo(this, infos.get(button.getID())));
	}

	@Override
	public void onButtonRightClick(Button button, MouseEvent e) {
		// NO-OP
	}

}

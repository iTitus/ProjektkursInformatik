package projektkurs.simulation.logic.gui;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;
import projektkurs.simulation.logic.EnumFlow;
import projektkurs.simulation.logic.rule.AndGateRule;
import projektkurs.simulation.logic.rule.NotGateRule;
import projektkurs.simulation.logic.rule.OmniSourceRule;
import projektkurs.simulation.logic.rule.OrGateRule;
import projektkurs.simulation.logic.rule.SimpleWireRule;
import projektkurs.simulation.logic.rule.XOrGateRule;
import projektkurs.util.RenderUtil;

/**
 * Ein Logik-Simulations-GUI.
 */
public class GuiLogicBoard extends Gui {

	private ElementLogicBoard logicBoardElement;

	/**
	 * Konstruktor.
	 */
	public GuiLogicBoard() {
	}

	@Override
	public void initGui() {
		super.initGui();
		logicBoardElement = new ElementLogicBoard(64, 64, 96, 48, 0);

		logicBoardElement.setRule(new OmniSourceRule(), 2, 2);
		logicBoardElement.setRule(new OmniSourceRule(), 93, 2);
		for (int i = 3; i <= 92; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 2);
		}
		logicBoardElement.setRule(new AndGateRule(EnumFlow.SOUTH), 15, 2);
		for (int i = 3; i <= 30; i++) {
			logicBoardElement.setRule(new SimpleWireRule(false), 15, i);
		}

		// NOT
		logicBoardElement.setRule(new OmniSourceRule(), 18, 16);
		for (int i = 19; i <= 24; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 16);
		}
		logicBoardElement.setRule(new NotGateRule(EnumFlow.EAST), 25, 16);
		for (int i = 26; i <= 32; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 16);
		}

		// OR
		logicBoardElement.setRule(new OmniSourceRule(), 18, 20);
		for (int i = 19; i <= 24; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 20);
		}
		logicBoardElement.setRule(new OrGateRule(EnumFlow.SOUTH), 25, 20);
		for (int i = 26; i <= 31; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 20);
		}
		logicBoardElement.setRule(new OmniSourceRule(), 32, 20);
		for (int i = 21; i <= 24; i++) {
			logicBoardElement.setRule(new SimpleWireRule(false), 25, i);
		}

		// AND
		logicBoardElement.setRule(new OmniSourceRule(), 18, 26);
		for (int i = 19; i <= 24; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 26);
		}
		logicBoardElement.setRule(new AndGateRule(EnumFlow.SOUTH), 25, 26);
		for (int i = 26; i <= 31; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 26);
		}
		logicBoardElement.setRule(new OmniSourceRule(), 32, 26);
		for (int i = 27; i <= 30; i++) {
			logicBoardElement.setRule(new SimpleWireRule(false), 25, i);
		}

		// XOR
		logicBoardElement.setRule(new OmniSourceRule(), 18, 32);
		for (int i = 19; i <= 24; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 32);
		}
		logicBoardElement.setRule(new XOrGateRule(EnumFlow.SOUTH), 25, 32);
		for (int i = 26; i <= 31; i++) {
			logicBoardElement.setRule(new SimpleWireRule(true), i, 32);
		}
		logicBoardElement.setRule(new OmniSourceRule(), 32, 32);
		for (int i = 33; i <= 36; i++) {
			logicBoardElement.setRule(new SimpleWireRule(false), 25, i);
		}

		addElement(logicBoardElement);
	}

	@Override
	public void render(Screen screen) {
		RenderUtil.drawDefaultBackground(screen);
		super.render(screen);
	}

	@Override
	public void update() {
		super.update();
	}

}

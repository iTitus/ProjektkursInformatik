package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.dialog.DialogPart;
import projektkurs.entity.Entity;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.Element;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.TextView;
import projektkurs.lib.Integers;

/**
 * Das Dialogs-GUI.
 */
public class GuiDialog extends Gui implements IButtonListener {

	/**
	 * Aktuell angezeigter DialogPart.
	 */
	private final DialogPart dialogPart;
	/**
	 * Entity No. 1.
	 */
	@SuppressWarnings("unused")
	private final Entity entity1;
	/**
	 * Entity No. 2.
	 */
	@SuppressWarnings("unused")
	private final Entity entity2;
	/**
	 * Das Dialog-Anzeigefeld.
	 */
	private final TextView text;

	/**
	 * Konstruktor.
	 *
	 * @param parent     Eltern-Gui
	 * @param dialogPart anzuzeigender DialogPart
	 * @param entity1    ein Entity
	 * @param entity2    anderer Entity
	 */
	public GuiDialog(Gui parent, DialogPart dialogPart, Entity entity1, Entity entity2) {
		super(parent);
		this.dialogPart = dialogPart;
		this.entity1 = entity1;
		this.entity2 = entity2;
		text = new TextView(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, 0, dialogPart.getNextString());
		text.setBackgroundColor(0x0);
		text.setTextColor(0xFFFFFF);
	}

	@Override
	public void initGui() {
		super.initGui();
		addElement(text);
		addElement(new Button(34, 34 + 64, 1, this, "button.dialog.next"));
	}

	@Override
	public void onButtonLeftClick(Button button, MouseEvent e) {
		if (dialogPart.hasNextString()) {
			text.setText(dialogPart.getNextString());
			if (!dialogPart.hasNextString()) {
				button.setName("button.dialog.exit");
			}
		} else {
			dialogPart.onFinish();
			Main.openGui(getParent());
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
}

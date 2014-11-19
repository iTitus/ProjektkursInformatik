package projektkurs.gui.element;

import java.awt.event.MouseEvent;

public interface IButtonListener extends IElementListener {

	public void onButtonLeftClick(Button button, MouseEvent e);

	public void onButtonRightClick(Button button, MouseEvent e);

}

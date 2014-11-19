package projektkurs.gui.element;

public interface ITextFieldListener extends IElementListener {

	public void onFocusGained(TextField field);

	public void onFocusLost(TextField field);

	public void onTextChanged(TextField field);

}

package projektkurs.dialog;

import projektkurs.dialog.part.DialogPart;

public class Dialog {

	private DialogPart currPart;

	public Dialog() {
	}

	public String[] getStrings() {
		String[] ret = new String[currPart.getSize()];
		for (int i = 0; i < currPart.getSize(); i++) {
			ret[i] = currPart.isGoodEnoughFor(i) ? currPart.getStringAt(i)
					: null;
		}
		return ret;
	}

	public void setNext(int i) {
		DialogManager.changeValueBy(currPart.getValue());
		if (!currPart.isGoodEnoughFor(i))
			throw new IllegalArgumentException();
		currPart = currPart.getPartAt(i);
	}
}

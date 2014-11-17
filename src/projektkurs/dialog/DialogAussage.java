package projektkurs.dialog;

import projektkurs.util.I18n;

public class DialogAussage {

	private final String string;

	private final int wertung;

	public DialogAussage(String _string, int _wertung) {
		string = _string;
		wertung = _wertung;
	}

	public String getString() {
		return I18n.getString(string);
	}

	public int getWertung() {
		return wertung;
	}
}

package projektkurs.dialogsystem;

import projektkurs.util.I18n;

public class DialogAussage {

	private String string;

	private int wertung;

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

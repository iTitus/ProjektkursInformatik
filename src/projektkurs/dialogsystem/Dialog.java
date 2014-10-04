package projektkurs.dialogsystem;

public class Dialog {

	private int AnzahlAussagenmöglichkeiten;
	private DialogAussage[] Aussagenarray;
	private int Dialogdurchläufe;
	private int Dialognummer;

	public Dialog() {

		Dialognummer = 0;
		Dialogdurchläufe = 0;
		AnzahlAussagenmöglichkeiten = 0;

		Aussagenarray = new DialogAussage[AnzahlAussagenmöglichkeiten];
	}

	public int[] Aussagenbelegung(DialogAussage[] _Aussagenarray) {
		// Ich habe keine Ahnung was diese Methode tut
		return null;

	}

	public int[] ReaktionaufDialog() {
		return null;
	}

	private int getDialognummer(int Dialognummer) {
		return Dialognummer;
	}
}

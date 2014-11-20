package projektkurs.dialog.part;

import projektkurs.dialog.DialogManager;

public class DialogPart {

	private boolean isGood;
	private int necValue;
	private String npcAnswer;
	private DialogPart[] parts;
	private int value;

	public DialogPart(String npcAnswer, int value, int necValue,
			boolean isGood, DialogPart... parts) {
		this.parts = parts;
		this.value = value;
		this.npcAnswer = npcAnswer;
		this.parts = parts;
		this.necValue = necValue;
		this.isGood = isGood;
	}

	public int getNecValue() {
		return necValue;
	}

	public String getNpcAnswer() {
		return npcAnswer;
	}

	public DialogPart getPartAt(int i) {
		if (i < 0 || i > parts.length)
			throw new IllegalArgumentException(i + "is not in parts");
		return parts[i];
	}

	public int getSize() {
		return parts.length;
	}

	public String getStringAt(int i) {
		if (i < 0 || i > parts.length)
			throw new IllegalArgumentException(i + " is not in parts");
		return parts[i].getNpcAnswer();
	}

	public int getValue() {
		return value;
	}

	public boolean isGoodEnoughFor(int i) {
		return isGood ? parts[i].getNecValue() >= DialogManager.getCurrValue()
				: parts[i].getNecValue() < DialogManager.getCurrValue();
	}

	public boolean shouldExit() {
		return parts == null;
	}

	public String string(int i) {
		if (i < 0 || i > parts.length)
			throw new IllegalArgumentException(i + "is not in parts");
		return parts[i].getNpcAnswer();
	}
}

package projektkurs.dialog.part;

import projektkurs.dialog.DialogManager;

public class DialogPart {

	private String npcAnswer;
	private int value;
	private DialogPart[] parts;
	private int necValue;
	private boolean isGood;

	public DialogPart(String npcAnswer, int value, int necValue,
			boolean isGood, DialogPart... parts) {
		this.parts = parts;
		this.value = value;
		this.npcAnswer = npcAnswer;
		this.parts = parts;
		this.necValue = necValue;
		this.isGood = isGood;
	}

	public String string(int i) {
		if (i < 0 || i > parts.length)
			throw new IllegalArgumentException(i + "is not in parts");
		return parts[i].getNpcAnswer();
	}

	public int getSize() {
		return parts.length;
	}

	public String getNpcAnswer() {
		return npcAnswer;
	}

	public int getValue() {
		return value;
	}

	public DialogPart getPartAt(int i) {
		if (i < 0 || i > parts.length)
			throw new IllegalArgumentException(i + "is not in parts");
		return parts[i];
	}

	public boolean isGoodEnoughFor(int i) {
		return isGood ? parts[i].getNecValue() >= DialogManager.getCurrValue()
				: parts[i].getNecValue() < DialogManager.getCurrValue();
	}

	public String getStringAt(int i) {
		if (i < 0 || i > parts.length)
			throw new IllegalArgumentException(i + " is not in parts");
		return parts[i].getNpcAnswer();
	}

	public boolean shouldExit() {
		return parts == null;
	}

	public int getNecValue() {
		return necValue;
	}
}

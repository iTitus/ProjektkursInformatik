package projektkurs.dialog;

public class DialogManager {

	private static int currValue;

	public static void changeValueBy(int dValue) {
		currValue += dValue;
	}

	public static int getCurrValue() {
		return currValue;
	}
}

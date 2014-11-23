package projektkurs.dialog;

/**
 * Managt den aktuell laufenden Dialog.
 */
public final class DialogManager {

  /**
   * Der aktuelle Wert des Dialogs.
   */
  private static int currValue;

  /**
   * Ändert den Wert des Dialogs.
   *
   * @param value
   *          Änderung
   */
  public static void changeValueBy(int value) {
    currValue += value;
  }

  /**
   * Der Wert des aktuellen Dialogs.
   *
   * @return Wert
   */
  public static int getCurrValue() {
    return currValue;
  }

  /**
   * Konstruktor.
   */
  private DialogManager() {
  }
}

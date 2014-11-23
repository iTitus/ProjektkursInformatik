package projektkurs.dialog;

import projektkurs.dialog.part.DialogPart;

/**
 * Dialog.
 */
public class Dialog {

  /**
   * Der aktuelle DialogPart.
   */
  private DialogPart currPart;

  /**
   * Strings des aktuellen DialogParts.
   *
   * @return Strings des aktuellen DialogParts
   */
  public String[] getStrings() {
    String[] ret = new String[currPart.getSize()];
    for (int i = 0; i < currPart.getSize(); i++) {
      ret[i] = currPart.isGoodEnoughFor(i) ? currPart.getStringAt(i) : null;
    }
    return ret;
  }

  /**
   * Ändert den aktuellen DialogPart.
   *
   * @param i
   *          Index
   */
  public void setNext(int i) {
    DialogManager.changeValueBy(currPart.getValue());
    if (!currPart.isGoodEnoughFor(i)) {
      throw new IllegalArgumentException();
    }
    currPart = currPart.getPartAt(i);
  }
}

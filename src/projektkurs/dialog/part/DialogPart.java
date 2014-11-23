package projektkurs.dialog.part;

import projektkurs.dialog.DialogManager;

/**
 * Ein Teil des Dialoges.
 */
public class DialogPart {

  /**
   * Ist dieser DialogPart schlecht.
   */
  private final boolean isGood;
  /**
   * Nec-Wert dieses DialogParts.
   */
  private final int     necValue;
  /**
   * Die Antwort des NPCs.
   */
  private final String  npcAnswer;
  /**
   * Die weiterführenden DialogParts.
   */
  private DialogPart[]  parts;
  /**
   * Wert dieses DialogParts.
   */
  private final int     value;

  /**
   * Konstruktor.
   *
   * @param npcAnswer
   *          Antwort des NPCs
   * @param value
   *          Wert
   * @param necValue
   *          Nec-Wert
   * @param isGood
   *          Ist der DialogPart gut
   * @param parts
   *          Weiterführend DialogParts
   */
  public DialogPart(String npcAnswer, int value, int necValue, boolean isGood, DialogPart... parts) {
    this.parts = parts;
    this.value = value;
    this.npcAnswer = npcAnswer;
    this.parts = parts;
    this.necValue = necValue;
    this.isGood = isGood;
  }

  /**
   * Der Nec-Wert.
   *
   * @return Nec-Wert
   */
  public int getNecValue() {
    return necValue;
  }

  /**
   * Die Antwort des NPCs.
   *
   * @return Antwort
   */
  public String getNpcAnswer() {
    return npcAnswer;
  }

  /**
   * Der weiterführende DialogPart am gegebenen Index.
   *
   * @param i
   *          Index
   * @return der weiterführende DialogPart
   */
  public DialogPart getPartAt(int i) {
    if (i < 0 || i >= parts.length) {
      throw new IllegalArgumentException(i + " is not in parts");
    }
    return parts[i];
  }

  /**
   * Die Größe dieses DialogParts.
   *
   * @return Größe
   */
  public int getSize() {
    return parts.length;
  }

  /**
   * Die Antwort des NPCs im weiterführenden DialogPart am gegebenen Index.
   *
   * @param i
   *          Index
   * @return Antwort
   */
  public String getStringAt(int i) {
    return getPartAt(i).getNpcAnswer();
  }

  /**
   * Wert dieses DialogParts.
   *
   * @return Wert
   */
  public int getValue() {
    return value;
  }

  /**
   * Ist der DialogPart am gegebenen Index gut genug.
   *
   * @param i
   *          Index
   * @return true, wenn ja; false, wenn nein
   */
  public boolean isGoodEnoughFor(int i) {
    return isGood ? parts[i].getNecValue() >= DialogManager.getCurrValue() : parts[i].getNecValue() < DialogManager.getCurrValue();
  }

  /**
   * Soll der Dialog beendet werden.
   *
   * @return true, wenn ja; false, wenn nein
   */
  public boolean shouldExit() {
    return parts == null;
  }
}

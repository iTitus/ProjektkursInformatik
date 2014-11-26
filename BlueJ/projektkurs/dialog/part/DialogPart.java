package projektkurs.dialog.part;

import java.util.Iterator;

import projektkurs.dialog.DialogManager;

/**
 * Ein Teil des Dialoges.
 */
public class DialogPart implements Iterable<String> {

    /**
     * Aktueller Index.
     */
    private int currIndex;
    /**
     * Ist dieser DialogPart gut. Wenn ja muss man über dem benötigten Wert liegen, wenn nein darunter.
     */
    private final boolean isGood;
    /**
     * Anzeigename.
     */
    private final String name;
    /**
     * Benötigter Wert.
     */
    private final int necessaryValue;
    /**
     * Enthält das Gespräch, jeweils ein String pro Person und Sprechpart.
     */
    private final String[] talk;
    /**
     * Änderung des Wertes.
     */
    private final int valueChange;

    /**
     * Konstruktor.
     *
     * @param name
     *            Anzeigename
     * @param valueChange
     *            Wertänderung
     * @param necessaryValue
     *            benötigter Wert
     * @param isGood
     *            ist dieser DialogPart gut
     * @param talk
     *            weiterführendes Gespräch
     */
    public DialogPart(String name, int valueChange, int necessaryValue, boolean isGood, String... talk) {
        this.name = name;
        this.valueChange = valueChange;
        this.necessaryValue = necessaryValue;
        this.isGood = isGood;
        this.talk = talk;
        currIndex = 0;
    }

    /**
     * Anzeigename.
     *
     * @return Anzeigename
     */
    public String getName() {
        return name;
    }

    /**
     * Der benötigte Wert.
     *
     * @return benötigter Wert
     */
    public int getNecessaryValue() {
        return necessaryValue;
    }

    /**
     * Nächster Sprechpart.
     *
     * @return nächster Sprechpart
     */
    public String getNextString() {
        return talk[currIndex++];
    }

    /**
     * Änderung des Wertes.
     *
     * @return Wertänderung
     */
    public int getValueChange() {
        return valueChange;
    }

    /**
     * Soll der aktuelle Sprechpart vom NPC gesprochen werden. Muss nach getName() ausgeführt werden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isNPCSpeaking() {
        return currIndex % 2 == 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return currIndex < talk.length;
            }

            @Override
            public String next() {
                return getNextString();
            }

            @Override
            public void remove() {
                // NO-OP
            }
        };
    }

    /**
     * Ist dieser DialogPart gut genug um angezeigt zu werden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldShowUp() {
        return isGood ? necessaryValue <= DialogManager.getCurrentValue() : necessaryValue >= DialogManager.getCurrentValue();
    }
}

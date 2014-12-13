package projektkurs.dialog;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Ein Teil des Dialoges.
 */
public class DialogPart implements Iterable<String> {

    /**
     * Ist dieser DialogPart aktivert.
     */
    private boolean activated;
    /**
     * Aktueller Index.
     */
    private int index;
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
     * @param talkLength
     *            Länge des weiterführenden Gesprächs
     */
    public DialogPart(String name, int valueChange, int necessaryValue, boolean isGood, int talkLength) {
        this.name = name;
        this.valueChange = valueChange;
        this.necessaryValue = necessaryValue;
        this.isGood = isGood;
        talk = new String[talkLength];
        for (int i = 0; i < talk.length; i++) {
            talk[i] = name + "." + i;
        }
        index = 0;
        activated = true;
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
        return talk[index++];
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
     * Gibt es einen nächsten Sprechpart.
     *
     * @return true, wenn ja; false wenn nein
     */
    public boolean hasNextString() {
        return index < talk.length;
    }

    /**
     * Ist dieser DialogPart aktiviert.
     *
     * @return true, wenn ja; false, wenn nein.
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Soll der aktuelle Sprechpart vom NPC gesprochen werden. Muss nach getNextString() ausgeführt werden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isNPCSpeaking() {
        return index % 2 == 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < talk.length;
            }

            @Override
            public String next() {
                if (index + 1 >= talk.length) {
                    throw new NoSuchElementException();
                }
                return talk[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Setzt den Anzeigenstatus.
     *
     * @param activated
     *            true, wenn er aktivert werden soll; false, wenn er deaktiviert werden soll
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * Ist dieser DialogPart gut genug um angezeigt zu werden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldShowUp() {
        return activated && (isGood ? necessaryValue <= DialogManager.getValue() : necessaryValue >= DialogManager.getValue());
    }

}

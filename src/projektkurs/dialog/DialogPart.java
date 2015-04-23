package projektkurs.dialog;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.NoSuchElementException;

import projektkurs.util.MethodInvoker;

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

    private MethodInvoker methodInvoker;
    /**
     * Anzeigename.
     */
    private final String name;
    /**
     * Benoetigter Wert.
     */
    private final long necessaryValue;
    /**
     * Enthaelt das Gespraech, jeweils ein String pro Person und Sprechpart.
     */
    private final String[] talk;
    /**
     * Aenderung des Wertes.
     */
    private final int valueChange;

    /**
     * Konstruktor
     *
     * @param name
     *            Anzeigename
     * @param talkLength
     *            Laenge des weiterfuehrenden Gespraechs
     */

    public DialogPart(String name, int talkLength) {
        this(name, 0, 0, talkLength);
    }

    /**
     * Konstruktor.
     *
     * @param name
     *            Anzeigename
     * @param valueChange
     *            Wertaenderung
     * @param necessaryValue
     *            benoetigter Wert
     * @param talkLength
     *            Laenge des weiterfuehrenden Gespraechs
     */
    public DialogPart(String name, int valueChange, int necessaryValue, int talkLength) {
        this.name = name;
        this.valueChange = valueChange;
        this.necessaryValue = necessaryValue;
        talk = new String[talkLength];
        for (int i = 0; i < talk.length; i++) {
            talk[i] = "dialog.part." + name + "." + i;
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
        return "dialog.part." + name + ".name";
    }

    /**
     * Der benoetigte Wert.
     *
     * @return benoetigter Wert
     */
    public long getNecessaryValue() {
        return necessaryValue;
    }

    /**
     * Naechster Sprechpart.
     *
     * @return naechster Sprechpart
     */
    public String getNextString() {
        return talk[index++];
    }

    /**
     * Aenderung des Wertes.
     *
     * @return Wertaenderung
     */
    public int getValueChange() {
        return valueChange;
    }

    /**
     * Gibt es einen naechsten Sprechpart.
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
     * Soll der aktuelle Sprechpart vom NPC gesprochen werden. Muss nach getNextString() ausgefuehrt werden.
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
                if (!hasNext()) {
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

    public void onFinish() {
        if (methodInvoker != null) {
            methodInvoker.invoke();
        }
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

    public DialogPart setEndAction(Method m, Object... objects) {
        methodInvoker = new MethodInvoker(m, objects);
        return this;
    }

    /**
     * Ist dieser DialogPart gut genug um angezeigt zu werden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldShowUp() {
        return necessaryValue == (necessaryValue & DialogManager.getValue());
    }

}

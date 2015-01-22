package projektkurs.gui.element;

import java.awt.event.MouseEvent;

/**
 * Ein Knopf, der beim Druecken den Namen wechselt.
 */
public class ToggleButton extends Button {

    /**
     * Aktueller Namensindex.
     */
    private int index;
    /**
     * Alle Namen.
     */
    private String[] names;

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param id
     *            Nummer
     * @param listener
     *            Listener
     * @param names
     *            alle Namen
     */
    public ToggleButton(int posX, int posY, int id, IButtonListener listener, String... names) {
        super(posX, posY, id, listener, names[0]);
        this.names = names;
        index = 0;
    }

    /**
     * Der aktuelle Namensindex.
     *
     * @return Namensindex
     */
    public int getIndex() {
        return index;
    }

    /**
     * Alle Namen.
     *
     * @return Namen.
     */
    public String[] getNames() {
        return names;
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            if (e.isShiftDown()) {
                setIndex(index + 10);
            }
            setIndex(index + 1);
            getListener().onButtonLeftClick(this, e);
        }
    }

    @Override
    public void onRightClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            if (e.isShiftDown()) {
                setIndex(index - 10);
            }
            setIndex(index - 1);
            getListener().onButtonRightClick(this, e);
        }
    }

    /**
     * Setzt den Namensindex fest.
     *
     * @param index
     *            Namensindex
     */
    public void setIndex(int index) {
        this.index = index;
        if (this.index < 0) {
            this.index = names.length - 1;
        }
        if (this.index >= names.length) {
            this.index = 0;
        }
        name = names[this.index];
    }

    /**
     * Setzt alle Namen fest.
     *
     * @param names
     *            Namen.
     */
    public void setNames(String[] names) {
        this.names = names;
        setIndex(index);
    }

}

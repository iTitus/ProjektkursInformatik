package projektkurs.gui.element;

import projektkurs.util.I18n;

/**
 * Ein Textfeld, das man nicht veraendern kann.
 */
public class TextView extends TextField {

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     * @param id
     *            Nummer
     * @param text
     *            Anfangstext
     * @param objects
     *            eventuelle Objekte
     */
    public TextView(int posX, int posY, int sizeX, int sizeY, int id, String text, Object... objects) {
        super(posX, posY, sizeX, sizeY, id, null, "");
        if (objects != null) {
            if (text == null && objects.length > 0) {
                text = "";
                for (int i = 0; i < objects.length - 1; i++) {
                    text += "%s, ";
                }
                text += "%s";
                this.text = String.format(text, objects);
            } else {
                this.text = I18n.getStringFormatted(text, objects);
            }
        } else {
            this.text = I18n.getString(text);
        }
        enabled = false;
    }

    @Override
    public void setEnabled(boolean enabled) {
        // NO-OP
    }

    @Override
    public void setFocused(boolean focussed) {
        // NO-OP
    }

    public void setText(String text, Object... objects) {
        if (objects != null) {
            if (text == null && objects.length > 0) {
                text = "";
                for (int i = 0; i < objects.length - 1; i++) {
                    text += "%s, ";
                }
                text += "%s";
                this.text = String.format(text, objects);
            } else {
                this.text = I18n.getStringFormatted(text, objects);
            }
        } else {
            this.text = I18n.getString(text);
        }
    }

    @Override
    public void setText(String text) {
        setText(text, new Object[0]);
    }

}

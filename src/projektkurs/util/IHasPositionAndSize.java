package projektkurs.util;

/**
 * Interface fuer Objekte, die eine Position und eine Groesse haben.
 */
public interface IHasPositionAndSize extends IHasPosition {

    /**
     * Die Breite.
     *
     * @return Breite
     */
    int getSizeX();

    /**
     * Die Hoehe.
     *
     * @return Hoehe
     */
    int getSizeY();

    /**
     * Setzt die Groesse.
     *
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     */
    void setSize(int sizeX, int sizeY);

}

package projektkurs.util;

/**
 * Interface fuer Objekte, die eine Position haben.
 */
public interface IHasPosition {

    /**
     * Die X-Koordinate.
     *
     * @return X-Koordinate.
     */
    int getPosX();

    /**
     * Die Y-Koordinate.
     *
     * @return Y-Koordinate.
     */
    int getPosY();

    /**
     * Setzt die Positon.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    void setPosition(int x, int y);

}

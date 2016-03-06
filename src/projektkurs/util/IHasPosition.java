package projektkurs.util;

/**
 * Interface fuer Objekte, die eine Position haben.
 */
public interface IHasPosition<P extends Number> {

    /**
     * Die X-Koordinate.
     * @return X-Koordinate.
     */
    P getPosX();

    /**
     * Die Y-Koordinate.
     * @return Y-Koordinate.
     */
    P getPosY();

    /**
     * Setzt die Positon.
     * @param x
     * X-Koordinate
     * @param y
     * Y-Koordinate
     */
    void setPosition(P x, P y);

}

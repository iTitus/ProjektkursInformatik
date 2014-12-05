package projektkurs.util;

/**
 * Interface für Objekte, die eine Position und eine Größe haben.
 */
public interface IHasPositionAndSize extends IHasPosition {

    /**
     * Die Breite.
     *
     * @return Breite
     */
    int getSizeX();

    /**
     * Die Höhe.
     *
     * @return Höhe
     */
    int getSizeY();

}

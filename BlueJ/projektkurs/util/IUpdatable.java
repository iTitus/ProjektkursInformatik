package projektkurs.util;

/**
 * Interface fuer Objekte, die upgedated werden koennen.
 */
public interface IUpdatable {

    /**
     * Kann dieses Objekt jetzt updaten.
     *
     * @return true, wenn ja; false, wenn nein
     */
    boolean canUpdate();

    /**
     * Updated.
     */
    void update();

}

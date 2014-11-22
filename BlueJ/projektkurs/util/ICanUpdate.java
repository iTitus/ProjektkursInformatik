package projektkurs.util;

/**
 * Interface für Objekte, die upgedated werden können.
 */
public interface ICanUpdate {

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

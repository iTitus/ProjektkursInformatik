package projektkurs.util;

/**
 * Mit diesem Interface können Objekte gespeichert und geladen werden.
 */
public interface ISaveable {

    public void load(SaveData data);

    public void write(SaveData data);

}

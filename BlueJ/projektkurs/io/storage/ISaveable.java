package projektkurs.io.storage;

/**
 * Mit diesem Interface koennen Objekte gespeichert und geladen werden.
 */
public interface ISaveable {

    /**
     * Laedt alle Felder des Objektes aus der gegebenen SaveData.
     *
     * @param data
     *            SaveData
     */
    void load(SaveData data);

    /**
     * Speichert ale Felder in der gegebenen SaveData.
     *
     * @param data
     *            SaveData
     */
    void write(SaveData data);

}

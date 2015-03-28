package projektkurs.io.storage;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import projektkurs.util.Logger;

public class SpeicherDatei {

    private List<SaveData> entitiesList;
    private Feld[] felder;
    private List<SaveData> infoList;
    private String name;
    private List<SaveData> triggerList;

    public SpeicherDatei(Feld[] felder, List<SaveData> infoList, List<SaveData> entitiesList, List<SaveData> triggerList) {
        this.felder = felder;
        this.infoList = infoList;
        this.entitiesList = entitiesList;
        this.triggerList = triggerList;
    }

    public List<SaveData> getEntitiesList() {
        return entitiesList;
    }

    public Feld[] getFelder() {
        return felder;
    }

    public List<SaveData> getInfoList() {
        return infoList;
    }

    public String getName() {
        return name;
    }

    public List<SaveData> getTriggerList() {
        return triggerList;
    }

    public void save(String name) {
        this.name = name;

        saveInfoList();
        saveEntitiesList();
        saveTriggerList();
        saveFeld();
    }

    public void setEntitiesList(List<SaveData> entitiesList) {
        this.entitiesList = entitiesList;
    }

    public void setFelder(Feld[] felder) {
        this.felder = felder;
    }

    public void setInfoList(List<SaveData> infoList) {
        this.infoList = infoList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTriggerList(List<SaveData> triggerList) {
        this.triggerList = triggerList;
    }

    /**
     * Speichert die Entities Liste
     */
    private void saveEntitiesList() {
        saveList("entities", entitiesList);
    }

    private void saveFeld() {
        // String s = "";
        // for (int i = 0; i < felder.length; i++) {
        // s += felder[i];
        // }
        // TODO
    }

    /**
     * Speichert die Info Liste
     */
    private void saveInfoList() {
        saveList("info", infoList);

    }

    /**
     * @param art
     *            Art der Liste
     * @param List
     *            Welche Liste
     */
    private void saveList(String art, List<SaveData> List) {

        try (FileOutputStream fileOut = new FileOutputStream(art + name + ".txt"); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(List);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to save data", t);
        }
    }

    /**
     * Speichert die Trigger List
     */
    private void saveTriggerList() {
        saveList("trigger", triggerList);
    }

}

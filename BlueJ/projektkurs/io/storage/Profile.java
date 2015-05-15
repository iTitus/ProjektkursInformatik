package projektkurs.io.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import projektkurs.util.Logger;

public class Profile {

    private final int level;
    private final String name;
    private final boolean neuerSpieler;
    private SpeicherDatei speicher;

    public Profile(String name, int level, boolean neu) {

        this.name = name;
        this.level = level;
        neuerSpieler = neu;
        if (neuerSpieler) {
            speicher = new SpeicherDatei(loadStandardFelder(), loadStandardInfoList(), loadStandartEntitiesList(), loadStandardTriggerList());
            Players newPlayer = new Players();
            newPlayer.setNewPlayer(name);
        } else {
            speicher = new SpeicherDatei(loadFelder(), loadInfoList(), loadEntitiesList(), loadTriggerList());
        }

    }

    public void editEntitiesList(List<SaveData> entities) {
        speicher.setEntitiesList(entities);
    }

    public void editInfoList(List<SaveData> info) {
        speicher.setInfoList(info);
    }

    public void editRaster(String[][] s) {

        Feld[] tempFelder = new Feld[s.length * s[0].length];
        int i = 0;
        for (int i1 = 0; i1 <= s[0].length; i1++) {

            for (int i2 = 0; i2 <= s.length; i2++) {

                tempFelder[i] = new Feld(i1, i2, s[i1][i2]);
                i++;

            }
        }

    }

    public void editTriggerList(ArrayList<SaveData> trigger) {
        speicher.setTriggerList(trigger);
    }

    public List<SaveData> getEntitiesList() {
        return speicher.getEntitiesList();
    }

    public List<SaveData> getInfoList() {
        return speicher.getInfoList();
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String[][] getRaster() {

        int laengeX = getSizeX();
        int laengeY = getSizeY();
        int X2 = 0;

        String temp;
        temp = loadTXTDatei("feld");
        String[] teile1 = temp.split(";");
        String[][] returnString = new String[laengeX][laengeY];

        for (int X = 0; X <= laengeX; X++) {

            for (int Y = 0; Y <= laengeY; Y++) {

                String[] temp2 = teile1[X2].split(" ");

                String[] temp3 = temp2[2].split("=");

                returnString[X][Y] = temp3[1];
                X2++;
            }

        }
        return returnString;
    }

    public List<SaveData> getTriggerList() {
        return speicher.getTriggerList();
    }

    public void savePlayer() {
        speicher.save(name);
        writeSaveableTXTDatei(name, level);
    }

    private int getSizeX() {

        int i = 0;
        int x = 0;
        String temp;
        temp = loadTXTDatei("feld");
        String[] teile1 = temp.split(";");

        while (i == x) {

            String[] temp2 = teile1[i].split(" ");

            String[] temp3 = temp2[0].split("=");
            int tempX = Integer.parseInt(temp3[1]);

            x = tempX;
            i++;

        }
        return i - 1;
    }

    private int getSizeY() {

        int i = 0;
        int x = 0;
        String temp;
        temp = loadTXTDatei("feld");
        String[] Teile1 = temp.split(";");

        while (i == x) {

            String[] temp2 = Teile1[i].split(" ");

            String[] temp3 = temp2[1].split("=");
            int tempY = Integer.parseInt(temp3[1]);
            x = tempY;
            i++;

        }

        return i - 1;
    }

    private List<SaveData> loadEntitiesList() {
        return loadList("entities");
    }

    private Feld[] loadFelder() {
        String temp;
        temp = loadTXTDatei("feld");

        String[] teile1 = temp.split(";");
        Feld[] tempFeld = new Feld[teile1.length];

        for (int X = 0; X <= teile1.length; X++) {

            String[] temp2 = teile1[X].split(" ");

            String[] temp3 = temp2[0].split("=");
            int tempX = Integer.parseInt(temp3[1]);

            temp3 = temp2[1].split("=");
            int tempY = Integer.parseInt(temp3[1]);

            temp3 = temp2[2].split("=");

            tempFeld[X] = new Feld(tempX, tempY, temp3[1]);

        }
        return tempFeld;
    }

    private List<SaveData> loadInfoList() {
        return loadList("info");
    }

    @SuppressWarnings("unchecked")
    private List<SaveData> loadList(String art) {
        List<SaveData> tempList = null;
        try (FileInputStream fileIn = new FileInputStream(art + name + ".txt"); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            tempList = (List<SaveData>) in.readObject();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return tempList;

    }

    @SuppressWarnings("unchecked")
    private List<SaveData> loadNewList(String s) {
        List<SaveData> tempList = null;
        try (FileInputStream fileIn = new FileInputStream(s); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            tempList = (List<SaveData>) in.readObject();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return tempList;
    }

    private String loadNewTXTDatei(String s) {
        String zeile = null;
        try (FileInputStream EingabeStrom = new FileInputStream(s); DataInputStream EingabeDatei = new DataInputStream(EingabeStrom); BufferedReader SavedataLesen = new BufferedReader(new InputStreamReader(EingabeDatei))) {
            zeile = SavedataLesen.readLine();
        } catch (Exception e) {
            Logger.logThrowable("Unable to read data", e);
        }
        return zeile;
    }

    private Feld[] loadStandardFelder() {
        String temp;
        temp = loadNewTXTDatei("standardFeld.txt");

        String[] teile1 = temp.split(";");
        Feld[] tempFeld = new Feld[teile1.length];

        for (int X = 0; X == teile1.length; X++) {

            String[] temp2 = teile1[X].split(" ");

            String[] temp3 = temp2[0].split("=");
            int tempX = Integer.parseInt(temp3[1]);

            temp3 = temp2[1].split("=");
            int tempY = Integer.parseInt(temp3[1]);

            temp3 = temp2[2].split("=");

            tempFeld[X] = new Feld(tempX, tempY, temp3[1]);

        }
        return tempFeld;
    }

    private List<SaveData> loadStandardInfoList() {
        return loadNewList("standardInfo.txt");
    }

    private List<SaveData> loadStandardTriggerList() {
        return loadNewList("standardTrigger.txt");
    }

    private List<SaveData> loadStandartEntitiesList() {
        return loadNewList("standardEntities.txt");
    }

    private List<SaveData> loadTriggerList() {
        return loadList("trigger");
    }

    private String loadTXTDatei(String art) {
        String zeile = null;
        try (FileInputStream EingabeStrom = new FileInputStream(art + name + ".txt"); DataInputStream EingabeDatei = new DataInputStream(EingabeStrom); BufferedReader SavedataLesen = new BufferedReader(new InputStreamReader(EingabeDatei))) {
            zeile = SavedataLesen.readLine();
        } catch (Exception e) {
            Logger.logThrowable("Unable to read data", e);
        }
        return zeile;
    }

    private void writeSaveableTXTDatei(String n, int l) {
        try (FileWriter savedataOeffnen = new FileWriter(name + name); BufferedWriter savedataSchreiben = new BufferedWriter(savedataOeffnen)) {
            savedataSchreiben.write(n + " " + l);
        } catch (Exception e) {
            Logger.logThrowable("Unable to write data", e);
        }
    }
}

package projektkurs.io.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import projektkurs.util.Logger;

public class Players {

    public Players() {
        // NO-OP
    }

    public String[] getAllPlayers() {
        String zeile = getTXTDatei();
        String[] zeile2 = zeile.split(";");
        return zeile2;
    }

    public void setNewPlayer(String name) {
        String zeile = getTXTDatei();
        zeile = zeile + name + ";";
        writeTXTDatei(zeile);
    }

    public void writeTXTDatei(String s) {
        try (FileWriter SavedataÖffnen = new FileWriter("allPlayers.txt"); BufferedWriter SavedataSchreiben = new BufferedWriter(SavedataÖffnen)) {
            SavedataSchreiben.write(s);
        } catch (Exception e) {
            Logger.logThrowable("Unable to write data", e);
        }

    }

    private String getTXTDatei() {
        String zeile = null;
        try (FileInputStream EingabeStrom = new FileInputStream("allPlayers.txt"); DataInputStream EingabeDatei = new DataInputStream(EingabeStrom); BufferedReader SavedataLesen = new BufferedReader(new InputStreamReader(EingabeDatei))) {
            zeile = SavedataLesen.readLine();
        } catch (Exception e) {
            Logger.logThrowable("Unable to read profile", e);
        }
        return zeile;
    }

}

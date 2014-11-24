package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityRedNPC;
import projektkurs.lib.Entities;
import projektkurs.util.Logger;
import projektkurs.util.SaveData;

/**
 * Test.
 */
public final class Testing {

  /**
   * Hauptmethode.
   *
   * @param args
   *          Konsolenargumente
   */
  public static void main(String[] args) {
    // Main.main(args);
    // saveDataTest();
    int j = 11664;
    Logger.debug((char) j + " - " + j);
    Logger.debug((char) -j + " - " + -j);
    for (int i = 0; i < 1 << 15; i++) {
      Logger.debug((char) i + " - " + i);
    }
    Logger.saveLog();
  }

  /**
   * Der SaveData-Test.
   */
  @SuppressWarnings("resource")
  public static void saveDataTest() {
    SaveData a = new SaveData();
    a.set("a", "'A'");

    SaveData b = new SaveData();
    b.set("cccccCccccc", "lalalala");
    a.set("MOAR", b);
    System.out.println(a);

    try {
      FileOutputStream fileOut = new FileOutputStream("savedata.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(a);
      out.close();
      fileOut.close();
      System.out.println("Serialized data is saved in savedata.ser");
    } catch (Throwable t) {
      t.printStackTrace();
    }

    SaveData deser = null;

    try {
      FileInputStream fileIn = new FileInputStream("savedata.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      deser = (SaveData) in.readObject();
      in.close();
      fileIn.close();
      System.out.println("Deserialized SaveData...");
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.out.println(deser);

    Entity toWrite = new EntityRedNPC(5, 5, null);
    System.out.println(toWrite);
    SaveData ind = Entities.writeEntity(toWrite);
    System.out.println(ind);

    try {
      FileOutputStream fileOut = new FileOutputStream("entity.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(ind);
      out.close();
      fileOut.close();
      System.out.println("Serialized data is saved in entity.ser");
    } catch (Throwable t) {
      t.printStackTrace();
    }

    SaveData eDeser = null;

    try {
      FileInputStream fileIn = new FileInputStream("entity.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      eDeser = (SaveData) in.readObject();
      in.close();
      fileIn.close();
      System.out.println("Deserialized Entity-SaveData...");
    } catch (Throwable t) {
      t.printStackTrace();
    }
    if (eDeser != null) {
      Entity e = Entities.loadEntity(eDeser);
      System.out.println(eDeser);
      System.out.println(e);
    } else {
      System.out.println("Loading failed");
    }
  }

  /**
   * Nicht instanziierbar.
   */
  private Testing() {
  }
}

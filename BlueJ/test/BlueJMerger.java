package test;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

import projektkurs.Main;

/**
 * Helperklasse zum Automatischen BlueJ-Mergen.
 */
public final class BlueJMerger {

  /**
   * Allows directories and all the files with the given endings.
   */
  public static class FileFilterImpl implements FileFilter {

    /**
     * Mögliche Endungen.
     */
    private final String[] endings;

    /**
     * Konstruktor.
     *
     * @param endings
     *          Endungen
     */
    public FileFilterImpl(String... endings) {
      this.endings = new String[endings.length];
      for (int i = 0; i < endings.length; i++) {
        this.endings[i] = endings[i].toLowerCase(Locale.ENGLISH);
      }
    }

    @Override
    public boolean accept(File pathName) {

      String name = pathName.getName().toLowerCase(Locale.ENGLISH);

      for (String ending : endings) {
        if (name.endsWith(ending)) {
          return true;
        }
      }

      return pathName.isDirectory();
    }
  }

  /**
   * Hauptmethode.
   *
   * @param args
   *          Konsolenargumente
   * @throws Throwable
   *           damit kein try-catch benutzt werden muss
   */
  public static void main(String[] args) throws Throwable {

    File projectDir = new File(Main.class.getResource("").toURI()).getParentFile().getParentFile();
    File blueJDir = new File(projectDir, "BlueJ");
    File eclipseDir = new File(projectDir, "src");

    System.out.println(blueJDir);
    System.out.println(eclipseDir);

    walkDirDelete(blueJDir.getAbsolutePath(), ".java", ".class", ".ctxt", ".png", ".wav");
    walkDirCopy(eclipseDir.getAbsolutePath(), blueJDir.getAbsolutePath(), true, null, ".java", ".png", ".wav");
  }

  /**
   * Bennent eine Datei solange um, bis sie passt.
   *
   * @param path
   *          Pfad zur Datei
   * @return Datei, die noch nicht existiert
   */
  public static File rename(String path) {
    File newFile = new File(path);

    while (newFile.exists()) {
      String newPath = newFile.getPath();
      int lastIndex = newPath.lastIndexOf(".");
      newPath = newPath.substring(0, lastIndex) + "_" + newPath.substring(lastIndex);
      newFile = new File(newPath);
    }

    return newFile;
  }

  /**
   * Kopiert alle Dateien mit den gegebenen Endungen aus einem Ordner und fügt sie in einen anderen Ordner wieder ein.
   *
   * @param fromDir
   *          Quellordner
   * @param toDir
   *          Zielordner
   * @param keepFileStructure
   *          ob die Ordnerstruktur behalten werden soll
   * @param currentRelativePath
   *          sollte am Anfang null sein - wird nur für die rekursiven Ausdrücke benutzt
   * @param endings
   *          Endungen
   */
  public static void walkDirCopy(String fromDir, String toDir, boolean keepFileStructure, String currentRelativePath, String... endings) {

    File fromFile = new File(fromDir);
    File toFile = new File(toDir);
    if (!fromFile.exists() || fromFile.isFile() || !fromFile.isDirectory() || !fromFile.canRead() || fromFile.listFiles() == null || fromFile.listFiles().length <= 0 || !toFile.exists() || toFile.isFile() || !toFile.isDirectory() || !toFile.canRead() || !toFile.canWrite()) {
      throw new IllegalArgumentException("Quellen-/Zielordner ungültig");
    }

    File[] files = fromFile.listFiles(new FileFilterImpl(endings));
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          walkDirCopy(file.getPath(), toDir, keepFileStructure, (currentRelativePath != null ? currentRelativePath + File.separator : File.separator) + file.getName(), endings);
        } else {
          System.out.println("Copying file: " + file.getAbsolutePath());
          try {

            if (keepFileStructure && currentRelativePath != null) {
              File folder = new File(toDir + currentRelativePath);
              if (!folder.exists()) {
                folder.mkdirs();
              }
            }

            Files.copy(file.toPath(), rename(toDir + (keepFileStructure && currentRelativePath != null ? currentRelativePath : "") + File.separator + file.getName()).toPath(), StandardCopyOption.COPY_ATTRIBUTES);
          } catch (Throwable t) {
            t.printStackTrace();
          }
        }
      }
    }

  }

  /**
   * Schneidet alle Dateien mit den gegebenen Endungen aus einem Ordner aus und fügt sie in einen anderen Ordner wieder ein.
   *
   * @param dir
   *          Quellordner
   * @param path
   *          Zielordner
   * @param keepFileStructure
   *          ob die Ordnerstruktur behalten werden soll
   * @param endings
   *          Endungen
   */
  public static void walkDirCut(String dir, String path, boolean keepFileStructure, String... endings) {
    walkDirCopy(dir, path, keepFileStructure, null, endings);
    walkDirDelete(dir, endings);
  }

  /**
   * Löscht alle Dateien in einem Ordner mit den gegebenen Endungen.
   *
   * @param dir
   *          Ordner
   * @param endings
   *          Endungen
   */
  public static void walkDirDelete(String dir, String... endings) {
    File[] files = new File(dir).listFiles(new FileFilterImpl(endings));
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory()) {
          walkDirDelete(file.getPath(), endings);
        } else {
          System.out.println("Deleting file: " + file.getAbsolutePath());
          file.delete();
        }
      }
    }
  }

  /**
   * Nicht instanziierbar.
   */
  private BlueJMerger() {
  }

}

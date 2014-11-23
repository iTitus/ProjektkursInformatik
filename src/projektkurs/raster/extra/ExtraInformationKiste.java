package projektkurs.raster.extra;

import projektkurs.inventory.Inventory;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.util.SaveData;

/**
 * ExtraInformation einer Kiste.
 */
public class ExtraInformationKiste extends ExtraInformation {

  /**
   * Das Inventar.
   */
  private Inventory inventar;

  /**
   * Konstruktor.
   */
  public ExtraInformationKiste() {
    inventar = new Inventory(Integers.KISTENGROESSE);
  }

  /**
   * Das Inventar der Kiste.
   *
   * @return Inventar.
   */
  public Inventory getInventar() {
    return inventar;
  }

  @Override
  public void load(SaveData data) {
    super.load(data);
    inventar = Inventory.load(data.getSaveData(Strings.EXTRA_INV));
  }

  /**
   * Setzt das Inventar der Kiste.
   *
   * @param inventar
   *          Inventar
   */
  public void setInventar(Inventory inventar) {
    this.inventar = inventar;
  }

  @Override
  public void write(SaveData data) {
    super.write(data);
    data.set(Strings.EXTRA_INV, inventar.write());
  }

}

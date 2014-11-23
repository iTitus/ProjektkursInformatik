package projektkurs.lib;

import java.util.HashMap;

import projektkurs.raster.AbstractRaster;
import projektkurs.raster.DoorRaster;
import projektkurs.raster.FinishRaster;
import projektkurs.raster.FireRaster;
import projektkurs.raster.KistenRaster;
import projektkurs.raster.SimpleRaster;
import projektkurs.raster.SolidRaster;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

/**
 * Alle Raster.
 */
public final class Raster {

  /**
   * Zurück-Mappings.
   */
  public static final HashMap<AbstractRaster, String> BACK_MAPPINGS = new HashMap<AbstractRaster, String>();
  /**
   * Baum.
   */
  public static AbstractRaster                        baum;
  /**
   * Zerstörter Boden.
   */
  public static AbstractRaster                        destroyedRaster;
  /**
   * Tür.
   */
  public static AbstractRaster                        door;
  /**
   * Ziel.
   */
  public static AbstractRaster                        finish;
  /**
   * Feuer.
   */
  public static AbstractRaster                        fire;
  /**
   * Kiste.
   */
  public static AbstractRaster                        kiste;
  /**
   * Mappings.
   */
  public static final HashMap<String, AbstractRaster> MAPPINGS      = new HashMap<String, AbstractRaster>();
  /**
   * Rasen.
   */
  public static AbstractRaster                        rasen;
  /**
   * Wand.
   */
  public static AbstractRaster                        wand;

  /**
   * Initialisiert alle Items.
   */
  @Init(state = State.PRE)
  public static void init() {
    wand = new SolidRaster(Images.wand);
    registerRaster("wand", wand);

    kiste = new KistenRaster();
    registerRaster("kiste", kiste);

    rasen = new SimpleRaster(Images.rasen);
    registerRaster("rasen", rasen);

    baum = new SolidRaster(Images.baum);
    registerRaster("baum", baum);

    door = new DoorRaster();
    registerRaster("door", door);

    finish = new FinishRaster();
    registerRaster("finish", finish);

    destroyedRaster = new SimpleRaster(Images.destroyedRaster);
    registerRaster("destroyedRaster", destroyedRaster);

    fire = new FireRaster();
    registerRaster("fire", fire);

  }

  /**
   * Registriert ein Mapping.
   *
   * @param name
   *          Name
   * @param raster
   *          Raster
   */
  private static void registerRaster(String name, AbstractRaster raster) {
    MAPPINGS.put(name, raster);
    BACK_MAPPINGS.put(raster, name);
  }

  /**
   * Konstruktor.
   */
  private Raster() {
  }

}

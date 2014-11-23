package projektkurs.raster.extra;

import java.awt.image.BufferedImage;

import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Items;
import projektkurs.lib.Strings;
import projektkurs.util.Direction;
import projektkurs.util.SaveData;

/**
 * ExtraInformation einer Tür.
 */
public class ExtraInformationDoor extends ExtraInformation {

  /**
   * Richtung/Orientierung.
   */
  private Direction direction;
  /**
   * Ist die Tür geöffnet.
   */
  private boolean   isOpen;
  /**
   * Öffnungscode.
   */
  private int       openingKey;

  /**
   * Konstruktor.
   */
  public ExtraInformationDoor() {
    direction = Direction.UNKNOWN;
  }

  /**
   * Die Richtung/Orientierung.
   *
   * @return Richtung/Orientierung
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * Das Bild.
   *
   * @return Bild.
   */
  public BufferedImage getImage() {

    switch (direction) {
      case LEFT:
      case RIGHT:
        return isOpen ? Images.doorOpenEW : Images.doorEW;
      default:
        return isOpen ? Images.doorOpenNS : Images.doorNS;
    }
  }

  /**
   * Ist die Tür von der gegebenen Richtung aus offen und damit begehbar.
   *
   * @param dir
   *          Richtung
   * @return true, wenn ja; false, wenn nein
   */
  public boolean isOpenFromDirection(Direction dir) {
    return isOpen && (dir == direction || dir == direction.getOpposite());
  }

  @Override
  public void load(SaveData data) {
    super.load(data);
    direction = Direction.values()[data.getInteger(Strings.EXTRA_DIR)];
    isOpen = data.getBoolean(Strings.EXTRA_OPEN);
    openingKey = data.getInteger(Strings.EXTRA_KEY);
  }

  /**
   * Setzt die Richtung/Orientierung.
   *
   * @param direction
   *          Setzt die Richtung/Orientierung
   */
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * Setzt den Öffnungscode.
   *
   * @param openingKey
   *          Öffnungscode
   */
  public void setOpeningKey(int openingKey) {
    this.openingKey = openingKey;
  }

  /**
   * Versucht die Tür mit dem gegebenen Schlüssel zu öffnen.
   *
   * @param key
   *          ItemStack
   */
  public void tryOpen(ItemStack key) {
    if (key != null && key.itemEquals(Items.key) && key.getDamage() == openingKey) {
      isOpen = true;
    }
  }

  @Override
  public void write(SaveData data) {
    super.write(data);
    data.set(Strings.EXTRA_DIR, direction.ordinal());
    data.set(Strings.EXTRA_OPEN, isOpen);
    data.set(Strings.EXTRA_KEY, openingKey);
  }
}

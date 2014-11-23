package projektkurs.story.trigger;

/**
 * Prüft, ob sich der Spieler an einer gegebenen Position befindet.
 */
public class PosTrigger extends AreaTrigger {

  /**
   * Konstruktor.
   *
   * @param posX
   *          X-Koordinate
   * @param posY
   *          y-Koordinate
   */
  public PosTrigger(int posX, int posY) {
    super(posX, posY, 1, 1);
  }

}

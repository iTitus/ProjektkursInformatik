package projektkurs.story.trigger;

import projektkurs.Main;

/**
 * Prüft, ob sich der Spieler in einem gegebenen Rechteck befindet.
 */
public class AreaTrigger extends Trigger {

    /**
     * X-Koordinate.
     */
    private final int posX;
    /**
     * Y-Koordinate.
     */
    private final int posY;
    /**
     * Breite.
     */
    private final int sizeX;
    /**
     * Höhe.
     */
    private final int sizeY;

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     */
    public AreaTrigger(int posX, int posY, int sizeX, int sizeY) {
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean isTriggerActive() {
        return Main.getPlayer().isInside(posX, posY, sizeX, sizeY);
    }
}

package projektkurs.story.trigger;

import projektkurs.Main;
import projektkurs.io.storage.SaveData;

/**
 * Prueft, ob sich der Spieler in einem gegebenen Rechteck befindet.
 */
public class AreaTrigger extends AbstractTrigger {

    /**
     * X-Koordinate.
     */
    private int posX;
    /**
     * Y-Koordinate.
     */
    private int posY;
    /**
     * Breite.
     */
    private int sizeX;
    /**
     * Hoehe.
     */
    private int sizeY;

    /**
     * Konstruktor.
     * @param posX
     * X-Koordinate
     * @param posY
     * Y-Koordinate
     * @param sizeX
     * Breite
     * @param sizeY
     * Hoehe
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

    @Override
    public void load(SaveData data) {
        super.load(data);
        posX = data.getInteger("posX");
        posY = data.getInteger("posX");
        sizeX = data.getInteger("sizeX");
        sizeY = data.getInteger("sizeY");
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set("posX", posX);
        data.set("posY", posY);
        data.set("sizeX", sizeX);
        data.set("sizeY", sizeY);
    }
}

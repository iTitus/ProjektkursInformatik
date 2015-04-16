package projektkurs.render;

import projektkurs.lib.Integers;
import projektkurs.util.IHasPosition;

/**
 * Helperklasse zum Rendern.
 */
public class RenderHelper {

    /**
     * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map.
     */
    private int sightX;
    /**
     * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map.
     */
    private int sightY;

    /**
     * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map.
     *
     * @return X-Koordinate
     */
    public int getSightX() {
        return sightX;
    }

    /**
     * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map.
     *
     * @return Y-Koordinate
     */
    public int getSightY() {
        return sightY;
    }

    /**
     * Ist das gegebene Objekt im Sichtfeld.
     *
     * @param iHasPosition
     *            IHasPosition
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInSight(IHasPosition<Integer> iHasPosition) {
        return iHasPosition.getPosX() >= sightX && iHasPosition.getPosY() >= sightY && iHasPosition.getPosX() < sightX + Integers.sightX && iHasPosition.getPosY() < sightY + Integers.sightY;
    }

    /**
     * Ist der gegebene Punkt im Sichtfeld.
     *
     * @param x
     *            x
     * @param y
     *            y
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInSight(int x, int y) {
        return x >= sightX && y >= sightY && x < sightX + Integers.sightX && y < sightY + Integers.sightY;
    }

    /**
     * Bewegt das Spielfeld um dx und dy.
     *
     * @param dx
     *            Bewegung in x-Richtung
     * @param dy
     *            Bewegung in y-Richtung
     */
    public void moveSight(int dx, int dy) {
        sightX += dx;
        sightY += dy;
    }

    /**
     * Aktualisiert das Sichtfeld.
     *
     * @param sightX
     *            X-Koordinate der oberen linken Ecke des Sichtfeldes
     * @param sightY
     *            Y-Koordinate der oberen linken Ecke des Sichtfeldes
     */
    public void setSight(int sightX, int sightY) {
        this.sightX = sightX;
        this.sightY = sightY;
    }
}

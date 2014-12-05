package projektkurs.render;

import projektkurs.lib.Integers;
import projektkurs.util.IHasPosition;

/**
 * Helperklasse zum Rendern.
 */
public class RenderHelper {

    /**
     * Vergangene Renderticks.
     */
    private int renderTicks;

    /**
     * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map.
     */
    private int sightX;
    /**
     * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map.
     */
    private int sightY;

    /**
     * Fügt einen Rendertick hinzu.
     */
    public void addRenderTick() {
        renderTicks++;
    }

    /**
     * Die vergangenen Renderticks.
     *
     * @return Renderticks
     */
    public int getRenderTicks() {
        return renderTicks;
    }

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
    public boolean isInSight(IHasPosition iHasPosition) {
        return iHasPosition.getPosX() >= sightX && iHasPosition.getPosX() >= sightY && iHasPosition.getPosX() < sightX + Integers.sightX && iHasPosition.getPosY() < sightY + Integers.sightY;
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
        if (dx != 0 || dy != 0) {
            sightX += dx;
            sightY += dy;
        }
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
        if (sightX != this.sightX || sightY != this.sightY) {
            this.sightX = sightX;
            this.sightY = sightY;
        }
    }
}

package projektkurs.render;

import projektkurs.lib.Integers;

/**
 * Helperklasse zum Rendern
 */
public class RenderHelper {

    private int renderTicks;

    /**
     * Koordinaten der oberen linken Ecke des Sichtfeldes in der Map
     */
    private int sightX, sightY;

    public void addRenderTick() {
        renderTicks++;
    }

    public int getRenderTicks() {
        return renderTicks;
    }

    /**
     * X-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
     *
     * @return
     */
    public int getSightX() {
        return sightX;
    }

    /**
     * Y-Koordinate der oberen linken Ecke des Sichtfeldes in der Map
     *
     * @return
     */
    public int getSightY() {
        return sightY;
    }

    /**
     * Ist der gegebene Punk im Sichtfeld
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isInSight(int x, int y) {
        return x >= sightX && y >= sightY && x < sightX + Integers.SIGHT_X && y < sightY + Integers.SIGHT_Y;
    }

    /**
     * Bewegt das Spielfeld um dx und dy
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
     * Aktualisiert das Sichtfeld
     *
     * @param newSightX
     *            X-Koordinate der oberen linken Ecke des Sichtfeldes
     * @param newSightY
     *            Y-Koordinate der oberen linken Ecke des Sichtfeldes
     */
    public void setSight(int sightX, int sightY) {
        if (sightX != this.sightX || sightY != this.sightY) {
            this.sightX = sightX;
            this.sightY = sightY;
        }
    }
}

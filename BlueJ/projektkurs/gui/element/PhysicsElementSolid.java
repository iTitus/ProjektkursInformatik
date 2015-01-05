package projektkurs.gui.element;

import projektkurs.util.Vector2d;

/**
 * Ein festes Physik-Element.
 */
public class PhysicsElementSolid extends PhysicsElement implements IPhysicsListener {

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
     * @param id
     *            Nummer
     * @param listener
     *            Listener
     * @param mass
     *            Masse
     */
    public PhysicsElementSolid(int posX, int posY, int sizeX, int sizeY, int id, IPhysicsListener listener, double mass) {
        super(posX, posY, sizeX, sizeY, id, listener, 1);
    }

    @Override
    public void applyForceOnce(double fx, double fy) {
        // NO-OP
    }

    @Override
    public void applyForceOnce(Vector2d f) {
        // NO-OP
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void setAcceleration(double ax, double ay) {
        // NO-OP
    }

    @Override
    public void setAcceleration(Vector2d acceleration) {
        // NO-OP
    }

    @Override
    public void setForce(double fx, double fy) {
        // NO-OP
    }

    @Override
    public void setForce(Vector2d f) {
        // NO-OP
    }

    @Override
    public void setVelocity(double vx, double vy) {
        // NO-OP
    }

    @Override
    public void setVelocity(Vector2d velocity) {
        // NO-OP
    }

    @Override
    public void onCollide(PhysicsElement element) {
        Vector2d v_e = element.getVelocity();
        v_e.rotateRad(2 * v_e.angleBetweenRad(new Vector2d(0, 0)));
        element.setVelocity(v_e);
    }

}

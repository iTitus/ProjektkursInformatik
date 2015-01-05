package projektkurs.gui.element;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.util.RenderUtil;
import projektkurs.util.Vector2d;

/**
 * Ein Element mit physikalischen Eigenschaften.
 */
public class PhysicsElement extends Element {

    /**
     * Der Beschleunigungs-Vektor.
     */
    protected Vector2d acceleration;
    /**
     * Die Masse.
     */
    protected final double mass;
    /**
     * Der Geschwindigkeits-Vektor.
     */
    protected Vector2d velocity;
    /**
     * Der Kraft-Vektor.
     */
    protected Vector2d force;

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
    public PhysicsElement(int posX, int posY, int sizeX, int sizeY, int id, IPhysicsListener listener, double mass) {
        super(posX, posY, sizeX, sizeY, id, listener);
        velocity = new Vector2d(0, 0);
        acceleration = new Vector2d(0, 0);
        this.mass = mass;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public Vector2d getAcceleration() {
        return acceleration;
    }

    @Override
    public IPhysicsListener getListener() {
        return (IPhysicsListener) super.getListener();
    }

    public double getMass() {
        return mass;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setAcceleration(double ax, double ay) {
        acceleration = new Vector2d(ax, ay);
    }

    public void setAcceleration(Vector2d acceleration) {
        this.acceleration = acceleration;
    }

    public void setVelocity(double vx, double vy) {
        velocity = new Vector2d(vx, vy);
    }

    public void applyForceOnce(double fx, double fy) {
        acceleration = acceleration.add(new Vector2d(fx, fy).div(mass));
    }

    public void applyForceOnce(Vector2d f) {
        acceleration = acceleration.add(f.div(mass));
    }

    public void setForce(double fx, double fy) {
        force = new Vector2d(fx, fy);
    }

    public void setForce(Vector2d f) {
        force = f;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update() {
        acceleration = acceleration.add(force.div(mass));
        velocity = velocity.add(acceleration);
        posX += velocity.getIntX();
        posY += velocity.getIntY();
    }

    @Override
    public void render(Graphics2D g) {
        RenderUtil.drawFilledRectangle(g, Color.black, posX, posY, sizeX, sizeY);
    }

}

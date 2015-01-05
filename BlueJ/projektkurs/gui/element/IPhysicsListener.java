package projektkurs.gui.element;

/**
 * Ein Interface für ein GUI, das auf physikalische Aktionen hören soll.
 */
public interface IPhysicsListener extends IElementListener {

    void onCollide(PhysicsElement element);

}

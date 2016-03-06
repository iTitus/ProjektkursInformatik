package projektkurs.entity.behaviour;

import projektkurs.entity.Entity;
import projektkurs.util.IUpdatable;

/**
 * Abstrakte Behavior.
 */
public abstract class Behaviour implements IUpdatable {

    /**
     * Der Entity.
     */
    protected final Entity entity;

    /**
     * Konstruktor.
     * @param entity
     * Entity
     */
    public Behaviour(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Ist diese Behaviour kompatibel mit der gegebenen Behaviour.
     * @param behaviour
     * Behaviour
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isCompatibleWith(Behaviour behaviour) {
        return true;
    }

}

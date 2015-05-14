package projektkurs.entity.behaviour;

import projektkurs.entity.Entity;

/**
 * Der Entity tut nichts.
 */
public class BehaviourNothing extends Behaviour {

	/**
	 * Konstruktor.
	 *
	 * @param entity Entity
	 */
	public BehaviourNothing(Entity entity) {
		super(entity);
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public boolean isCompatibleWith(Behaviour behaviour) {
		return false;
	}

	@Override
	public void update() {
		// NO-OP
	}

}

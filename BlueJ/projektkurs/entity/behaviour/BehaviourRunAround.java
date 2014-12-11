package projektkurs.entity.behaviour;

import projektkurs.entity.Entity;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

/**
 * Der Entity läuft herum.
 */
public class BehaviourRunAround extends Behaviour {

    /**
     * Konstruktor.
     *
     * @param entity
     *            Entity
     */
    public BehaviourRunAround(Entity entity) {
        super(entity);
    }

    @Override
    public void update() {
        if (MathUtil.randomInt(Integers.RUN_AROUND_MOVE_CHANCE) == 0) {
            entity.moveBy(MathUtil.randomInt(-1, 1), MathUtil.randomInt(-1, 1));
        }
    }
}

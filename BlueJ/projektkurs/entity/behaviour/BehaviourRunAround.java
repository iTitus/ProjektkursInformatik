package projektkurs.entity.behaviour;

import projektkurs.entity.Entity;
import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

/**
 * Der Entity läuft herum.
 */
public class BehaviourRunAround implements IBehaviour {

  @Override
  public void update(Entity e) {
    if (MathUtil.randomInt(Integers.RUN_AROUND_MOVE_CHANCE) == 0) {
      e.moveBy(MathUtil.randomInt(-1, 1), MathUtil.randomInt(-1, 1));
    }
  }
}

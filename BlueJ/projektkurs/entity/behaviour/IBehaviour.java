package projektkurs.entity.behaviour;

import projektkurs.entity.Entity;

/**
 * Interface für eine Behavior.
 */
public interface IBehaviour {

  /**
   * Updated diese Behaviour.
   *
   * @param e
   *          der Entity
   */
  void update(Entity e);

}

package projektkurs.story.trigger;

/**
 * Trigger, der mehrere Trigger mit ODER verbindet.
 */
public class CombinedOrTrigger extends CombinedTrigger {

  /**
   * Konstruktor.
   *
   * @param trigger
   *          die Trigger
   */
  public CombinedOrTrigger(ITrigger... trigger) {
    super(trigger);
  }

  @Override
  public boolean isTriggerActive() {
    for (int i = 0; i < trigger.length; i++) {
      if (!trigger[i].isTriggerActive()) {
        return true;
      }
    }
    return false;
  }

}

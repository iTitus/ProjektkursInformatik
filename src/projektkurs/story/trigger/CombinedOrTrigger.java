package projektkurs.story.trigger;

/**
 * Trigger, der mehrere Trigger mit ODER verbindet.
 */
public class CombinedOrTrigger extends CombinedTrigger {

	/**
	 * Konstruktor.
	 *
	 * @param trigger die Trigger
	 */
	public CombinedOrTrigger(Trigger... trigger) {
		super(trigger);
	}

	@Override
	public boolean isTriggerActive() {
		for (Trigger trigger : triggers) {
			if (!trigger.isTriggerActive()) {
				return true;
			}
		}
		return false;
	}

}

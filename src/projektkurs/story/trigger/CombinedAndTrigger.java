package projektkurs.story.trigger;

/**
 * Trigger, der mehrere Trigger mit UND verbindet.
 */
public class CombinedAndTrigger extends CombinedTrigger {

	/**
	 * Konstruktor.
	 *
	 * @param trigger die Trigger
	 */
	public CombinedAndTrigger(Trigger... trigger) {
		super(trigger);
	}

	@Override
	public boolean isTriggerActive() {
		for (Trigger trigger : triggers) {
			if (!trigger.isTriggerActive()) {
				return false;
			}
		}
		return true;
	}

}

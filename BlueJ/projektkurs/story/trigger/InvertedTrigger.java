package projektkurs.story.trigger;

public class InvertedTrigger extends Trigger {

	private final Trigger t;

	public InvertedTrigger(Trigger t) {
		this.t = t;
	}

	@Override
	public boolean isTriggerActive() {
		return !t.isTriggerActive();
	}

}

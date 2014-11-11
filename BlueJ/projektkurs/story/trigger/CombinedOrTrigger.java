package projektkurs.story.trigger;

import java.lang.reflect.Method;

public class CombinedOrTrigger extends Trigger {

	private Trigger[] triggers;

	public CombinedOrTrigger(Method m, Trigger[] triggers, Object... objects) {
		super(m, objects);
		this.triggers = triggers;
	}

	@Override
	public boolean isTriggerActive() {
		for (int i = 0; i < triggers.length; i++) {
			if (!triggers[i].isTriggerActive())
				return true;
		}
		return false;
	}

}

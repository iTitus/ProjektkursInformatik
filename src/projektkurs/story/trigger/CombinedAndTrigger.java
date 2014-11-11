package projektkurs.story.trigger;

import java.lang.reflect.Method;

public class CombinedAndTrigger extends Trigger {

	private Trigger[] triggers;

	public CombinedAndTrigger(Method m, Trigger... triggers) {
		super(m);
		this.triggers = triggers;
	}

	@Override
	public boolean isTriggerActive() {
		for (int i = 0; i < triggers.length; i++) {
			if (!triggers[i].isTriggerActive())
				return false;
		}
		return true;
	}

}

package projektkurs.story.trigger;

import projektkurs.Main;

public class DialogTrigger extends Trigger {

	private final int value;

	public DialogTrigger(int value) {
		this.value = value;
	}

	@Override
	public boolean isTriggerActive() {
		return Main.getLevel().getDialogManager().getValue() == value;
	}
}

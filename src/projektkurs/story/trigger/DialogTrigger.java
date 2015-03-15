package projektkurs.story.trigger;

import projektkurs.dialog.DialogManager;

public class DialogTrigger extends Trigger {

	private final int value;

	public DialogTrigger(int value) {
		this.value = value;

	}

	@Override
	public boolean isTriggerActive() {

		return DialogManager.getValue() == value;
	}
}

package projektkurs.story.trigger;

import java.lang.reflect.Method;

import projektkurs.item.AbstractItem;

public class InventoryTrigger extends Trigger {

	private AbstractItem item;

	protected InventoryTrigger(Method m, AbstractItem item) {
		super(m);
		this.item = item;
	}

	@Override
	public boolean isTriggerActive() {
		return false;
	}

}

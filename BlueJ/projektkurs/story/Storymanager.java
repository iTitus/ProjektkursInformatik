package projektkurs.story;

import java.util.ArrayList;

import projektkurs.story.trigger.Trigger;

/**
 * 
 *
 */
public class Storymanager {

	/**
	 * 
	 */
	private Trigger[] triggers;

	/**
	 * 
	 * @param triggers
	 */
	public Storymanager() {
		this.triggers = new Trigger[0];
	}

	public void addTrigger(Trigger trigger) {
		for (int i = 0; i < triggers.length; i++) {
			if (triggers[i] == null) {
				triggers[i] = trigger;
				return;
			}
		}
		increaseCapacity();
		triggers[triggers.length - 1] = trigger;
	}

	/**
	 * 
	 * @return
	 */
	public Trigger[] getActiveTriggers() {
		ArrayList<Trigger> trArrayList = new ArrayList<Trigger>();
		for (int i = 0; i < triggers.length; i++) {
			if (triggers[i] != null && triggers[i].isTriggerActive()) {
				trArrayList.add(triggers[i]);
			}
		}
		return trArrayList.toArray(new Trigger[trArrayList.size()]);
	}

	public boolean isTriggerActive() {
		for (int i = 0; i < triggers.length; i++) {
			if (triggers[i] != null && triggers[i].isTriggerActive())
				return true;
		}
		return false;
	}

	public void removeTrigger(Trigger trigger) {
		Trigger t = null;
		for (int i = 0; i < triggers.length; i++) {
			t = triggers[i];
			if (t != null && t.equals(trigger)) {
				triggers[i] = null;
			}
		}
	}

	public void update() {
		ArrayList<Trigger> triggersToRemove = new ArrayList<Trigger>();
		Trigger t = null;
		for (int i = 0; i < triggers.length; i++) {
			t = triggers[i];
			if (t != null && t.isTriggerActive()) {
				t.doTrigger();
				triggersToRemove.add(t);
			}
		}

		for (Trigger toRemove : triggersToRemove)
			removeTrigger(toRemove);
	}

	private void increaseCapacity() {
		Trigger[] temp = new Trigger[triggers.length + 1];
		for (int i = 0; i < triggers.length; i++) {
			temp[i] = triggers[i];
		}
		triggers = temp;
	}
}
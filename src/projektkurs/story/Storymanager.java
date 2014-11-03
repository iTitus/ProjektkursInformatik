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
		for (int i = 0; i < triggers.length; i++) {
			if (triggers[i].equals(trigger)) {
				triggers[i] = null;
			}
		}
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

	private void increaseCapacity() {
		Trigger[] temp = new Trigger[triggers.length + 1];
		for (int i = 0; i < triggers.length; i++) {
			temp[i] = triggers[i];
		}
		triggers = temp;
	}

	public void update() {
		ArrayList<Trigger> toRemove = new ArrayList<Trigger>();

		for (int i = 0; i < triggers.length; i++) {
			if (triggers[i].isTriggerActive()) {
				triggers[i].doTrigger();
				toRemove.add(triggers[i]);
			}
		}

		for (Trigger t : toRemove)
			removeTrigger(t);

	}
}

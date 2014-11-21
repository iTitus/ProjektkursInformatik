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
     * @param triggers
     */
    public Storymanager() {
        triggers = new Trigger[0];
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
     * @return
     */
    public Trigger[] getActiveTriggers() {
        ArrayList<Trigger> trArrayList = new ArrayList<Trigger>();
        for (Trigger trigger : triggers) {
            if (trigger != null && trigger.isTriggerActive()) {
                trArrayList.add(trigger);
            }
        }
        return trArrayList.toArray(new Trigger[trArrayList.size()]);
    }

    public boolean isTriggerActive() {
        for (Trigger trigger : triggers) {
            if (trigger != null && trigger.isTriggerActive()) {
                return true;
            }
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
        for (Trigger trigger : triggers) {
            t = trigger;
            if (t != null && t.isTriggerActive()) {
                t.doTrigger();
                triggersToRemove.add(t);
            }
        }

        for (Trigger toRemove : triggersToRemove) {
            removeTrigger(toRemove);
        }
    }

    private void increaseCapacity() {
        Trigger[] temp = new Trigger[triggers.length + 1];
        System.arraycopy(triggers, 0, temp, 0, triggers.length);
        triggers = temp;
    }
}

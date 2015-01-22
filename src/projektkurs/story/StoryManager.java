package projektkurs.story;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import projektkurs.story.trigger.Trigger;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.MethodInvoker;

/**
 * Der Storymanager.
 */
public class StoryManager implements IUpdatable {

    /**
     * Alle Trigger.
     */
    private final HashMap<Trigger, MethodInvoker> triggerMap;

    /**
     * Konstruktor.
     */
    public StoryManager() {
        triggerMap = new HashMap<Trigger, MethodInvoker>();
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Registriert einen neuen Trigger hinzu.
     *
     * @param trigger
     *            hinzuzufuegender Trigger
     * @param m
     *            auszufuehrende Methode
     * @param objects
     *            eventuelle Parameter
     */
    public void registerTrigger(Trigger trigger, Method m, Object... objects) {
        if (triggerMap.containsKey(trigger)) {
            Logger.logThrowable("Unable to register Trigger '" + trigger.getClass() + "'", new IllegalArgumentException("'" + trigger.getClass() + "' is already registered"));
        } else {
            triggerMap.put(trigger, new MethodInvoker(m, objects));
        }
    }

    /**
     * Entfernt einen Trigger.
     *
     * @param trigger
     *            zu entfernender Trigger.
     */
    public void removeTrigger(Trigger trigger) {
        triggerMap.remove(trigger);
    }

    /**
     * Updated alle Trigger.
     */
    @Override
    public void update() {
        ArrayList<Trigger> triggerToRemove = new ArrayList<Trigger>();
        for (Entry<Trigger, MethodInvoker> entry : triggerMap.entrySet()) {
            if (entry.getKey().isTriggerActive()) {
                entry.getValue().invoke();
                triggerToRemove.add(entry.getKey());
            }
        }

        for (Trigger toRemove : triggerToRemove) {
            removeTrigger(toRemove);
        }

    }

}

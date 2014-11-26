package projektkurs.story;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import projektkurs.story.trigger.ITrigger;
import projektkurs.util.Logger;
import projektkurs.util.MethodInvoker;

/**
 * Der Storymanager.
 */
public class StoryManager {

    /**
     * Alle Trigger.
     */
    private final HashMap<ITrigger, MethodInvoker> triggerMap;

    /**
     * Konstruktor.
     */
    public StoryManager() {
        triggerMap = new HashMap<ITrigger, MethodInvoker>();
    }

    /**
     * Registriert einen neuen Trigger hinzu.
     *
     * @param trigger
     *            hinzuzufügender Trigger
     * @param m
     *            auszuführende Methode
     * @param objects
     *            eventuelle Parameter
     */
    public void registerTrigger(ITrigger trigger, Method m, Object... objects) {
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
    public void removeTrigger(ITrigger trigger) {
        triggerMap.remove(trigger);
    }

    /**
     * Updated alle Trigger.
     */
    public void update() {
        ArrayList<ITrigger> triggerToRemove = new ArrayList<ITrigger>();
        for (Entry<ITrigger, MethodInvoker> entry : triggerMap.entrySet()) {
            if (entry.getKey().isTriggerActive()) {
                entry.getValue().invoke();
                triggerToRemove.add(entry.getKey());
            }
        }

        for (ITrigger toRemove : triggerToRemove) {
            removeTrigger(toRemove);
        }

    }

}

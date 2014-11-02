package projektkurs.cutscene;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.Queue;

/**
 * Eine CutScene
 *
 */
public class CutScene {

	private Queue<Action> actionQueue, startupQueue, tempQueue;

	private int elapsedTicks;

	private Set<CutSceneObject> objects;

	/**
	 * Konstruktor für eine CutScene
	 */
	public CutScene() {
		actionQueue = new Queue<Action>();
		startupQueue = new Queue<Action>();
		tempQueue = new Queue<Action>();
		objects = Collections.synchronizedSet(new HashSet<CutSceneObject>());
		elapsedTicks = 0;
	}

	public void deSpawn(CutSceneObject object) {
		if (object != null) {
			synchronized (objects) {
				objects.add(object);
			}
		}
	}

	public int getElapsedTicks() {
		return elapsedTicks;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isFinished() {
		return actionQueue == null || actionQueue.empty();
	}

	/**
	 * Registriert eine einmalige Aktion
	 * 
	 * @param action
	 */
	public void registerStartupAction(Action action) {
		startupQueue.enQueue(action);
	}

	/**
	 * 
	 * @param action
	 */
	public void registerTickAction(Action action) {
		actionQueue.enQueue(action);
	}

	/**
	 * 
	 */
	public void setFinished() {
		actionQueue = null;
	}

	public void spawn(CutSceneObject object) {
		if (object != null) {
			synchronized (objects) {
				objects.add(object);
			}
		}
	}

	public void update() {

		if (elapsedTicks == 0 && !startupQueue.empty()) {

			while (!startupQueue.empty()) {
				startupQueue.front().doAction(this);
				startupQueue.deQueue();
			}

			startupQueue = null;

		}
		if (!actionQueue.empty()) {

			tempQueue.clear();

			Action currAction = actionQueue.front();

			while (!actionQueue.empty()) {
				currAction = actionQueue.front();
				actionQueue.deQueue();

				if (currAction != null && currAction.shouldDoAction(this))
					currAction.doAction(this);

				if (actionQueue == null)
					return;

				tempQueue.enQueue(currAction);
			}
			actionQueue = tempQueue;
		}

		elapsedTicks++;

	}
}
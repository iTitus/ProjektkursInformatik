package projektkurs.cutscene;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.util.Queue;

/**
 * Eine CutScene
 */
public class CutScene {

	private Queue<Action> actionQueue, startupQueue, tempQueue;
	private int elapsedTicks;
	private boolean isFinished;
	private final Set<CutSceneObject> objects;

	/**
	 * Konstruktor für eine CutScene
	 */
	public CutScene() {
		actionQueue = new Queue<Action>();
		startupQueue = new Queue<Action>();
		tempQueue = new Queue<Action>();
		objects = Collections.synchronizedSet(new HashSet<CutSceneObject>());
		elapsedTicks = 0;
		isFinished = false;
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
	 * @return
	 */
	public boolean isFinished() {
		return isFinished;
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
	 * @param action
	 */
	public void registerTickAction(Action action) {
		actionQueue.enQueue(action);
	}

	/**
     *
     */
	public void setFinished() {
		isFinished = true;
	}

	public void spawn(CutSceneObject object) {
		if (object != null) {
			synchronized (objects) {
				objects.add(object);
			}
		}
	}

	public void update() {

		if (elapsedTicks == 0 && startupQueue != null && !startupQueue.empty()) {

			Action currAction;
			while (!startupQueue.empty()) {
				currAction = startupQueue.frontDeQueue();
				if (currAction != null)
					currAction.doAction(this);
			}

			startupQueue = null;

		}
		if (actionQueue != null && !actionQueue.empty()) {

			tempQueue = new Queue<Action>();

			Action currAction;
			while (!actionQueue.empty()) {
				currAction = actionQueue.frontDeQueue();

				if (currAction != null && currAction.shouldDoAction(this))
					currAction.doAction(this);
				tempQueue.enQueue(currAction);
			}
			actionQueue = tempQueue;
		} else {
			isFinished = true;
		}

		elapsedTicks++;

	}
}
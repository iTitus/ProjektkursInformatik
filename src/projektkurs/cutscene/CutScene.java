package projektkurs.cutscene;

import java.util.ArrayList;

import projektkurs.cutscene.action.Action;
import projektkurs.cutscene.object.CutSceneObject;

/**
 * Eine CutScene
 *
 */
public class CutScene {

	private ActionQueue actionQueue, startupQueue, tempQueue;

	private int elapsedTicks;

	@SuppressWarnings("unused")
	private ArrayList<CutSceneObject> objects;

	/**
	 * Konstruktor für eine CutScene
	 */
	public CutScene() {
		actionQueue = new ActionQueue();
		startupQueue = new ActionQueue();
		objects = new ArrayList<CutSceneObject>();
		elapsedTicks = 0;
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

	public void update() {

		if (elapsedTicks == 0 && !startupQueue.empty()) {

			while (!startupQueue.empty()) {
				startupQueue.front().doAction(this);
				startupQueue.deQueue();
			}

			startupQueue = null;

		}
		if (!actionQueue.empty()) {

			tempQueue = new ActionQueue();

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
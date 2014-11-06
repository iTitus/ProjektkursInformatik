package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 *
 *
 */
public class TickCondition extends Condition {

	public static enum TickConditionType {
		EQUALS, GREATER, GREATER_EQUALS, LESSER, LESSER_EQUALS, MODULO_0
	}

	private final int ticks;

	private final TickConditionType type;

	/**
	 * Konstruktor für TickConditions
	 *
	 * @param _type
	 * @param _ticks
	 */
	public TickCondition(TickConditionType _type, int _ticks) {
		type = _type;
		ticks = _ticks;
	}

	@Override
	public boolean isTrue(Action action, CutScene cutScene) {

		switch (type) {
		case EQUALS: {
			return cutScene.getElapsedTicks() == ticks;
		}
		case LESSER: {
			return cutScene.getElapsedTicks() < ticks;
		}
		case LESSER_EQUALS: {
			return cutScene.getElapsedTicks() <= ticks;
		}
		case GREATER: {
			return cutScene.getElapsedTicks() > ticks;
		}
		case GREATER_EQUALS: {
			return cutScene.getElapsedTicks() >= ticks;
		}
		case MODULO_0: {
			return cutScene.getElapsedTicks() % ticks == 0;
		}
		default: {
			return false;
		}
		}

	}
}

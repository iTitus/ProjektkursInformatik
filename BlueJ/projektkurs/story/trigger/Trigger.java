package projektkurs.story.trigger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import projektkurs.util.Logger;

/**
 * Ein abstrakter Trigger
 *
 */
public abstract class Trigger {

	protected Method M;

	protected Trigger(Method M) {
		this.M = M;
	}

	/**
	 * 
	 * @return
	 */
	public abstract boolean isTriggerActive();

	@SuppressWarnings("all")
	public void doTrigger() {
		try {
			M.invoke(null, null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			Logger.logThrowable("This went wrong...", e);
		}
		
	}
}

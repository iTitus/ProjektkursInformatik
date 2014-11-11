package projektkurs.story.trigger;

import java.lang.reflect.Method;

import projektkurs.Main;

public class PosTrigger extends Trigger {

	private final int x, y;

	public PosTrigger(Method m, int x, int y, Object... objects) {
		super(m, objects);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isTriggerActive() {
		return (Main.getPlayer().getPosX() == x)
				&& (Main.getPlayer().getPosY() == y);
	}

}

package projektkurs.story.trigger;

import java.lang.reflect.Method;

import projektkurs.Main;

public class PosTrigger extends Trigger {

	private final int x, y;

	public PosTrigger(int x, int y, Method M) {
		super(M);
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean isTriggerActive() {
		return (Main.getPlayer().getPosX() == x)
				&& (Main.getPlayer().getPosY() == y);
	}

}

package projektkurs.story.trigger;

import java.lang.reflect.Method;

import projektkurs.Main;

public class AreaTrigger extends Trigger {

    private final int posX, posY, sizeX, sizeY;

    public AreaTrigger(Method m, int posX, int posY, int sizeX, int sizeY, Object... objects) {
        super(m, objects);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean isTriggerActive() {
        return Main.getPlayer().isInside(posX, posY, sizeX, sizeY);
    }
}

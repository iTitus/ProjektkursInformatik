package projektkurs.story.trigger;

import projektkurs.Main;
import projektkurs.util.MathUtil;

public class MouseInAreaTrigger extends AbstractTrigger {

    private final int posX, posY, sizeX, sizeY;

    public MouseInAreaTrigger(int posX, int posY, int sizeX, int sizeY) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean isTriggerActive() {
        return MathUtil.isInside(posX, posY, sizeX, sizeY, Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY(), 1, 1);
    }

}

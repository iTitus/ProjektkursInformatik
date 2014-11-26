package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;

/**
 * Thread, der für die Spielerbewegung zuständig ist.
 */
public class MoveThread extends LoopThread {

    /**
     * Konstruktor.
     */
    public MoveThread() {
        super("Movement", Integers.RPS);
    }

    @Override
    protected void runLoop() {
        try {
            Main.getInputManager().updateMoveDir();
        } catch (Throwable t) {
            Logger.logThrowable("Unable to update move direction", t);
            Main.exit();
        }
    }

}

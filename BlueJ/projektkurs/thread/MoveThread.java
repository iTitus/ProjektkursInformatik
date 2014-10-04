package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;

public class MoveThread extends LoopThread {

	public MoveThread() {
		super("Movement", Integers.REACTION_TIME);
	}

	@Override
	protected void runLoop() {
		Main.getInputManager().updateMoveDir();
	}

}

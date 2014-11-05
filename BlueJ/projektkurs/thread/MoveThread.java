package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;

public class MoveThread extends LoopThread {

	public MoveThread() {
		super("Movement", Integers.RPS);
	}

	@Override
	protected void runLoop() {
		Main.getInputManager().updateMoveDir();
	}

}

package projektkurs.render;

import java.awt.Canvas;

import projektkurs.Main;
import projektkurs.lib.Integers;

public class GameCanvas extends Canvas {

	private static final long serialVersionUID = 1L;

	public GameCanvas() {

		addKeyListener(Main.getInputManager());
		addMouseListener(Main.getInputManager());
		addMouseMotionListener(Main.getInputManager());
		addMouseWheelListener(Main.getInputManager());

		requestFocus();

		setIgnoreRepaint(true);
		setBounds(0, 0, Integers.WINDOW_X, Integers.WINDOW_Y);
	}

}

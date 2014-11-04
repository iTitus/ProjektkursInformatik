package projektkurs.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;

import javax.swing.event.MouseInputListener;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.lib.Sounds;
import projektkurs.util.Direction;
import projektkurs.world.raster.AbstractRaster;

/**
 * Zustaendig fuer den Input (Tasten, Maus)
 * 
 */
public class InputManager implements KeyListener, MouseInputListener,
		MouseWheelListener {

	/**
	 * Table for decoding of the int moveDir to the x-offset
	 */
	public static final int[] ofX = { 0, 0, 0, 0, -1, -1, -1, -1, 1, 1, 1, 1,
			0, 0, 0, 0 };
	/**
	 * Table for decoding of the int moveDir to the y-offset
	 */
	public static final int[] ofY = { 0, -1, 1, 0, 0, -1, 1, 0, 0, -1, 1, 0, 0,
			-1, 1, 0 };

	private static final int LEFT_MOUSE_BUTTON = MouseEvent.BUTTON1;
	private static final int RIGHT_MOUSE_BUTTON = MouseEvent.BUTTON3;

	/**
	 * Speichert alle gerade gedrueckten Tasten
	 */
	private final HashSet<Integer> keysPressed;

	private int mouseX, mouseY;

	/**
	 * Zwischenvariable, um die aktuelle Bewgeungsrichtung zu speichern
	 */
	private volatile int moveDir;

	/**
	 * Konstruktor um den InputManger zu initialisieren
	 */
	public InputManager() {
		keysPressed = new HashSet<Integer>(0);
		moveDir = 0b0000;
	}

	/**
	 * 
	 * @return
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * 
	 * @return
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * Die nächste Bewegungsrichtung. ACHTUNG: Zwischenspeichern empfohlen,
	 * resettet sich selber!
	 * 
	 * @return Direction, die nächste Bewegungsrichtung
	 */
	public Direction getNextDirection() {
		Direction dir = Direction.getDirectionForOffset(ofX[moveDir],
				ofY[moveDir]);
		moveDir = 0b0000;
		return dir;
	}

	/**
	 * 
	 * @param keyCode
	 *            Ein KeyCode aus KeyEvent
	 * @return Ob die mit keyCode verbundene Taste im Moment gedrueckt ist
	 */
	public boolean isKeyPressed(int keyCode) {
		return keysPressed.contains(keyCode);
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Taste heruntergedrueckt wird/ist
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		keysPressed.add(e.getKeyCode());

		if (keysPressed.contains(KeyBindings.KEY_EXIT))
			Main.exit();

	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Taste losgelassen wird
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		keysPressed.remove(e.getKeyCode());

	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Taste gedrueckt und wieder
	 * losgelassen wird
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Maustaste heruntergedrueckt und
	 * wieder losgelassen wird
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == RIGHT_MOUSE_BUTTON && e.isShiftDown())
			CutSceneManager.startCutScene(CutSceneManager.TEST());
		else if (e.getButton() == LEFT_MOUSE_BUTTON)
			Sounds.test.playFromStart();

		float rasterX = (((e.getX() + (Main.getRenderHelper().getSightX() * Integers.RASTER_SIZE)) - Integers.WINDOW_HUD_X) / (float) (Integers.RASTER_SIZE));
		if (rasterX < 0)
			rasterX--;
		float rasterY = (((e.getY() + (Main.getRenderHelper().getSightY() * Integers.RASTER_SIZE)) - Integers.WINDOW_HUD_Y) / (float) (Integers.RASTER_SIZE));
		if (rasterY < 0)
			rasterY--;
		AbstractRaster r = Main.getSpielfeld().getRasterAt((int) rasterX,
				(int) rasterY);
		if (r != null) {
			r.onClick((int) rasterX, (int) rasterY, e.getButton());
		}

	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus mit gedrueckter Maustaste bewegt
	 * wird
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus in das Fenster eingefuehrt wird
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus aus dem Fenster herausgeschoben
	 * wird
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus bewegt wird
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Maustaste heruntergedrueckt wird/ist
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Maustaste losgelassen wird
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgefuehrt, wenn das Mausrad gedreht wird
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			Main.getFigur()
					.getInventory()
					.setSelectedItemStack(
							Main.getFigur().getInventory().getSelectedIndex() >= Main
									.getFigur().getInventory().getSize() ? 0
									: Main.getFigur().getInventory()
											.getSelectedIndex() + 1);
		} else if (e.getWheelRotation() < 0) {
			Main.getFigur()
					.getInventory()
					.setSelectedItemStack(
							(Main.getFigur().getInventory().getSelectedIndex() <= 0 ? Main
									.getFigur().getInventory().getSize() - 1
									: Main.getFigur().getInventory()
											.getSelectedIndex() - 1));
		}
	}

	/**
	 * Methode um moveDir zu veraendern
	 */
	public void updateMoveDir() {

		moveDir = 0b0000;

		if (keysPressed.contains(KeyBindings.KEY_UP))
			moveDir |= 0b0001;
		if (keysPressed.contains(KeyBindings.KEY_DOWN))
			moveDir |= 0b0010;
		if (keysPressed.contains(KeyBindings.KEY_LEFT))
			moveDir |= 0b0100;
		if (keysPressed.contains(KeyBindings.KEY_RIGHT))
			moveDir |= 0b1000;

	}
}
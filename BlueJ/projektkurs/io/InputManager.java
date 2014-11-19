package projektkurs.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;

import javax.swing.event.MouseInputListener;

import projektkurs.Main;
import projektkurs.gui.GuiIngame;
import projektkurs.lib.KeyBindings;
import projektkurs.util.Direction;
import projektkurs.util.Queue;

/**
 * Zuständig für den Input (Tasten, Maus)
 */
public class InputManager implements KeyListener, MouseInputListener,
		MouseWheelListener {

	public static final int LEFT_MOUSE_BUTTON = MouseEvent.BUTTON1;
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
	public static final int RIGHT_MOUSE_BUTTON = MouseEvent.BUTTON3;

	private final Queue<KeyEvent> keyEvents;

	/**
	 * Speichert alle gerade gedrückten Tasten
	 */
	private final HashSet<Integer> keysPressed;
	private final Queue<MouseEvent> mouseEvents;
	private final Queue<MouseWheelEvent> mouseWheelEvents;

	private int mouseX, mouseY;

	/**
	 * Zwischenvariable, um die aktuelle Bewegungsrichtung zu speichern
	 */
	private volatile int moveDir;

	/**
	 * Konstruktor um den InputManger zu initialisieren
	 */
	public InputManager() {
		keysPressed = new HashSet<Integer>();
		keyEvents = new Queue<KeyEvent>();
		mouseEvents = new Queue<MouseEvent>();
		mouseWheelEvents = new Queue<MouseWheelEvent>();
		moveDir = 0;
	}

	/**
	 * @return
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
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

	public KeyEvent getNextKeyEvent() {
		return keyEvents.frontDeQueue();
	}

	public MouseEvent getNextMouseEvent() {
		return mouseEvents.frontDeQueue();
	}

	public MouseWheelEvent getNextMouseWheelEvent() {
		return mouseWheelEvents.frontDeQueue();
	}

	public boolean hasKeyEvents() {
		return !keyEvents.empty();
	}

	public boolean hasMouseEvents() {
		return !mouseEvents.empty();
	}

	public boolean hasMouseWheelEvents() {
		return !mouseWheelEvents.empty();
	}

	/**
	 * @param keyCode
	 *            Ein KeyCode aus KeyEvent
	 * @return Ob die mit keyCode verbundene Taste im Moment gedrückt ist
	 */
	public boolean isKeyPressed(int keyCode) {
		return keysPressed.contains(keyCode);
	}

	/**
	 * Wird von Java ausgeführt, wenn eine Taste heruntergedrückt wird/ist
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		keysPressed.add(e.getKeyCode());

	}

	/**
	 * Wird von Java ausgeführt, wenn eine Taste losgelassen wird
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		keysPressed.remove(e.getKeyCode());

	}

	/**
	 * Wird von Java ausgeführt, wenn eine Taste gedrückt und wieder losgelassen
	 * wird
	 */
	@Override
	public void keyTyped(KeyEvent e) {

		keyEvents.enQueue(e);

	}

	/**
	 * Wird von Java ausgeführt, wenn eine Maustaste heruntergedrückt und wieder
	 * losgelassen wird
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		mouseEvents.enQueue(e);

	}

	/**
	 * Wird von Java ausgeführt, wenn die Maus mit gedrückter Maustaste bewegt
	 * wird
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgeführt, wenn die Maus in das Fenster eingeführt wird
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgeführt, wenn die Maus aus dem Fenster herausgeschoben
	 * wird
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgeführt, wenn die Maus bewegt wird
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgeführt, wenn eine Maustaste heruntergedrückt wird/ist
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgeführt, wenn eine Maustaste losgelassen wird
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgeführt, wenn das Mausrad gedreht wird
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		mouseWheelEvents.enQueue(e);

	}

	/**
	 * Methode um moveDir zu verändern
	 */
	public void updateMoveDir() {

		moveDir = 0b0000;

		if (Main.getGui() instanceof GuiIngame) {
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
}
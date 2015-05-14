package projektkurs.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

import projektkurs.Main;
import projektkurs.gui.GuiIngame;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.util.Direction;
import projektkurs.util.IUpdatable;
import projektkurs.util.MathUtil;
import projektkurs.util.Queue;

/**
 * Zustaendig fuer den Input (Tastatur, Maus).
 */
public class InputManager implements KeyListener, MouseInputListener, MouseWheelListener, IUpdatable {

	/**
	 * Linker Mausknopf.
	 */
	public static final int LEFT_MOUSE_BUTTON = MouseEvent.BUTTON1;
	/**
	 * Tabelle um die moveDir in den X-Offset zu dekodieren.
	 */
	public static final int[] OFFSET_X = {0, 0, 0, 0, -1, -1, -1, -1, 1, 1, 1, 1, 0, 0, 0, 0};

	/**
	 * Tabelle um die moveDir in den Y-Offset zu dekodieren.
	 */
	public static final int[] OFFSET_Y = {0, -1, 1, 0, 0, -1, 1, 0, 0, -1, 1, 0, 0, -1, 1, 0};
	/**
	 * Rechter Mausknopf.
	 */
	public static final int RIGHT_MOUSE_BUTTON = MouseEvent.BUTTON3;
	/**
	 * Zu verarbeitende KeyEvents.
	 */
	private final Queue<KeyEvent> keyEvents;
	/**
	 * Speichert alle gerade gedrueckten Tasten.
	 */
	private final boolean[] keysPressed;
	/**
	 * Zu verarbeitende MouseEvents.
	 */
	private final Queue<MouseEvent> mouseEvents;
	/**
	 * Zu verarbeitende MouseWheelEvents.
	 */
	private final Queue<MouseWheelEvent> mouseWheelEvents;
	/**
	 * X-Koordinate der Maus.
	 */
	private int mouseX;
	/**
	 * Y-Koordinate der Maus.
	 */
	private int mouseY;

	/**
	 * Zwischenvariable, um die aktuelle Bewegungsrichtung zu speichern.
	 */
	private int moveDir;

	/**
	 * Konstruktor.
	 */
	public InputManager() {
		keysPressed = new boolean[0xFFFF];
		keyEvents = new Queue<KeyEvent>();
		mouseEvents = new Queue<MouseEvent>();
		mouseWheelEvents = new Queue<MouseWheelEvent>();
		moveDir = 0;
	}

	@Override
	public boolean canUpdate() {
		return Main.getTicks() % Integers.MPU == 0;
	}

	/**
	 * X-Koordinate der Maus.
	 *
	 * @return X-Koordinate
	 */
	public int getMouseX() {
		return mouseX;
	}

	/**
	 * Y-Koordinate der Maus.
	 *
	 * @return Y-Koordinate
	 */
	public int getMouseY() {
		return mouseY;
	}

	/**
	 * Die naechste Bewegungsrichtung. ACHTUNG: Zwischenspeichern empfohlen, resettet sich selber!
	 *
	 * @return Direction, die naechste Bewegungsrichtung
	 */
	public Direction getNextDirection() {
		Direction dir = Direction.getDirectionForOffset(OFFSET_X[moveDir], OFFSET_Y[moveDir]);
		moveDir = 0b0000;
		return dir;
	}

	/**
	 * Das naechste zu verarbeitende KeyEvent.
	 *
	 * @return KeyEvent
	 */
	public KeyEvent getNextKeyEvent() {
		return keyEvents.frontDeQueue();
	}

	/**
	 * Das naechste zu verarbeitende MouseEvent.
	 *
	 * @return MouseEvent
	 */
	public MouseEvent getNextMouseEvent() {
		return mouseEvents.frontDeQueue();
	}

	/**
	 * Das naechste zu verarbeitende MouseWheelEvent.
	 *
	 * @return MouseWheelEvent
	 */
	public MouseWheelEvent getNextMouseWheelEvent() {
		return mouseWheelEvents.frontDeQueue();
	}

	/**
	 * Sind KeyEvents zu verarbeiten.
	 *
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean hasKeyEvents() {
		return !keyEvents.empty();
	}

	/**
	 * Sind MouseEvents zu verarbeiten.
	 *
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean hasMouseEvents() {
		return !mouseEvents.empty();
	}

	/**
	 * Sind MouseWheelEvents zu verarbeiten.
	 *
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean hasMouseWheelEvents() {
		return !mouseWheelEvents.empty();
	}

	/**
	 * Ist die mit gegebene Taste im Moment gedrueckt.
	 *
	 * @param keyCode Ein KeyCode aus der KeyEvent-Klasse
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean isKeyPressed(int keyCode) {
		if (!MathUtil.isInArray(keyCode, keysPressed.length)) {
			return false;
		}
		return keysPressed[keyCode];
	}

	public boolean isShiftDown() {
		return isKeyPressed(KeyEvent.VK_SHIFT);
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Taste heruntergedrueckt wird/ist.
	 *
	 * @param e KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keyEvents.enQueue(e);
		if (MathUtil.isInArray(e.getKeyCode(), keysPressed.length)) {
			keysPressed[e.getKeyCode()] = true;
		}
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Taste losgelassen wird. *
	 *
	 * @param e KeyEvent
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (MathUtil.isInArray(e.getKeyCode(), keysPressed.length)) {
			keysPressed[e.getKeyCode()] = false;
		}
	}

	/**
	 * Wird von Java ausgefuehrt, wenn ein Unicode-Buchstabe eingegeben wird.
	 *
	 * @param e KeyEvent
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Maustaste heruntergedrueckt und wieder losgelassen wird.
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		mouseEvents.enQueue(e);

	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus mit gedrueckter Maustaste bewegt wird.
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus in das Fenster eingefuehrt wird. * @param e MouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus aus dem Fenster herausgeschoben wird. * @param e MouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn die Maus bewegt wird. * @param e MouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Maustaste heruntergedrueckt wird/ist.
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgefuehrt, wenn eine Maustaste losgelassen wird.
	 *
	 * @param e MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// NO-OP
	}

	/**
	 * Wird von Java ausgefuehrt, wenn das Mausrad gedreht wird.
	 *
	 * @param e MouseWheelEvent
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		mouseWheelEvents.enQueue(e);

	}

	@Override
	public void update() {

		moveDir = 0b0000;

		if (Main.getGui() instanceof GuiIngame) {
			if (keysPressed[KeyBindings.KEY_UP]) {
				moveDir |= 0b0001;
			}
			if (keysPressed[KeyBindings.KEY_DOWN]) {
				moveDir |= 0b0010;
			}
			if (keysPressed[KeyBindings.KEY_LEFT]) {
				moveDir |= 0b0100;
			}
			if (keysPressed[KeyBindings.KEY_RIGHT]) {
				moveDir |= 0b1000;
			}
		}

	}
}

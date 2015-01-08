package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import projektkurs.Main;
import projektkurs.gui.element.Element;
import projektkurs.lib.KeyBindings;
import projektkurs.util.IUpdatable;

/**
 * Ein Graphical User Interface (GUI).
 */
public abstract class Gui implements IUpdatable {

    /**
     * Alle Elements in diesem Gui.
     */
    private final ArrayList<Element> guiElements;

    /**
     * Konstruktor.
     */
    public Gui() {
        guiElements = new ArrayList<Element>();
    }

    @Override
    public boolean canUpdate() {
        return !getGuiElements().isEmpty();
    }

    /**
     * Alle Elemente in diesem Gui.
     *
     * @return alle Elemente
     */
    public ArrayList<Element> getGuiElements() {
        return guiElements;
    }

    /**
     * Initialísiert das Gui.
     */
    public void initGui() {
        getGuiElements().clear();
    }

    /**
     * Wird aufgerufen, wenn eine Taste gedrückt wird.
     *
     * @param keyChar
     *            gedrückter Buchstabe
     * @param e
     *            KeyEvent
     */
    public void onKeyTyped(char keyChar, KeyEvent e) {
        for (Element el : getGuiElements()) {
            el.onKeyTyped(keyChar, e);
        }
        if (keyChar == KeyBindings.KEY_OPTION) {
            Main.closeGui();
        }
    }

    /**
     * Wird ausgeführt, wenn mit der linken Maustaste auf den Bildschirm geklickt wird.
     *
     * @param screenX
     *            X-Bildschirmkoordinate
     * @param screenY
     *            Y-Bildschirmkoordinate
     * @param e
     *            MouseEvent
     */
    public void onLeftClick(int screenX, int screenY, MouseEvent e) {
        for (Element el : getGuiElements()) {
            el.onLeftClick(screenX, screenY, e);
        }
    }

    /**
     * Wird ausgeführt, wenn das Mausrad bewegt im Bildschirm bewegt wird.
     *
     * @param by
     *            Bewegung des Mausrads
     * @param e
     *            MouseWheelEvent
     */
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        for (Element el : getGuiElements()) {
            el.onMouseWheelMoved(by, e);
        }
    }

    /**
     * Wird ausgeführt, wenn mit der rechten Maustaste auf den Bildschirm geklickt wird.
     *
     * @param screenX
     *            X-Bildschirmkoordinate
     * @param screenY
     *            Y-Bildschirmkoordinate
     * @param e
     *            MouseEvent
     */
    public void onRightClick(int screenX, int screenY, MouseEvent e) {
        for (Element el : getGuiElements()) {
            el.onRightClick(screenX, screenY, e);
        }
    }

    /**
     * Rendert das Gui.
     *
     * @param g
     *            Graphics2D Objekt
     */
    public void render(Graphics2D g) {
        Element hovered = null;
        for (Element el : getGuiElements()) {
            el.render(g);
            if (el.isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
                hovered = el;
            }
        }
        if (hovered != null) {
            hovered.renderTooltip(g);
        }
    }

    @Override
    public void update() {
        for (Element e : getGuiElements()) {
            if (e.canUpdate()) {
                e.update();
            }
        }
    }

    /**
     * Fügt dem Gui ein Element hinzu.
     *
     * @param e
     *            Element
     */
    protected final void addElement(Element e) {
        if (e != null) {
            for (Element element : getGuiElements()) {
                if (element.getID() == e.getID()) {
                    throw new IllegalArgumentException("An Element with the ID '" + e.getID() + "' is already registered");
                }
            }
            getGuiElements().add(e);
        }
    }

    /**
     * Entfernt ein Element aus dem Gui.
     *
     * @param e
     *            Element
     */
    protected final void removeElement(Element e) {
        getGuiElements().remove(e);
    }

}

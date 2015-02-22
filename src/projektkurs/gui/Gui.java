package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projektkurs.Main;
import projektkurs.gui.element.Element;
import projektkurs.lib.KeyBindings;
import projektkurs.render.Screen;
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
     * Eltern-Gui.
     */
    private final Gui parent;

    /**
     * Konstruktor.
     */
    public Gui() {
        this(null);
    }

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     */
    public Gui(Gui parent) {
        guiElements = new ArrayList<Element>();
        this.parent = parent;
    }

    @Override
    public boolean canUpdate() {
        return !guiElements.isEmpty();
    }

    /**
     * Alle Elemente in diesem Gui.
     *
     * @return alle Elemente
     */
    public List<Element> getGuiElements() {
        return Collections.unmodifiableList(guiElements);
    }

    /**
     * Das Eltern-Gui
     *
     * @return Eltern-Gui
     */
    public Gui getParent() {
        return parent;
    }

    /**
     * Initialísiert das Gui.
     */
    public void initGui() {
        guiElements.clear();
    }

    /**
     * Wird aufgerufen, wenn eine Taste gedrueckt wird.
     *
     * @param keyChar
     *            gedrueckter Buchstabe
     * @param e
     *            KeyEvent
     */
    public void onKeyTyped(char keyChar, KeyEvent e) {
        for (Element el : guiElements) {
            el.onKeyTyped(keyChar, e);
        }
        if (keyChar == KeyBindings.KEY_MENU) {
            Main.openGui(parent);
        }
    }

    /**
     * Wird ausgefuehrt, wenn mit der linken Maustaste auf den Bildschirm geklickt wird.
     *
     * @param screenX
     *            X-Bildschirmkoordinate
     * @param screenY
     *            Y-Bildschirmkoordinate
     * @param e
     *            MouseEvent
     */
    public void onLeftClick(int screenX, int screenY, MouseEvent e) {
        for (Element el : guiElements) {
            el.onLeftClick(screenX, screenY, e);
        }
    }

    /**
     * Wird ausgefuehrt, wenn das Mausrad bewegt im Bildschirm bewegt wird.
     *
     * @param by
     *            Bewegung des Mausrads
     * @param e
     *            MouseWheelEvent
     */
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        for (Element el : guiElements) {
            el.onMouseWheelMoved(by, e);
        }
    }

    /**
     * Wird ausgefuehrt, wenn mit der rechten Maustaste auf den Bildschirm geklickt wird.
     *
     * @param screenX
     *            X-Bildschirmkoordinate
     * @param screenY
     *            Y-Bildschirmkoordinate
     * @param e
     *            MouseEvent
     */
    public void onRightClick(int screenX, int screenY, MouseEvent e) {
        for (Element el : guiElements) {
            el.onRightClick(screenX, screenY, e);
        }
    }

    /**
     * Rendert das Gui.
     *
     * @param screen
     *            Screen
     */
    public void render(Screen screen) {
        Element hovered = null;
        for (Element el : guiElements) {
            el.render(screen);
            if (el.isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
                hovered = el;
            }
        }
        if (hovered != null) {
            hovered.renderTooltip(screen);
        }

        // RenderUtil.drawSprite((new Sprite("",Images.ladyvO_EW)).rescale("", factorX, factorY), sprite, x, y);
    }

    @Override
    public void update() {
        for (Element e : guiElements) {
            if (e.canUpdate()) {
                e.update();
            }
        }
    }

    /**
     * Fuegt dem Gui ein Element hinzu.
     *
     * @param e
     *            Element
     */
    protected final void addElement(Element e) {
        if (e != null) {
            for (Element element : guiElements) {
                if (element.getID() == e.getID()) {
                    throw new IllegalArgumentException("An Element with the ID '" + e.getID() + "' is already registered");
                }
            }
            guiElements.add(e);
        }
    }

    /**
     * Entfernt ein Element aus dem Gui.
     *
     * @param e
     *            Element
     */
    protected final void removeElement(Element e) {
        guiElements.remove(e);
    }

}

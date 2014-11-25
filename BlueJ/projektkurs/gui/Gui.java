package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import projektkurs.Main;
import projektkurs.gui.element.Element;
import projektkurs.lib.KeyBindings;

/**
 * Ein Graphical User Interface (GUI).
 */
public abstract class Gui {

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

  /**
   * Initialísiert das Gui.
   */
  public void initGui() {
    guiElements.clear();
  }

  /**
   * Wird aufgerufen, wenn eine Taste gedrückt wird.
   *
   * @param keyChar
   *          gedrückter Buchstabe
   * @param e
   *          KeyEvent
   */
  public void onKeyTyped(char keyChar, KeyEvent e) {
    for (Element el : guiElements) {
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
   *          X-Bildschirmkoordinate
   * @param screenY
   *          Y-Bildschirmkoordinate
   * @param e
   *          MouseEvent
   */
  public void onLeftClick(int screenX, int screenY, MouseEvent e) {
    for (Element el : guiElements) {
      el.onLeftClick(screenX, screenY, e);
    }
  }

  /**
   * Wird ausgeführt, wenn das Mausrad bewegt im Bildschirm bewegt wird.
   *
   * @param by
   *          Bewegung des Mausrads
   * @param e
   *          MouseWheelEvent
   */
  public void onMouseWheelMoved(int by, MouseWheelEvent e) {
    for (Element el : guiElements) {
      el.onMouseWheelMoved(by, e);
    }
  }

  /**
   * Wird ausgeführt, wenn mit der rechten Maustaste auf den Bildschirm geklickt wird.
   *
   * @param screenX
   *          X-Bildschirmkoordinate
   * @param screenY
   *          Y-Bildschirmkoordinate
   * @param e
   *          MouseEvent
   */
  public void onRightClick(int screenX, int screenY, MouseEvent e) {
    for (Element el : guiElements) {
      el.onRightClick(screenX, screenY, e);
    }
  }

  /**
   * Rendert das Gui.
   *
   * @param g
   *          Graphics2D Objekt
   */
  public void render(Graphics2D g) {
    Element hovered = null;
    for (Element el : guiElements) {
      el.render(g);
      if (el.isInside(Main.getInputManager().getMouseX(), Main.getInputManager().getMouseY())) {
        hovered = el;
      }
    }
    if (hovered != null) {
      hovered.renderTooltip(g);
    }
  }

  /**
   * Fügt dem Gui ein Element hinzu.
   *
   * @param e
   *          Element
   */
  protected void addElement(Element e) {
    guiElements.add(e);
  }

  /**
   * Entfernt ein Element aus dem Gui.
   *
   * @param e
   *          Element
   */
  protected void removeElement(Element e) {
    guiElements.remove(e);
  }

}

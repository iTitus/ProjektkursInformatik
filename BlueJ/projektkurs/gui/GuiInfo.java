package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import projektkurs.Main;
import projektkurs.lib.KeyBindings;
import projektkurs.util.RenderUtil;

/**
 * Informations-GUI.
 */
public class GuiInfo extends Gui {

  /**
   * Eltern-Gui.
   */
  private final Gui parent;

  /**
   * Konstruktor.
   *
   * @param parent
   *          Eltern-Gui
   */
  public GuiInfo(Gui parent) {
    this.parent = parent;
  }

  @Override
  public void initGui() {
    super.initGui();
  }

  @Override
  public void onKeyTyped(char keyChar, KeyEvent e) {
    super.onKeyTyped(keyChar, e);
    if (keyChar == KeyBindings.KEY_OPTION) {
      Main.openGui(parent);
    }
  }

  @Override
  public void render(Graphics2D g) {
    RenderUtil.drawDefaultBackground(g);
    super.render(g);
  }

}

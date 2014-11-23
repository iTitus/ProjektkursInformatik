package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import projektkurs.Main;
import projektkurs.gui.element.ITextFieldListener;
import projektkurs.gui.element.TextField;
import projektkurs.item.AbstractItem;
import projektkurs.item.ItemStack;
import projektkurs.level.Level;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.KeyBindings;
import projektkurs.lib.Levels;
import projektkurs.lib.Scripts;
import projektkurs.util.I18n;
import projektkurs.util.I18n.SupportedLocales;
import projektkurs.util.RenderUtil;

/**
 * Konsolen-GUI.
 */
public class GuiConsole extends Gui implements ITextFieldListener {

  /**
   * Konsoleneingabenfeld.
   */
  private TextField field;

  @Override
  public void initGui() {
    super.initGui();
    field = new TextField(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 256, 64, 0, this);
    field.setFocused(true);
    guiElements.add(field);
  }

  @Override
  public void onFocusGained(TextField field) {
    // NO-OP
  }

  @Override
  public void onFocusLost(TextField field) {
    // NO-OP
  }

  @Override
  public void onKeyTyped(char keyChar, KeyEvent e) {
    super.onKeyTyped(keyChar, e);
    if (e.getKeyCode() == KeyBindings.ENTER) {
      onSent(field.getText());
    }
  }

  @Override
  public void onTextChanged(TextField field) {
    // NO-OP
  }

  @Override
  public void render(Graphics2D g) {
    RenderUtil.drawDefaultBackground(g);
    super.render(g);
  }

  /**
   * Wird ausgeführt, wenn die Enter-Taste gedrückt wird.
   *
   * @param text
   *          Text im TextField
   */
  private void onSent(String text) {
    if (text != null) {
      text = text.trim();
      String[] strings = text.split(" ");
      if (strings != null && strings.length > 0) {
        switch (strings[0].toLowerCase(SupportedLocales.DEFAULT.getLocale())) {
          case "item":
            if (strings.length == 2) {
              AbstractItem item = Items.MAPPINGS.get(strings[1]);
              if (item != null) {
                Main.getPlayer().getInventory().addItemStack(new ItemStack(item));
                Main.closeGui();
              }
            } else if (strings.length == 3) {
              AbstractItem item = Items.MAPPINGS.get(strings[1]);
              int size = -1;
              try {
                size = Integer.valueOf(strings[2]);
                if (item != null && size > 0) {
                  Main.getPlayer().getInventory().addItemStack(new ItemStack(item, size));
                  Main.closeGui();
                }
              } catch (NumberFormatException e) {
                // NO-OP
              }

            } else if (strings.length == 4) {

              AbstractItem item = Items.MAPPINGS.get(strings[1]);
              int size = -1;
              int damage = -1;
              try {
                size = Integer.valueOf(strings[2]);
                damage = Integer.valueOf(strings[3]);
                if (item != null && size > 0) {
                  Main.getPlayer().getInventory().addItemStack(new ItemStack(item, size, damage));
                  Main.closeGui();
                }
              } catch (NumberFormatException e) {
                // NO-OP
              }

            }

            break;
          case "switch":
            int i = -1;
            if (strings.length == 2) {
              try {
                i = Integer.valueOf(strings[1]);
                if (i >= 0 && i < Main.getLevel().getMapCount()) {
                  Scripts.switchMap(i);
                  Main.closeGui();
                }
              } catch (NumberFormatException e) {
                // NO-OP
              }

            } else if (strings.length == 3) {
              try {
                i = Integer.valueOf(strings[2]);
                Level l = Levels.MAPPINGS.get(strings[1]);
                if (l != null && i >= 0 && i < l.getMapCount()) {
                  Main.setLevel(l);
                  Scripts.switchMap(i);
                  Main.closeGui();
                }
              } catch (NumberFormatException e) {
                // NO-OP
              }
            }
            break;
          default:
        }
      }
      field.setText(I18n.getString("command.fail"));
    }
  }
}

package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.dialog.DialogPart;
import projektkurs.entity.Entity;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.Element;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.TextView;
import projektkurs.lib.Integers;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;

/**
 * Das Dialogs-GUI.
 */
public class GuiDialog extends Gui implements IButtonListener {

    /**
     * Aktuell angezeigter DialogPart.
     */
    private final DialogPart dialogPart;
    /**
     * Entity No. 1.
     */
    private final Entity entity1;
    /**
     * Entity No. 2.
     */
    private final Entity entity2;
    /**
     * Das Dialog-Anzeigefeld.
     */
    private final TextView text;

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
     * @param dialogPart
     *            anzuzeigender DialogPart
     * @param entity1
     *            ein Entity
     * @param entity2
     *            anderer Entity
     */
    public GuiDialog(Gui parent, DialogPart dialogPart, Entity entity1, Entity entity2) {
        super(parent);
        this.dialogPart = dialogPart;
        this.entity1 = entity1;
        this.entity2 = entity2;
        text = new TextView(34, 34, 256, 64, 0, dialogPart.getNextString());
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(text);
        addElement(new Button(34, 34 + 64, 256, 64, 1, this, "button.dialog.next"));
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        if (dialogPart.hasNextString()) {
            text.setText(dialogPart.getNextString());
            if (!dialogPart.hasNextString()) {
                button.setName("button.dialog.exit");
            }
        } else {
            Main.openGui(getParent());
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        for (Element el : getGuiElements()) {
            el.onKeyTyped(keyChar, e);
        }
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        if (dialogPart.isNPCSpeaking()) {
            if (entity2 != null) {
                RenderUtil.drawSprite(screen, entity2.getSprite(), 34 + 256, 34);
            }
            if (entity1 != null) {
                RenderUtil.drawSprite(screen, entity1.getSprite(), 34, Integers.windowY - 34 - entity1.getSizeY() * Integers.RASTER_SIZE);
            }
        } else {
            if (entity1 != null) {
                RenderUtil.drawSprite(screen, entity1.getSprite(), 34 + 256, 34);
            }
            if (entity2 != null) {
                RenderUtil.drawSprite(screen, entity2.getSprite(), Integers.windowX - 34 - entity2.getSizeX() * Integers.RASTER_SIZE, Integers.windowY - 34 - entity2.getSizeY() * Integers.RASTER_SIZE);
            }
        }
        super.render(screen);
    }
}

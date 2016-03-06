package projektkurs.gui;

import java.awt.event.MouseEvent;
import java.util.List;

import projektkurs.Main;
import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogPart;
import projektkurs.entity.Entity;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.DialogButton;
import projektkurs.gui.element.IButtonListener;

/**
 * Das Dialog GUI.
 */
public class GuiDialogChooser extends Gui implements IButtonListener {

    /**
     * Aktueller Dialog.
     */
    private final Dialog dialog;
    /**
     * Entity No. 1.
     */
    private final Entity entity1;
    /**
     * Entity No. 2.
     */
    private final Entity entity2;
    private List<DialogPart> shownParts;

    /**
     * Konstruktor.
     * @param dialog
     * Dialog
     * @param entity
     * anderer Entity
     */
    public GuiDialogChooser(Dialog dialog, Entity entity) {
        this(dialog, Main.getPlayer(), entity);
    }

    /**
     * Konstruktor.
     * @param dialog
     * Dialog
     * @param entity1
     * ein Entity
     * @param entity2
     * anderer Entity
     */
    public GuiDialogChooser(Dialog dialog, Entity entity1, Entity entity2) {
        this.dialog = dialog;
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    @Override
    public void initGui() {
        super.initGui();
        shownParts = dialog.getShownParts();
        if (shownParts.size() > 1) {
            int id = 0;
            for (DialogPart shown : shownParts) {
                addElement(new DialogButton(34, 34 + id * 64, id++, this, shown));
            }
        } else if (shownParts.size() == 1) {
            Main.openGui(new GuiDialog(getParent(), shownParts.get(0), entity1, entity2));
        } else {
            Main.openGui(getParent());
        }
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        if (button instanceof DialogButton) {
            DialogPart part = ((DialogButton) button).getDialogPart();
            part.setActivated(false);
            if (shownParts.size() == 1) {
                Main.openGui(new GuiDialog(getParent(), part, entity1, entity2));
            } else {
                Main.openGui(new GuiDialog(this, part, entity1, entity2));
            }
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

}

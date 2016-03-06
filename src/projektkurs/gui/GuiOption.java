package projektkurs.gui;

import java.awt.Desktop;
import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.ToggleButton;
import projektkurs.io.Directories;
import projektkurs.lib.Configs;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;

/**
 * Optionen-GUI.
 */
public class GuiOption extends Gui implements IButtonListener {

    /**
     * Konstruktor.
     * @param parent
     * Eltern-Gui
     */
    public GuiOption(Gui parent) {
        super(parent);
    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT, 0, this, "Back"));
        ToggleButton soundButton = new ToggleButton(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 2, 1, this, "button.sound.on", "button.sound.off");
        soundButton.setIndex(Configs.soundsMuted.getValue() ? 1 : 0);
        addElement(soundButton);
        if (Configs.debugMode.getValue()) {
            addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 3, 2, this, "button.info"));
        }
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 4, 3, this, "button.lang"));
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 5, 4, this, "button.reloadConfig"));
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 6, 5, this, "button.openProgramDir"));
    }

    @Override
    public void onButtonLeftClick(Button button, MouseEvent e) {
        switch (button.getID()) {
            case 0:
                Main.openGui(getParent());
                break;
            case 1:
                Configs.soundsMuted.setValue(((ToggleButton) button).getIndex() != 0);
                Configs.generalConfig.writeConfig();
                break;
            case 2:
                Main.openGui(new GuiInfoChooser(this));
                break;
            case 3:
                Main.openGui(new GuiLangChooser(this));
                break;
            case 4:
                Configs.reloadConfigs();
                break;
            case 5:
                try {
                    Desktop.getDesktop().browse(Directories.getProgramDir().toURI());
                } catch (Exception ex) {
                    Logger.logThrowable("Unable to browse to program dir", ex);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

}

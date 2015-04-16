package projektkurs.gui;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.gui.element.Button;
import projektkurs.gui.element.IButtonListener;
import projektkurs.gui.element.ToggleButton;
import projektkurs.lib.Configs;
import projektkurs.lib.Integers;
import projektkurs.render.Screen;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Optionen-GUI.
 */
public class GuiOption extends Gui implements IButtonListener {

    /**
     * Konstruktor.
     *
     * @param parent
     *            Eltern-Gui
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
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 3, 2, this, "button.info"));
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 4, 3, this, "button.lang"));
        addElement(new Button(MathUtil.ceilDiv(Integers.windowX, 2) - MathUtil.ceilDiv(Integers.DEFAULT_BUTTON_WIDTH, 2), Integers.windowY - Integers.WINDOW_HUD_Y - Integers.DEFAULT_BUTTON_HEIGHT * 5, 4, this, "button.reloadConfig"));
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
                Main.openGui(new GuiInfo(this));
                break;
            case 3:
                Main.openGui(new GuiLangChooser(this));
                break;
            case 4:
                Configs.reloadConfigs();
                break;
            default:
                break;
        }
    }

    @Override
    public void onButtonRightClick(Button button, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}

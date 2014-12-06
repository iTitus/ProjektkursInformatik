package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import projektkurs.Main;
import projektkurs.command.EnumCommandResult;
import projektkurs.command.ICommand;
import projektkurs.gui.element.ITextFieldListener;
import projektkurs.gui.element.TextField;
import projektkurs.lib.Commands;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.util.I18n;
import projektkurs.util.I18n.SupportedLocales;
import projektkurs.util.Logger;
import projektkurs.util.RenderUtil;

/**
 * Konsolen-GUI.
 */
public class GuiConsole extends Gui implements ITextFieldListener {

    /**
     * Konsoleneingabenfeld.
     */
    private TextField field;

    /**
     * Das TextField in diesem Gui.
     *
     * @return TextField
     */
    public TextField getTextField() {
        return field;
    }

    @Override
    public void initGui() {
        super.initGui();
        field = new TextField(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, 256, 64, 0, this);
        field.setFocused(true);
        addElement(field);
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
     *            Text im TextField
     */
    private void onSent(String text) {

        if (text != null && text.length() > 0) {
            text = text.trim();

            ArrayList<String> partList = new ArrayList<String>(Arrays.asList(text.split(" ")));
            ArrayList<String> toRemove = new ArrayList<String>();
            for (String part : partList) {
                if (part == null || part.trim().length() < 1) {
                    toRemove.add(part);
                }
            }
            partList.removeAll(toRemove);
            String[] parts = partList.toArray(new String[partList.size()]);
            String command = parts[0].toLowerCase(SupportedLocales.DEFAULT.getLocale());
            ICommand c = Commands.MAPPINGS.get(command);
            if (c != null) {
                String[] args = new String[parts.length - 1];
                for (int i = 0; i < args.length; i++) {
                    args[i] = parts[i + 1];
                }
                EnumCommandResult result;
                try {
                    result = c.execute(args);
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to execute command '" + command + "' with the aruments " + partList, t);
                    result = EnumCommandResult.GENERAL_FAILURE;
                }
                if (result == null) {
                    result = EnumCommandResult.GENERAL_FAILURE;
                }
                switch (result) {
                    case GENERAL_FAILURE:
                        field.setText(I18n.getStringFormatted("command.failure.general", command));
                        break;
                    case NO_SUCCESS:
                    case SUCCESS:
                        if (Main.getGui() == this) {
                            Main.closeGui();
                        }
                        break;
                    case NUMBER_PARSING:
                        field.setText(I18n.getString("command.failure.number"));
                        break;
                    case OBJECT_NOT_FOUND:
                        field.setText(I18n.getString("command.failure.object"));
                        break;
                    case OUT_OF_BOUNDS:
                        field.setText(I18n.getString("command.failure.bounds"));
                        break;
                    case WRONG_USAGE:
                        field.setText(I18n.getStringFormatted("command.usage", I18n.getString("command." + command + ".usage")));
                        break;
                    default:
                        break;
                }
            } else {
                field.setText(I18n.getString("command.notFound"));
            }
        }

    }
}

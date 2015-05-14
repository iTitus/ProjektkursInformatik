package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import projektkurs.Main;
import projektkurs.command.Command;
import projektkurs.command.EnumCommandResult;
import projektkurs.gui.element.ITextFieldListener;
import projektkurs.gui.element.TextField;
import projektkurs.lib.Commands;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.util.I18n;
import projektkurs.util.Logger;
import projektkurs.util.StringUtil;

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
		field = new TextField(Integers.WINDOW_HUD_X, Integers.WINDOW_HUD_Y, Integers.windowX - 2 * Integers.WINDOW_HUD_X, 64, 0, this);
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

	private Command getCommand(String command) {
		if (StringUtil.isNullOrEmpty(command)) {
			return null;
		}
		for (Command c : Commands.MAPPINGS.values()) {
			if (c != null) {
				if (command.equalsIgnoreCase(c.getCommand())) {
					return c;
				}
				String[] aliases = c.getAliases();
				if (aliases != null && aliases.length > 0) {
					for (String alias : aliases) {
						if (command.equalsIgnoreCase(alias)) {
							return c;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Wird ausgefuehrt, wenn die Enter-Taste gedrueckt wird.
	 *
	 * @param text Text im TextField
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
			String commandString = parts[0].toLowerCase(Locale.ENGLISH);
			Command c = getCommand(commandString);
			if (c != null) {
				String[] args = new String[parts.length - 1];
				for (int i = 0; i < args.length; i++) {
					args[i] = parts[i + 1];
				}
				EnumCommandResult result;
				try {
					result = c.execute(args);
				} catch (Throwable t) {
					Logger.logThrowable("Unable to execute command '" + commandString + "' with the arguments " + partList, t);
					result = EnumCommandResult.GENERAL_FAILURE;
				}
				if (result == null) {
					result = EnumCommandResult.GENERAL_FAILURE;
				}
				switch (result) {
					case GENERAL_FAILURE:
						field.setText(I18n.getStringFormatted("command.failure.general", commandString));
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
						field.setText(I18n.getStringFormatted("command.usage", I18n.getString("command." + c.getCommand() + ".usage")));
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

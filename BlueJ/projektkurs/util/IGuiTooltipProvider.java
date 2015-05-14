package projektkurs.util;

import java.util.List;

import projektkurs.gui.Gui;

public interface IGuiTooltipProvider {

	void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip);

}

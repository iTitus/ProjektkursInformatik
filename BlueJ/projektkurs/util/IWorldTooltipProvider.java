package projektkurs.util;

import java.util.List;

import projektkurs.world.Spielfeld;

public interface IWorldTooltipProvider {

	void addTooltip(Spielfeld map, int x, int y, List<String> tooltip);

}

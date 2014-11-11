package projektkurs.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;
import projektkurs.world.raster.extra.ExtraInformationKiste;

public class GuiKiste extends Gui {

	private ExtraInformationKiste kiste;

	public GuiKiste(ExtraInformationKiste kiste) {
		this.kiste = kiste;
	}

	@Override
	public void render(Graphics2D g) {
		drawDefaultBackground(g);
		g.setColor(Color.BLUE);
		g.drawString("[Kiste @{x=" + kiste.getX() + ", y=" + kiste.getY()
				+ "}] " + kiste.getInventar().toString(),
				MathUtil.ceilDiv(Integers.WINDOW_X, 2),
				MathUtil.ceilDiv(Integers.WINDOW_Y, 2));
	}

}

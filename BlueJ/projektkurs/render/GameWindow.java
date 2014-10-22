package projektkurs.render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JLabel;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.render.entity.RenderEntity;

public class GameWindow extends JLabel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		for (int x = 0; x < Main.getRenderHelper().getSight().length; x++) {
			for (int y = 0; y < Main.getRenderHelper().getSight()[x].length; y++) {
				g2d.drawImage(Main.getRenderHelper().getSight()[x][y], x
						* Integers.RASTER_SIZE + Integers.WINDOW_HUD_X, y
						* Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y, this);
			}
		}
		synchronized (Main.getSpielfeld().getEntityList()) {
			Iterator<RenderEntity> i = Main.getRenderHelper()
					.getEntitiesInSight().iterator();
			RenderEntity e;
			while (i.hasNext()) {
				e = i.next();
				g2d.drawImage(e.getE().getImage(), e.getRelX()
						+ Integers.WINDOW_HUD_X, e.getRelY()
						+ Integers.WINDOW_HUD_Y, this);
			}
		}

		g2d.drawImage(Images.charakter,
				(int) ((Integers.WINDOW_X + Integers.RASTER_SIZE) / 2D),
				(int) ((Integers.WINDOW_Y + Integers.RASTER_SIZE) / 2D), this);
	}

}

package projektkurs.world.raster;

import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.lib.Direction;
import projektkurs.lib.Images;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationKiste;

/**
 * Ein Raster mit einer Kiste
 *
 */
public class KistenRaster extends Raster {

	private static final KistenRaster INSTANCE = new KistenRaster();

	/**
	 * Die Instanz
	 * 
	 * @return
	 */
	public static KistenRaster getInstance() {
		return INSTANCE;
	}

	private KistenRaster() {
	}

	@Override
	public boolean canWalkOnFromDirection(int x, int y, Direction dir) {

		ExtraInformationKiste kiste = (ExtraInformationKiste) Main
				.getSpielfeld().getExtraInformationAt(x, y);

		// if (!kiste.isUsed()) {
		// kiste.setUsed(true);

		System.out.println("[Kiste @{x=" + x + ", y=" + y
				+ "}] Aua, ich habe dies für dich: "
				+ kiste.getInventar().toString());

		// Inventar öffnen/auslesen
		// if (!kiste.getInventar().isInventoryEmpty()
		// && !Main.getFigur().getInventory().isInventoryFull()) {
		//
		// ArrayList<String> names = new ArrayList<String>(kiste
		// .getInventar().getNumberOfItemsInInventory());
		//
		// for (int i = 0; i < kiste.getInventar().getSize(); i++) {
		// if (kiste.getInventar().getItemAt(i) != null) {
		// names.add(kiste.getInventar().getItemAt(i).getName());
		// }
		// }
		//
		// int indexToTake = OutputManager.showInputButtonWindow(
		// I18n.getString("raster.chest"),
		// I18n.getString("raster.chest.chooseToPick"),
		// names.toArray());
		//
		// Main.getFigur().getInventory()
		// .addItem(kiste.getInventar().getItemAt(indexToTake));
		// kiste.getInventar().removeItem(indexToTake);
		// System.out.println(indexToTake);
		//
		// }
		// kiste.setUsed(false);
		// }
		return false;
	}

	@Override
	public ExtraInformation getExtraInformation() {
		return new ExtraInformationKiste();
	}

	@Override
	public BufferedImage getImage(int x, int y) {
		return Images.kiste;
	}

	@Override
	public boolean isInteractive() {
		return true;
	}

}

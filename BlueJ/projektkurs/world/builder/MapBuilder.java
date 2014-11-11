package projektkurs.world.builder;

import java.util.Random;

import projektkurs.Main;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityRedNPC;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Images;
import projektkurs.story.scripts.Scripts;
import projektkurs.story.trigger.InventoryTrigger;
import projektkurs.util.Direction;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;
import projektkurs.world.raster.Raster;
import projektkurs.world.raster.extra.ExtraInformationDoor;
import projektkurs.world.raster.extra.ExtraInformationKiste;

public class MapBuilder {

	public static void Level1generateAndPopulateMap0(Spielfeld map) {
		Random rand = new Random();

		// RASEN!
		for (int x = 0; x < map.getMapSizeX(); x++) {
			for (int y = 0; y < map.getMapSizeY(); y++)
				map.setRasterAt(x, y, Raster.rasen);

		}

		// BÄUME!
		for (int i = 0; i < rand.nextInt(51) + 25; i++) {
			map.setRasterAt(rand.nextInt(map.getMapSizeX()),
					rand.nextInt(map.getMapSizeY()), Raster.baum);
		}

		// KISTEN!
		for (int i = 0; i < rand.nextInt(7) + 3; i++) {
			map.setRasterAt(rand.nextInt(map.getMapSizeX()),
					rand.nextInt(map.getMapSizeY()), Raster.kiste);

		}

		// WÄNDE!
		for (int x = 0; x < map.getMapSizeX(); x++) {
			map.setRasterAt(x, 0, Raster.wand);
			map.setRasterAt(x, map.getMapSizeY() - 1, Raster.wand);
		}
		for (int y = 0; y < map.getMapSizeY(); y++) {
			map.setRasterAt(0, y, Raster.wand);
			map.setRasterAt(map.getMapSizeX() - 1, y, Raster.wand);
		}

		// Animation Test
		map.setRasterAt(1, 1, Raster.testAnimation);

		// TÜREN!
		map.setRasterAt(20, 18, Raster.baum);
		map.setRasterAt(21, 18, Raster.baum);
		map.setRasterAt(22, 18, Raster.baum);
		map.setRasterAt(23, 18, Raster.baum);
		map.setRasterAt(24, 18, Raster.baum);
		map.setRasterAt(24, 19, Raster.baum);
		map.setRasterAt(24, 20, Raster.baum);
		map.setRasterAt(24, 21, Raster.baum);
		map.setRasterAt(24, 22, Raster.baum);
		map.setRasterAt(20, 19, Raster.baum);
		map.setRasterAt(20, 20, Raster.door);
		map.setRasterAt(20, 21, Raster.baum);
		map.setRasterAt(20, 22, Raster.baum);
		map.setRasterAt(21, 22, Raster.baum);
		map.setRasterAt(22, 22, Raster.baum);
		map.setRasterAt(23, 22, Raster.baum);
		map.setRasterAt(22, 20, Raster.finish);
		((ExtraInformationDoor) map.getExtraInformationAt(20, 20))
				.setDirection(Direction.LEFT);
		((ExtraInformationDoor) map.getExtraInformationAt(20, 20))
				.setOpeningKey(1000);

		// KISTENINHALTE!
		for (int x = 0; x < map.getMapSizeX(); x++) {
			for (int y = 0; y < map.getMapSizeY(); y++) {
				if (map.getExtraInformationAt(x, y) instanceof ExtraInformationKiste) {
					((ExtraInformationKiste) map.getExtraInformationAt(x, y))
							.getInventar().addItemStack(
									new ItemStack(Items.item_42, 42));
					((ExtraInformationKiste) map.getExtraInformationAt(x, y))
							.getInventar().addItemStack(
									new ItemStack(Items.nuke));
					((ExtraInformationKiste) map.getExtraInformationAt(x, y))
							.getInventar().addItemStack(
									new ItemStack(Items.key));
				}
			}
		}

		// ENTITIES!
		map.spawn(Main.getPlayer());
		map.spawn(new EntityRedNPC(1, 1, Images.redNPC));
		map.spawn(new EntityItem(5, 5, new ItemStack(Items.key, 1, 1000)));
		map.spawn(new EntityItem(5, 6, new ItemStack(Items.item_42, 42)));
		map.spawn(new EntityItem(5, 7, new ItemStack(Items.nuke)));

		// STORYMANAGER!
		Main.getLevel()
				.getCurrMap()
				.getStorymanager()
				.addTrigger(
						new InventoryTrigger(ReflectionUtil.getMethod(
								Scripts.class, "loose"), new ItemStack(
								Items.nuke)));

	}

}

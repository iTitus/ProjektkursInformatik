package projektkurs.world.builder;

import java.util.Random;

import projektkurs.Main;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityRedNPC;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.Raster;
import projektkurs.story.script.Scripts;
import projektkurs.story.trigger.AreaTrigger;
import projektkurs.story.trigger.CombinedAndTrigger;
import projektkurs.story.trigger.InventoryTrigger;
import projektkurs.story.trigger.PosTrigger;
import projektkurs.story.trigger.Trigger;
import projektkurs.util.Direction;
import projektkurs.util.MathUtil;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationDoor;
import projektkurs.world.raster.extra.ExtraInformationKiste;

public class MapBuilder {

    public static void Level1generateAndPopulateMap0(Spielfeld map) {
        Random rand = new Random();

        // RASEN!
        for (int x = 0; x < map.getMapSizeX(); x++) {
            for (int y = 0; y < map.getMapSizeY(); y++) {
                map.setRasterAt(x, y, Raster.rasen);
            }

        }

        // BÄUME!
        for (int i = 0; i < rand.nextInt(51) + 25; i++) {
            map.setRasterAt(rand.nextInt(map.getMapSizeX()), rand.nextInt(map.getMapSizeY()), Raster.baum);
        }

        // KISTEN!
        for (int i = 0; i < rand.nextInt(7) + 3; i++) {
            map.setRasterAt(rand.nextInt(map.getMapSizeX()), rand.nextInt(map.getMapSizeY()), Raster.kiste);

        }

        map.setRasterAt(MathUtil.roundDiv(Integers.SIGHT_X, 2), MathUtil.roundDiv(Integers.SIGHT_Y, 2), Raster.kiste);

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
        map.setRasterAt(1, 1, Raster.fire);

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
        map.setRasterAt(20, 21, Raster.baum);
        map.setRasterAt(20, 22, Raster.baum);
        map.setRasterAt(21, 22, Raster.baum);
        map.setRasterAt(22, 22, Raster.baum);
        map.setRasterAt(23, 22, Raster.baum);
        map.setRasterAt(22, 20, Raster.finish);
        map.setRasterAt(20, 20, Raster.door);
        ExtraInformationDoor door = (ExtraInformationDoor) map.getExtraInformationAt(20, 20);
        door.setDirection(Direction.LEFT);
        door.setOpeningKey(1000);

        // KISTENINHALTE!
        Inventory inv;
        ExtraInformation extra;
        for (int x = 0; x < map.getMapSizeX(); x++) {
            for (int y = 0; y < map.getMapSizeY(); y++) {
                inv = new Inventory(Integers.KISTENGROESSE);
                inv.addItemStack(new ItemStack(Items.item_42, 42));
                inv.addItemStack(new ItemStack(Items.nuke));
                inv.addItemStack(new ItemStack(Items.key));
                extra = map.getExtraInformationAt(x, y);
                if (extra instanceof ExtraInformationKiste) {
                    ((ExtraInformationKiste) extra).setInventar(inv);
                }
            }
        }

        // ENTITIES!
        map.spawn(Main.getPlayer());
        for (int x = 0; x < 3; x++) {
            map.spawn(new EntityRedNPC(MathUtil.roundMul(Math.random(), 20) + 10, MathUtil.roundMul(Math.random(), 20) + 10, Images.redNPC));
        }

        // ITEMS
        map.spawn(new EntityItem(5, 5, new ItemStack(Items.key, 1, 1000)));
        map.spawn(new EntityItem(5, 6, new ItemStack(Items.item_42, 0)));
        map.spawn(new EntityItem(5, 7, new ItemStack(Items.nuke, 1234)));
        map.spawn(new EntityItem(5, 8, new ItemStack(Items.healthPotion, 42)));

        // STORYMAGER!
        map.getStorymanager().addTrigger(
                new CombinedAndTrigger(ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.class), new Trigger[] {
                        new AreaTrigger(null, 50, 50, 10, 10), new InventoryTrigger(null, new ItemStack(Items.nuke)) }, 1));

    }

    public static void Level1generateAndPopulateMap1(Spielfeld map) {

        // ANIMATIONS!
        for (int x = 0; x < map.getMapSizeX(); x++) {
            for (int y = 0; y < map.getMapSizeY(); y++) {
                map.setRasterAt(x, y, Raster.destroyedRaster);
            }
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

        // ENTITIES!
        map.spawn(Main.getPlayer());

        // STORYMANAGER!
        map.getStorymanager().addTrigger(new PosTrigger(ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.class), 8, 8, 0));
    }

    public static void Level2generateAndPopulateMap0(Spielfeld map) {

        for (int x = 0; x < map.getMapSizeX(); x++) {
            for (int y = 0; y < map.getMapSizeY(); y++) {
                map.setRasterAt(x, y, Raster.rasen);
            }
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

        // ENTITIES!
        map.spawn(Main.getPlayer());
    }
}

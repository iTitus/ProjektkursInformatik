package projektkurs.world.builder;

import projektkurs.Main;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityRedNPC;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.Raster;
import projektkurs.lib.Scripts;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.raster.extra.ExtraInformationKiste;
import projektkurs.story.trigger.AreaTrigger;
import projektkurs.story.trigger.CombinedAndTrigger;
import projektkurs.story.trigger.InventoryTrigger;
import projektkurs.story.trigger.PosTrigger;
import projektkurs.util.Direction;
import projektkurs.util.MathUtil;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;

/**
 * Spielfeld-Generierung.
 */
public final class MapBuilder {

    /**
     * Level 0 - Spielfeld 0.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel0Map0(Spielfeld map) {

        // RASEN!
        for (int x = 0; x < map.getMapSizeX(); x++) {
            for (int y = 0; y < map.getMapSizeY(); y++) {
                map.setRasterAt(x, y, Raster.rasen);
            }
        }

        // BÄUME!
        for (int i = 0; i < MathUtil.randomInt(25, 75); i++) {
            map.setRasterAt(MathUtil.nextInt(map.getMapSizeX()), MathUtil.nextInt(map.getMapSizeY()), Raster.baum);
        }

        // KISTEN!
        for (int i = 0; i < MathUtil.randomInt(10, 15); i++) {
            map.setRasterAt(MathUtil.nextInt(map.getMapSizeX()), MathUtil.nextInt(map.getMapSizeY()), Raster.kiste);
        }
        map.setRasterAt(MathUtil.floorDiv(Integers.sightX, 2) - 1, MathUtil.floorDiv(Integers.sightY, 2) - 1, Raster.kiste);

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
        map.setRasterAt(3, 1, Raster.fire);

        // TÜREN!
        for (int x = 20; x < 25; x++) {
            for (int y = 18; y < 23; y++) {
                map.setRasterAt(x, y, Raster.rasen);
            }
        }
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
                inv.addItemStack(new ItemStack(Items.item42, 42));
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
        map.spawn(new EntityItem(5, 6, new ItemStack(Items.item42, 42, 42)));
        map.spawn(new EntityItem(5, 7, new ItemStack(Items.nuke, 1234)));
        map.spawn(new EntityItem(5, 8, new ItemStack(Items.healthPotion, 1234, 100)));

        // STORYMAGER!
        map.getStoryManager().registerTrigger(new CombinedAndTrigger(new AreaTrigger(50, 50, 10, 10), new InventoryTrigger(new ItemStack(Items.nuke))), ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.class), 1);

    }

    /**
     * Level 0 - Spielfeld 1.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel0Map1(Spielfeld map) {

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
        map.getStoryManager().registerTrigger(new PosTrigger(8, 8), ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.class), 0);
    }

    /**
     * Level 1 - Spielfeld 0.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel1Map0(Spielfeld map) {

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

    /**
     * Level 1 - Spielfeld 1.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel1Map1(Spielfeld map) {

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

    /**
     * Nicht instanziierbar.
     */
    private MapBuilder() {
    }
}

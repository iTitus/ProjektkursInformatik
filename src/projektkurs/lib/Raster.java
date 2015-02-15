package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.raster.AbstractRaster;
import projektkurs.raster.ChestRaster;
import projektkurs.raster.DoorRaster;
import projektkurs.raster.FinishRaster;
import projektkurs.raster.FireRaster;
import projektkurs.raster.SimpleRaster;
import projektkurs.raster.SolidRaster;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Raster.
 */
public final class Raster {

    /**
     * Baum.
     */
    public static AbstractRaster baum;
    public static AbstractRaster chair_1;
    public static AbstractRaster chair_2;
    public static AbstractRaster chair_3;
    public static AbstractRaster chair_4;
    public static AbstractRaster chair_5;
    public static AbstractRaster chair_6;
    public static AbstractRaster chair_7;
    /**
     * Kiste.
     */
    public static AbstractRaster chest;
    public static AbstractRaster cobbles_1;
    public static AbstractRaster cobbles_2;

    /**
     * Zerstoerter Boden.
     */
    public static AbstractRaster destroyedRaster;

    /**
     * Tuer.
     */
    public static AbstractRaster door;
    /**
     * Ziel.
     */
    public static AbstractRaster finish;
    /**
     * Feuer.
     */
    public static AbstractRaster fire;
    public static AbstractRaster floor_1;
    public static AbstractRaster floor_10;
    public static AbstractRaster floor_11;
    public static AbstractRaster floor_12;
    public static AbstractRaster floor_2;
    public static AbstractRaster floor_3;
    public static AbstractRaster floor_4;
    public static AbstractRaster floor_5;
    public static AbstractRaster floor_6;
    public static AbstractRaster floor_7;
    public static AbstractRaster floor_8;
    public static AbstractRaster floor_9;
    /**
     * Mappings.
     */
    public static final HashMap<String, AbstractRaster> MAPPINGS = new HashMap<String, AbstractRaster>();
    /**
     * Rasen.
     */
    public static AbstractRaster rasen;
    public static AbstractRaster rasen_2;
    /**
     * Array aller Raster nach ihrer ID.
     */
    public static final AbstractRaster[] RASTER = new AbstractRaster[Integers.MAX_RASTER_NUMBER];
    public static AbstractRaster street_asphalt;
    public static AbstractRaster street_l_b_1;
    public static AbstractRaster street_l_b_2;
    public static AbstractRaster street_l_b_3;
    public static AbstractRaster street_l_b_4;
    public static AbstractRaster street_l_b_5;
    public static AbstractRaster street_l_b_6;
    public static AbstractRaster street_l_b_7;
    public static AbstractRaster street_l_b_8;
    public static AbstractRaster street_l_t_1;
    public static AbstractRaster street_l_t_2;
    public static AbstractRaster street_l_t_3;
    public static AbstractRaster street_l_t_4;
    public static AbstractRaster street_l_t_5;
    public static AbstractRaster street_l_t_6;
    public static AbstractRaster street_l_t_7;
    public static AbstractRaster street_l_t_8;
    public static AbstractRaster tree_10ne;
    public static AbstractRaster tree_10nw;
    public static AbstractRaster tree_10se;
    public static AbstractRaster tree_10sw;
    public static AbstractRaster tree_11ne;
    public static AbstractRaster tree_11nw;
    public static AbstractRaster tree_11se;
    public static AbstractRaster tree_11sw;
    public static AbstractRaster tree_12ne;
    public static AbstractRaster tree_12nw;
    public static AbstractRaster tree_12se;
    public static AbstractRaster tree_12sw;
    public static AbstractRaster tree_13ne;
    public static AbstractRaster tree_13nw;
    public static AbstractRaster tree_13se;
    public static AbstractRaster tree_13sw;

    public static AbstractRaster tree_14ne;
    public static AbstractRaster tree_14nw;

    public static AbstractRaster tree_14se;
    public static AbstractRaster tree_14sw;
    public static AbstractRaster tree_2ne;
    public static AbstractRaster tree_2nw;
    public static AbstractRaster tree_2se;
    public static AbstractRaster tree_2sw;
    public static AbstractRaster tree_3ne;
    public static AbstractRaster tree_3nw;
    public static AbstractRaster tree_3se;
    public static AbstractRaster tree_3sw;
    public static AbstractRaster tree_4ne;
    public static AbstractRaster tree_4nw;
    public static AbstractRaster tree_4se;
    public static AbstractRaster tree_4sw;
    public static AbstractRaster tree_5ne;
    public static AbstractRaster tree_5nw;
    public static AbstractRaster tree_5se;

    public static AbstractRaster tree_5sw;
    public static AbstractRaster tree_6ne;
    public static AbstractRaster tree_6nw;
    public static AbstractRaster tree_6se;
    public static AbstractRaster tree_6sw;
    public static AbstractRaster tree_7ne;
    public static AbstractRaster tree_7nw;
    public static AbstractRaster tree_7se;
    public static AbstractRaster tree_7sw;
    public static AbstractRaster tree_8ne;
    public static AbstractRaster tree_8nw;
    public static AbstractRaster tree_8se;

    public static AbstractRaster tree_8sw;
    public static AbstractRaster tree_9ne;
    public static AbstractRaster tree_9nw;
    public static AbstractRaster tree_9se;
    public static AbstractRaster tree_9sw;
    /**
     * Wand.
     */
    public static AbstractRaster wall;
    /**
     * Wasser
     */
    public static AbstractRaster water;

    /**
     * Das Pair, das alle Raster enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.raster", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Items.
     */
    @Init(State.PRE)
    public static void init() {
        wall = new SolidRaster(1, "wall", Sprites.wall);
        registerRaster(wall);

        rasen = new SimpleRaster(2, "grass", Sprites.grass);
        registerRaster(rasen);

        baum = new SolidRaster(3, "tree", Sprites.tree);
        registerRaster(baum);

        chest = new ChestRaster(4);
        registerRaster(chest);

        door = new DoorRaster(5);
        registerRaster(door);

        finish = new FinishRaster(6);
        registerRaster(finish);

        destroyedRaster = new SimpleRaster(7, "destroyedRaster", Sprites.destroyed);
        registerRaster(destroyedRaster);

        fire = new FireRaster(8);
        registerRaster(fire);

        water = new SolidRaster(9, "water", Sprites.water);
        registerRaster(water);

        rasen_2 = new SimpleRaster(10, "rasen_2", Sprites.rasen_2);
        registerRaster(rasen_2);

        tree_2nw = new SolidRaster(11, "tree_2nw", Sprites.tree_2nw);
        registerRaster(tree_2nw);

        tree_2ne = new SolidRaster(12, "tree_2ne", Sprites.tree_2ne);
        registerRaster(tree_2ne);

        tree_2se = new SolidRaster(13, "tree_2se", Sprites.tree_2se);
        registerRaster(tree_2se);

        tree_2sw = new SolidRaster(14, "tree_2sw", Sprites.tree_2sw);
        registerRaster(tree_2sw);

        tree_3nw = new SolidRaster(15, "tree_3nw", Sprites.tree_3nw);
        registerRaster(tree_3nw);

        tree_3ne = new SolidRaster(16, "tree_3ne", Sprites.tree_3ne);
        registerRaster(tree_3ne);

        tree_3se = new SolidRaster(17, "tree_3se", Sprites.tree_3se);
        registerRaster(tree_3se);

        tree_3sw = new SolidRaster(18, "tree_3sw", Sprites.tree_3sw);
        registerRaster(tree_3sw);

        tree_4nw = new SolidRaster(19, "tree_4nw", Sprites.tree_4nw);
        registerRaster(tree_4nw);

        tree_4ne = new SolidRaster(20, "tree_4ne", Sprites.tree_4ne);
        registerRaster(tree_4ne);

        tree_4se = new SolidRaster(21, "tree_4se", Sprites.tree_4se);
        registerRaster(tree_4se);

        tree_4sw = new SolidRaster(22, "tree_4sw", Sprites.tree_4sw);
        registerRaster(tree_4sw);

        tree_5nw = new SolidRaster(23, "tree_5nw", Sprites.tree_5nw);
        registerRaster(tree_5nw);

        tree_5ne = new SolidRaster(24, "tree_5ne", Sprites.tree_5ne);
        registerRaster(tree_5ne);

        tree_5se = new SolidRaster(25, "tree_5se", Sprites.tree_5se);
        registerRaster(tree_5se);

        tree_5sw = new SolidRaster(26, "tree_5sw", Sprites.tree_5sw);
        registerRaster(tree_5sw);

        tree_6nw = new SolidRaster(27, "tree_6nw", Sprites.tree_6nw);
        registerRaster(tree_6nw);

        tree_6ne = new SolidRaster(28, "tree_6ne", Sprites.tree_6ne);
        registerRaster(tree_6ne);

        tree_6se = new SolidRaster(29, "tree_6se", Sprites.tree_6se);
        registerRaster(tree_6se);

        tree_6sw = new SolidRaster(30, "tree_6sw", Sprites.tree_6sw);
        registerRaster(tree_6sw);

        tree_7nw = new SolidRaster(31, "tree_7nw", Sprites.tree_7nw);
        registerRaster(tree_7nw);

        tree_7ne = new SolidRaster(32, "tree_7ne", Sprites.tree_7ne);
        registerRaster(tree_7ne);

        tree_7se = new SolidRaster(33, "tree_7se", Sprites.tree_7se);
        registerRaster(tree_7se);

        tree_7sw = new SolidRaster(34, "tree_7sw", Sprites.tree_7sw);
        registerRaster(tree_7sw);

        tree_8nw = new SolidRaster(35, "tree_8nw", Sprites.tree_8nw);
        registerRaster(tree_8nw);

        tree_8ne = new SolidRaster(36, "tree_8ne", Sprites.tree_8ne);
        registerRaster(tree_8ne);

        tree_8se = new SolidRaster(37, "tree_8se", Sprites.tree_8se);
        registerRaster(tree_8se);

        tree_8sw = new SolidRaster(38, "tree_8sw", Sprites.tree_8sw);
        registerRaster(tree_8sw);

        tree_9nw = new SolidRaster(39, "tree_9nw", Sprites.tree_9nw);
        registerRaster(tree_9nw);

        tree_9ne = new SolidRaster(40, "tree_9ne", Sprites.tree_9ne);
        registerRaster(tree_9ne);

        tree_9se = new SolidRaster(41, "tree_9se", Sprites.tree_9se);
        registerRaster(tree_9se);

        tree_9sw = new SolidRaster(42, "tree_9sw", Sprites.tree_9sw);
        registerRaster(tree_9sw);

        tree_10nw = new SolidRaster(43, "tree_10nw", Sprites.tree_10nw);
        registerRaster(tree_10nw);

        tree_10ne = new SolidRaster(44, "tree_10ne", Sprites.tree_10ne);
        registerRaster(tree_10ne);

        tree_10se = new SolidRaster(45, "tree_10se", Sprites.tree_10se);
        registerRaster(tree_10se);

        tree_10sw = new SolidRaster(46, "tree_10sw", Sprites.tree_10sw);
        registerRaster(tree_10sw);

        tree_11nw = new SolidRaster(47, "tree_11nw", Sprites.tree_11nw);
        registerRaster(tree_11nw);

        tree_11ne = new SolidRaster(48, "tree_11ne", Sprites.tree_11ne);
        registerRaster(tree_11ne);

        tree_11se = new SolidRaster(49, "tree_11se", Sprites.tree_11se);
        registerRaster(tree_11se);

        tree_11sw = new SolidRaster(50, "tree_11sw", Sprites.tree_11sw);
        registerRaster(tree_11sw);

        tree_12nw = new SolidRaster(51, "tree_12nw", Sprites.tree_12nw);
        registerRaster(tree_12nw);

        tree_12ne = new SolidRaster(52, "tree_12ne", Sprites.tree_12ne);
        registerRaster(tree_12ne);

        tree_12se = new SolidRaster(53, "tree_12se", Sprites.tree_12se);
        registerRaster(tree_12se);

        tree_12sw = new SolidRaster(54, "tree_12sw", Sprites.tree_12sw);
        registerRaster(tree_12sw);

        tree_13nw = new SolidRaster(55, "tree_13nw", Sprites.tree_13nw);
        registerRaster(tree_13nw);

        tree_13ne = new SolidRaster(56, "tree_13ne", Sprites.tree_13ne);
        registerRaster(tree_13ne);

        tree_13se = new SolidRaster(57, "tree_13se", Sprites.tree_13se);
        registerRaster(tree_13se);

        tree_13sw = new SolidRaster(58, "tree_13sw", Sprites.tree_13sw);
        registerRaster(tree_13sw);

        tree_14nw = new SolidRaster(59, "tree_14nw", Sprites.tree_14nw);
        registerRaster(tree_14nw);

        tree_14ne = new SolidRaster(60, "tree_14ne", Sprites.tree_14ne);
        registerRaster(tree_14ne);

        tree_14se = new SolidRaster(61, "tree_14se", Sprites.tree_14se);
        registerRaster(tree_14se);

        tree_14sw = new SolidRaster(62, "tree_14sw", Sprites.tree_14sw);
        registerRaster(tree_14sw);

        cobbles_1 = new SimpleRaster(63, "cobbles_1", Sprites.cobbles_1);
        registerRaster(cobbles_1);

        cobbles_2 = new SimpleRaster(64, "cobbles_2", Sprites.cobbles_2);
        registerRaster(cobbles_2);

        street_asphalt = new SimpleRaster(65, "street_asphalt", Sprites.street_asphalt);
        registerRaster(street_asphalt);

        street_l_t_1 = new SimpleRaster(66, "street_l_t_1", Sprites.street_l_t_1);
        registerRaster(street_l_t_1);

        street_l_t_2 = new SimpleRaster(67, "street_l_t_2", Sprites.street_l_t_2);
        registerRaster(street_l_t_2);

        street_l_t_3 = new SimpleRaster(68, "street_l_t_3", Sprites.street_l_t_3);
        registerRaster(street_l_t_3);

        street_l_t_4 = new SimpleRaster(69, "street_l_t_4", Sprites.street_l_t_4);
        registerRaster(street_l_t_4);

        street_l_t_5 = new SimpleRaster(70, "street_l_t_5", Sprites.street_l_t_5);
        registerRaster(street_l_t_5);

        street_l_t_6 = new SimpleRaster(71, "street_l_t_6", Sprites.street_l_t_6);
        registerRaster(street_l_t_6);

        street_l_t_7 = new SimpleRaster(72, "street_l_t_7", Sprites.street_l_t_7);
        registerRaster(street_l_t_7);

        street_l_t_8 = new SimpleRaster(73, "street_l_t_8", Sprites.street_l_t_8);
        registerRaster(street_l_t_8);

        street_l_b_1 = new SimpleRaster(74, "street_l_b_1", Sprites.street_l_b_1);
        registerRaster(street_l_b_1);

        street_l_b_2 = new SimpleRaster(75, "street_l_b_2", Sprites.street_l_b_2);
        registerRaster(street_l_b_2);

        street_l_b_3 = new SimpleRaster(76, "street_l_b_3", Sprites.street_l_b_3);
        registerRaster(street_l_b_3);

        street_l_b_4 = new SimpleRaster(77, "street_l_b_4", Sprites.street_l_b_4);
        registerRaster(street_l_b_4);

        street_l_b_5 = new SimpleRaster(78, "street_l_b_5", Sprites.street_l_b_5);
        registerRaster(street_l_b_5);

        street_l_b_6 = new SimpleRaster(79, "street_l_b_6", Sprites.street_l_b_6);
        registerRaster(street_l_b_6);

        street_l_b_7 = new SimpleRaster(80, "street_l_b_7", Sprites.street_l_b_7);
        registerRaster(street_l_b_7);

        street_l_b_8 = new SimpleRaster(81, "street_l_b_8", Sprites.street_l_b_8);
        registerRaster(street_l_b_8);

        floor_1 = new SimpleRaster(82, "floor_1", Sprites.floor_1);
        registerRaster(floor_1);

        floor_2 = new SimpleRaster(83, "floor_2", Sprites.floor_2);
        registerRaster(floor_2);

        floor_3 = new SimpleRaster(84, "floor_3", Sprites.floor_3);
        registerRaster(floor_3);

        floor_4 = new SimpleRaster(85, "floor_4", Sprites.floor_4);
        registerRaster(floor_4);

        floor_5 = new SimpleRaster(86, "floor_5", Sprites.floor_5);
        registerRaster(floor_5);

        floor_6 = new SimpleRaster(87, "floor_6", Sprites.floor_6);
        registerRaster(floor_6);

        floor_7 = new SimpleRaster(88, "floor_7", Sprites.floor_7);
        registerRaster(floor_7);

        floor_8 = new SimpleRaster(89, "floor_8", Sprites.floor_8);
        registerRaster(floor_8);

        floor_9 = new SimpleRaster(90, "floor_9", Sprites.floor_9);
        registerRaster(floor_9);

        floor_10 = new SimpleRaster(91, "floor_10", Sprites.floor_10);
        registerRaster(floor_10);

        floor_11 = new SimpleRaster(92, "floor_11", Sprites.floor_11);
        registerRaster(floor_11);

        floor_12 = new SimpleRaster(93, "floor_12", Sprites.floor_12);
        registerRaster(floor_12);

        chair_1 = new SolidRaster(94, "chair_1", Sprites.chair_1);
        registerRaster(chair_1);

        chair_2 = new SolidRaster(95, "chair_2", Sprites.chair_2);
        registerRaster(chair_2);

        chair_3 = new SolidRaster(96, "chair_2", Sprites.chair_3);
        registerRaster(chair_3);

        chair_4 = new SolidRaster(97, "chair_3", Sprites.chair_4);
        registerRaster(chair_4);

        chair_5 = new SolidRaster(98, "chair_4", Sprites.chair_5);
        registerRaster(chair_5);

        chair_6 = new SolidRaster(99, "chair_5", Sprites.chair_6);
        registerRaster(chair_6);

        chair_7 = new SolidRaster(100, "chair_6", Sprites.chair_7);
        registerRaster(chair_7);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param r
     *            Raster
     */
    private static void registerRaster(AbstractRaster r) {
        if (r != null && !MAPPINGS.containsKey(r.getName()) && r.getID() > 0 && r.getID() < RASTER.length && RASTER[r.getID()] == null) {
            MAPPINGS.put(r.getName(), r);
            RASTER[r.getID()] = r;
        } else {
            Logger.warn("Unable to register Raster", r);
            throw new IllegalArgumentException("Unable to register Raster " + r);
        }
    }

    /**
     * Konstruktor.
     */
    private Raster() {
    }
}

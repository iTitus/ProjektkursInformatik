package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.render.Sprite;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Sprites.
 */
public final class Sprites {

    public static Sprite boomBarrier;
    public static Sprite boomBarrierOpen;
    public static Sprite boy;
    public static Sprite button;
    public static Sprite button_disabled;
    public static Sprite button_highlight;
    public static Sprite car_empty_E;
    public static Sprite car_empty_N;
    public static Sprite car_empty_S;
    public static Sprite car_empty_W;
    public static Sprite car_frauV;
    public static Sprite car_frauV_E;
    public static Sprite car_frauV_N;
    public static Sprite car_frauV_S;
    public static Sprite car_frauV_W;
    public static Sprite car_lord_frauV_E;
    public static Sprite car_lord_frauV_N;
    public static Sprite car_lord_frauV_S;
    public static Sprite car_lord_frauV_W;
    public static Sprite cards;
    public static Sprite chair_1;
    public static Sprite chair_2;
    public static Sprite chair_3;
    public static Sprite chair_4;
    public static Sprite chair_5;
    public static Sprite chair_6;
    public static Sprite chair_7;
    public static Sprite chest;
    public static Sprite chewingGum;
    public static Sprite cobbles_1;
    public static Sprite cobbles_2;
    public static Sprite destroyed;
    public static Sprite door_NS;
    public static Sprite door_open_NS;
    public static Sprite door_open_WE;
    public static Sprite door_WE;
    public static Sprite earrings;
    public static Sprite ferry;
    public static Sprite ferryhouse;
    public static Sprite ferryhouse_door;
    public static Sprite ferryhouse_ecke_ne;
    public static Sprite ferryhouse_ecke_nw;
    public static Sprite ferryhouse_ecke_se;
    public static Sprite ferryhouse_ecke_sw;
    public static Sprite ferryhouse_wall_e;

    public static Sprite ferryhouse_wall_n;
    public static Sprite ferryhouse_wall_s;
    public static Sprite ferryhouse_wall_w;
    public static Sprite finish;
    public static Sprite[] fire;
    public static Sprite fisher;
    public static Sprite fisherboat;
    public static Sprite fishnet;
    public static Sprite fishnetString;
    public static Sprite floor_1;
    public static Sprite floor_10;
    public static Sprite floor_11;
    public static Sprite floor_12;
    public static Sprite floor_2;
    public static Sprite floor_3;
    public static Sprite floor_4;
    public static Sprite floor_5;
    public static Sprite floor_6;
    public static Sprite floor_7;
    public static Sprite floor_8;
    public static Sprite floor_9;
    public static Sprite frauV_E;
    public static Sprite frauV_N;
    public static Sprite frauV_S;
    public static Sprite frauV_W;

    public static Sprite gramophoneEntity;
    public static Sprite gramophoneItem;
    public static Sprite grass;
    public static Sprite grass_2;
    public static Sprite healthPotion;
    public static Sprite item42;
    public static Sprite key;
    public static Sprite knife;
    public static Sprite lordvO_N;
    public static Sprite lordvO_O;
    public static Sprite lordvO_S;
    public static Sprite lordvO_W;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Sprite> MAPPINGS = new HashMap<String, Sprite>();

    public static final Sprite MISSING_ICON = new Sprite("MISSING_ICON", 16, 0, 0xFF01FF, 0, 0xFF01FF);
    public static Sprite nest;
    public static Sprite nuke;
    public static Sprite redNPC;
    public static Sprite slot;
    public static Sprite slot_highlight;
    public static Sprite stone;
    public static Sprite stoneCatapult;
    public static Sprite stoneCatapultwithStone;
    public static Sprite street_asphalt;
    public static Sprite street_l_b_1_senk;
    public static Sprite street_l_b_1_wage;
    public static Sprite street_l_b_2_senk;
    public static Sprite street_l_b_2_wage;
    public static Sprite street_l_b_3_senk;

    public static Sprite street_l_b_3_wage;
    public static Sprite street_l_b_4_senk;
    public static Sprite street_l_b_4_wage;
    public static Sprite street_l_b_5_senk;
    public static Sprite street_l_b_5_wage;
    public static Sprite street_l_b_6_senk;
    public static Sprite street_l_b_6_wage;
    public static Sprite street_l_b_7_senk;
    public static Sprite street_l_b_7_wage;
    public static Sprite street_l_b_8_senk;
    public static Sprite street_l_b_8_wage;
    public static Sprite street_l_t_1_senk;
    public static Sprite street_l_t_1_wage;
    public static Sprite street_l_t_2_senk;
    public static Sprite street_l_t_2_wage;
    public static Sprite street_l_t_3_senk;
    public static Sprite street_l_t_3_wage;
    public static Sprite street_l_t_4_senk;
    public static Sprite street_l_t_4_wage;
    public static Sprite street_l_t_5_senk;
    public static Sprite street_l_t_5_wage;
    public static Sprite street_l_t_6_senk;
    public static Sprite street_l_t_6_wage;
    public static Sprite street_l_t_7_senk;
    public static Sprite street_l_t_7_wage;
    public static Sprite street_l_t_8_senk;
    public static Sprite street_l_t_8_wage;
    public static Sprite suitCase;
    public static Sprite teddydefault;
    public static Sprite teddyVoodoo;
    public static Sprite teddyWithEarrings;
    public static Sprite teddyWithFishnetString;
    public static Sprite thread;
    public static Sprite trashCan;
    public static Sprite tree;
    public static Sprite tree_10ne;
    public static Sprite tree_10nw;
    public static Sprite tree_10se;
    public static Sprite tree_10sw;
    public static Sprite tree_11ne;
    public static Sprite tree_11nw;
    public static Sprite tree_11se;
    public static Sprite tree_11sw;
    public static Sprite tree_12ne;
    public static Sprite tree_12nw;
    public static Sprite tree_12se;
    public static Sprite tree_12sw;
    public static Sprite tree_13ne;
    public static Sprite tree_13nw;
    public static Sprite tree_13se;
    public static Sprite tree_13sw;
    public static Sprite tree_14ne;
    public static Sprite tree_14nw;
    public static Sprite tree_14se;
    public static Sprite tree_14sw;
    public static Sprite tree_2ne;
    public static Sprite tree_2nw;
    public static Sprite tree_2se;
    public static Sprite tree_2sw;
    public static Sprite tree_3ne;
    public static Sprite tree_3nw;
    public static Sprite tree_3se;
    public static Sprite tree_3sw;
    public static Sprite tree_4ne;
    public static Sprite tree_4nw;
    public static Sprite tree_4se;
    public static Sprite tree_4sw;
    public static Sprite tree_5ne;
    public static Sprite tree_5ne_water;
    public static Sprite tree_5nw;
    public static Sprite tree_5nw_water;
    public static Sprite tree_5se;
    public static Sprite tree_5se_water;
    public static Sprite tree_5sw;
    public static Sprite tree_5sw_water;
    public static Sprite tree_6ne;
    public static Sprite tree_6nw;
    public static Sprite tree_6se;
    public static Sprite tree_6sw;
    public static Sprite tree_7ne;
    public static Sprite tree_7nw;
    public static Sprite tree_7se;
    public static Sprite tree_7sw;
    public static Sprite tree_8ne;
    public static Sprite tree_8nw;
    public static Sprite tree_8se;
    public static Sprite tree_8sw;

    public static Sprite tree_9ne;
    public static Sprite tree_9ne_water;
    public static Sprite tree_9nw;
    public static Sprite tree_9nw_water;
    public static Sprite tree_9se;
    public static Sprite tree_9se_water;

    public static Sprite tree_9sw;
    public static Sprite tree_9sw_water;
    public static Sprite wall;
    public static Sprite water;
    public static Sprite witchCauldron;

    public static Sprite women0_E;
    public static Sprite women0_N;
    public static Sprite women0_S;
    public static Sprite women0_W;
    public static Sprite yoyoBroken;
    public static Sprite yoyoFixed;

    /**
     * Das Pair, das alle Sprites enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.sprites", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Sprites.
     */
    @Init(State.RESOURCES_POST)
    public static void init() {
        wall = new Sprite("wall", Integers.RASTER_SIZE, 0, 0, SpriteSheets.raster);
        registerSprite(wall);

        grass = new Sprite("grass", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(grass);

        tree = new Sprite("tree", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree);

        chest = new Sprite("chest", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(chest);

        door_NS = new Sprite("door_NS", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(door_NS);

        door_WE = door_NS.rotate("door_WE", 90);
        registerSprite(door_WE);

        door_open_NS = new Sprite("door_open_NS", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(door_open_NS);

        door_open_WE = door_open_NS.rotate("door_open_WE", 90);
        registerSprite(door_open_WE);

        finish = new Sprite("finish", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(finish);

        destroyed = new Sprite("destroyed", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(destroyed);

        fire = new Sprite[4];
        for (int i = 0; i < fire.length; i++) {
            fire[i] = new Sprite("fire_" + i, Integers.RASTER_SIZE, i * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
            registerSprite(fire[i]);
        }

        water = new Sprite("water", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(water);

        grass_2 = new Sprite("rasen_2", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(grass_2);

        tree_2nw = new Sprite("tree_2nw", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2nw);

        tree_2ne = new Sprite("tree_2ne", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2ne);

        tree_2se = new Sprite("tree_2se", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2se);

        tree_2sw = new Sprite("tree_2sw", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_2sw);

        tree_3nw = new Sprite("tree_3nw", Integers.RASTER_SIZE, 0, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3nw);

        tree_3ne = new Sprite("tree_3ne", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3ne);

        tree_3se = new Sprite("tree_3se", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3se);

        tree_3sw = new Sprite("tree_3sw", Integers.RASTER_SIZE, 0, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_3sw);

        tree_4nw = new Sprite("tree_4nw", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4nw);

        tree_4ne = new Sprite("tree_4ne", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4ne);

        tree_4se = new Sprite("tree_4se", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4se);

        tree_4sw = new Sprite("tree_4sw", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_4sw);

        tree_5nw = new Sprite("tree_5nw", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_5nw);

        tree_5ne = new Sprite("tree_5ne", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_5ne);

        tree_5se = new Sprite("tree_5se", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_5se);

        tree_5sw = new Sprite("tree_5sw", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_5sw);

        tree_5nw_water = new Sprite("tree_5nw_water", Integers.RASTER_SIZE, 16 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_5nw_water);

        tree_5ne_water = new Sprite("tree_5ne_water", Integers.RASTER_SIZE, 17 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_5ne_water);

        tree_5se_water = new Sprite("tree_5se_water", Integers.RASTER_SIZE, 17 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_5se_water);

        tree_5sw_water = new Sprite("tree_5sw_water", Integers.RASTER_SIZE, 16 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_5sw_water);

        tree_6nw = new Sprite("tree_6nw", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_6nw);

        tree_6ne = new Sprite("tree_6ne", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_6ne);

        tree_6se = new Sprite("tree_6se", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_6se);

        tree_6sw = new Sprite("tree_6sw", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_6sw);

        tree_7nw = new Sprite("tree_7nw", Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_7nw);

        tree_7ne = new Sprite("tree_7ne", Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_7ne);

        tree_7se = new Sprite("tree_7se", Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_7se);

        tree_7sw = new Sprite("tree_7sw", Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_7sw);

        tree_8nw = new Sprite("tree_8nw", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_8nw);

        tree_8ne = new Sprite("tree_8ne", Integers.RASTER_SIZE, 13 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_8ne);

        tree_8se = new Sprite("tree_8se", Integers.RASTER_SIZE, 13 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_8se);

        tree_8sw = new Sprite("tree_8sw", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_8sw);

        tree_9nw = new Sprite("tree_9nw", Integers.RASTER_SIZE, 14 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_9nw);

        tree_9ne = new Sprite("tree_9ne", Integers.RASTER_SIZE, 15 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_9ne);

        tree_9se = new Sprite("tree_9se", Integers.RASTER_SIZE, 15 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_9se);

        tree_9sw = new Sprite("tree_9sw", Integers.RASTER_SIZE, 14 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_9sw);

        tree_9nw_water = new Sprite("tree_9nw_water", Integers.RASTER_SIZE, 18 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_9nw_water);

        tree_9ne_water = new Sprite("tree_9ne_water", Integers.RASTER_SIZE, 19 * Integers.RASTER_SIZE, 0, SpriteSheets.raster);
        registerSprite(tree_9ne_water);

        tree_9se_water = new Sprite("tree_9se_water", Integers.RASTER_SIZE, 19 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_9se_water);

        tree_9sw_water = new Sprite("tree_9sw_water", Integers.RASTER_SIZE, 18 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_9sw_water);

        tree_10nw = new Sprite("tree_10nw", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_10nw);

        tree_10ne = new Sprite("tree_10ne", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_10ne);

        tree_10se = new Sprite("tree_10se", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_10se);

        tree_10sw = new Sprite("tree_10sw", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_10sw);

        tree_11nw = new Sprite("tree_11nw", Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_11nw);

        tree_11ne = new Sprite("tree_11ne", Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_11ne);

        tree_11se = new Sprite("tree_11se", Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_11se);

        tree_11sw = new Sprite("tree_11sw", Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_11sw);

        tree_12nw = new Sprite("tree_12nw", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_12nw);

        tree_12ne = new Sprite("tree_12ne", Integers.RASTER_SIZE, 13 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_12ne);

        tree_12se = new Sprite("tree_12se", Integers.RASTER_SIZE, 13 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_12se);

        tree_12sw = new Sprite("tree_12sw", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_12sw);

        tree_13nw = new Sprite("tree_13nw", Integers.RASTER_SIZE, 14 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_13nw);

        tree_13ne = new Sprite("tree_13ne", Integers.RASTER_SIZE, 15 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_13ne);

        tree_13se = new Sprite("tree_13se", Integers.RASTER_SIZE, 15 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_13se);

        tree_13sw = new Sprite("tree_13sw", Integers.RASTER_SIZE, 14 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_13sw);

        tree_14nw = new Sprite("tree_14nw", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_14nw);

        tree_14ne = new Sprite("tree_14ne", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_14ne);

        tree_14se = new Sprite("tree_14se", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_14se);

        tree_14sw = new Sprite("tree_14sw", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(tree_14sw);

        cobbles_1 = new Sprite("cobbles_1", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(cobbles_1);

        cobbles_2 = new Sprite("cobbles_2", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(cobbles_2);

        street_asphalt = new Sprite("street_asphalt", Integers.RASTER_SIZE, 0, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_asphalt);

        street_l_t_1_wage = new Sprite("street_l_t_1_wage", Integers.RASTER_SIZE, 0, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_1_wage);

        street_l_t_2_wage = new Sprite("street_l_t_2_wage", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_2_wage);

        street_l_t_3_wage = new Sprite("street_l_t_3_wage", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_3_wage);

        street_l_t_4_wage = new Sprite("street_l_t_4_wage", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_4_wage);

        street_l_t_5_wage = new Sprite("street_l_t_5_wage", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_5_wage);

        street_l_t_6_wage = new Sprite("street_l_t_6_wage", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_6_wage);

        street_l_t_7_wage = new Sprite("street_l_t_7_wage", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_7_wage);

        street_l_t_8_wage = new Sprite("street_l_t_8_wage", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_t_8_wage);

        street_l_b_1_wage = new Sprite("street_l_b_1_wage", Integers.RASTER_SIZE, 0, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_1_wage);

        street_l_b_2_wage = new Sprite("street_l_b_2_wage", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_2_wage);

        street_l_b_3_wage = new Sprite("street_l_b_3_wage", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_3_wage);

        street_l_b_4_wage = new Sprite("street_l_b_4_wage", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_4_wage);

        street_l_b_5_wage = new Sprite("street_l_b_5_wage", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_5_wage);

        street_l_b_6_wage = new Sprite("street_l_b_6_wage", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_6_wage);

        street_l_b_7_wage = new Sprite("street_l_b_7_wage", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_7_wage);

        street_l_b_8_wage = new Sprite("street_l_b_8_wage", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(street_l_b_8_wage);

        street_l_b_1_senk = street_l_b_1_wage.rotate("street_l_b_1_senk", 90);
        registerSprite(street_l_b_1_senk);

        street_l_b_2_senk = street_l_b_2_wage.rotate("street_l_b_2_senk", 90);
        registerSprite(street_l_b_2_senk);

        street_l_b_3_senk = street_l_b_3_wage.rotate("street_l_b_3_senk", 90);
        registerSprite(street_l_b_3_senk);

        street_l_b_4_senk = street_l_b_4_wage.rotate("street_l_b_4_senk", 90);
        registerSprite(street_l_b_4_senk);

        street_l_b_5_senk = street_l_b_5_wage.rotate("street_l_b_5_senk", 90);
        registerSprite(street_l_b_5_senk);

        street_l_b_6_senk = street_l_b_6_wage.rotate("street_l_b_6_senk", 90);
        registerSprite(street_l_b_6_senk);

        street_l_b_7_senk = street_l_b_7_wage.rotate("street_l_b_7_senk", 90);
        registerSprite(street_l_b_7_senk);

        street_l_b_8_senk = street_l_b_8_wage.rotate("street_l_b_8_senk", 90);
        registerSprite(street_l_b_8_senk);

        street_l_t_1_senk = street_l_t_1_wage.rotate("street_l_t_1_senk", 90);
        registerSprite(street_l_t_1_senk);

        street_l_t_2_senk = street_l_t_2_wage.rotate("street_l_t_2_senk", 90);
        registerSprite(street_l_t_2_senk);

        street_l_t_3_senk = street_l_t_3_wage.rotate("street_l_t_3_senk", 90);
        registerSprite(street_l_t_3_senk);

        street_l_t_4_senk = street_l_t_4_wage.rotate("street_l_t_4_senk", 90);
        registerSprite(street_l_t_4_senk);

        street_l_t_5_senk = street_l_t_5_wage.rotate("street_l_t_5_senk", 90);
        registerSprite(street_l_t_5_senk);

        street_l_t_6_senk = street_l_t_6_wage.rotate("street_l_t_6_senk", 90);
        registerSprite(street_l_t_6_senk);

        street_l_t_7_senk = street_l_t_7_wage.rotate("street_l_t_7_senk", 90);
        registerSprite(street_l_t_7_senk);

        street_l_t_8_senk = street_l_t_8_wage.rotate("street_l_t_8_senk", 90);
        registerSprite(street_l_t_8_senk);

        floor_1 = new Sprite("floor_1", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_1);

        floor_2 = new Sprite("floor_2", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_2);

        floor_3 = new Sprite("floor_3", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_3);

        floor_4 = new Sprite("floor_4", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_4);

        floor_5 = new Sprite("floor_5", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_5);

        floor_6 = new Sprite("floor_6", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_6);

        floor_7 = new Sprite("floor_7", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_7);

        floor_8 = new Sprite("floor_8", Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_8);

        floor_9 = new Sprite("floor_9", Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_9);

        floor_10 = new Sprite("floor_10", Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_10);

        floor_11 = new Sprite("floor_11", Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_11);

        floor_12 = new Sprite("floor_12", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(floor_12);

        chair_1 = new Sprite("chair_1", Integers.RASTER_SIZE, 13 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_1);

        chair_2 = new Sprite("chair_2", Integers.RASTER_SIZE, 14 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_2);

        chair_3 = new Sprite("chair_3", Integers.RASTER_SIZE, 15 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_3);

        chair_4 = new Sprite("chair_4", Integers.RASTER_SIZE, 16 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_4);

        chair_5 = new Sprite("chair_5", Integers.RASTER_SIZE, 17 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_5);

        chair_6 = new Sprite("chair_6", Integers.RASTER_SIZE, 18 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_6);

        chair_7 = new Sprite("chair_7", Integers.RASTER_SIZE, 19 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(chair_7);

        item42 = new Sprite("item42", Integers.RASTER_SIZE, 0, 0, SpriteSheets.items);
        registerSprite(item42);

        nuke = new Sprite("nuke", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(nuke);

        key = new Sprite("key", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(key);

        healthPotion = new Sprite("healthPotion", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(healthPotion);

        button = new Sprite("button", Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, 0, 0, SpriteSheets.guis);
        registerSprite(button);

        button_highlight = new Sprite("button_highlight", Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, 0, Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(button_highlight);

        button_disabled = new Sprite("button_disabled", Integers.DEFAULT_BUTTON_WIDTH, Integers.DEFAULT_BUTTON_HEIGHT, 0, 2 * Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(button_disabled);

        slot = new Sprite("slot", Integers.SLOT_SIZE, 0, 3 * Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(slot);

        slot_highlight = new Sprite("slot_highlight", Integers.SLOT_SIZE, Integers.SLOT_SIZE, 3 * Integers.DEFAULT_BUTTON_HEIGHT, SpriteSheets.guis);
        registerSprite(slot_highlight);

        lordvO_N = new Sprite("lordvO_N", Integers.RASTER_SIZE, 0, 0, SpriteSheets.entities);
        registerSprite(lordvO_N);

        lordvO_O = lordvO_N.rotate("lordvO_O", 90);
        registerSprite(lordvO_O);

        lordvO_S = lordvO_N.rotate("lordvO_S", 180);
        registerSprite(lordvO_S);

        lordvO_W = lordvO_N.rotate("lordvO_W", 270);
        registerSprite(lordvO_W);

        redNPC = new Sprite("redNPC", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(redNPC);

        frauV_N = new Sprite("frauV_N", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(frauV_N);

        frauV_E = frauV_N.rotate("frauV_E", 90);
        registerSprite(frauV_E);

        frauV_S = frauV_N.rotate("frauV_S", 180);
        registerSprite(frauV_S);

        frauV_W = frauV_N.rotate("frauV_W", 270);
        registerSprite(frauV_W);

        boy = new Sprite("boy", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(boy);

        fisher = new Sprite("fischer", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 0, 9 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(fisher);

        women0_N = new Sprite("women0_N", Integers.RASTER_SIZE, 0, 9 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(women0_N);

        women0_E = women0_N.rotate("women0_E", 90);
        registerSprite(women0_E);

        women0_S = women0_N.rotate("women0_S", 180);
        registerSprite(women0_S);

        women0_W = women0_N.rotate("women0_W", 270);
        registerSprite(women0_W);

        ferry = new Sprite("ferry", 4 * Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 0, Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(ferry);

        car_empty_N = new Sprite("car_empty_N", 2 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(car_empty_N);

        car_empty_E = car_empty_N.rotate("car_empty_E", 90);
        registerSprite(car_empty_E);

        car_empty_S = car_empty_N.rotate("car_empty_S", 180);
        registerSprite(car_empty_S);

        car_empty_W = car_empty_N.rotate("car_empty_W", 270);
        registerSprite(car_empty_W);

        car_lord_frauV_N = new Sprite("car_lord_frauV_N", 2 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(car_lord_frauV_N);

        car_lord_frauV_E = car_lord_frauV_N.rotate("car_lord_frauV_E", 90);
        registerSprite(car_lord_frauV_E);

        car_lord_frauV_S = car_lord_frauV_N.rotate("car_lord_frauV_S", 180);
        registerSprite(car_lord_frauV_S);

        car_lord_frauV_W = car_lord_frauV_N.rotate("car_lord_frauV_W", 270);
        registerSprite(car_lord_frauV_W);

        car_frauV_N = new Sprite("car_frauV_N", 2 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(car_frauV_N);

        car_frauV_E = car_frauV_N.rotate("car_frauV_E", 90);
        registerSprite(car_frauV_E);

        car_frauV_S = car_frauV_N.rotate("car_frauV_S", 180);
        registerSprite(car_frauV_S);

        car_frauV_W = car_frauV_N.rotate("car_frauV_W", 270);
        registerSprite(car_frauV_W);

        fisherboat = new Sprite("fisherboat", 6 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(fisherboat);

        fishnetString = new Sprite("fishnetString", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(fishnetString);

        knife = new Sprite("knife", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(knife);

        thread = new Sprite("thread", 2 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 0, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(thread);

        teddydefault = new Sprite("teddydefault", Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(teddydefault);

        earrings = new Sprite("earrings", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(earrings);

        stoneCatapult = new Sprite("stoneCatapult", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(stoneCatapult);

        gramophoneItem = new Sprite("gramophonItem", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(gramophoneItem);

        gramophoneEntity = new Sprite("gramophonEntity", 1 * Integers.RASTER_SIZE, 1 * Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(gramophoneEntity);

        yoyoFixed = new Sprite("yoyoFixed", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 0, SpriteSheets.items);
        registerSprite(yoyoFixed);

        suitCase = new Sprite("suitCase", Integers.RASTER_SIZE, 0, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(suitCase);

        cards = new Sprite("cards", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(cards);

        chewingGum = new Sprite("chewingGum", Integers.RASTER_SIZE, Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(chewingGum);

        ferryhouse_wall_w = new Sprite("ferryhouse_wall_w", Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(ferryhouse_wall_w);

        ferryhouse_wall_s = ferryhouse_wall_w.rotate("ferryhouse_wall_s", 270);
        registerSprite(ferryhouse_wall_s);

        ferryhouse_wall_n = ferryhouse_wall_w.rotate("ferryhouse_wall_n", 90);
        registerSprite(ferryhouse_wall_n);

        ferryhouse_wall_e = ferryhouse_wall_w.rotate("ferryhouse_wall_e", 180);
        registerSprite(ferryhouse_wall_e);

        ferryhouse_ecke_sw = new Sprite("ferryhouse_ecke_sw", Integers.RASTER_SIZE, 11 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(ferryhouse_ecke_sw);

        ferryhouse_ecke_nw = ferryhouse_ecke_sw.rotate("ferryhouse_ecke_nw", 90);
        registerSprite(ferryhouse_ecke_nw);

        ferryhouse_ecke_ne = ferryhouse_ecke_sw.rotate("ferryhouse_ecke_ne", 180);
        registerSprite(ferryhouse_ecke_ne);

        ferryhouse_ecke_se = ferryhouse_ecke_sw.rotate("ferryhouse_ecke_se", 270);
        registerSprite(ferryhouse_ecke_se);

        ferryhouse_door = new Sprite("ferryhouse_door", Integers.RASTER_SIZE, 12 * Integers.RASTER_SIZE, 5 * Integers.RASTER_SIZE, SpriteSheets.raster);
        registerSprite(ferryhouse_door);

        car_frauV = new Sprite("car_frauV", 2 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 8 * Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(car_frauV);

        boomBarrier = new Sprite("boomBarrier", 4 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 13 * Integers.RASTER_SIZE, 0, SpriteSheets.entities);
        registerSprite(boomBarrier);

        ferryhouse = new Sprite("ferryhouse", 6 * Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(ferryhouse);

        boomBarrierOpen = new Sprite("boomBarrierOpen", 2 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(boomBarrierOpen);

        witchCauldron = new Sprite("witchCauldron", 4 * Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(witchCauldron);

        nest = new Sprite("nest", Integers.RASTER_SIZE, Integers.RASTER_SIZE, Integers.RASTER_SIZE, 9 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(nest);

        trashCan = new Sprite("trashCan", 2 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, 10 * Integers.RASTER_SIZE, SpriteSheets.entities);
        registerSprite(trashCan);

        teddyWithEarrings = new Sprite("teddyWithEarrings", Integers.RASTER_SIZE, 6 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(teddyWithEarrings);

        teddyWithFishnetString = new Sprite("teddyWithFishnetString", Integers.RASTER_SIZE, 7 * Integers.RASTER_SIZE, Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(teddyWithFishnetString);

        fishnet = new Sprite("fishnet", Integers.RASTER_SIZE, 0, 2 * Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(fishnet);

        stone = new Sprite("stone", Integers.RASTER_SIZE, Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(stone);

        teddyVoodoo = new Sprite("teddyVoodoo", Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(teddyVoodoo);

        stoneCatapultwithStone = new Sprite("stoneCatapultwithStone", Integers.RASTER_SIZE, 3 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(stoneCatapultwithStone);

        yoyoBroken = new Sprite("yoyoBroken", Integers.RASTER_SIZE, 4 * Integers.RASTER_SIZE, 2 * Integers.RASTER_SIZE, SpriteSheets.items);
        registerSprite(yoyoBroken);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param s
     *            Sprite
     */
    public static void registerSprite(Sprite s) {
        if (s != null && !MAPPINGS.containsKey(s.getName())) {
            MAPPINGS.put(s.getName(), s);
        } else {
            Logger.warn("Unable to register Sprite", s);
            throw new IllegalArgumentException("Unable to register Sprite " + s);
        }
    }

    private Sprites() {
    }

}

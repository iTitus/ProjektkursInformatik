package projektkurs.lib;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import projektkurs.Main;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.Pair;

/**
 * Alle Bilder.
 */
public final class Images {

    public static BufferedImage auto_dc_EW;
    /**
     *
     */
    public static BufferedImage auto_dc_NS;
    public static BufferedImage auto_dc_SN;
    public static BufferedImage auto_dc_WE;
    public static BufferedImage auto_di_EW;
    /**
     *
     */
    public static BufferedImage auto_di_NS;
    public static BufferedImage auto_di_SN;
    public static BufferedImage auto_di_WE;
    public static BufferedImage auto_di2_EW;
    /**
     *
     */
    public static BufferedImage auto_di2_NS;
    public static BufferedImage auto_di2_SN;
    public static BufferedImage auto_di2_WE;
    public static BufferedImage auto_di3_EW;
    /**
     *
     */
    public static BufferedImage auto_di3_NS;
    public static BufferedImage auto_di3_SN;
    public static BufferedImage auto_di3_WE;
    public static BufferedImage auto_di4_EW;
    /**
     *
     */
    public static BufferedImage auto_di4_NS;
    public static BufferedImage auto_di4_SN;
    public static BufferedImage auto_di4_WE;
    public static BufferedImage auto_do_EW;
    /**
     * Autobild
     */
    public static BufferedImage auto_do_NS;
    public static BufferedImage auto_do_SN;
    public static BufferedImage auto_do_WE;
    public static BufferedImage auto_do2_EW;
    /**
     *
     */
    public static BufferedImage auto_do2_NS;
    public static BufferedImage auto_do2_SN;
    public static BufferedImage auto_do2_WE;
    public static BufferedImage auto_do3_EW;
    /**
     *
     */
    public static BufferedImage auto_do3_NS;
    public static BufferedImage auto_do3_SN;
    public static BufferedImage auto_do3_WE;
    public static BufferedImage auto_do4_EW;
    /**
     *
     */
    public static BufferedImage auto_do4_NS;
    public static BufferedImage auto_do4_SN;
    public static BufferedImage auto_do4_WE;
    public static BufferedImage auto_EW;
    /**
     *
     */
    public static BufferedImage auto_NS;
    public static BufferedImage auto_SN;
    public static BufferedImage auto_w_EW;
    /**
     *
     */
    public static BufferedImage auto_w_NS;
    public static BufferedImage auto_w_SN;
    public static BufferedImage auto_w_WE;
    public static BufferedImage auto_w2_EW;
    /**
     *
     */
    public static BufferedImage auto_w2_NS;
    public static BufferedImage auto_WE;
    /**
    *
    */
    public static BufferedImage auto2vOnO;
    /**
    *
    */
    public static BufferedImage auto2vOnW;
    /**
    *
    */
    public static BufferedImage auto2vV;
    public static BufferedImage autogelbvO_EW;
    public static BufferedImage autogelbvO_NS;
    public static BufferedImage autogelbvO_SN;
    public static BufferedImage autogelbvO_WE;
    /**
    *
    */
    public static BufferedImage autogelbvOnO;
    /**
    *
    */
    public static BufferedImage autogelbvOnW;
    public static BufferedImage autopolizeivdS_EW;
    public static BufferedImage autopolizeivdS_WE;
    public static BufferedImage autopolizeivO_EW;
    public static BufferedImage autopolizeivO_NS;
    public static BufferedImage autopolizeivO_SN;
    public static BufferedImage autopolizeivO_WE;
    public static BufferedImage autopolizeivV;
    /**
     * Zurueck-Mappings.
     */
    public static final HashMap<BufferedImage, String> BACK_MAPPINGS = new HashMap<BufferedImage, String>();
    /**
     * Baumbild.
     */
    public static BufferedImage baum;
    /**
     *
     */
    public static BufferedImage baum1;
    /**
     *
     */
    public static BufferedImage baum10;
    /**
     *
     */
    public static BufferedImage baum11;
    /**
     *
     */
    public static BufferedImage baum12;
    /**
     *
     */
    public static BufferedImage baum13;
    /**
     *
     */
    public static BufferedImage baum2;
    /**
     *
     */
    public static BufferedImage baum3;
    /**
     *
     */
    public static BufferedImage baum4;
    /**
     *
     */
    public static BufferedImage baum5;
    /**
     *
     */
    public static BufferedImage baum6;
    /**
     *
     */
    public static BufferedImage baum7;
    /**
     *
     */
    public static BufferedImage baum8;
    /**
     *
     */
    public static BufferedImage baum9;
    public static BufferedImage bodenbeige;
    public static BufferedImage bodenblau;
    public static BufferedImage bodendunkelgrau;
    public static BufferedImage bodenhellgrau;
    public static BufferedImage bodenkachelngross;
    public static BufferedImage bodenkachelnklein;
    public static BufferedImage bodenorange;
    public static BufferedImage bodenpink;
    public static BufferedImage bodenrot;
    public static BufferedImage bodenschwarz;
    public static BufferedImage bodenstein;
    public static BufferedImage bodensteinkacheln;
    /**
    *
    */
    public static BufferedImage busviolettvOnO;
    /**
    *
    */
    public static BufferedImage busviolettvSnW;
    /**
    *
    */
    public static BufferedImage busviolettvV;
    /**
     * Gui-Knopf-Bild.
     */
    public static BufferedImage button;
    /**
     * Deaktiviertes Gui-Knopf-Bild.
     */
    public static BufferedImage buttonDisabled;
    /**
     * Hervorgehobenes Gui-Knopf-Bild.
     */
    public static BufferedImage buttonHighlight;
    /**
     * Charakterbild - Userauswahl.
     */
    public static BufferedImage charakter;
    /**
     * ZerstÃ¶rtes-Land-Bild.
     */
    public static BufferedImage destroyedRaster;
    /**
     * Geschlossene Tuer in Ost-West Ausrichtung.
     */
    public static BufferedImage doorEW;
    /**
     * Geschlossene Tuer in Nord-Sued Ausrichtung.
     */
    public static BufferedImage doorNS;
    /**
     * Offene Tuer in Ost-West Ausrichtung.
     */
    public static BufferedImage doorOpenEW;
    /**
     * Offene Tuer in Nord-Sued-Ausrichtung.
     */
    public static BufferedImage doorOpenNS;
    public static BufferedImage elster;
    public static BufferedImage faehre;
    /**
     * Zielbild.
     */
    public static BufferedImage finish;
    /**
     * Feueranimationsbilder.
     */
    public static BufferedImage[] fire;
    public static BufferedImage grammophonvdS;
    public static BufferedImage grammophonvO_EW;
    public static BufferedImage grammophonvO_NS;
    public static BufferedImage grammophonvO_SN;
    public static BufferedImage grammophonvO_WE;
    /**
     *
     */
    public static BufferedImage gras;
    /**
    *
    */
    public static BufferedImage haus1;
    public static BufferedImage haus1_EW;

    public static BufferedImage haus1_NS;

    public static BufferedImage haus1_SN;

    public static BufferedImage haus1_WE;

    /**
    *
    */
    public static BufferedImage haus2;

    public static BufferedImage haus2_EW;

    public static BufferedImage haus2_NS;

    public static BufferedImage haus2_SN;
    public static BufferedImage haus2_WE;
    /**
    *
    */
    public static BufferedImage haus3;
    public static BufferedImage haus3_EW;
    public static BufferedImage haus3_NS;
    public static BufferedImage haus3_SN;
    public static BufferedImage haus3_WE;
    /**
    *
    */
    public static BufferedImage haus4;
    public static BufferedImage haus4_EW;

    public static BufferedImage haus4_NS;

    public static BufferedImage haus4_SN;

    public static BufferedImage haus4_WE;

    /**
    *
    */
    public static BufferedImage haus5;

    public static BufferedImage haus5ecke_ES;

    public static BufferedImage haus5ecke_NE;

    public static BufferedImage haus5ecke_SW;

    public static BufferedImage haus5ecke_WN;

    /**
    *
    */
    public static BufferedImage haus6;

    /**
    *
    */
    public static BufferedImage haus7;

    public static BufferedImage haus7ecke_ES;

    public static BufferedImage haus7ecke_NE;

    public static BufferedImage haus7ecke_SW;

    public static BufferedImage haus7ecke_WN;

    public static BufferedImage hauswelldach_EW;

    public static BufferedImage hauswelldach_NS;

    public static BufferedImage hauswelldach_SN;

    public static BufferedImage hauswelldach_WE;

    /**
     *
     */
    // public static BufferedImage haus8;
    /**
     * Gesundheitstrank.
     */
    public static BufferedImage healthpotion;

    /**
     * Item_42-Bild.
     */
    public static BufferedImage item42;

    public static BufferedImage jojo;

    public static BufferedImage jojokaputt;

    public static BufferedImage kaugummigelb;

    public static BufferedImage kaugummirot;

    /**
     * Schluesselbild.
     */
    public static BufferedImage key;

    /**
     * Kistenbild.
     */
    public static BufferedImage kiste;

    public static BufferedImage klavier;

    public static BufferedImage klavier_EW;

    public static BufferedImage klavier_NS;

    public static BufferedImage klavier_SN;

    public static BufferedImage klavier_WE;

    /**
     *
     */
    public static BufferedImage kreuzung;

    /**
     *
     */
    public static BufferedImage kreuzungT;

    /**
    *
    */
    public static BufferedImage kreuzungT_EW;

    /**
    *
    */
    public static BufferedImage kreuzungT_NS;

    /**
    *
    */
    public static BufferedImage kreuzungT_SN;

    /**
    *
    */
    public static BufferedImage kreuzungT_WE;

    /**
    *
    */
    public static BufferedImage ladyvO_EW;

    /**
    *
    */
    public static BufferedImage ladyvO_NS;

    /**
    *
    */
    public static BufferedImage ladyvO_SN;

    /**
    *
    */
    public static BufferedImage ladyvO_WE;

    /**
    *
    */
    public static BufferedImage ladyvObAho_EW;

    /**
    *
    */
    public static BufferedImage ladyvObAho_NS;

    /**
    *
    */
    public static BufferedImage ladyvObAho_SN;

    /**
    *
    */
    public static BufferedImage ladyvObAho_WE;

    /**
    *
    */
    public static BufferedImage ladyvOrAho_EW;

    /**
    *
    */
    public static BufferedImage ladyvOrAho_NS;

    /**
    *
    */
    public static BufferedImage ladyvOrAho_SN;

    /**
    *
    */
    public static BufferedImage ladyvOrAho_WE;

    /**
    *
    */
    public static BufferedImage ladyvOrAo_EW;

    /**
    *
    */
    public static BufferedImage ladyvOrAo_NS;

    /**
    *
    */
    public static BufferedImage ladyvOrAo_SN;

    /**
    *
    */
    public static BufferedImage ladyvOrAo_WE;

    /**
    *
    */
    public static BufferedImage ladyvVbAho;

    /**
    *
    */
    public static BufferedImage ladyvVbAho2;

    /**
    *
    */
    public static BufferedImage ladyvVbAo;

    /**
    *
    */
    public static BufferedImage ladyvVlAqurAo;

    /**
    *
    */
    public static BufferedImage ladyvVrS;

    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhaengervO_EW;

    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhaengervO_NS;

    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhaengervO_SN;

    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhaengervO_WE;

    /**
    *
    */
    public static BufferedImage lasterschwarzvO_EW;

    /**
    *
    */
    public static BufferedImage lasterschwarzvO_NS;

    /**
    *
    */
    public static BufferedImage lasterschwarzvO_SN;

    /**
    *
    */
    public static BufferedImage lasterschwarzvO_WE;

    /**
    *
    */
    public static BufferedImage lasterschwarzvV;

    /**
    *
    */
    public static BufferedImage lasterweissvO_EW;

    /**
    *
    */
    public static BufferedImage lasterweissvO_NS;

    /**
    *
    */
    public static BufferedImage lasterweissvO_SN;

    /**
    *
    */
    public static BufferedImage lasterweissvO_WE;

    /**
    *
    */
    public static BufferedImage lasterweissvV;

    /**
     *
     */
    public static BufferedImage lordH;

    /**
     *
     */
    public static BufferedImage lordS;

    public static BufferedImage lordS_EW;

    public static BufferedImage lordS_NS;

    public static BufferedImage lordS_SN;

    public static BufferedImage lordS_WE;

    /**
     *
     */
    public static BufferedImage lordvdS;

    /**
     *
     */
    public static BufferedImage lordvdS2;

    /**
     *
     */
    public static BufferedImage lordvdS3;

    public static BufferedImage lordvO_EW;

    public static BufferedImage lordvO_NS;

    public static BufferedImage lordvO_SN;

    public static BufferedImage lordvO_WE;

    /**
     * Mappings.
     */
    public static final HashMap<String, BufferedImage> MAPPINGS = new HashMap<String, BufferedImage>();

    public static BufferedImage messer;

    public static BufferedImage muelltonneoffenvO;

    public static BufferedImage muelltonnezuvO;

    public static BufferedImage nestmitEiernvO;

    public static BufferedImage nestvO;

    public static BufferedImage netz;

    public static BufferedImage netzfaden;

    /**
     * Atombombenbild.
     */
    public static BufferedImage nuke;

    public static BufferedImage ohrringe;

    public static BufferedImage ohrringe_EW;

    public static BufferedImage ohrringe_NS;

    public static BufferedImage ohrringe_SN;

    public static BufferedImage ohrringe_WE;

    /**
     * Pflastersteine.
     */
    public static BufferedImage pflaster;

    public static BufferedImage pflaster2;

    /**
     * Rasenbild.
     */
    public static BufferedImage rasen;

    /**
     *
     */
    public static BufferedImage rasen2;

    public static BufferedImage rasen3;

    /**
     *
     */
    public static BufferedImage redNPC;

    public static BufferedImage schranke_EW;

    public static BufferedImage schranke_NS;

    public static BufferedImage schranke_SN;

    public static BufferedImage schranke_WE;

    public static BufferedImage schranke2_EW;

    public static BufferedImage schranke2_NS;

    public static BufferedImage schranke2_SN;

    public static BufferedImage schranke2_WE;

    public static BufferedImage schranke3_EW;

    public static BufferedImage schranke3_NS;

    public static BufferedImage schranke3_SN;

    public static BufferedImage schranke3_WE;

    public static BufferedImage schranke4_EW;

    public static BufferedImage schranke4_NS;

    public static BufferedImage schranke4_SN;

    public static BufferedImage schranke4_WE;

    public static BufferedImage schranke5_EW;

    public static BufferedImage schranke5_NS;

    public static BufferedImage schranke5_SN;

    public static BufferedImage schranke5_WE;

    public static BufferedImage schranke6_EW;

    public static BufferedImage schranke6_NS;

    public static BufferedImage schranke6_SN;

    public static BufferedImage schranke6_WE;

    public static BufferedImage schranke7_EW;

    public static BufferedImage schranke7_NS;

    public static BufferedImage schranke7_SN;

    public static BufferedImage schranke7_WE;

    public static BufferedImage sesselbeigefell_EW;

    public static BufferedImage sesselbeigefell_NS;

    public static BufferedImage sesselbeigefell_SN;

    public static BufferedImage sesselbeigefell_WE;

    public static BufferedImage sesselschwarz_EW;

    public static BufferedImage sesselschwarz_NS;

    public static BufferedImage sesselschwarz_SN;

    public static BufferedImage sesselschwarz_WE;

    public static BufferedImage sesselschwarzbeige_EW;

    public static BufferedImage sesselschwarzbeige_NS;

    public static BufferedImage sesselschwarzbeige_SN;

    public static BufferedImage sesselschwarzbeige_WE;

    /**
     *
     */
    public static BufferedImage slot;

    /**
     * Hervorgehobenes Inventarslotbild.
     */
    public static BufferedImage slothighlight;

    public static BufferedImage steinschleuder;

    public static BufferedImage strasse_EW;

    public static BufferedImage strasse_NS;

    public static BufferedImage stuhlbraunundholz_EW;

    public static BufferedImage stuhlbraunundholz_NS;

    public static BufferedImage stuhlbraunundholz_SN;

    public static BufferedImage stuhlbraunundholz_WE;

    public static BufferedImage stuhlbuero_EW;

    public static BufferedImage stuhlbuero_NS;

    public static BufferedImage stuhlbuero_SN;

    public static BufferedImage stuhlbuero_WE;

    public static BufferedImage stuhlholz_EW;

    public static BufferedImage stuhlholz_NS;

    public static BufferedImage stuhlholz_SN;

    public static BufferedImage stuhlholz_WE;

    public static BufferedImage stuhltriptrap_EW;

    public static BufferedImage stuhltriptrap_NS;

    public static BufferedImage stuhltriptrap_SN;

    public static BufferedImage stuhltriptrap_WE;

    public static BufferedImage teddymOhrringenvV;

    public static BufferedImage teddysvO_EW;

    public static BufferedImage teddysvO_NS;

    public static BufferedImage teddysvO_SN;

    public static BufferedImage teddysvO_WE;

    public static BufferedImage teddysvV;

    public static BufferedImage teddyvO_EW;

    public static BufferedImage teddyvO_NS;

    public static BufferedImage teddyvO_SN;

    public static BufferedImage teddyvO_WE;

    public static BufferedImage teddyvOs_WE;

    public static BufferedImage teddyvV;

    public static BufferedImage typvO_EW;

    public static BufferedImage typvO_NS;

    public static BufferedImage typvO_SN;

    public static BufferedImage typvO_WE;

    public static BufferedImage typvO2_EW;

    public static BufferedImage typvO2_NS;

    public static BufferedImage typvO2_SN;

    public static BufferedImage typvO2_WE;

    public static BufferedImage typvObAo_EW;

    public static BufferedImage typvObAo_NS;

    public static BufferedImage typvObAo_SN;

    public static BufferedImage typvObAo_WE;

    public static BufferedImage typvOrAho_EW;

    public static BufferedImage typvOrAho_NS;

    public static BufferedImage typvOrAho_WE;

    public static BufferedImage typvOrAo_EW;

    public static BufferedImage typvOrAo_NS;

    public static BufferedImage typvOrAo_SN;

    public static BufferedImage typvOrAo_WE;

    /**
     * Wandbild.
     */
    public static BufferedImage wand;

    public static BufferedImage wasser;

    public static BufferedImage wasserdunkel;

    public static BufferedImage weibvO;

    public static BufferedImage weibvO_EW;

    public static BufferedImage weibvO_SO;

    public static BufferedImage weibvO_WE;

    public static BufferedImage weibvOlAo;

    public static BufferedImage weibvOlAo_EW;

    public static BufferedImage weibvOlAo_NS;

    public static BufferedImage weibvOlAo_SN;

    public static BufferedImage weibvOlAo_WE;

    public static BufferedImage weibvVbAo;

    public static BufferedImage weibvVrAho;

    /**
     * Befreit alle Bilder.
     */
    public static void flushAll() {
        for (BufferedImage img : MAPPINGS.values()) {
            if (img != null) {
                img.flush();
            }
        }
    }

    /**
     * Das Pair, das alle Images enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.images", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Bilder.
     */
    @Init(state = State.RESOURCES)
    public static void init() {
        charakter = loadImage("charakter", "charakter.png");

        redNPC = loadImage("redNPC", "redNPC.png");

        rasen = loadImage("rasen", "rasen.png");

        wand = loadImage("wand", "wand.png");

        baum = loadImage("baum", "baum.png");

        kiste = loadImage("kiste", "kiste.png");

        item42 = loadImage("item_42", "item_42.png");

        nuke = loadImage("nuke", "nuke.png");

        key = loadImage("key", "key.png");

        slot = loadImage("slot", "slot.png");

        slothighlight = loadImage("slot_highlight", "slot_highlight.png");

        doorNS = loadImage("door_NS", "door.png");

        doorEW = loadImageRotate90("door_EW", "door.png");

        doorOpenNS = loadImage("door_open_NS", "door_open.png");

        doorOpenEW = loadImageRotate90("door_open_EW", "door_open.png");

        finish = loadImage("finish", "finish.png");

        destroyedRaster = loadImage("destroyedRaster", "destroyed.png");

        fire = loadImageArray("fire_%d", "fire_%d.png", 4);

        healthpotion = loadImage("healthpotion", "healthpotion.png");

        button = loadImage("button", "button.png");

        buttonHighlight = loadImage("button_highlight", "button_highlight.png");

        buttonDisabled = loadImage("button_disabled", "button_disabled.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDraussen1.png", "automitoffenerTuerFahrerdraussen1", auto_do_NS, auto_do_SN, auto_do_EW, auto_do_WE);

        auto_do_NS = loadImage("auto_do_NS", "AutoMitOffenerTuerFahrerDraussen1.png");

        auto_do_EW = loadImageRotate90("auto_do_EW", "AutoMitOffenerTuerFahrerDraussen1.png");

        auto_do_SN = loadImageRotate180("auto_do_SN", "AutoMitOffenerTuerFahrerDraussen1.png");

        auto_do_WE = loadImageRotate270("auto_do_WE", "AutoMitOffenerTuerFahrerDraussen1.png");

        // registerAllDir("AutoMitFahrerDrinnen.png", "automitFahrerDrinnen", auto_dc_NS, auto_dc_SN, auto_dc_EW, auto_dc_WE);

        auto_dc_NS = loadImage("auto_dc_NS", "AutoMitFahrerDrinnen.png");

        auto_dc_EW = loadImageRotate90("auto_dc_EW", "AutoMitFahrerDrinnen.png");

        auto_dc_SN = loadImageRotate180("auto_dc_SN", "AutoMitFahrerDrinnen.png");

        auto_dc_WE = loadImageRotate270("auto_dc_WE", "AutoMitFahrerDrinnen.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDraussen2.png", "automitoffenerTuerFahrerdraussen2", auto_do2_NS, auto_do2_SN, auto_do2_EW, auto_do2_WE);

        auto_do2_NS = loadImage("auto_do2_NS", "AutoMitOffenerTuerFahrerDraussen2.png");

        auto_do2_EW = loadImageRotate90("auto_do2_EW", "AutoMitOffenerTuerFahrerDraussen2.png");

        auto_do2_SN = loadImageRotate180("auto_do2_SN", "AutoMitOffenerTuerFahrerDraussen2.png");

        auto_do2_WE = loadImageRotate270("auto_do2_WE", "AutoMitOffenerTuerFahrerDraussen2.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDraussen3.png", "automitoffenerTuerFahrerdraussen3", auto_do3_NS, auto_do3_SN, auto_do3_EW, auto_do3_WE);

        auto_do3_NS = loadImage("auto_do3_NS", "AutoMitOffenerTuerFahrerDraussen3.png");

        auto_do3_EW = loadImageRotate90("auto_do3_EW", "AutoMitOffenerTuerFahrerDraussen3.png");

        auto_do3_SN = loadImageRotate180("auto_do3_SN", "AutoMitOffenerTuerFahrerDraussen3.png");

        auto_do3_WE = loadImageRotate270("auto_do3_WE", "AutoMitOffenerTuerFahrerDraussen3.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDraussen4.png", "automitoffenerTuerFahrerdraussen4", auto_do4_NS, auto_do4_SN, auto_do4_EW, auto_do4_WE);

        auto_do4_NS = loadImage("auto_do4_NS", "AutoMitOffenerTuerFahrerDraussen4.png");

        auto_do4_EW = loadImageRotate90("auto_do4_EW", "AutoMitOffenerTuerFahrerDraussen4.png");

        auto_do4_SN = loadImageRotate180("auto_do4_SN", "AutoMitOffenerTuerFahrerDraussen4.png");

        auto_do4_WE = loadImageRotate270("auto_do4_WE", "AutoMitOffenerTuerFahrerDraussen4.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDrinnen.png", "automitoffenerTuerFahrerdrinnen", auto_di_NS, auto_di_SN, auto_di_EW, auto_di_WE);

        auto_di_NS = loadImage("auto_di_NS", "AutoMitOffenerTuerFahrerDrinnen.png");

        auto_di_EW = loadImageRotate90("auto_di_EW", "AutoMitOffenerTuerFahrerDrinnen.png");

        auto_di_SN = loadImageRotate180("auto_di_SN", "AutoMitOffenerTuerFahrerDrinnen.png");

        auto_di_WE = loadImageRotate270("auto_di_WE", "AutoMitOffenerTuerFahrerDrinnen.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDrinnen2.png", "automitoffenerTuerFahrerdrinnen2", auto_di2_NS, auto_di2_SN, auto_di2_EW, auto_di2_WE);

        auto_di2_NS = loadImage("auto_di2_NS", "AutoMitOffenerTuerFahrerDrinnen2.png");

        auto_di2_EW = loadImageRotate90("auto_di2_EW", "AutoMitOffenerTuerFahrerDrinnen2.png");

        auto_di2_SN = loadImageRotate180("auto_di2_SN", "AutoMitOffenerTuerFahrerDrinnen2.png");

        auto_di2_WE = loadImageRotate270("auto_di2_WE", "AutoMitOffenerTuerFahrerDrinnen2.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDrinnen3.png", "automitoffenerTuerFahrerdrinnen3", auto_di3_NS, auto_di3_SN, auto_di3_EW, auto_di3_WE);

        auto_di3_NS = loadImage("auto_di3_NS", "AutoMitOffenerTuerFahrerDrinnen3.png");

        auto_di3_EW = loadImageRotate90("auto_di3_EW", "AutoMitOffenerTuerFahrerDrinnen3.png");

        auto_di3_SN = loadImageRotate180("auto_di3_SN", "AutoMitOffenerTuerFahrerDrinnen3.png");

        auto_di3_WE = loadImageRotate270("auto_di3_WE", "AutoMitOffenerTuerFahrerDrinnen3.png");

        // registerAllDir("AutoMitOffenerTuerFahrerDrinnen4.png", "automitoffenerTuerFahrerdrinnen4", auto_di4_NS, auto_di4_SN, auto_di4_EW, auto_di4_WE);

        auto_di4_NS = loadImage("auto_di4_NS", "AutoMitOffenerTuerFahrerDrinnen4.png");

        auto_di4_EW = loadImageRotate90("auto_di4_EW", "AutoMitOffenerTuerFahrerDrinnen4.png");

        auto_di4_SN = loadImageRotate180("auto_di4_SN", "AutoMitOffenerTuerFahrerDrinnen4.png");

        auto_di4_WE = loadImageRotate270("auto_di4_WE", "AutoMitOffenerTuerFahrerDrinnen4.png");

        // registerAllDir("Auto.png", "Auto", auto_NS, auto_SN, auto_EW, auto_WE);

        auto_NS = loadImage("auto_NS", "Auto.png");

        auto_EW = loadImageRotate90("auto_EW", "Auto.png");

        auto_SN = loadImageRotate180("auto_SN", "Auto.png");

        auto_WE = loadImageRotate270("auto_WE", "Auto.png");

        // registerAllDir("AutoMitFahrerDrinnen.png", "automitFahrerDrinnen", auto_w_NS, auto_w_SN, auto_w_EW, auto_w_WE);

        auto_w_NS = loadImage("auto_w_NS", "AutoMitFahrerDrinnen.png");

        auto_w_EW = loadImageRotate90("auto_w_EW", "AutoMitFahrerDrinnen.png");

        auto_w_SN = loadImage("auto_w_SN", "AutoMitFahrerDrinnen1.2.png");

        auto_w_WE = loadImageRotate90("auto_w_WE", "AutoMitFahrerDrinnen1.2.png");

        // registerAllDir("AutoMitFahrerDrinnen2.png", "automitFahrerDrinnen2", auto_w2_NS, auto_w2_SN, auto_w2_EW, auto_w2_WE);

        auto_w2_NS = loadImage("auto_w2_NS", "AutoMitFahrerDrinnen2.png");

        auto_w2_EW = loadImageRotate90("auto_w2_EW", "AutoMitFahrerDrinnen2.png");

        auto2vV = loadImage("auto2vV", "Auto1.0.png");

        auto2vOnO = loadImage("auto2vOnO", "Auto1.1.png");

        auto2vOnW = loadImage("auto2vOnW", "Auto1.2.png");

        autogelbvO_WE = loadImage("autogelbvO_WE", "Auto3.0.png");

        autogelbvO_NS = loadImageRotate90("autogelbvO_NS", "Auto3.0.png");

        autogelbvO_EW = loadImage("autogelbvO_EW", "Auto3.1.png");

        autogelbvO_SN = loadImageRotate90("autogelbvO_SN", "Auto3.1.png");

        autopolizeivV = loadImage("autopolizeivV", "BullenAutoVonVorne.png");

        autopolizeivO_WE = loadImage("autopolizeivO_WE", "BullenAutoVonOben.png");
        autopolizeivO_NS = loadImageRotate90("autopolizeivO_NS", "BullenAutoVonOben.png");
        autopolizeivO_EW = loadImageRotate180("autopolizeivO_EW", "BullenAutoVonOben.png");
        autopolizeivO_SN = loadImageRotate270("autopolizeivO_SN", "BullenAutoVonOben.png");

        autopolizeivdS_WE = loadImage("autopolizeivdS_WE", "BullenAutoVonDerSeite.png");
        autopolizeivdS_EW = loadImage("autopolizeivdS_EW", "BullenAutoVonDerSeite1.2.png");

        baum1 = loadImage("Baum1", "Baum1.png");

        baum2 = loadImage("Baum2", "Baum2.png");

        baum3 = loadImage("Baum3", "Baum3.png");

        baum4 = loadImage("Baum4", "Baum4.png");

        baum5 = loadImage("Baum5", "Baum5.png");

        baum6 = loadImage("Baum6", "Baum6.png");

        baum7 = loadImage("Baum7", "Baum7.png");

        baum8 = loadImage("Baum8", "Baum8.png");

        baum9 = loadImage("Baum9", "Baum9.png");

        baum10 = loadImage("Baum10", "Baum10.png");

        baum11 = loadImage("Baum11", "Baum11.png");

        baum12 = loadImage("Baum12", "Baum12.png");

        baum13 = loadImage("Baum13", "Baum13.png");

        bodenrot = loadImage("bodenrot", "Boden12.png");

        bodenpink = loadImage("bodenpink", "Boden5.png");

        bodenschwarz = loadImage("bodenschwarz", "Boden11.png");

        bodendunkelgrau = loadImage("bodendunkelgrau", "Boden10.png");

        bodenkachelngross = loadImage("bodenkachelngross", "boden.png");

        bodenkachelnklein = loadImage("bodenkachelnklein", "boden2.png");

        bodenstein = loadImage("bodenstein", "Boden6.png");

        bodensteinkacheln = loadImage("bodensteinkacheln", "boden3.png");

        bodenblau = loadImage("bodenblau", "Boden9.png");

        bodenbeige = loadImage("bodenbeige", "Boden4.png");

        bodenorange = loadImage("bodenorange", "Boden8.png");

        busviolettvV = loadImage(" busviolettvV", "Bus1.2.png");

        busviolettvSnW = loadImage(" busviolettvSnW", "Bus1.1.png");

        busviolettvOnO = loadImage("busviolettvOvO", "Bus1.3.png");

        // bus1.4 ignoriert

        elster = loadImage("elster", ".png");

        faehre = loadImage("faehre", ".png");

        grammophonvdS = loadImage("grammophonvdS", ".png");

        grammophonvO_WE = loadImage("grammophonvO_WE", ".png");

        grammophonvO_NS = loadImageRotate90("grammophonvO_NS", ".png");

        grammophonvO_EW = loadImageRotate180("grammophonvO_EW", ".png");

        grammophonvO_SN = loadImageRotate270("grammophonvO_SN", ".png");

        gras = loadImage("gras", "Gras.png");

        haus1_NS = loadImage("haus1_NS", "Haus1.2.png");

        haus1_EW = loadImageRotate90("haus1_EW", "Haus1.2.png");

        haus1_SN = loadImageRotate180("haus1_SN", "Haus1.2.png");

        haus1_WE = loadImageRotate270("haus1_WE", "Haus1.2.png");

        haus2_NS = loadImage("haus2_NS", "Haus2.png");

        haus2_EW = loadImage("haus2_EW", "Haus2.png");

        haus2_SN = loadImage("haus2_SN", "Haus2.png");

        haus2_WE = loadImage("haus2_WE", "Haus2.png");

        haus3_NS = loadImage("haus3_NS", "Haus3.png");

        haus3_EW = loadImageRotate90("haus3_EW", "Haus3.png");

        haus3_SN = loadImageRotate180("haus3_SN", "Haus3.png");

        haus3_WE = loadImageRotate270("haus3_WE", "Haus3.png");

        haus4_WE = loadImage("haus4_WE", "Haus4.png");
        haus4_NS = loadImageRotate90("haus4_NS", "Haus4.png");
        haus4_EW = loadImageRotate180("haus4_EW", "Haus4.png");
        haus4_SN = loadImageRotate270("haus4_SN", "Haus4.png");

        haus5ecke_WN = loadImage("haus5ecke_WN", "Haus5.png");
        haus5ecke_NE = loadImageRotate90("haus5ecke_NE", "Haus5.png");
        haus5ecke_ES = loadImageRotate180("haus5ecke_ES", "Haus5.png");
        haus5ecke_SW = loadImageRotate270("haus5ecke_SW", "Haus5.png");

        haus6 = loadImage("haus6", "Haus6.png");

        haus7ecke_NE = loadImage("haus7_NE", "Haus7.2.png");
        haus7ecke_ES = loadImageRotate90("haus7_ES", "Haus7.2.png");
        haus7ecke_SW = loadImageRotate180("haus7_SW", "Haus7.2.png");
        haus7ecke_WN = loadImageRotate270("haus7_WN", "Haus7.2.png");

        // sieht scheisse aus!!! haus8 = loadImage("haus8", "Haus8.png");

        hauswelldach_WE = loadImage("hauswelldach_WE", ".png");
        hauswelldach_NS = loadImageRotate90("hauswelldach_NS", ".png");
        hauswelldach_EW = loadImageRotate180("hauswelldach_EW", ".png");
        hauswelldach_SN = loadImageRotate270("hauswelldach_SN", ".png");

        jojo = loadImage("jojo", ".png");

        jojokaputt = loadImage("jojokaputt", ".png");

        kaugummigelb = loadImage("kaugummigelb", ".png");

        kaugummirot = loadImage("kaugummirot", ".png");// TODO: Pic missing

        klavier_WE = loadImage("klavier_WE", "Keyboard.png");

        klavier_NS = loadImageRotate90("klavier_NS", "Keyboard.png");

        klavier_EW = loadImageRotate180("klavier_EW", "Keyboard.png");

        klavier_SN = loadImageRotate270("klavier_SN", "Keyboard.png");

        kreuzung = loadImage("kreuzung", "Kreuzung.png");

        kreuzungT_EW = loadImage("kreuzungT_EW", "Kreuzung2.png");

        kreuzungT_SN = loadImageRotate90("kreuzungT_SN", "Kreuzung2.png");

        kreuzungT_WE = loadImageRotate180("kreuzungT_WE", "Kreuzung2.png");

        kreuzungT_NS = loadImageRotate270("kreuzungT_NS", "Kreuzung2.png");

        ladyvOrAho_NS = loadImage("ladyvOrAho_NS", "LadyVonOben.png");

        ladyvOrAho_EW = loadImageRotate90("ladyvOrAho_EW", "LadyVonOben.png");

        ladyvOrAho_SN = loadImage("ladyvOrAho_SN", "LadyVonOben1.2.png");

        ladyvOrAho_WE = loadImageRotate90("ladyvOrAho_WE", "LadyVonOben1.2.png");

        ladyvO_NS = loadImage("ladyvO_NS", "LadyVonOben2.png");

        ladyvO_EW = loadImageRotate90("ladyvO_EW", "LadyVonOben2.png");

        ladyvO_SN = loadImage("ladyvO_SN", "LadyVonOben2.2.png");

        ladyvO_WE = loadImageRotate90("ladyvO_WE", "LadyVonOben2.2.png");

        ladyvOrAo_WE = loadImage("ladyvOrAo_WE", "LadyVonOben3.png");

        ladyvOrAo_NS = loadImageRotate90("ladyvOrAo_NS", "LadyVonOben3.png");

        ladyvOrAo_EW = loadImage("ladyvOrAo_EW", "LadyVonOben3.2.png");

        ladyvOrAo_SN = loadImageRotate90("ladyvOrAo_SN", "LadyVonOben3.2.png");

        ladyvObAho_NS = loadImage("ladyvonObAho_NS", "LadyVonOben4.png");

        ladyvObAho_EW = loadImageRotate90("ladyvonObAho_EW", "LadyVonOben4.png");

        ladyvObAho_SN = loadImage("ladyvonObAho_SN", "LadyVonOben4.2.png");

        ladyvObAho_WE = loadImageRotate90("ladyvonObAho_WE", "LadyVonOben4.2.png");

        ladyvVbAho = loadImage("ladyvbAho", "LadyVonVorne.png");

        ladyvVbAho2 = loadImage("ladyvVbAho2", "LadyVonVorne2.png");

        ladyvVbAo = loadImage("ladyvVbAo", "LadyVonVorne3.png");

        ladyvVlAqurAo = loadImage("ladyvVlAqurAo", "LadyVonVorne4.png");

        ladyvVrS = loadImage("ladyvVrS", "LadyVonVorne5.png");

        lasterschwarzvV = loadImage("lasterschwarzvV", "Lastwagen1.0.png");

        lasterschwarzvO_NS = loadImage("lasterschwarzvO_NS", "Lastwagen1.6.png");

        lasterschwarzvO_EW = loadImageRotate90("lasterschwarzvO_EW", "Lastwagen1.6.png");

        lasterschwarzvO_SN = loadImage("lasterschwarzvO_SN", "Lastwagen1.6.2.png");

        lasterschwarzvO_WE = loadImageRotate90("lasterschwarzvO_WE", "Lastwagen1.6.2.png");

        lasterschwarzmitAnhaengervO_EW = loadImage("lasterschwarzmitAnhaengervO_EW", "Lastwagen1.4.png");

        lasterschwarzmitAnhaengervO_SN = loadImageRotate90("lasterschwarzmitAnhaengervO_SN", "Lastwagen1.4.png");

        lasterschwarzmitAnhaengervO_WE = loadImage("lasterschwarzmitAnhaengervO_WE", "Lastwagen1.4.2.png");

        lasterschwarzmitAnhaengervO_NS = loadImageRotate90("lasterschwarzmitAnhaengervO_NS", "Lastwagen1.4.2.png");

        lasterweissvO_NS = loadImage("lasterweissvO_NS", "FliegendeZahnpastaTube1.0.png");

        lasterweissvO_EW = loadImageRotate90("lasterweissvO_EW", "FliegendeZahnpastaTube1.0.png");

        lasterweissvV = loadImage("lasterweissvV", "FliegendeZahnpastaTube1.2.png");

        lordvdS = loadImage("lordvonderSeite", "LordVonDerSeite.png");

        lordS_SN = loadImage("lordS_SN", "LordSitzend.png");

        lordS_WE = loadImageRotate90("lordS_WE", "LordSitzend.png");

        lordS_NS = loadImageRotate180("lordS_NS", "LordSitzend.png");

        lordS_EW = loadImageRotate270("lordS_EW", "LordSitzend.png");

        lordH = loadImage("lordH", "LordVonHinten.png");

        // registerAllDir("LordVonOben.png", "LordvO", LordvO_NS, LordvO_SN, LordvO_EW,LordvO_WE);

        lordvO_NS = loadImage("lordvO_NS", "LordVonDerSeite2.png");

        lordvO_EW = loadImageRotate90("lordvO_EW", "LordVonDerSeite2.png");

        lordvO_SN = loadImageRotate180("lordvO_SN", "LordVonDerSeite2.png");

        lordvO_WE = loadImageRotate270("lordvO_WE", "LordVonDerSeite2.png");

        messer = loadImage("messer", ".png");// TODO: Pic missing

        muelltonnezuvO = loadImage("muelltonnezuvO", ".png");// TODO: Pic missing

        muelltonneoffenvO = loadImage("muelltonneoffenvO", ".png");// TODO: Pic missing

        nestvO = loadImage("nestvO", ".png");// TODO: Pic missing

        nestmitEiernvO = loadImage("nestmitEiernvO", ".png");// TODO: Pic missing

        netz = loadImage("netz", ".png");// TODO: Pic missing

        netzfaden = loadImage("netzfaden", ".png");// TODO: Pic missing

        ohrringe_EW = loadImage("ohrringe_EW", "Ohrringe.png");

        ohrringe_SN = loadImageRotate90("ohrringe_SN", "Ohrringe.png");

        ohrringe_WE = loadImageRotate180("ohrringe_WE", "Ohrringe.png");

        ohrringe_NS = loadImageRotate270("ohrringe_NS", "Ohrringe.png");

        pflaster = loadImage("pflaster", "PflasterStein.png");

        pflaster2 = loadImage("pflaster2", "PflasterSteine2.png");

        rasen2 = loadImage("rasen2", "Wiese.png");

        rasen3 = loadImage("rasen3", "rasen3.png");

        schranke_NS = loadImage("schranke_NS", "Schranke.png");
        schranke_EW = loadImageRotate90("schranke_EW", "Schranke.png");
        schranke_SN = loadImageRotate180("schranke_SN", "Schranke.png");
        schranke_WE = loadImageRotate270("schranke_WE", "Schranke.png");

        schranke2_NS = loadImage("schranke2_NS", "Schranke4.png");
        schranke2_EW = loadImageRotate90("schranke2_EW", "Schranke4.png");
        schranke2_SN = loadImageRotate180("schranke2_SN", "Schranke4.png");
        schranke2_WE = loadImageRotate270("schranke2_WE", "Schranke4.png");

        schranke3_NS = loadImage("schranke3_NS", "Schranke2(2).png");
        schranke3_EW = loadImageRotate90("schranke3_EW", "Schranke2(2).png");
        schranke3_SN = loadImageRotate180("schranke3_SN", "Schranke2(2).png");
        schranke3_WE = loadImageRotate270("schranke3_WE", "Schranke2(2).png");

        schranke4_NS = loadImage("schranke4_NS", "Schranke1(2).png");
        schranke4_EW = loadImageRotate90("schranke4_EW", "Schranke1(2).png");
        schranke4_SN = loadImageRotate180("schranke4_SN", "Schranke1(2).png");
        schranke4_WE = loadImageRotate270("schranke4_WE", "Schranke1(2).png");

        schranke5_NS = loadImage("schranke5_NS", "Schranke3(1).png");
        schranke5_EW = loadImageRotate90("schranke5_EW", "Schranke3(1).png");
        schranke5_SN = loadImageRotate180("schranke5_SN", "Schranke3(1).png");
        schranke5_WE = loadImageRotate270("schranke5_WE", "Schranke3(1).png");

        schranke6_NS = loadImage("schranke6_NS", "Schranke5.png");
        schranke6_EW = loadImageRotate90("schranke6_EW", "Schranke5.png");
        schranke6_SN = loadImageRotate180("schranke6_SN", "Schranke5.png");
        schranke6_WE = loadImageRotate270("schranke6_WE", "Schranke5.png");

        // schranke7_NS = loadImage("schranke7_NS", ".png");
        // schranke7_EW = loadImageRotate90("schranke7_EW", "Schranke.png");
        // schranke7_SN = loadImageRotate180("schranke7_SN", "Schranke.png");
        // schranke7_WE = loadImageRotate270("schranke7_WE", "Schranke.png");

        sesselschwarz_SN = loadImage("sesselschwarz_SN", "Stuhl5.png");
        sesselschwarz_WE = loadImageRotate90("sesselschwarz_WE", "Stuhl5.png");
        sesselschwarz_NS = loadImageRotate180("sesselschwarz_NS", "Stuhl5.png");
        sesselschwarz_EW = loadImageRotate270("sesselschwarz_EW", "Stuhl5.png");

        sesselschwarzbeige_NS = loadImage("sesselschwarzbeige_NS", "Stuhl7.png");
        sesselschwarzbeige_EW = loadImageRotate90("sesselschwarzbeige_EW", "Stuhl7.png");
        sesselschwarzbeige_SN = loadImageRotate180("sesselschwarzbeige_SN", "Stuhl7.png");
        sesselschwarzbeige_WE = loadImageRotate270("sesselschwarzbeige_WE", "Stuhl7.png");

        sesselbeigefell_NS = loadImage("sesselbeigefell_NS", "Stuhl6.png");
        sesselbeigefell_EW = loadImageRotate90("sesselbeigefell_EW", "Stuhl6.png");
        sesselbeigefell_SN = loadImageRotate180("sesselbeigefell_SN", "Stuhl6.png");
        sesselbeigefell_WE = loadImageRotate270("sesselbeigefell_WE", "Stuhl6.png");

        steinschleuder = loadImage("steinschleuder", ".png");// TODO: Pic missing

        strasse_EW = loadImage("strasse_EW", "Strasse.png");

        strasse_NS = loadImageRotate90("strasse_NS", "Strasse.png");

        stuhlbraunundholz_EW = loadImage("stuhlbraunundholz_EW", "Stuhl3.png");
        stuhlbraunundholz_SN = loadImageRotate90("stuhlbraunundholz_SN", "Stuhl3.png");
        stuhlbraunundholz_WE = loadImageRotate180("stuhlbraunundholz_WE", "Stuhl3.png");
        stuhlbraunundholz_NS = loadImageRotate270("stuhlbraunundholz_NS", "Stuhl3.png");

        stuhltriptrap_WE = loadImage("stuhltriptrap_WE", "Stuhl4.png");
        stuhltriptrap_NS = loadImageRotate90("stuhltriptrap_NS", "Stuhl4.png");
        stuhltriptrap_EW = loadImageRotate180("stuhltriptrap_EW", "Stuhl4.png");
        stuhltriptrap_SN = loadImageRotate270("stuhltriptrap_SN", "Stuhl4.png");

        stuhlbuero_WE = loadImage("stuhlbuero_WE", "Stuhl1.png");
        stuhlbuero_NS = loadImageRotate90("stuhlbuero_NS", "Stuhl1.png");
        stuhlbuero_EW = loadImageRotate180("stuhlbuero_EW", "Stuhl1.png");
        stuhlbuero_SN = loadImageRotate270("stuhlbuero_SN", "Stuhl1.png");

        stuhlholz_NS = loadImage("stuhlholz_NS", "Stuhl2.png");
        stuhlholz_EW = loadImageRotate90("stuhlholz_EW", "Stuhl2.png");
        stuhlholz_SN = loadImageRotate180("stuhlholz_SN", "Stuhl2.png");
        stuhlholz_WE = loadImageRotate270("stuhlholz_WE", "Stuhl2.png");

        teddyvV = loadImage("teddyvV", "Teddy6.png");

        teddyvO_NS = loadImage("teddyvO_NS", "Teddy7.png");

        teddyvO_EW = loadImageRotate90("teddyvO_EW", "Teddy7.png");

        teddyvO_SN = loadImageRotate180("teddyvO_SN", "Teddy7.png");

        teddyvO_WE = loadImageRotate270("teddyvO_WE", "Teddy7.png");

        teddysvO_NS = loadImage("teddysvO_NS", "Teddy4.png");

        teddysvO_EW = loadImageRotate90("teddysvO_EW", "Teddy4.png");

        teddysvO_SN = loadImageRotate180("teddysv_SN", "Teddy4.png");

        teddysvO_WE = loadImageRotate270("teddysvO_WE", "Teddy4.png");

        teddysvV = loadImage("teddysvV", "Teddy1.png");

        teddymOhrringenvV = loadImage("teddymOhrringenvV", "Teddy3.png");

        typvObAo_EW = loadImage("typvObAo_EW", "Typ1.2.png");

        typvObAo_SN = loadImageRotate90("typvObAo_SN", "Typ1.2.png");

        typvObAo_WE = loadImage("typvObAo_WE", "Typ1.2.png");

        typvObAo_NS = loadImageRotate90("typvObAo_NS", "Typ1.2.png");

        typvO_WE = loadImage("typvO_WE", "Typ1.3.png");

        typvO_NS = loadImageRotate90("typvO_NS", "Typ1.3.png");

        typvO_EW = loadImage("typvO_EW", "Typ1.3.png");

        typvO_SN = loadImageRotate90("typvO_SN", "Typ1.3.png");

        typvO2_WE = loadImage("typvO2_WE", "Typ1.png");

        typvO2_NS = loadImageRotate90("typvO2_NS", "Typ1.png");

        typvO2_EW = loadImage("typvO2_EW", "Typ1.0.2.png");

        typvO2_SN = loadImageRotate90("typvO2_SN", "Typ1.0.2.png");

        typvOrAho_WE = loadImage("typvOrAho_WE", "Typ1.4.png");

        typvOrAho_NS = loadImageRotate90("typvOrAho_NS", "Typ1.4.png");

        typvOrAo_WE = loadImage("typvOrAo_WE", "Typ1.5.png");

        typvOrAo_NS = loadImageRotate90("typvOrAo_NS", "Typ1.5.png");

        typvOrAo_EW = loadImage("typvOrAo_EW", "Typ1.5.2.png");

        typvOrAo_SN = loadImageRotate90("typvOrAo_SN", "Typ1.5.2.png");

        wasser = loadImage("wasser", "Wasser.png");

        wasserdunkel = loadImage("wasserdunkel", ".png");// TODO: Pic missing

        weibvVbAo = loadImage("weibvVbAo", "Weib1.0.png");

        weibvOlAo_NS = loadImage("weibvOlAo_NS", "Weib1.1.png");

        weibvOlAo_EW = loadImageRotate90("weibvOlAo_EW", "Weib1.1.png");

        weibvOlAo_SN = loadImage("weibvOlAo_SN", "Weib1.1.2.png");

        weibvOlAo_WE = loadImageRotate90("weibvOlAo_WE", "Weib1.1.2.png");

        weibvVrAho = loadImage("weibvVrAho", "Weib1.2.png");

        weibvO = loadImage("weibvO", "Weib1.3.png");

        weibvO_SO = loadImageRotate90("weibvO_SO", "Weib1.3.png");

        weibvO_WE = loadImage("weibvO_WE", "Weib1.3.2.png");

        weibvO_EW = loadImageRotate90("weibvO_EW", "Weib1.3.2.png");

        Field[] fields = Images.class.getDeclaredFields();

        for (Field f : fields) {
            if (f != null && f.getType() != HashMap.class) {
                if (f.getType().isArray()) {
                    try {
                        BufferedImage[] images = (BufferedImage[]) f.get(null);
                        if (images == null) {
                            Logger.warn(f.getName());
                        } else {
                            for (BufferedImage i : images) {
                                if (i == null) {
                                    Logger.warn(f.getName());
                                }
                            }
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                } else {
                    try {
                        if (f.get(null) == null) {
                            Logger.warn(f.getName());
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * Laedt ein Bild.
     *
     * @param name
     *            Name des Bildes
     * @return BufferedImage
     */
    public static BufferedImage loadImage(String name, String fileName) {

        String path = "resources/images/" + fileName;

        BufferedImage img = null;
        try {
            img = ImageIO.read(Main.class.getResource(path));
            Logger.info("Successfully loaded image '" + fileName + "'");
        } catch (Throwable t1) {
            try (InputStream stream = Main.class.getResourceAsStream(path)) {
                img = ImageIO.read(stream);
                Logger.info("Successfully loaded image '" + fileName + "'");
            } catch (Throwable t2) {
                Logger.logThrowable("Unable to load image '" + fileName + "'", t2);
                // throw new IllegalArgumentException("Unable to load image " + fileName);
            }
        }
        if (img != null) {
            registerImage(name, img);
        }
        return img;
    }

    /**
     * Laedt ein Array aus Bildern.
     *
     * @param name
     *            Name des Bildes
     * @param length
     *            Anzahl der Bilder
     * @return BufferedImage[]
     */
    public static BufferedImage[] loadImageArray(String name, String fileName, int length) {
        BufferedImage[] images = new BufferedImage[length];
        for (int i = 0; i < images.length; i++) {
            images[i] = loadImage(String.format(name, i), String.format(fileName, i));
        }
        return images;
    }

    /**
     * Laedt ein um 180° gedrehtes Bild.
     *
     * @param name
     *            Name des Bildes.
     * @return BufferedImage
     */
    public static BufferedImage loadImageRotate180(String name, String fileName) {
        BufferedImage img = loadImage(name, fileName);
        if (img != null) {
            return rotate(img, Math.toRadians(180));
        }
        return null;
    }

    /**
     * Laedt ein um 270° gedrehtes Bild.
     *
     * @param name
     *            Name des Bildes.
     * @return BufferedImage
     */
    public static BufferedImage loadImageRotate270(String name, String fileName) {
        BufferedImage img = loadImage(name, fileName);
        if (img != null) {
            return rotate(img, Math.toRadians(270));
        }
        return null;
    }

    /**
     * Laedt ein um 90° gedrehtes Bild.
     *
     * @param name
     *            Name des Bildes.
     * @return BufferedImage
     */
    public static BufferedImage loadImageRotate90(String name, String fileName) {
        BufferedImage img = loadImage(name, fileName);
        if (img != null) {
            return rotate(img, Math.toRadians(90));
        }
        return null;
    }

    /**
     * Rotiert ein gegebenes Bild um den gegebenen Winkel.
     *
     * @param image
     *            Bild
     * @param angle
     *            Winkel im Bogenmass
     * @return gedrehtes Bild
     */
    public static BufferedImage rotate(BufferedImage img, double angle) {
        double sin = Math.abs(Math.sin(angle));
        double cos = Math.abs(Math.cos(angle));
        int w = img.getWidth();
        int h = img.getHeight();
        int newW = MathUtil.floor(w * cos + h * sin);
        int newH = MathUtil.floor(h * cos + w * sin);
        BufferedImage result = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = result.createGraphics();
        g.translate(MathUtil.floorDiv(newW - w, 2), MathUtil.floorDiv(newH - h, 2));
        g.rotate(angle, MathUtil.floorDiv(w, 2), MathUtil.floorDiv(h, 2));
        g.drawRenderedImage(img, null);
        g.dispose();
        return result;
    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param image
     *            Bild
     */
    private static void registerImage(String name, BufferedImage image) {
        if (image != null && !MAPPINGS.containsKey(name) && !BACK_MAPPINGS.containsKey(image)) {
            MAPPINGS.put(name, image);
            BACK_MAPPINGS.put(image, name);
        } else {
            Logger.warn("Unable to register image", name, image);
            throw new IllegalArgumentException("Unable to register image " + name + " (" + image + ")");
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Images() {
    }
}

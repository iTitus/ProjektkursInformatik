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
import projektkurs.util.Pair;

/**
 * Alle Bilder.
 */
public final class Images {

    /**
     *
     */
    public static BufferedImage auto_dc_NS, auto_dc_SN, auto_dc_EW, auto_dc_WE;
    /**
     *
     */
    public static BufferedImage auto_di_NS, auto_di_SN, auto_di_EW, auto_di_WE;
    /**
     *
     */
    public static BufferedImage auto_di2_NS, auto_di2_SN, auto_di2_EW, auto_di2_WE;
    /**
     *
     */
    public static BufferedImage auto_di3_NS, auto_di3_SN, auto_di3_EW, auto_di3_WE;
    /**
     *
     */
    public static BufferedImage auto_di4_NS, auto_di4_SN, auto_di4_EW, auto_di4_WE;
    /**
     * Autobild
     */
    public static BufferedImage auto_do_NS, auto_do_SN, auto_do_EW, auto_do_WE;
    /**
     *
     */
    public static BufferedImage auto_do2_NS, auto_do2_SN, auto_do2_EW, auto_do2_WE;
    /**
     *
     */
    public static BufferedImage auto_do3_NS, auto_do3_SN, auto_do3_EW, auto_do3_WE;
    /**
     *
     */
    public static BufferedImage auto_do4_NS, auto_do4_SN, auto_do4_EW, auto_do4_WE;
    /**
     *
     */
    public static BufferedImage auto_NS, auto_SN, auto_EW, auto_WE;
    /**
     *
     */
    public static BufferedImage auto_w_NS, auto_w_SN, auto_w_EW, auto_w_WE;
    /**
     *
     */
    public static BufferedImage auto_w2_NS, auto_w2_SN, auto_w2_EW, auto_w2_WE;
    public static BufferedImage auto2vO_EW;
    public static BufferedImage auto2vO_NS;
    public static BufferedImage auto2vO_SN;
    public static BufferedImage auto2vO_WE;
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
    public static BufferedImage autogelbvO_WE;
    /**
    *
    */
    public static BufferedImage autogelbvOnO;
    /**
    *
    */
    public static BufferedImage autogelbvOnW;
    /**
     * Zurück-Mappings.
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
     * Zerstörtes-Land-Bild.
     */
    public static BufferedImage destroyedRaster;
    /**
     * Geschlossene Tür in Ost-West Ausrichtung.
     */
    public static BufferedImage doorEW;
    /**
     * Geschlossene Tür in Nord-Süd-Ausrichtung.
     */
    public static BufferedImage doorNS;
    /**
     * Offene Tür in Ost-West Ausrichtung.
     */
    public static BufferedImage doorOpenEW;
    /**
     * Offene Tür in Nord-Süd-Ausrichtung.
     */
    public static BufferedImage doorOpenNS;
    /**
     * Zielbild.
     */
    public static BufferedImage finish;
    /**
     * Feueranimationsbilder.
     */
    public static BufferedImage[] fire;
    /**
     *
     */
    public static BufferedImage gras;
    /**
    *
    */
    public static BufferedImage haus1;
    /**
    *
    */
    public static BufferedImage haus2;
    /**
    *
    */
    public static BufferedImage haus3;
    /**
    *
    */
    public static BufferedImage haus4;
    /**
    *
    */
    public static BufferedImage haus5;
    /**
    *
    */
    public static BufferedImage haus6;
    /**
    *
    */
    public static BufferedImage haus7;
    /**
     *
     */
    public static BufferedImage haus8;
    /**
     * Gesundheitstrank.
     */
    public static BufferedImage healthpotion;
    /**
     * Item_42-Bild.
     */
    public static BufferedImage item42;
    /**
     * Schlüsselbild.
     */
    public static BufferedImage key;
    /**
     * Kistenbild.
     */
    public static BufferedImage kiste;
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
    public static BufferedImage lasterschwarzmitAnhängervO_EW;
    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhängervO_NS;
    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhängervO_SN;
    /**
    *
    */
    public static BufferedImage lasterschwarzmitAnhängervO_WE;
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
    public static BufferedImage lasterweißvO_EW;

    /**
    *
    */
    public static BufferedImage lasterweißvO_NS;

    /**
    *
    */
    public static BufferedImage lasterweißvO_SN;

    /**
    *
    */
    public static BufferedImage lasterweißvO_WE;

    /**
    *
    */
    public static BufferedImage lasterweißvV;

    /**
     *
     */
    public static BufferedImage lordH;

    /**
     *
     */
    public static BufferedImage lordS;

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
    /**
     * Atombombenbild.
     */
    public static BufferedImage nuke;

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

    /**
     *
     */
    public static BufferedImage slot;

    /**
     * Hervorgehobenes Inventarslotbild.
     */
    public static BufferedImage slothighlight;

    public static BufferedImage strasse_EW;

    public static BufferedImage strasse_NS;

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
     * Das Pair, das alle Images enth�lt.
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

        // registerAllDir("AutoMitOffenerTürFahrerDraussen1.png", "automitoffenerTürFahrerdraussen1", auto_do_NS, auto_do_SN, auto_do_EW, auto_do_WE);

        auto_do_NS = loadImage("auto_do_NS", "AutoMitOffenerTürFahrerDraussen1.png");

        auto_do_EW = loadImageRotate90("auto_do_EW", "AutoMitOffenerTürFahrerDraussen1.png");

        // registerAllDir("AutoMitFahrerDrinnen.png", "automitFahrerDrinnen", auto_dc_NS, auto_dc_SN, auto_dc_EW, auto_dc_WE);

        auto_dc_NS = loadImage("auto_dc_NS", "AutoMitFahrerDrinnen.png");

        auto_dc_EW = loadImageRotate90("auto_dc_EW", "AutoMitFahrerDrinnen.png");

        auto_dc_SN = loadImageRotate180("auto_dc_SN", "AutoMitFahrerDrinnen.png");

        auto_dc_WE = loadImageRotate270("auto_dc_WE", "AutoMitFahrerDrinnen.png");

        // registerAllDir("AutoMitOffenerTürFahrerDraussen2.png", "automitoffenerTürFahrerdraussen2", auto_do2_NS, auto_do2_SN, auto_do2_EW, auto_do2_WE);

        auto_do2_NS = loadImage("auto_do2_NS", "AutoMitOffenerTürFahrerDraussen2.png");

        auto_do2_EW = loadImageRotate90("auto_do2_EW", "AutoMitOffenerTürFahrerDraussen2.png");

        // registerAllDir("AutoMitOffenerTürFahrerDraussen3.png", "automitoffenerTürFahrerdraussen3", auto_do3_NS, auto_do3_SN, auto_do3_EW, auto_do3_WE);

        auto_do3_NS = loadImage("auto_do3_NS", "AutoMitOffenerTürFahrerDraussen3.png");

        auto_do3_EW = loadImageRotate90("auto_do3_EW", "AutoMitOffenerTürFahrerDraussen3.png");

        // registerAllDir("AutoMitOffenerTürFahrerDraussen4.png", "automitoffenerTürFahrerdraussen4", auto_do4_NS, auto_do4_SN, auto_do4_EW, auto_do4_WE);

        auto_do4_NS = loadImage("auto_do4_NS", "AutoMitOffenerTürFahrerDraussen4.png");

        auto_do4_EW = loadImageRotate90("auto_do4_EW", "AutoMitOffenerTürFahrerDraussen4.png");

        // registerAllDir("AutoMitOffenerTürFahrerDrinnen.png", "automitoffenerTürFahrerdrinnen", auto_di_NS, auto_di_SN, auto_di_EW, auto_di_WE);

        auto_di_NS = loadImage("auto_di_NS", "AutoMitOffenerTürFahrerDrinnen.png");

        auto_di_EW = loadImageRotate90("auto_di_EW", "AutoMitOffenerTürFahrerDrinnen.png");

        // registerAllDir("AutoMitOffenerTürFahrerDrinnen2.png", "automitoffenerTürFahrerdrinnen2", auto_di2_NS, auto_di2_SN, auto_di2_EW, auto_di2_WE);

        auto_di2_NS = loadImage("auto_di2_NS", "AutoMitOffenerTürFahrerDrinnen2.png");

        auto_di2_EW = loadImageRotate90("auto_di2_EW", "AutoMitOffenerTürFahrerDrinnen2.png");

        // registerAllDir("AutoMitOffenerTürFahrerDrinnen3.png", "automitoffenerTürFahrerdrinnen3", auto_di3_NS, auto_di3_SN, auto_di3_EW, auto_di3_WE);

        auto_di3_NS = loadImage("auto_di3_NS", "AutoMitOffenerTürFahrerDrinnen3.png");

        auto_di3_EW = loadImageRotate90("auto_di3_EW", "AutoMitOffenerTürFahrerDrinnen3.png");

        // registerAllDir("AutoMitOffenerTürFahrerDrinnen4.png", "automitoffenerTürFahrerdrinnen4", auto_di4_NS, auto_di4_SN, auto_di4_EW, auto_di4_WE);

        auto_di4_NS = loadImage("auto_di4_NS", "AutoMitOffenerTürFahrerDrinnen4.png");

        auto_di4_EW = loadImageRotate90("auto_di4_EW", "AutoMitOffenerTürFahrerDrinnen4.png");

        // registerAllDir("Auto.png", "Auto", auto_NS, auto_SN, auto_EW, auto_WE);

        auto_NS = loadImage("auto_NS", "Auto.png");

        auto_EW = loadImageRotate90("auto_EW", "Auto.png");

        // registerAllDir("AutoMitFahrerDrinnen.png", "automitFahrerDrinnen", auto_w_NS, auto_w_SN, auto_w_EW, auto_w_WE);

        auto_w_NS = loadImage("auto_w_NS", "AutoMitFahrerDrinnen.png");

        auto_w_EW = loadImageRotate90("auto_w_EW", "AutoMitFahrerDrinnen.png");

        auto_w_SN = loadImage("auto_w_SN", "AutoMitFahrerDrinnen1.2.png");

        auto_w_WE = loadImageRotate90("auto_w_WE", "AutoMitFahrerDrinnen1.2.png");

        // registerAllDir("AutoMitFahrerDrinnen2.png", "automitFahrerDrinnen2", auto_w2_NS, auto_w2_SN, auto_w2_EW, auto_w2_WE);

        auto_w2_NS = loadImage("auto_w2_NS", "AutoMitFahrerDrinnen2.png");

        auto_w2_EW = loadImageRotate90("auto_w2_EW", "AutoMitFahrerDrinnen2.png");

        auto2vV = loadImage("auto2vV", "Auto1.0.png");

        auto2vO_WE = loadImage("auto2vO_WE", "Auto1.1.png");

        auto2vO_NS = loadImageRotate90("auto2vO_NS", "Auto1.1.png");

        auto2vO_EW = loadImage("auto2vO_EW", "Auto1.2.png");

        auto2vO_SN = loadImage("auto2vO_SN", "Auto1.2.png");

        autogelbvO_WE = loadImage("autogelbvO_WE", "Auto3.0.png");

        autogelbvO_EW = loadImage("autogelbvO_EW", "Auto3.1.png");

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

        busviolettvV = loadImage(" busviolettvV", "Bus1.2.png");

        busviolettvSnW = loadImage(" busviolettvSnW", "Bus1.1.png");

        busviolettvOnO = loadImage("busviolettvOvO", "Bus1.3.png");

        // bus1.4 ignoriert

        haus1 = loadImage("haus1", "Haus.png");

        haus2 = loadImage("haus2", "Haus2.png");

        haus3 = loadImage("haus3", "Haus3.png");

        haus4 = loadImage("haus4", "Haus4.png");

        haus5 = loadImage("haus5", "Haus5.png");

        haus6 = loadImage("haus6", "Haus6.png");

        haus7 = loadImage("haus7", "Haus7.png");

        haus8 = loadImage("haus8", "Haus8.png");

        kreuzung = loadImage("kreuzung", "Kreuzung.png");

        kreuzungT_EW = loadImage("kreuzungT_EW", "Kreuzung2.png");

        kreuzungT_SN = loadImageRotate90("kreuzungT_SN", "Kreuzung2.png");

        kreuzungT_WE = loadImage("kreuzungT_WE", "Kreuzung2.2.png");

        kreuzungT_NS = loadImageRotate90("kreuzungT_NS", "Kreuzung2.2.png");

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

        lasterschwarzmitAnhängervO_EW = loadImage("lasterschwarzmitAnhängervO_EW", "Lastwagen1.4.png");

        lasterschwarzmitAnhängervO_SN = loadImageRotate90("lasterschwarzmitAnhängervO_SN", "Lastwagen1.4.png");

        lasterschwarzmitAnhängervO_WE = loadImage("lasterschwarzmitAnhängervO_WE", "Lastwagen1.4.2.png");

        lasterschwarzmitAnhängervO_NS = loadImageRotate90("lasterschwarzmitAnhängervO_NS", "Lastwagen1.4.2.png");

        lasterweißvO_NS = loadImage("lasterweißvO_NS", "FliegendeZahnpastaTube1.0.png");

        lasterweißvO_EW = loadImageRotate90("lasterweißvO_EW", "FliegendeZahnpastaTube1.0.png");

        lasterweißvV = loadImage("lasterweißvV", "FliegendeZahnpastaTube1.2.png");

        lordvdS = loadImage("lordvonderSeite", "LordVonDerSeite.png");

        lordvdS2 = loadImage("lordvonderSeite2", "LordVonDerSeite2.png");

        lordS = loadImage("lordS", "LordSitzend.png");

        lordH = loadImage("lordH", "LordVonHinten.png");

        // registerAllDir("LordVonOben.png", "LordvO", LordvO_NS, LordvO_SN, LordvO_EW,LordvO_WE);

        lordvO_NS = loadImage("lordvO_NS", "LordVonOben1.png");

        lordvO_EW = loadImageRotate90("lordvO_EW", "LordVonOben1.png");

        lordvO_SN = loadImage("lordvO_SN", "LordVonOben1.2.png");

        lordvO_WE = loadImageRotate90("lordvO_WE", "LordVonOben1.2.png");

        gras = loadImage("gras", "Gras.png");

        pflaster = loadImage("pflaster", "PflasterStein.png");

        pflaster2 = loadImage("pflaster2", "PflasterSteine2.png");

        rasen2 = loadImage("rasen2", "Wiese.png");

        rasen3 = loadImage("rasen3", "rasen3.png");

        schranke_NS = loadImage("schranke_NS", "Schranke.png");

        schranke_EW = loadImageRotate90("schranke_EW", "Schranke.png");

        schranke_SN = loadImage("schranke_SN", "Schranke1.2.png");

        schranke_WE = loadImage("schranke_WE", "Schranke1.2.png");

        strasse_EW = loadImage("strasse_EW", "Strasse.png");

        strasse_NS = loadImageRotate90("strasse_NS", "Strasse.png");

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

        weibvVbAo = loadImage("weibvVbAo", "Weib1.0.png");

        wasser = loadImage("wasser", "Wasser.png");

        weibvOlAo_NS = loadImage("weibvOlAo_NS", "Weib1.1.png");

        weibvOlAo_EW = loadImageRotate90("weibvOlAo_EW", "Weib1.1.png");

        weibvOlAo_SN = loadImage("weibvOlAo_SN", "Weib1.1.2.png");

        weibvOlAo_WE = loadImageRotate90("weibvOlAo_WE", "Weib1.1.2.png");

        weibvVrAho = loadImage("weibvVrAho", "Weib1.2.png");

        weibvO = loadImage("weibvO", "Weib1.3.png");

        weibvO_SO = loadImageRotate90("weibvO_SO", "Weib1.3.png");

        weibvO_WE = loadImage("weibvO_WE", "Weib1.3.2.png");

        weibvO_EW = loadImageRotate90("weibvO_EW", "Weib1.3.2.png");

        Logger.warn("################# Variablen die null sind: ######################################");
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
                                    Logger.warn(f.getName() + " -> nur min. ein Element");
                                    break;
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
        Logger.warn("##############################################################################");
    }

    /**
     * Rotiert ein gegebenes Bild um den gegebenen Winkel.
     *
     * @param image
     *            Bild
     * @param angle
     *            Winkel im Bogenmaß
     * @return gedrehtes Bild
     */
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage result = new BufferedImage(neww, newh, image.getType());
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    /**
     * Lädt ein Bild.
     *
     * @param name
     *            Name des Bildes
     * @return BufferedImage
     */
    private static BufferedImage loadImage(String name, String fileName) {

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
            }
        }
        if (img != null) {
            registerImage(name, img);
        }
        return img;
    }

    /**
     * Lädt ein Array aus Bildern.
     *
     * @param name
     *            Name des Bildes
     * @param length
     *            Anzahl der Bilder
     * @return BufferedImage[]
     */
    private static BufferedImage[] loadImageArray(String name, String fileName, int length) {
        BufferedImage[] images = new BufferedImage[length];
        for (int i = 0; i < images.length; i++) {
            images[i] = loadImage(String.format(name, i), String.format(fileName, i));
        }
        return images;
    }

    /**
     * Lädt ein um 180° gedrehtes Bild.
     *
     * @param name
     *            Name des Bildes.
     * @return BufferedImage
     */
    private static BufferedImage loadImageRotate180(String name, String fileName) {
        BufferedImage img = loadImage(name, fileName);
        if (img != null) {
            return rotate(img, Math.toRadians(180));
        }
        return null;
    }

    /**
     * Lädt ein um 270° gedrehtes Bild.
     *
     * @param name
     *            Name des Bildes.
     * @return BufferedImage
     */
    private static BufferedImage loadImageRotate270(String name, String fileName) {
        BufferedImage img = loadImage(name, fileName);
        if (img != null) {
            return rotate(img, Math.toRadians(270));
        }
        return null;
    }

    /**
     * Lädt ein um 90° gedrehtes Bild.
     *
     * @param name
     *            Name des Bildes.
     * @return BufferedImage
     */
    private static BufferedImage loadImageRotate90(String name, String fileName) {
        BufferedImage img = loadImage(name, fileName);
        if (img != null) {
            return rotate(img, Math.toRadians(90));
        }
        return null;
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
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Images() {
    }
}

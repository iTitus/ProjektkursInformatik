package projektkurs.lib;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
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

    public static BufferedImage auto2vOnO;

    public static BufferedImage auto2vOnW;

    public static BufferedImage auto2vV;

    public static BufferedImage autogelbvOnO;

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

    public static BufferedImage busviolettvOnO;

    public static BufferedImage busviolettvSnW;

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
     * Standard-Charakterbild.
     */
    public static BufferedImage defaultCharakter;

    /**
     * Zerstörtes-Land-Bild.
     */
    public static BufferedImage destroyedRaster;

    /**
     * Geschlossene Tür in Ost-West Ausrichtung.
     */
    public static BufferedImage doorEW;

    /**
     * Geschlossene Tür in Nord-Süd Ausrichtung.
     */
    public static BufferedImage doorNS;

    /**
     * Offene Tür in Ost-West Ausrichtung.
     */
    public static BufferedImage doorOpenEW;

    /**
     * Offene Tür in Nord-Süd Ausrichtung.
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

    public static BufferedImage haus1;

    public static BufferedImage haus2;

    public static BufferedImage haus3;

    public static BufferedImage haus4;

    public static BufferedImage haus5;

    public static BufferedImage haus6;

    public static BufferedImage haus7;

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

    public static BufferedImage kreuzungT_EW;

    public static BufferedImage kreuzungT_NS;

    public static BufferedImage kreuzungT_SN;

    public static BufferedImage kreuzungT_WE;

    public static BufferedImage ladyvO_EW;

    public static BufferedImage ladyvO_NS;

    public static BufferedImage ladyvO_SN;

    public static BufferedImage ladyvO_WE;

    public static BufferedImage ladyvObAho_EW;

    public static BufferedImage ladyvObAho_NS;

    public static BufferedImage ladyvObAho_SN;

    public static BufferedImage ladyvObAho_WE;

    public static BufferedImage ladyvOrAho_EW;

    public static BufferedImage ladyvOrAho_NS;

    public static BufferedImage ladyvOrAho_SN;

    public static BufferedImage ladyvOrAho_WE;

    public static BufferedImage ladyvOrAo_EW;

    public static BufferedImage ladyvOrAo_NS;

    public static BufferedImage ladyvOrAo_SN;

    public static BufferedImage ladyvOrAo_WE;

    public static BufferedImage ladyvVbAho;

    public static BufferedImage ladyvVbAho2;

    public static BufferedImage ladyvVbAo;

    public static BufferedImage ladyvVlAqurAo;

    public static BufferedImage ladyvVrS;

    public static BufferedImage lasterschwarzmitAnhängervO_EW;

    public static BufferedImage lasterschwarzmitAnhängervO_NS;

    public static BufferedImage lasterschwarzmitAnhängervO_SN;

    public static BufferedImage lasterschwarzmitAnhängervO_WE;

    public static BufferedImage lasterschwarzvO_EW;

    public static BufferedImage lasterschwarzvO_NS;

    public static BufferedImage lasterschwarzvO_SN;

    public static BufferedImage lasterschwarzvO_WE;

    public static BufferedImage lasterschwarzvV;

    public static BufferedImage lasterweißvO_EW;

    public static BufferedImage lasterweißvO_NS;

    public static BufferedImage lasterweißvO_SN;

    public static BufferedImage lasterweißvO_WE;

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

    public static BufferedImage pflaster;

    /**
     * Rasenbild.
     */
    public static BufferedImage rasen;

    /**
     *
     */
    public static BufferedImage rasen2;

    /**
     *
     */
    public static BufferedImage redNPC;

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

    public static BufferedImage weibvV;

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
     * Das Pair, das alle Images enthält.
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
        defaultCharakter = loadImage("charakter.png");
        charakter = defaultCharakter;
        registerImage("defaultCharakter", defaultCharakter);

        redNPC = loadImage("redNPC.png");
        registerImage("redNPC", redNPC);

        rasen = loadImage("rasen.png");
        registerImage("rasen", rasen);

        wand = loadImage("wand.png");
        registerImage("wand", wand);

        baum = loadImage("baum.png");
        registerImage("baum", baum);

        kiste = loadImage("kiste.png");
        registerImage("kiste", kiste);

        item42 = loadImage("item_42.png");
        registerImage("item_42", item42);

        nuke = loadImage("nuke.png");
        registerImage("nuke", nuke);

        key = loadImage("key.png");
        registerImage("key", key);

        slot = loadImage("slot.png");
        registerImage("slot", slot);

        slothighlight = loadImage("slot_highlight.png");
        registerImage("slot_highlight", slothighlight);

        doorNS = loadImage("door.png");
        registerImage("door_NS", doorNS);

        doorEW = loadImageRotate("door.png");
        registerImage("door_WE", doorEW);

        doorOpenNS = loadImage("door_open.png");
        registerImage("door_open_NS", doorOpenNS);

        doorOpenEW = loadImageRotate("door_open.png");
        registerImage("door_open_WE", doorOpenEW);

        finish = loadImage("finish.png");
        registerImage("finish", finish);

        destroyedRaster = loadImage("destroyed.png");
        registerImage("destroyedRaster", destroyedRaster);

        fire = loadImageArray("fire_%d.png", 4);
        for (int i = 0; i < fire.length; i++) {
            registerImage("fire_" + i, fire[i]);
        }

        healthpotion = loadImage("healthpotion.png");
        registerImage("healthpotion", healthpotion);

        button = loadImage("button.png");
        registerImage("button", button);

        buttonHighlight = loadImage("button_highlight.png");
        registerImage("button_highlight", buttonHighlight);

        buttonDisabled = loadImage("button_disabled.png");
        registerImage("button_disabled", buttonDisabled);

        // registerAllDir("AutoMitOffenerTürFahrerDraussen1.png", "automitoffenerTürFahrerdraussen1", auto_do_NS, auto_do_SN, auto_do_EW, auto_do_WE);
        auto_do_NS = loadImage("AutoMitOffenerTürFahrerDraussen1.png");
        registerImage("auto_do_NS", auto_do_NS);

        auto_do_EW = loadImageRotate90("AutoMitOffenerTürFahrerDraussen1.png");
        registerImage("auto_do_EW", auto_do_EW);
        // registerAllDir("AutoMitFahrerDrinnen.png", "automitFahrerDrinnen", auto_dc_NS, auto_dc_SN, auto_dc_EW, auto_dc_WE);
        auto_dc_NS = loadImage("AutoMitFahrerDrinnen.png");
        registerImage("auto_dc_NC", auto_dc_NS);

        auto_dc_EW = loadImageRotate90("AutoMitFahrerDrinnen.png");
        registerImage("auto_dc_EW", auto_dc_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDraussen2.png", "automitoffenerTürFahrerdraussen2", auto_do2_NS, auto_do2_SN, auto_do2_EW, auto_do2_WE);
        auto_do2_NS = loadImage("AutoMitOffenerTürFahrerDraussen2.png");
        registerImage("auto_do2_NS", auto_do2_NS);

        auto_do2_EW = loadImageRotate90("AutoMitOffenerTürFahrerDraussen2.png");
        registerImage("auto_do2_EW", auto_do2_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDraussen3.png", "automitoffenerTürFahrerdraussen3", auto_do3_NS, auto_do3_SN, auto_do3_EW, auto_do3_WE);
        auto_do3_NS = loadImage("AutoMitOffenerTürFahrerDraussen3.png");
        registerImage("auto_do3_NS", auto_do3_NS);

        auto_do3_EW = loadImageRotate90("AutoMitOffenerTürFahrerDraussen3.png");
        registerImage("auto_do3_EW", auto_do3_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDraussen4.png", "automitoffenerTürFahrerdraussen4", auto_do4_NS, auto_do4_SN, auto_do4_EW, auto_do4_WE);
        auto_do4_NS = loadImage("AutoMitOffenerTürFahrerDraussen4.png");
        registerImage("auto_do4_NS", auto_do4_NS);

        auto_do4_EW = loadImageRotate90("AutoMitOffenerTürFahrerDraussen4.png");
        registerImage("auto_do4_EW", auto_do4_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDrinnen.png", "automitoffenerTürFahrerdrinnen", auto_di_NS, auto_di_SN, auto_di_EW, auto_di_WE);
        auto_di_NS = loadImage("AutoMitOffenerTürFahrerDrinnen.png");
        registerImage("auto_di_NS", auto_di_NS);

        auto_di_EW = loadImageRotate90("AutoMitOffenerTürFahrerDrinnen.png");
        registerImage("auto_di_EW", auto_di_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDrinnen2.png", "automitoffenerTürFahrerdrinnen2", auto_di2_NS, auto_di2_SN, auto_di2_EW, auto_di2_WE);
        auto_di2_NS = loadImage("AutoMitOffenerTürFahrerDrinnen2.png");
        registerImage("auto_di2_NS", auto_di2_NS);

        auto_di2_EW = loadImageRotate90("AutoMitOffenerTürFahrerDrinnen2.png");
        registerImage("auto_di2_EW", auto_di2_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDrinnen3.png", "automitoffenerTürFahrerdrinnen3", auto_di3_NS, auto_di3_SN, auto_di3_EW, auto_di3_WE);
        auto_di3_NS = loadImage("AutoMitOffenerTürFahrerDrinnen3.png");
        registerImage("auto_di3_NS", auto_di3_NS);

        auto_di3_EW = loadImageRotate90("AutoMitOffenerTürFahrerDrinnen3.png");
        registerImage("auto_di3_EW", auto_di3_EW);
        // registerAllDir("AutoMitOffenerTürFahrerDrinnen4.png", "automitoffenerTürFahrerdrinnen4", auto_di4_NS, auto_di4_SN, auto_di4_EW, auto_di4_WE);
        auto_di4_NS = loadImage("AutoMitOffenerTürFahrerDrinnen4.png");
        registerImage("auto_di4_NS", auto_di4_NS);

        // N
        // ^
        // W < + > O
        // v
        // S

        auto_di4_EW = loadImageRotate90("AutoMitOffenerTürFahrerDrinnen4.png");
        registerImage("auto_di4_EW", auto_di4_EW); // WE FAILED THE TURING TEST!!!
        // registerAllDir("Auto.png", "Auto", auto_NS, auto_SN, auto_EW, auto_WE);
        auto_NS = loadImage("Auto.png");
        registerImage("auto_NS", auto_NS);

        auto_EW = loadImageRotate90("Auto.png");
        registerImage("auto_EW", auto_EW);
        // registerAllDir("AutoMitFahrerDrinnen.png", "automitFahrerDrinnen", auto_w_NS, auto_w_SN, auto_w_EW, auto_w_WE);
        auto_w_NS = loadImage("AutoMitFahrerDrinnen.png");
        registerImage("auto_w_NS", auto_w_NS);

        auto_w_EW = loadImageRotate90("AutoMitFahrerDrinnen.png");
        registerImage("auto_w_EW", auto_w_EW);
        // registerAllDir("AutoMitFahrerDrinnen2.png", "automitFahrerDrinnen2", auto_w2_NS, auto_w2_SN, auto_w2_EW, auto_w2_WE);
        auto_w2_NS = loadImage("AutoMitFahrerDrinnen2.png");
        registerImage("auto_w2_NS", auto_w2_NS);

        auto_w2_EW = loadImageRotate90("AutoMitFahrerDrinnen2.png");
        registerImage("auto_w2_EW", auto_w2_EW);

        auto2vV = loadImage("Auto1.0.png");
        registerImage("auto2vV", auto2vV);

        auto2vOnO = loadImage("Auto1.1.png");
        registerImage("auto2vOnO", auto2vOnO);

        auto2vOnW = loadImage("Auto1.2.png");
        registerImage("auto2vOnW", auto2vOnW);

        autogelbvOnO = loadImage("Auto3.0.png");
        registerImage("autogelbvOnO", autogelbvOnO);

        autogelbvOnW = loadImage("Auto3.1.png");
        registerImage("autogelbvOnW", autogelbvOnW);

        baum1 = loadImage("Baum1.png");
        registerImage("Baum1", baum1);

        baum2 = loadImage("Baum2.png");
        registerImage("Baum2", baum2);

        baum3 = loadImage("Baum3.png");
        registerImage("Baum3", baum3);

        baum4 = loadImage("Baum4.png");
        registerImage("Baum4", baum4);

        baum5 = loadImage("Baum5.png");
        registerImage("Baum5", baum5);

        baum6 = loadImage("Baum6.png");
        registerImage("Baum6", baum6);

        baum7 = loadImage("Baum7.png");
        registerImage("Baum7", baum7);

        baum8 = loadImage("Baum8.png");
        registerImage("Baum8", baum8);

        baum9 = loadImage("Baum9.png");
        registerImage("Baum9", baum9);

        baum10 = loadImage("Baum10.png");
        registerImage("Baum10", baum10);

        baum11 = loadImage("Baum11.png");
        registerImage("Baum11", baum11);

        baum12 = loadImage("Baum12.png");
        registerImage("Baum12", baum12);

        baum13 = loadImage("Baum13.png");
        registerImage("Baum13", baum13);

        busviolettvV = loadImage("Lastwagen1.2.png");
        registerImage(" busviolettvV", busviolettvV);

        busviolettvSnW = loadImage("Bus1.1.png");
        registerImage(" busviolettvSnW", busviolettvSnW);

        busviolettvV = loadImage("Bus1.2.png");
        registerImage(" busviolettvV", busviolettvV);

        busviolettvOnO = loadImage("Bus1.3.png");
        registerImage("busviolettvOvO", busviolettvOnO);

        // bus1.4 ignoriert

        haus1 = loadImage("Haus.png");
        registerImage("haus1", haus1);

        haus2 = loadImage("Haus2.png");
        registerImage("haus2", haus2);

        haus3 = loadImage("Haus3.png");
        registerImage("haus3", haus3);

        haus4 = loadImage("Haus4.png");
        registerImage("haus4", haus4);

        haus5 = loadImage("Haus5.png");
        registerImage("haus5", haus5);

        haus6 = loadImage("Haus6.png");
        registerImage("haus6", haus6);

        haus7 = loadImage("Haus7.png");
        registerImage("haus7", haus7);

        haus8 = loadImage("Haus8.png");
        registerImage("haus8", haus8);

        kreuzung = loadImage("Kreuzung.png");
        registerImage("kreuzung", kreuzung);

        kreuzungT_EW = loadImage("Kreuzung2.png");
        registerImage("kreuzungT_EW", kreuzungT_EW);

        kreuzungT_SN = loadImageRotate90("Kreuzung2.png");
        registerImage("kreuzungT_SN", kreuzungT_SN);

        kreuzungT_WE = loadImage("Kreuzung2.2.png");
        registerImage("kreuzungT_WE", kreuzungT_WE);

        kreuzungT_NS = loadImage("Kreuzung2.2.png");
        registerImage("kreuzungT_NS", kreuzungT_NS);

        ladyvOrAho_NS = loadImage("LadyVonOben.png");
        registerImage("ladyvO_NS", ladyvO_NS);

        ladyvOrAho_EW = loadImageRotate90("LadyVonOben.png");
        registerImage("ladyvO_EW", ladyvO_EW);

        ladyvOrAho_SN = loadImage("LadyVonOben1.2.png");
        registerImage("ladyvO_SN", ladyvO_SN);

        ladyvOrAho_WE = loadImageRotate90("LadyVonOben1.2.png");
        registerImage("ladyvO_WE", ladyvO_WE);

        ladyvO_NS = loadImage("LadyVonOben2.png");
        registerImage("ladyvO_NS", ladyvO_NS);

        ladyvO_EW = loadImageRotate90("LadyVonOben2.png");
        registerImage("ladyvO_EW", ladyvO_EW);

        ladyvO_SN = loadImage("LadyVonOben2.2.png");
        registerImage("ladyvO_SN", ladyvO_SN);

        ladyvO_WE = loadImageRotate90("LadyVonOben2.2.png");
        registerImage("ladyvO_WE", ladyvO_WE);

        ladyvOrAo_WE = loadImage("LadyVonOben3.png");
        registerImage("ladyvOrAo_WE", ladyvOrAo_WE);

        ladyvOrAo_NS = loadImageRotate90("LadyVonOben3.png");
        registerImage("ladyvOrAo_NS", ladyvOrAo_NS);

        ladyvOrAo_EW = loadImage("LadyVonOben3.2.png");
        registerImage("ladyvOrAo_EW", ladyvOrAo_EW);

        ladyvOrAo_SN = loadImageRotate90("LadyVonOben3.2.png");
        registerImage("ladyvOrAo_SN", ladyvOrAo_SN);

        ladyvObAho_NS = loadImage("LadyVonOben4.png");
        registerImage("ladyvonObAho_NS", ladyvObAho_NS);

        ladyvObAho_EW = loadImageRotate90("LadyVonOben4.png");
        registerImage("ladyvonObAho_EW", ladyvObAho_EW);

        ladyvObAho_SN = loadImage("LadyVonOben4.2.png");
        registerImage("ladyvonObAho_SN", ladyvObAho_SN);

        ladyvObAho_WE = loadImageRotate90("LadyVonOben4.2.png");
        registerImage("ladyvonObAho_WE", ladyvObAho_WE);

        ladyvVbAho = loadImage("LadyVonVorne.png");
        registerImage("ladyvbAho", ladyvVbAho);

        ladyvVbAho2 = loadImage("LadyVonVorne2.png");
        registerImage("ladyvVbAho2", ladyvVbAho2);

        ladyvVbAo = loadImage("LadyVonVorne3.png");
        registerImage("ladyvVbAo", ladyvVbAo);

        ladyvVlAqurAo = loadImage("LadyVonVorne4.png");// <--Nice
        registerImage("ladyvVlAqurAo", ladyvVlAqurAo);

        ladyvVrS = loadImage("LadyVonVorne5.png");
        registerImage("ladyvVrS", ladyvVrS);

        lasterschwarzvV = loadImage("Lastwagen1.0.png");
        registerImage("lasterschwarzvV", lasterschwarzvV);

        lasterschwarzvO_NS = loadImage("Lastwagen1.6.png");
        registerImage("lasterschwarzvO_NS", lasterschwarzvO_NS);

        lasterschwarzvO_EW = loadImageRotate90("Lastwagen1.6.png");
        registerImage("lasterschwarzvO_EW", lasterschwarzvO_EW);

        lasterschwarzvO_SN = loadImage("Lastwagen1.6.2.png");
        registerImage("lasterschwarzvO_SN", lasterschwarzvO_SN);

        lasterschwarzvO_WE = loadImageRotate90("Lastwagen1.6.2.png");
        registerImage("lasterschwarzvO_WE", lasterschwarzvO_WE);

        lasterschwarzmitAnhängervO_EW = loadImage("Lastwagen1.3.png");
        registerImage("lasterschwarzmitAnhängervO_EW", lasterschwarzmitAnhängervO_EW);

        lasterschwarzmitAnhängervO_SN = loadImageRotate90("Lastwagen1.3.png");
        registerImage("lasterschwarzmitAnhängervO_SN", lasterschwarzmitAnhängervO_SN);

        lasterschwarzmitAnhängervO_WE = loadImage("Lastwagen1.3.2.png");
        registerImage("lasterschwarzmitAnhängervO_WE", lasterschwarzmitAnhängervO_WE);

        lasterschwarzmitAnhängervO_NS = loadImageRotate90("Lastwagen1.3.2.png");
        registerImage("lasterschwarzmitAnhängervO_NS", lasterschwarzmitAnhängervO_NS);

        lasterweißvO_NS = loadImage("FliegendeZahnpastaTube1.0.png");
        registerImage("lasterweißvO_NS", lasterweißvO_NS);

        lasterweißvO_EW = loadImageRotate90("FliegendeZahnpastaTube1.0.png");
        registerImage("lasterweißvO_EW", lasterweißvO_EW);

        lasterweißvO_SN = loadImage("FliegendeZahnpastaTube1.1.2");
        registerImage("lasterweißvO_SN", lasterweißvO_SN);

        lasterweißvO_WE = loadImageRotate90("FliegendeZahnpastaTube1.1.2");
        registerImage("lasterweißvO_WE", lasterweißvO_WE);

        lasterweißvV = loadImage("FliegendeZahnpastaTube1.2");
        registerImage("lasterweißvV", lasterweißvV);

        lordvdS = loadImage("LordVonDerSeite.png");
        registerImage("lordvonderSeite", lordvdS);

        lordvdS2 = loadImage("LordVonDerSeite2.png");
        registerImage("lordvonderSeite2", lordvdS2);

        lordvdS3 = loadImage("LordVonDerSeite3.png");
        registerImage("lordvonderSeite3", lordvdS3);

        lordS = loadImage("LordSitzend.png");
        registerImage("lordS", lordS);

        lordH = loadImage("LordHinten.png");// abcdefghijklmnopqrstuvwxyz
        registerImage("lordH", lordH);

        // registerAllDir("LordVonOben.png", "LordvO", LordvO_NS, LordvO_SN, LordvO_EW,LordvO_WE);

        lordvO_NS = loadImage("LordVonOben1.png");
        registerImage("lordvO_NS", lordvO_NS);

        lordvO_EW = loadImageRotate90("LordVonOben1.png");
        registerImage("lordvO_EW", lordvO_EW);

        lordvO_SN = loadImage("LordVonOben1.1.png");
        registerImage("lordvO_SN", lordvO_SN);

        lordvO_WE = loadImageRotate90("LordVonOben1.1.png");
        registerImage("lordvO_WE", lordvO_WE);

        gras = loadImage("Gras.png");
        registerImage("gras", gras);

        pflaster = loadImage("PflasterStein.png");
        registerImage("pflaster", pflaster);

        rasen2 = loadImage("Wiese.png");
        registerImage("rasen2", rasen2);

        strasse_EW = loadImage("Strasse.png");
        registerImage("strasse_EW", strasse_EW);

        strasse_NS = loadImageRotate90("");
        registerImage("strasse_NS", strasse_NS);

        typvObAo_EW = loadImage("Typ1.2.png");
        registerImage("typvObAo_EW", typvObAo_EW);

        typvObAo_SN = loadImageRotate90("Typ1.2.png");
        registerImage("typvObAo_SN", typvObAo_SN);

        typvObAo_WE = loadImage("Typ1.2.png");
        registerImage("typvObAo_WE", typvObAo_WE);

        typvObAo_NS = loadImageRotate90("Typ1.2.png");
        registerImage("typvObAo_NS", typvObAo_NS);

        typvO_WE = loadImage("Typ1.3.png");
        registerImage("typvO_WE", typvO_WE);

        typvO_NS = loadImageRotate90("Typ1.3.png");
        registerImage("typvO_NS", typvO_NS);

        typvO_EW = loadImage("Typ1.3.png");
        registerImage("typvO_EW", typvO_EW);

        typvO_SN = loadImageRotate90("Typ1.3.png");
        registerImage("typvO_SN", typvO_SN);

        typvO2_WE = loadImage("Typ1.png");
        registerImage("typvO2_WE", typvO2_WE);

        typvO2_NS = loadImageRotate90("Typ1.png");
        registerImage("typvO2_NS", typvO2_NS);

        typvO2_EW = loadImage("Typ1.0.2.png");
        registerImage("typvO2_EW", typvO2_EW);

        typvO2_SN = loadImageRotate90("Typ1.0.2.png");
        registerImage("typvO2_SN", typvO2_SN);

        typvOrAho_WE = loadImage("Typ1.4.png");
        registerImage("typvOrAho_WE", typvOrAho_WE);

        typvOrAho_NS = loadImageRotate90("Typ1.4.png");
        registerImage("typvOrAho_NS", typvOrAho_NS);

        typvOrAho_EW = loadImage("Typ1.4.png");
        registerImage("typvOrAho_EW", typvOrAho_EW);

        typvOrAho_WE = loadImageRotate90("Typ1.4.png");
        registerImage("typvOrAho_WE", typvOrAho_WE);

        typvOrAo_WE = loadImage("Typ1.5.png");
        registerImage("typvOrAo_WE", typvOrAo_WE);

        typvOrAo_NS = loadImageRotate90("Typ1.5.png");
        registerImage("typvOrAo_NS", typvOrAo_NS);

        typvOrAo_EW = loadImage("Typ1.5.2.png");
        registerImage("typvOrAo_EW", typvOrAo_EW);

        typvOrAo_SN = loadImageRotate90("Typ1.5.2.png");
        registerImage("typvOrAo_SN", typvOrAo_SN);

        weibvV = loadImage("Weib1.0.png");
        registerImage("weibvV", weibvV);

        wasser = loadImage("Wasser.png");
        registerImage("wasser", wasser);

        weibvOlAo_NS = loadImage("Weib1.1.png");
        registerImage("weibvOlAo", weibvOlAo);

        weibvOlAo_EW = loadImageRotate90("Weib1.1.png");
        registerImage("weibvOlAo", weibvOlAo);

        weibvOlAo_SN = loadImage("Weib1.1.2.png");
        registerImage("weibvOlAo", weibvOlAo);

        weibvOlAo_WE = loadImageRotate90("Weib1.1.2.png");
        registerImage("weibvOlAo", weibvOlAo);

        weibvV = loadImage("Weib1.2.png");
        registerImage("weibvV", weibvV);

        weibvO_EW = loadImage("Weib1.3.png");
        registerImage("weibvO", weibvO);

        weibvO_SO = loadImageRotate90("Weib1.3.png");
        registerImage("weibvO", weibvO);

        weibvO_WE = loadImage("Weib1.3.2.png");
        registerImage("weibvO", weibvO);

        weibvO_EW = loadImageRotate90("Weib1.3.2.png");
        registerImage("weibvO", weibvO);

    }

    /**
     * Setzt das Charakter-Bild.
     *
     * @param img
     *            Charakter-Bild
     */
    public static void setCharakterImage(BufferedImage img) {
        charakter = img;
    }

    /**
     * Lädt ein Bild.
     *
     * @param name
     *            Name des Bildes
     * @return BufferedImage
     */
    private static BufferedImage loadImage(String name) {

        String path = "resources/images/" + name;

        BufferedImage img = null;
        try {
            img = ImageIO.read(Main.class.getResource(path));
            Logger.info("Successfully loaded image '" + name + "'");
        } catch (Throwable t1) {
            try (InputStream stream = Main.class.getResourceAsStream(path)) {
                img = ImageIO.read(stream);
                Logger.info("Successfully loaded image '" + name + "'");
            } catch (Throwable t2) {
                Logger.logThrowable("Unable to load image '" + name + "'", t2);
            }
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
    private static BufferedImage[] loadImageArray(String name, int length) {
        BufferedImage[] images = new BufferedImage[length];
        for (int i = 0; i < images.length; i++) {
            images[i] = loadImage(String.format(name, i));
        }
        return images;
    }

    /**
     * Lädt ein Bild und dreht es dabei um i*90°.
     *
     * @param name
     *            Name des Bildes
     * @param i
     *            rotated by i*90degrees
     * @return BufferedImage
     */
    // FIXME: Problems!
    private static BufferedImage loadImageRotate(String name) {
        BufferedImage img = loadImage(name);
        if (img != null) {
            BufferedImage rotated = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            Graphics2D g = (Graphics2D) rotated.getGraphics();
            g.setTransform(AffineTransform.getQuadrantRotateInstance(1));
            g.drawImage(img, 0, -img.getWidth(), null);
            return rotated;
        }
        return null;
    }

    /**
     * "NS", "SN", "EW", "WE"
     *
     * @param imageName
     * @param name
     * @param images
     */

    private static BufferedImage loadImageRotate90(String name) {
        BufferedImage img = loadImage(name);
        if (img != null) {
            BufferedImage rotated = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
            Graphics2D g = (Graphics2D) rotated.getGraphics();
            g.setTransform(AffineTransform.getQuadrantRotateInstance(1));
            g.drawImage(img, 0, -img.getWidth(), null);
            return rotated;
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
        MAPPINGS.put(name, image);
        BACK_MAPPINGS.put(image, name);
    }

    /**
     * Nicht instanziierbar.
     */
    private Images() {
    }
}

package projektkurs.lib;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import projektkurs.Main;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;

/**
 * Alle Bilder.
 */
public final class Images {

    /**
     * Zurück-Mappings.
     */
    public static final HashMap<BufferedImage, String> BACK_MAPPINGS = new HashMap<BufferedImage, String>();
    /**
     * Baumbild.
     */
    public static BufferedImage                        baum;
    /**
     * Gui-Knopf-Bild.
     */
    public static BufferedImage                        button;
    /**
     * Deaktiviertes Gui-Knopf-Bild.
     */
    public static BufferedImage                        buttonDisabled;
    /**
     * Hervorgehobenes Gui-Knopf-Bild.
     */
    public static BufferedImage                        buttonHighlight;
    /**
     * Charakterbild - Userauswahl.
     */
    public static BufferedImage                        charakter;
    /**
     * Standard-Charakterbild.
     */
    public static BufferedImage                        defaultCharakter;
    /**
     * Zerstörtes-Land-Bild.
     */
    public static BufferedImage                        destroyedRaster;
    /**
     * Geschlossene Tür in Ost-West Ausrichtung.
     */
    public static BufferedImage                        doorEW;
    /**
     * Geschlossene Tür in Nord-Süd Ausrichtung.
     */
    public static BufferedImage                        doorNS;
    /**
     * Offene Tür in Ost-West Ausrichtung.
     */
    public static BufferedImage                        doorOpenEW;
    /**
     * Offene Tür in Nord-Süd Ausrichtung.
     */
    public static BufferedImage                        doorOpenNS;
    /**
     * Zielbild.
     */
    public static BufferedImage                        finish;
    /**
     * Feueranimationsbilder.
     */
    public static BufferedImage[]                      fire;
    /**
     * Gesundheitstrank.
     */
    public static BufferedImage                        healthpotion;
    /**
     * Item_42-Bild.
     */
    public static BufferedImage                        item42;
    /**
     * Schlüsselbild.
     */
    public static BufferedImage                        key;
    /**
     * Kistenbild.
     */
    public static BufferedImage                        kiste;
    /**
     * Mappings.
     */
    public static final HashMap<String, BufferedImage> MAPPINGS      = new HashMap<String, BufferedImage>();
    /**
     * Atombombenbild.
     */
    public static BufferedImage                        nuke;
    /**
     * Rasenbild.
     */
    public static BufferedImage                        rasen;
    /**
     * RedNPC-Bild.
     */
    public static BufferedImage                        redNPC;
    /**
     * Inventarslotbild.
     */
    public static BufferedImage                        slot;
    /**
     * Hervorgehobenes Inventarslotbild.
     */
    public static BufferedImage                        slothighlight;
    /**
     * Wandbild.
     */
    public static BufferedImage                        wand;

    /**
     * Rasenbild.
     */

    public static void flushAll() {
        for (BufferedImage img : MAPPINGS.values()) {
            if (img != null) {
                img.flush();
            }
        }
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

        doorEW = loadImageRotate90("door.png");
        registerImage("door_WE", doorEW);

        doorOpenNS = loadImage("door_open.png");
        registerImage("door_open_NS", doorOpenNS);

        doorOpenEW = loadImageRotate90("door_open.png");
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
     * Lädt ein Bild und dreht es dabei um 90°.
     *
     * @param name
     *            Name des Bildes
     * @return BufferedImage
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

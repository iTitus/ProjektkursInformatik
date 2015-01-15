package projektkurs.render;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import projektkurs.Main;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;

public class SpriteSheet {

    private final String name;
    private final int[] pixels;
    private final int sizeX;
    private final int sizeY;

    public SpriteSheet(String name, String fileName) {
        this.name = name;
        String path = "resources/images/" + fileName;
        BufferedImage image = null;
        try {
            image = ImageIO.read(Main.class.getResource(path));
            Logger.info("Successfully loaded image '" + fileName + "'");
        } catch (Throwable t1) {
            try (InputStream stream = Main.class.getResourceAsStream(path)) {
                image = ImageIO.read(stream);
                Logger.info("Successfully loaded image '" + fileName + "'");
            } catch (Throwable t2) {
                Logger.logThrowable("Unable to load image '" + fileName + "'", t2);
                throw new IllegalArgumentException("Unable to load image " + fileName);
            }
        }
        sizeX = image.getWidth();
        sizeY = image.getHeight();
        pixels = new int[sizeX * sizeY];
        image.getRGB(0, 0, sizeX, sizeY, pixels, 0, sizeX);
    }

    public String getName() {
        return name;
    }

    public int getPixel(int index) {
        if (MathUtil.isInArray(index, pixels.length)) {
            return pixels[index];
        }
        return 0;
    }

    public int getPixel(int x, int y) {
        if (MathUtil.isInArray(x, sizeX) && MathUtil.isInArray(y, sizeY)) {
            return pixels[x + y * sizeX];
        }
        return 0;
    }

    public int getPixelAmount() {
        return pixels.length;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    @Override
    public String toString() {
        return "SpriteSheet [" + name + "]";
    }

}

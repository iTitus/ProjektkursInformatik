package projektkurs.render;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class Sprite {

    private final String name;
    private final int[] pixels;
    private final int sizeX;
    private final int sizeY;

    public Sprite(String name, BufferedImage image) {
        this.name = name;
        pixels = new int[image.getWidth() * image.getHeight()];
        sizeX = image.getWidth();
        sizeY = image.getHeight();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                pixels[x + y * image.getWidth()] = ((image.getRGB(x,y) & 0xff000000) >> 24) == 0? 0xFFFFFF & image.getRGB(x, y) : Integers.TRANSPARENCY;
            }
        }
    }

    public Sprite(String name, int size, int color) {
        this(name, size, size, color);
    }

    public Sprite(String name, int sizeX, int sizeY, int color) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pixels = new int[sizeX * sizeY];
        Arrays.fill(pixels, color);
    }

    public Sprite(String name, int size, int colA, int colB, int colC, int colD) {
        this(name, size, size, colA, colB, colC, colD);
    }

    public Sprite(String name, int sizeX, int sizeY, int colA, int colB, int colC, int colD) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pixels = new int[sizeX * sizeY];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                pixels[x + y * sizeX] = RenderUtil.interpolate(colA, colB, colC, colD, x / (double) sizeX, y / (double) sizeY);
            }
        }
    }

    public Sprite(String name, int sizeX, int sizeY, int x, int y, SpriteSheet sheet) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pixels = new int[sizeX * sizeY];
        for (int yy = 0; yy < sizeY; yy++) {
            for (int xx = 0; xx < sizeX; xx++) {
                pixels[xx + yy * sizeX] = sheet.getPixel(xx + x, yy + y);
            }
        }
    }

    public Sprite(String name, int size, int x, int y, SpriteSheet sheet) {
        this(name, size, size, x, y, sheet);
    }

    public Sprite(String name, int[] pixels, int sizeX, int sizeY) {
        this.name = name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.pixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = pixels[i];
        }
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

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Sprite rescale(String name, double factorX, double factorY) {

        int newSizeX = MathUtil.floorMul(sizeX, factorX);
        int newSizeY = MathUtil.floorMul(sizeY, factorY);

        int[] pixels = new int[newSizeX * newSizeY];

        for (int x = 0; x < newSizeX; x++) {
            for (int y = 0; y < newSizeY; y++) {
                int xx = getPixel(MathUtil.floor(x / factorX), MathUtil.floor(y / factorY));
                int xx_r = RenderUtil.getRed(xx);
                int xx_g = RenderUtil.getGreen(xx);
                int xx_b = RenderUtil.getBlue(xx);
                int xy = getPixel(MathUtil.floor(x / factorX), MathUtil.ceil(y / factorY));
                int xy_r = RenderUtil.getRed(xy);
                int xy_g = RenderUtil.getGreen(xy);
                int xy_b = RenderUtil.getBlue(xy);
                int yx = getPixel(MathUtil.ceil(x / factorX), MathUtil.floor(y / factorY));
                int yx_r = RenderUtil.getRed(yx);
                int yx_g = RenderUtil.getGreen(yx);
                int yx_b = RenderUtil.getBlue(yx);
                int yy = getPixel(MathUtil.ceil(x / factorX), MathUtil.ceil(y / factorY));
                int yy_r = RenderUtil.getRed(yy);
                int yy_g = RenderUtil.getGreen(yy);
                int yy_b = RenderUtil.getBlue(yy);

                pixels[x + y * newSizeX] = RenderUtil.isTransparent(xx) && RenderUtil.isTransparent(xy) && RenderUtil.isTransparent(yx) && RenderUtil.isTransparent(yy) ? Integers.TRANSPARENCY : RenderUtil.getColor(
                        linEx(RenderUtil.isTransparent(xx) ? 0 : xx_r, RenderUtil.isTransparent(xy) ? 0 : xy_r, RenderUtil.isTransparent(yx) ? 0 : yx_r, RenderUtil.isTransparent(yy) ? 0 : yy_r, x / factorX - MathUtil.floor(x / factorX), y / factorY - MathUtil.floor(y / factorY)),
                        linEx(RenderUtil.isTransparent(xx) ? 0 : xx_g, RenderUtil.isTransparent(xy) ? 0 : xy_g, RenderUtil.isTransparent(yx) ? 0 : yx_g, RenderUtil.isTransparent(yy) ? 0 : yy_g, x / factorX - MathUtil.floor(x / factorX), y / factorY - MathUtil.floor(y / factorY)),
                        linEx(RenderUtil.isTransparent(xx) ? 0 : xx_b, RenderUtil.isTransparent(xy) ? 0 : xy_b, RenderUtil.isTransparent(yx) ? 0 : yx_b, RenderUtil.isTransparent(yy) ? 0 : yy_b, x / factorX - MathUtil.floor(x / factorX), y / factorY - MathUtil.floor(y / factorY)));
            }
        }

        return new Sprite(name, pixels, newSizeX, newSizeY);
    }

    public int[] rotate(double angle) {

        angle = Math.toRadians(angle - 90);

        int[] result = new int[pixels.length];

        double nx_x = MathUtil.rotX(-angle, 1, 0);
        double nx_y = MathUtil.rotY(-angle, 1, 0);
        double ny_x = MathUtil.rotX(-angle, 0, 1);
        double ny_y = MathUtil.rotY(-angle, 0, 1);

        double x0 = MathUtil.rotX(-angle, -sizeX / 2D, -sizeY / 2D) + sizeX / 2D;
        double y0 = MathUtil.rotY(-angle, -sizeX / 2D, -sizeY / 2D) + sizeY / 2D;

        double x1, y1;
        int xx, yy, col;

        for (int y = 0; y < sizeY; y++) {
            x1 = x0;
            y1 = y0;
            for (int x = 0; x < sizeX; x++) {
                xx = MathUtil.floor(x1);
                yy = MathUtil.floor(y1);

                if (MathUtil.isNotInArray(xx, sizeX) || MathUtil.isNotInArray(yy, sizeY)) {
                    col = Integers.TRANSPARENCY;
                } else {
                    col = getPixel(xx, yy);
                }

                result[x + y * sizeX] = col;

                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }

        return result;
    }

    public Sprite rotate(String name, double angle) {
        return new Sprite(name, rotate(angle), sizeX, sizeY);
    }

    @Override
    public String toString() {
        return "Sprite [" + name + "]";
    }

    private int linEx(int xx, int xy, int yx, int yy, double x, double y) {
        return MathUtil.floor(xx * (1 - x) * (1 - y) + yx * x * (1 - y) + xy * (1 - x) * y + yy * x * y);
    }
}

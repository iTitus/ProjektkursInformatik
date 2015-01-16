package projektkurs.render;

import java.util.Arrays;

import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

public class Sprite {

    private final String name;
    private final int[] pixels;
    private final int sizeX;
    private final int sizeY;

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
        this.pixels = Arrays.copyOf(pixels, pixels.length);
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

    public int[] rotate(double angle) {

        int[] result = new int[pixels.length];

        double nx_x = MathUtil.rotX(-angle, 1, 0);
        double nx_y = MathUtil.rotY(-angle, 1, 0);
        double ny_x = MathUtil.rotX(-angle, 0, 1);
        double ny_y = MathUtil.rotY(-angle, 0, 1);

        double x0 = MathUtil.rotX(-angle, -sizeX / 2D, -sizeY / 2D) + sizeX / 2D;
        double y0 = MathUtil.rotY(-angle, -sizeX / 2D, -sizeY / 2D) + sizeY / 2D;

        double x1 = x0;
        double y1 = y0;
        int xx;
        int yy;
        int col;

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                xx = MathUtil.floor(x1);
                yy = MathUtil.floor(y1);

                if (MathUtil.isInArray(xx, sizeX) && MathUtil.isInArray(yy, sizeY)) {
                    col = getPixel(xx, yy);
                } else {
                    col = Integers.TRANSPARENCY;
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

}

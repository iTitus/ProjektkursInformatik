package projektkurs.render;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "Sprite [" + name + "]";
    }

}

package projektkurs.render;

import java.util.Arrays;

import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;

public class Screen {

    private final int[] pixels;
    private final int sizeX;
    private final int sizeY;

    public Screen(int width, int height) {
        sizeX = width;
        sizeY = height;
        pixels = new int[width * height];
    }

    public void clear() {
        setColor(0);
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

    public void setColor(int color) {
        Arrays.fill(pixels, color);
    }

    public void setPixel(int color, int index) {
        if (MathUtil.isInArray(index, pixels.length)) {
            pixels[index] = color;
        }
    }

    public void setPixel(int color, int x, int y) {
        if (color != Integers.TRANSPARENCY && MathUtil.isInArray(x, sizeX) && MathUtil.isInArray(y, sizeY)) {
            pixels[x + y * sizeX] = color;
        }
    }

}

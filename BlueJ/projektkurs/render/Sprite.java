package projektkurs.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import projektkurs.lib.Integers;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class Sprite {

	private static final int[][] MORE_MAGIC_NUMBERS = {{0, 1, 2, 3}, {0, 1, 2, 0}, {0, 1, 3, 3}, {0, 0, 1, 1}, {0, 2, 2, 3}, {0, 0, 2, 2}, {0, 0, 3, 3}, {0, 0, 0, 0}, {1, 1, 2, 3}, {1, 1, 2, 2}, {1, 1, 3, 3}, {1, 1, 1, 1}, {3, 2, 2, 3}, {2, 2, 2, 2}, {3, 3, 3, 3}, {-1, -1, -1, -1}};

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
				pixels[x + y * image.getWidth()] = (image.getRGB(x, y) & 0xff000000) >> 24 != 0 ? 0xFFFFFF & image.getRGB(x, y) : Integers.TRANSPARENCY;
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

	public int[] rescale(double factorX, double factorY) {

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
				int[] r = {xx_r, xy_r, yx_r, yy_r};
				int[] g = {xx_g, xy_g, yx_g, yy_g};
				int[] b = {xx_b, xy_b, yx_b, yy_b};

				boolean xx_t = RenderUtil.isTransparent(xx);
				boolean xy_t = RenderUtil.isTransparent(xy);
				boolean yx_t = RenderUtil.isTransparent(yx);
				boolean yy_t = RenderUtil.isTransparent(yy);
				int all = (xx_t ? 0b0001 : 0b0000) | (xy_t ? 0b0010 : 0b0000) | (yx_t ? 0b0100 : 0b0000) | (yy_t ? 0b1000 : 0b0000);

				pixels[x + y * newSizeX] = all != 0 ? Integers.TRANSPARENCY : RenderUtil.getColor(linEx(r[MORE_MAGIC_NUMBERS[all][0]], r[MORE_MAGIC_NUMBERS[all][1]], r[MORE_MAGIC_NUMBERS[all][2]], r[MORE_MAGIC_NUMBERS[all][3]], x / factorX - MathUtil.floor(x / factorX), y / factorY - MathUtil.floor(y / factorY)),
						linEx(g[MORE_MAGIC_NUMBERS[all][0]], g[MORE_MAGIC_NUMBERS[all][1]], g[MORE_MAGIC_NUMBERS[all][2]], g[MORE_MAGIC_NUMBERS[all][3]], x / factorX - MathUtil.floor(x / factorX), y / factorY - MathUtil.floor(y / factorY)),
						linEx(b[MORE_MAGIC_NUMBERS[all][0]], b[MORE_MAGIC_NUMBERS[all][1]], b[MORE_MAGIC_NUMBERS[all][2]], b[MORE_MAGIC_NUMBERS[all][3]], x / factorX - MathUtil.floor(x / factorX), y / factorY - MathUtil.floor(y / factorY)));
			}
		}
		return pixels;
	}

	public Sprite rescale(String name, double factorX, double factorY) {
		return new Sprite(name, rescale(factorX, factorY), MathUtil.floorMul(sizeX, factorX), MathUtil.floorMul(sizeY, factorY));
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

	public BufferedImage toBufferedImage() {
		BufferedImage image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				int c = getPixel(x, y);
				g.setColor(new Color(c, c == Integers.TRANSPARENCY));
				g.drawLine(x, y, x, y);
			}
		}
		g.dispose();
		return image;
	}

	@Override
	public String toString() {
		return "Sprite [" + name + "]";
	}

	private int linEx(int xx, int xy, int yx, int yy, double x, double y) {
		return MathUtil.floor(xx * (1 - x) * (1 - y) + yx * x * (1 - y) + xy * (1 - x) * y + yy * x * y);
	}
}

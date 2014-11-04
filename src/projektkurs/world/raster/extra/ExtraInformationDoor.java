package projektkurs.world.raster.extra;

import java.awt.image.BufferedImage;

import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.Images;
import projektkurs.util.Direction;

public class ExtraInformationDoor extends ExtraInformation {

	private Direction direction;
	private boolean isOpen;
	private int openingKey;

	public ExtraInformationDoor() {
		isOpen = false;
		direction = Direction.UNKNOWN;
		openingKey = 0;
	}

	public BufferedImage getBufferedImage() {

		switch (direction) {
		case LEFT:
		case RIGHT:
			return (isOpen ? Images.door_open_WE : Images.door_WE);
		default:
			return (isOpen ? Images.door_open_NS : Images.door_NS);
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public boolean getIsOpen(Direction dir) {
		return isOpen && (dir == direction || dir == direction.getOpposite());
	}

	public int getOpeningKey() {
		return openingKey;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setOpeningKey(int openingKey) {
		this.openingKey = openingKey;
	}

	public void tryOpen(ItemStack key) {
		if (key != null && key.itemEquals(new ItemStack(Items.KEY))
				&& key.getDamage() == openingKey) {
			isOpen = true;
		}
	}
}

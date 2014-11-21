package projektkurs.world.raster.extra;

import java.awt.image.BufferedImage;

import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Items;
import projektkurs.lib.Strings;
import projektkurs.util.Direction;
import projektkurs.util.SaveData;

public class ExtraInformationDoor extends ExtraInformation {

    private Direction direction;
    private boolean   isOpen;
    private int       openingKey;

    public ExtraInformationDoor() {
        direction = Direction.UNKNOWN;
    }

    public Direction getDirection() {
        return direction;
    }

    public BufferedImage getImage() {

        switch (direction) {
            case LEFT:
            case RIGHT:
                return isOpen ? Images.doorOpenEW : Images.doorEW;
            default:
                return isOpen ? Images.doorOpenNS : Images.doorNS;
        }
    }

    public boolean getIsOpen(Direction dir) {
        return isOpen && (dir == direction || dir == direction.getOpposite());
    }

    public int getOpeningKey() {
        return openingKey;
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        direction = Direction.values()[data.getInteger(Strings.EXTRA_DIR)];
        isOpen = data.getBoolean(Strings.EXTRA_OPEN);
        openingKey = data.getInteger(Strings.EXTRA_KEY);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setOpeningKey(int openingKey) {
        this.openingKey = openingKey;
    }

    public void tryOpen(ItemStack key) {
        if (key != null && key.itemEquals(new ItemStack(Items.key)) && key.getDamage() == openingKey) {
            isOpen = true;
        }
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set(Strings.EXTRA_DIR, direction.ordinal());
        data.set(Strings.EXTRA_OPEN, isOpen);
        data.set(Strings.EXTRA_KEY, openingKey);
    }
}

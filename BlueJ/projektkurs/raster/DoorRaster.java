package projektkurs.raster;

import projektkurs.Main;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.render.Screen;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;
import projektkurs.world.Spielfeld;

/**
 * Eine Tuer.
 */
public class DoorRaster extends AbstractRaster implements IHasExtraInformation {

    /**
     * Konstruktor.
     * @param id
     * ID
     */
    public DoorRaster(int id) {
        super(id, "door");
    }

    @Override
    public boolean canWalkOnFromDirection(Spielfeld map, int x, int y, Entity entity, Direction dir) {
        ExtraInformation extra = map.getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            return ((ExtraInformationDoor) extra).isOpenFromDirection(dir);
        }
        return false;
    }

    @Override
    public ExtraInformation createExtraInformation(Spielfeld map, int x, int y) {
        return new ExtraInformationDoor(map, x, y);
    }

    @Override
    public void onCollideWith(Spielfeld map, int x, int y, Entity entity) {
        if (entity instanceof EntityPlayer) {
            ExtraInformation extra = map.getExtraInformationAt(x, y);
            if (extra instanceof ExtraInformationDoor) {
                ((ExtraInformationDoor) extra).tryOpen(Main.getPlayer().getInventory().getSelectedItemStack());
            }
        }
    }

    @Override
    public void render(Screen screen, Spielfeld map, int x, int y) {
        ExtraInformation extra = map.getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            RenderUtil.drawDefaultRaster(screen, ((ExtraInformationDoor) extra).getSprite(), x, y);
        }
    }

    @Override
    public void renderCutScene(Screen screen, int x, int y) {
        ExtraInformation extra = CutSceneManager.getMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            RenderUtil.drawCutSceneRaster(screen, ((ExtraInformationDoor) extra).getSprite(), x, y);
        }

    }
}

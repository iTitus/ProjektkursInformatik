package projektkurs.raster.extra;

import projektkurs.world.Spielfeld;

/**
 * Interface für Raster, die eine ExtraInformation besitzen.
 */
public interface IHasExtraInformation {

    /**
     * Erstellt eine Standard-ExtraInfomation.
     *
     * @return ExtraInfomation
     * @param map
     *            Spielfeld
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    ExtraInformation createExtraInformation(Spielfeld map, int x, int y);

}

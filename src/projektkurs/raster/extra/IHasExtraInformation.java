package projektkurs.raster.extra;

import projektkurs.world.Spielfeld;

/**
 * Interface fuer Raster, die eine ExtraInformation besitzen.
 */
public interface IHasExtraInformation {

    /**
     * Erstellt eine Standard-ExtraInfomation.
     *
     * @param map
     *            Spielfeld
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     * @return ExtraInfomation
     */
    ExtraInformation createExtraInformation(Spielfeld map, int x, int y);

}

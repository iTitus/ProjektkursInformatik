package projektkurs.lib;

import java.awt.image.BufferedImage;
import java.util.Random;

public class CutSceneUtil {

    private static BufferedImage[] baeume = { Images.baum1, Images.baum2, Images.baum3, Images.baum4, Images.baum5, Images.baum6, Images.baum7, Images.baum8, Images.baum9, Images.baum10, Images.baum11, Images.baum12, Images.baum13 };

    public static BufferedImage getRanBaum() {
        Random ran = new Random();
        return baeume[ran.nextInt(baeume.length)];
    }
}

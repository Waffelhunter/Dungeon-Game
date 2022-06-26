package logic;

import de.gurkenlabs.litiengine.resources.Resources;
import java.awt.image.BufferedImage;


public class CollectablesManager {
    public static BufferedImage[] img = new BufferedImage[50];
    public static boolean[] imgVisible = new boolean[50];
    private static final BufferedImage HaloChar = Resources.images().get("src/main/resources/Collectables/Halo.png") ;

// nicht hinterfrage erstes Bild das gerendert wird ist nummer 11 ka why
    public static void init(){
        for(int j =0; j< imgVisible.length;j++){
            imgVisible[j] = false;
        }
        img[11] = HaloChar;
        img[12] = HaloChar;
        img[13] = HaloChar;
        img[14] = HaloChar;
        imgVisible[11]= true;
        imgVisible[12]= true;
        imgVisible[13]= true;
        imgVisible[14]= true;


    }
}

package logic;


import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.image.BufferedImage;


public class CollectablesManager {
    public static BufferedImage[] img = new BufferedImage[50];
    public static boolean[] imgVisible = new boolean[50];
    private static final BufferedImage DoomChar = Resources.images().get("src/main/resources/Collectables/Halo.png") ;
    private static final BufferedImage Kaneki = Resources.images().get("src/main/resources/Collectables/Kaneki.png") ;
    private static final BufferedImage Apex = Resources.images().get("src/main/resources/Collectables/aus_dem_cringen_spiel.png") ;

// nicht hinterfrage erstes Bild das gerendert wird ist nummer 11 ka why
    // Die Figur ist vom Doom Slayer aus doom
    public static void init(){
        for(int j =0; j< imgVisible.length;j++){
            imgVisible[j] = false;
        }
        img[11] = DoomChar;
        img[12] = Kaneki;
        img[13] = Apex;
        img[14] = DoomChar;
        imgVisible[11]= true;
        imgVisible[12]= true;
        imgVisible[13]= true;
        imgVisible[14]= true;


    }
}

package de.dungeongame.logic;


import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.image.BufferedImage;


public class CollectablesManager {
    private static final BufferedImage DoomChar = Resources.images().get("Collectables/Halo.png");
    private static final BufferedImage Kaneki = Resources.images().get("Collectables/Kaneki.png");
    private static final BufferedImage Apex = Resources.images().get("Collectables/aus_dem_cringen_spiel.png");
    private static final BufferedImage oni = Resources.images().get("Collectables/oni.png");
    public static BufferedImage[] img = new BufferedImage[50];
    public static boolean[] imgVisible = new boolean[50];

    // nicht hinterfrage erstes Bild das gerendert wird ist nummer 11 ka why
    // Die Figur ist vom Halo
    public static void init() {
        for (int j = 0; j < imgVisible.length; j++) {
            imgVisible[j] = false;
        }
        img[11] = DoomChar;
        img[12] = Kaneki;
        img[13] = Apex;
        img[14] = oni;
        imgVisible[11] = true;
        imgVisible[12] = true;
        imgVisible[13] = true;
        imgVisible[14] = true;


    }
}

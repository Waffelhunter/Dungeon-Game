package org.example;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;

public class SpellManager {
   public static int spellType = 1;
    public static int state = 0;

    public static Boolean ShattersExist = false;

    public static int FireballCooldown;

    public static int IceShotCooldown;
    public static boolean IceShotUnlocked;
    public static int PoisonShotCooldown;
    public static boolean PoisonShotUnlocked;
    public static int ShatterShotCooldown;
    public static boolean ShatterShotUnlocked;

public static void setSpellType(int spell){
    switch(spell) {
        case 1:
            spellType = 1;
            break;
        case 2:
            if (ShatterShotUnlocked) {
                spellType = 2;

            }
            break;
        case 3:
            break;
        case 4:
            break;
    }



}


}









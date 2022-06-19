package org.example;

public class SpellManager {
    //defines the current spell type
    public static int spellType = 1;
    //indicates if a spell is currently used
    public static int state = 0;

    public static Boolean ShattersExist = false;

    public static int FireballCooldown;

    public static int IceShotCooldown;
    public static boolean IceShotUnlocked;
    public static int PoisonShotCooldown;
    public static boolean PoisonShotUnlocked;
    public static int ShatterShotCooldown;
    public static boolean ShatterShotUnlocked = true;


    public static void setSpellType(int spell) {
        switch (spell) {
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
            default:
                break;
        }


    }


}









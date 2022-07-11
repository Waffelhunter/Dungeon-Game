package org.example;


import de.gurkenlabs.litiengine.Game;

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
    private static double lastSwitchtime = 0;


    public static void setSpellType(int spell) {
        if (Game.loop().getTicks() > lastSwitchtime + 50) {
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
                    spellType = 3;
                    break;
                case 4:
                    spellType = 4;
                    break;
                default:
                    break;
            }
            lastSwitchtime = Game.loop().getTicks();


        }


    }
}









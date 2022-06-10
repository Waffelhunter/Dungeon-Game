package org.example;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;

public class SpellManager {
   public static int spellType = 1;
    public static int state = 0;

    public static Boolean ShattersExist = false;

    public static int FireballCooldown;
    public static int IceShotCooldown;
    public static int PoisonShotCooldown;
    public static int ShatterShotCooldown;

public static void setSpellType(int spell){
    spellType = spell;
}


}









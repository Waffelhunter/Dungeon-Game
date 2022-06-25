package org.example;

import creatures.Player;
import de.gurkenlabs.litiengine.Game;

public class ProjectileLogic {
    public static String fd;

    private ProjectileLogic() {

    }

    public static void spawnProjectile() {
        if (SpellManager.state == 0 && !Player.instance().isDead()) {
            fd = String.valueOf(Player.instance().getFacingDirection());
            switch (SpellManager.spellType) {
//Spell: Fireball
                case 1:
                    if (SpellManager.FireballCooldown == 0) {
                        SpellManager.state = 1;
                        SpellManager.FireballCooldown = 50;


                        Game.world().environment().add(Fireball.instance());

                        Fireball.instance().setLocation(Player.instance().getLocation());
                        Fireball.instance().setFacingDirection(Player.instance().getFacingDirection());
                    }
                    break;
//Spell: Shatter shot
                case 2:
                    if (SpellManager.ShatterShotCooldown == 0) {
                        SpellManager.state = 1;
                        SpellManager.ShatterShotCooldown = 100;


                        Game.world().environment().add(ShatterShot.instance());

                        ShatterShot.instance().setLocation(Player.instance().getLocation());
                        ShatterShot.instance().setFacingDirection(Player.instance().getFacingDirection());
                        break;
                    }
                case 3:
                    break;

            }

        }


    }
}
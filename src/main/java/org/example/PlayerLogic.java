package org.example;


import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;

public final class PlayerLogic {


    private PlayerLogic() {

    }

    public static void Logic() {

        Game.world().onLoaded(e -> {

            Spawnpoint summon = e.getSpawnpoint("summon");
            if (summon != null) {

                summon.spawn(Player.instance());


            }
        });
    }

    public static void respawn() {
        if (Player.instance().isDead()) {
            Player.instance().resurrect();
            Player.instance().hit(10 - Player.armor);
            Player.instance().setLocation(Game.world().environment().getSpawnpoint("summon").getLocation());

        }
    }
}




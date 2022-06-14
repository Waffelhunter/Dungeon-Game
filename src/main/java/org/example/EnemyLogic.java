package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;

public class EnemyLogic {

    private EnemyLogic() {

    }


    public static void Logic() {

        Game.world().onLoaded(e -> {

            Spawnpoint EnemySpawn = e.getSpawnpoint("EnemySpawn");


            if (EnemySpawn != null) {

                EnemySpawn.spawn(new Slime());


            }

        });
    }
}




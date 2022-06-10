package org.example;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.EntitySpawner;

import java.util.List;

public class EnemyLogic {

    private EnemyLogic() {

    }


    public static void Logic() {

        Game.world().onLoaded(e -> {

            Spawnpoint EnemySpawn = e.getSpawnpoint("EnemySpawn");








            if (EnemySpawn != null) {

                EnemySpawn.spawn(new Slime());


                System.out.println("spawned");

            }

        });
    }
}




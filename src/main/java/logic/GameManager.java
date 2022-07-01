package logic;


import creatures.Bookmonster;
import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;

import java.util.Collection;
import java.util.Optional;


public final class GameManager {
    public static boolean[] besucht;
    public static Environment[] maps;
    private GameManager() {
    }

    public static void init() {


        besucht = new boolean[22];
        maps = new Environment[22];

        int i = 0;
        Collection<Environment> mapCol = Game.world().getEnvironments();
        for (Environment e : mapCol) {
            maps[i] = e;
            besucht[i] = false;
            i++;

        }
        Game.world().onLoaded(GameManager::loaded);


    }

    private static void loaded(Environment e) {
        //spawnPlayer(e);
        spawnEnemy(e);

        Game.world().camera().setFocus(e.getCenter());

    }

    private static void spawnEnemy(Environment e) {
        //prüft, ob geladene map schonmal besucht wurde
        for (int i = 0; i < 22; i++) {

            if (maps[i] == e && (besucht[i] == false)) {

                //das passiert, bei erstem Besuch der map

                Optional<Spawnpoint> enemySpawn = Optional.ofNullable(e.getSpawnpoint("EnemySpawn"));
                // enemySpawn.ifPresent(s -> s.spawn(new Slime()));
                enemySpawn.ifPresent(s -> s.spawn(new Bookmonster()));

                besucht[i] = true;
            }
            if (maps[i] == e && (besucht[i] == true)) {
                //ruft respawn Enemy auf, wenn map schonmal besucht wurde.
                respawnEnemy(e, Game.random().nextInt(3));
            }

        }


    }

    private static void respawnEnemy(Environment e, int max) {


        Optional<Spawnpoint> enemySpawn = Optional.ofNullable(e.getSpawnpoint("EnemySpawn"));
        // enemySpawn.ifPresent(s -> s.spawn(new Slime()));
        if (Game.random().nextInt(2) == 2) {
            for (int i = 0; i < max; i++) {
                enemySpawn.ifPresent(s -> s.spawn(new Bookmonster()));
            }
        }

    }

    public static void spawnPlayer(Environment e, Prop p) {
        if (p == null) {
            Optional<Spawnpoint> summon = Optional.ofNullable(e.getSpawnpoint("s"));
            summon.ifPresent(s -> s.spawn(Player.instance()));
            return;
        }

        String spawn = "s";
        if (p.hasTag("s")) {
            spawn = "n";
        } else {
            if (p.hasTag("o")) {
                spawn = "w";
            } else {
                if (p.hasTag("w")) {
                    spawn = "o";
                }
            }
        }
        // Eine Tür soll dir dich vor eine bestimmte Tür in ein Raum spawnen und nicht einfach in der Mitte

        Optional<Spawnpoint> summon = Optional.ofNullable(e.getSpawnpoint(spawn));
        summon.ifPresent(s -> s.spawn(Player.instance()));
    }

    public static void respawn() {
        //spawnPlayer(Game.world().environment());
        if (Player.instance().isDead()) {
            Player.instance().resurrect();
            Player.instance().setLocation(Game.world().environment().getSpawnpoint("s").getLocation());
        }
    }
}

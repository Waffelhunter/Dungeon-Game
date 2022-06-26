package logic;



import creatures.Bookmonster;
import creatures.Player;
import creatures.Slime;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import java.util.Optional;


public final class GameManager {
    private GameManager() {}

    public static void init() {
        Game.world().onLoaded(GameManager::loaded);
    }

    private static void loaded(Environment e) {
        spawnPlayer(e);
        spawnEnemy(e);

        Game.world().camera().setFocus(e.getCenter());

    }

    private static void spawnEnemy(Environment e) {
        Optional<Spawnpoint> enemySpawn = Optional.ofNullable(e.getSpawnpoint("EnemySpawn"));
       // enemySpawn.ifPresent(s -> s.spawn(new Slime()));
        enemySpawn.ifPresent(s -> s.spawn(new Bookmonster()));
    }

    private static void spawnPlayer(Environment e) {
        Optional<Spawnpoint> summon = Optional.ofNullable(e.getSpawnpoint("summon"));
        summon.ifPresent(s -> s.spawn(Player.instance()));
    }

    public static void respawn() {
        //spawnPlayer(Game.world().environment());
        if (Player.instance().isDead()) {
            Player.instance().resurrect();
            Player.instance().setLocation(Game.world().environment().getSpawnpoint("summon").getLocation());
        }
    }
}

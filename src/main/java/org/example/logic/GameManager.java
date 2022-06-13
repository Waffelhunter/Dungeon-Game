package org.example.logic;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import java.util.Optional;
import org.example.entities.Player;
import org.example.entities.Slime;

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
    enemySpawn.ifPresent(s -> s.spawn(new Slime()));
  }

  private static void spawnPlayer(Environment e) {
    Optional<Spawnpoint> summon = Optional.ofNullable(e.getSpawnpoint("summon"));
    summon.ifPresent(s -> s.spawn(Player.instance()));
  }

  public static void respawn() {
    spawnPlayer(Game.world().environment());
  }
}

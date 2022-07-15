package logic;

import creatures.Dragon;
import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.entities.Trigger;
import de.gurkenlabs.litiengine.entities.TriggerEvent;
import de.gurkenlabs.litiengine.entities.TriggerListener;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.resources.Resources;

public class BossSpawner {
    private static final Environment Bossroom = Game.world().loadEnvironment("BossRoom");
    private static final Trigger bossTrigger = Bossroom.getTrigger("DragonAwakening");
    private static final Spawnpoint bossSpawn = Bossroom.getSpawnpoint("Bossspawn");
    public static boolean triggered;
    private static TriggerListener activationListener;

    public static void init() {
        bossTrigger.addActivatingCondition(event -> {
            if (bossTrigger.getBoundingBox().intersects(Player.instance().getCollisionBox())) {
                return null;
            }
            return String.valueOf(false);
        });
        activationListener = new TriggerListener() {

            @Override
            public void activated(TriggerEvent event) {
                bossSpawn.spawn(Dragon.instance());
                Game.world().camera().shake(8, 0, 600);
                Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/85568__joelaudio__dragon-roar.wav"));
                triggered = true;
            }

            @Override
            public String canActivate(TriggerEvent event) {
                return null;
            }

            @Override
            public void deactivated(TriggerEvent event) {

            }
        };
        bossTrigger.addActivatedListener(activationListener);
    }
}

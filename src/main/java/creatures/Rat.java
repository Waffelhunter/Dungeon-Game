package creatures;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@MovementInfo(velocity = 50)
@AnimationInfo(spritePrefix = {"ratte1"})
public class Rat extends Creature implements IUpdateable {
    //public String drops;
    private int angle;
    private long lastAngleChange;
    private int ANGLE_CHANGE_INTERVAL = 1000;

    public Rat() {
        super("ratte1");

    }

    @Override
    public void update() {
        final long currentTick = Game.loop().getTicks();

        if (angle == 0 || Game.time().since(lastAngleChange) > ANGLE_CHANGE_INTERVAL) {
            this.angle = Game.random().nextInt(360);
            this.lastAngleChange = currentTick;
            this.ANGLE_CHANGE_INTERVAL = Game.random().nextInt(2500, 3000);
            this.onCollision(event -> this.angle = Game.random().nextInt(360));
        }

        Game.physics().move(this, angle, this.getTickVelocity());
        return;
    }
}


package creatures;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;


@CombatInfo(hitpoints = 100)
@MovementInfo(velocity = 30)
@CollisionInfo(collision = true, collisionBoxWidth = 20, collisionBoxHeight = 20)


public class Bookmonster extends Creature implements IUpdateable {
    //public String drops;
    private int angle;
    private long lastAngleChange;
    private final int ANGLE_CHANGE_INTERVAL = 1000;

    private int lastFired = 200;


    public Bookmonster() {
        super("Bookmonster");
        this.setTeam(1);
        this.addTag("enemy");


    }

    @Override
    public void update() {

        if (this.getHitPoints().getRelativeCurrentValue() <= 0) {
            this.die();
            Game.world().environment().remove(this);
        }

        if (this.isDead()) {
            return;
        }

//            if (lastFired > 100) {
//                Player.instance().damage(1);
//               // lastFired = 0;
//            }


            final long currentTick = Game.loop().getTicks();

            if (angle == 0 || Game.time().since(lastAngleChange) > ANGLE_CHANGE_INTERVAL) {
                this.angle = Game.random().nextInt(360);
                this.lastAngleChange = currentTick;
                this.onCollision(event -> this.angle = Game.random().nextInt(360));
            }

            Game.physics().move(this, angle, this.getTickVelocity());
            return;
        }





        //lastFired++;
    }


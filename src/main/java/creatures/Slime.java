package creatures;

import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.emitters.EntityEmitter;
import props.Armor;
import props.life;


@CombatInfo(hitpoints = 100)
@MovementInfo(velocity = 30)
@CollisionInfo(collision = true, collisionBoxWidth = 20, collisionBoxHeight = 20)


public class Slime extends Creature implements IUpdateable {
    //public String drops;
    private int angle;
    private long lastAngleChange;
    private final int ANGLE_CHANGE_INTERVAL = 1000;

    private int lastHit = 200;
    private static final int STEP_DELAY = 360;
    private long lastWalkDust;


    public Slime() {
        super("Bookmonster");
        this.setTeam(1);
        this.addTag("enemy");
        onMoved(
                event -> {
                    if (Game.time().since(this.lastWalkDust) < STEP_DELAY) {
                        return;
                    }
                    this.lastWalkDust = Game.loop().getTicks();
                    EntityEmitter walkDust = new EntityEmitter(this, "slimeWalkEmitter", false);
                    Game.world().environment().add(walkDust);
                });
        onDeath(entity -> {
            int i = Game.random().nextInt(3);
            Prop h;
            switch (i) {
                case 0:
                    h = new life("life");
                    break;

                case 1:
                    h = new Armor();
                    break;

                default:
                    h = null;
                    break;
            }

            Game.world().environment().add(h);
            h.setLocation(this.getX(), this.getY());
        });
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
        if (this.getBoundingBox().intersects(Player.instance().getCollisionBox())) {
            if (lastHit > 100) {
                Player.instance().damage(1);
                lastHit = 0;
            }
        }
        if (Player.instance().isDead()) {
            final long currentTick = Game.loop().getTicks();

            if (angle == 0 || Game.time().since(lastAngleChange) > ANGLE_CHANGE_INTERVAL) {
                this.angle = Game.random().nextInt(360);
                this.lastAngleChange = currentTick;
            }

            Game.physics().move(this, angle, this.getTickVelocity());
            return;
        }


        Game.physics().move(this, Player.instance().getCenter(), this.getTickVelocity());

        lastHit++;
    }


//            if (this.getCollisionBox().intersects(Fireball.instance().getCollisionBox())){
//                this.hit(50);
//                Game.world().environment().remove(Fireball.instance());


}
//    @Override
//    public void die(){
//





package props;

import creatures.Player;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Prop;

@AnimationInfo(spritePrefix = {"prop-Spikes"})
@CombatInfo(hitpoints = 10)


public class Spikes extends Prop implements IUpdateable {
    private int inactiveTime;
    private int activeTime;
    private int hitTimer;
    private Boolean active = false;

    public Spikes(String spritesheetName) {
        super(spritesheetName);
        activeTime = 200;
        inactiveTime = 300;
    }

    @Override
    public void update() {

        if (this.getCollisionBox().intersects(Player.instance().getCollisionBox()) && hitTimer <= 0 && active == true) {
            Player.instance().damage(1);
            hitTimer = 70;
        }


        if (!this.isDead() && inactiveTime <= 0) {
            this.hit(100);
            activeTime = 200;
            active = true;
        }

        if (this.isDead() && activeTime <= 0) {
            this.resurrect();
            inactiveTime = 300;
            active = false;
        }
        activeTime--;
        inactiveTime--;
        hitTimer--;
    }
}

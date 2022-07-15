package props;

import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;

@CollisionInfo(collision = false)
@AnimationInfo(spritePrefix = "prop-life")

public class life extends Prop implements IUpdateable {
    public life(String spritesheetName) {
        super(spritesheetName);
    }

    @Override
    public void update() {
        if (this.getBoundingBox().intersects(Player.instance().getCollisionBox()) && Player.instance().getHitPoints().get() < Player.instance().getHitPoints().getMax()) {
            Game.world().environment().remove(this);

            Player.instance().hit(-5);


        }

    }
}

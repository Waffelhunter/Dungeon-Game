package props;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import creatures.Player;

@CollisionInfo(collision = false)
public class Armor extends Prop implements IUpdateable {
    public Armor() {
        super("Armor");
    }

    @Override
    public void update() {

        if (this.getBoundingBox().intersects(Player.instance().getCollisionBox()) && Player.armor < Player.maxArmor) {
            Game.world().environment().remove(this);
            Player.armor += 1;
        }
    }
}

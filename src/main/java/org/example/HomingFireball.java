package org.example;

import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@MovementInfo(velocity = 50)
@CollisionInfo(collision = false)
public class HomingFireball extends Creature implements IUpdateable {

    private int moves = 0;


    public HomingFireball(double x, double y) {
        super("DragonFire");
        this.setLocation(x, y);

    }

    @Override
    public void update() {

        if (moves < 400) {
            Game.physics().move(this, Player.instance().getCenter(), this.getTickVelocity());
            moves++;
        } else {
            Game.world().environment().remove(this);
            moves = 0;
        }
        if (this.getBoundingBox().intersects(Player.instance().getCollisionBox())) {
            Player.instance().damage(1);
            Game.world().environment().remove(this);
            moves = 0;

        }
    }
}

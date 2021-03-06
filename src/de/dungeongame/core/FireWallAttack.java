package de.dungeongame.core;

import de.dungeongame.entities.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;

@MovementInfo(velocity = 10)
@CollisionInfo(collision = false, collisionBoxHeight = 76, collisionBoxWidth = 19)
public class FireWallAttack extends Creature implements IUpdateable {
    private int moves = 0;
    private boolean hitPlayer;

    public FireWallAttack(double x, double y) {
        super("FireWall");
        this.setLocation(x, y);

    }

    @Override
    public void update() {

        if (moves < 150) {
            this.setLocation(this.getX() - 3, this.getY());
            moves++;

        } else {
            Game.world().environment().remove(this);
            moves = 0;

        }
        if (this.getBoundingBox().intersects(Player.instance().getCollisionBox()) && hitPlayer) {
            Player.instance().damage(2);
            hitPlayer = true;
        }
    }
}

package org.example.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;

@CollisionInfo(collision = false)
@AnimationInfo(spritePrefix = "prop-life")
public class Life extends Prop {
  public Life() {
    super("life");
    onCollision(
        c -> {
          if (c.getInvolvedEntities().contains(Player.instance())) {
            Game.world().environment().remove(this);
            Player.instance().getHitPoints().setToMax();
          }
        });
  }
}

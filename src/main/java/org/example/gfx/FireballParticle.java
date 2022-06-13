package org.example.gfx;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.PropState;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;
import de.gurkenlabs.litiengine.resources.Resources;
import java.awt.geom.Point2D;
import org.example.entities.Life;

public class FireballParticle extends SpriteParticle {
  private final Point2D origin;

  public FireballParticle(Point2D origin) {
    super(Resources.spritesheets().get("Feuerball"));
    this.origin = origin;
  }

  @Override
  public void update(Point2D emitterOrigin, float updateRatio) {
    super.update(emitterOrigin, updateRatio);
    for (Prop p : Game.world().environment().getProps("target")) {
      if (p.getCollisionBox().intersects(getBoundingBox(origin)) && !p.isDead()) {
        if (p.hasTag("explosive")) {
          p.die();
        } else {
          p.hit(50);
          if (p.getState() == PropState.DESTROYED && !p.hasTag("looted")) {
            Life h = new Life();
            Game.world().environment().add(h);
            h.setLocation(p.getX(), p.getY());
            p.addTag("looted");
            p.die();
          }
        }
      }
    }
  }
}

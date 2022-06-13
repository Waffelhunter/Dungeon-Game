package org.example.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;

@AnimationInfo(spritePrefix = {"prop-ExplosiveBarrel"})
public class ExplosiveBarrel extends Prop {
  private static String explodeAnimationName = "ExplosiveBarrel_explode";

  public ExplosiveBarrel() {
    super("prop-ExplosiveBarrel");
    animations().add(new Animation(Resources.spritesheets().get(explodeAnimationName), false));
    animations().get(explodeAnimationName).setDurationForAllKeyFrames(200);
  }

  @Override
  public void die() {
    super.die();
    animations().play(explodeAnimationName);
    hit(100);

    Game.audio().playSound(Resources.sounds().get("mixkit-shot-light-explosion-1682"), this);

    // Damages Player
    if (getCollisionBox().intersects(Player.instance().getBoundingBox())) {
      Player.instance().hit(5);
    }

    //    Destroy Props
    for (Prop p : Game.world().environment().getProps("target")) {
      if (getCollisionBox().intersects(p.getCollisionBox()) && !p.hasTag("explosive")) {
        p.die();
      }
    }

    // Opens Hidden Doors behind breakable Walls
    for (Prop p : Game.world().environment().getProps("breakablewall")) {
      if (p.getCollisionBox().intersects(this.getCollisionBox())) {
        p.die();
      }
    }
    super.die();
  }
}

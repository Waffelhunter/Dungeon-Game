package org.example.entities;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import org.example.abilities.InteractAbility;
import org.example.abilities.ShootFireballAbility;

@CollisionInfo(collisionBoxWidth = 21, collisionBoxHeight = 5, collision = true)
@MovementInfo(velocity = 80)
@EntityInfo(width = 32, height = 32)
@CombatInfo(hitpoints = 10)
public class Player extends Creature {
  private static Player instance;
  private InteractAbility interact;
  private ShootFireballAbility shoot;

  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }
    return instance;
  }

  private Player() {
    super("Hero");
    addController(new KeyboardEntityController<>(this));
    interact = new InteractAbility(this);
    shoot = new ShootFireballAbility(this);
    addEntityRenderListener(l -> ((Player) l.getEntity()).shoot.render(l.getGraphics()));
    addEntityRenderListener(l -> ((Player) l.getEntity()).interact.render(l.getGraphics()));
  }

  public void interact() {
    interact.cast();
  }

  public void shoot() {
    shoot.cast();
  }
}

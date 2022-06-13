package org.example.abilities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityExecution;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityPivotType;
import org.example.gfx.FireballEmitter;

@AbilityInfo(origin = EntityPivotType.COLLISIONBOX_CENTER, cooldown = 500, impact = 300)
public class ShootFireballAbility extends Ability {
  public ShootFireballAbility(Creature executor) {
    super(executor);
  }

  @Override
  public AbilityExecution cast() {
    Game.world().environment().add(new FireballEmitter(getExecutor()));
    return super.cast();
  }
}

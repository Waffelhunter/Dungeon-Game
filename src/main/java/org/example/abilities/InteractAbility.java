package org.example.abilities;

import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityExecution;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityPivotType;

@AbilityInfo(origin = EntityPivotType.COLLISIONBOX_CENTER, cooldown = 200, impact = 200)
public class InteractAbility extends Ability {
  public InteractAbility(Creature executor) {
    super(executor);
    addEffect(new InteractEffect(this));
  }

  @Override
  public AbilityExecution cast() {
    return super.cast();
  }
}

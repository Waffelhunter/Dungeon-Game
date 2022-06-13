package org.example.abilities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.effects.Effect;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import org.example.entities.InteractiveProp;
import org.example.entities.Life;
import org.example.entities.Spellscroll;

public class InteractEffect extends Effect {
  protected InteractEffect(Ability ability) {
    super(ability);
  }

  @Override
  protected void apply(ICombatEntity entity) {
    super.apply(entity);
    if (entity.getTags().contains("interactable")) {
      entity.setCollision(false);
      ((InteractiveProp) entity).spawnLoot(new Life());
    }

    if (entity.getTags().contains("door")) {
      entity.setCollision(false);
      Game.world().loadEnvironment(entity.getName());
    }

    if (entity.getTags().contains("spells")) {
      ((InteractiveProp) entity).spawnLoot(new Spellscroll(entity.getName()));
    }
  }
}

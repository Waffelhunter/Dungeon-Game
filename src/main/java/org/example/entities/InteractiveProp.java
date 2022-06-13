package org.example.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;

public class InteractiveProp extends Prop {
  public InteractiveProp(String spritesheetName) {
    super(spritesheetName);
  }

  public void spawnLoot(Prop... loot) {
    Game.world().environment().addAll(loot);
    for (Prop p : loot) {
      p.setLocation(getCenter());
    }
  }
}

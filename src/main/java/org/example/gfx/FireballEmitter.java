package org.example.gfx;

import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.graphics.emitters.EntityEmitter;
import de.gurkenlabs.litiengine.graphics.emitters.particles.Particle;

public class FireballEmitter extends EntityEmitter {
  public FireballEmitter(IEntity entity) {
    super(entity, "fireballEmitter");
  }

  @Override
  protected Particle createNewParticle() {
    return new FireballParticle(getCenter());
  }
}

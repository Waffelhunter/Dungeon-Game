package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.EntityMovedEvent;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.Emitter;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.geom.Point2D;

public class SlimeWalkDust implements IMobileEntity.EntityMovedListener {
    @Override
    public void moved(EntityMovedEvent event) {
        if(Game.world().environment() == null){
            return;
        }

        String walkSprite = "SlimeWalk.png";
        Spritesheet walkDustSprite= Resources.spritesheets().get("SlimeWalk.png");
        Point2D walkLocation = new Point2D.Double(event.getEntity().getCollisionBoxCenter().getX() - walkDustSprite.getSpriteWidth() / 2.0, event.getEntity().getCollisionBoxCenter().getY() - walkDustSprite.getSpriteHeight() / 2.0);
        Emitter walkDust = new Emitter(walkLocation,walkSprite);
        walkDust.setSize(walkDustSprite.getSpriteWidth(), walkDustSprite.getSpriteHeight());
        Game.world().environment().add(walkDust);
    }
}

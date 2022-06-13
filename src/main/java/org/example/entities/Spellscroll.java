package org.example.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;
@CollisionInfo(collision = false)
@AnimationInfo(spritePrefix = {"prop-Blitz"})
public class Spellscroll extends Prop implements IUpdateable {
    public Spellscroll(String spritesheetName) {
        super(spritesheetName);
    }

    @Override
    public void update() {
//        if(this.getBoundingBox().intersects(Player.instance().getCollisionBox())){
//            Game.world().environment().remove(this);
//
//        }
    }
}

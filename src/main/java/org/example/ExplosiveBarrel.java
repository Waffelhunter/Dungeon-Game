package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.function.Supplier;

@AnimationInfo(spritePrefix = {"prop-ExplosiveBarrel"})

public class ExplosiveBarrel extends Prop implements IUpdateable {
    private static String explodeAnimationName = "ExplosiveBarrel_explode";
Animation explosion = new Animation(explodeAnimationName,false,false,200);

    public ExplosiveBarrel() {
        super("prop-ExplosiveBarrel");
        animations().add(new Animation(Resources.spritesheets().get(explodeAnimationName), false));



    }
public void init(){
   this.animations().add(new Animation(Resources.spritesheets().get(explodeAnimationName), false));
    animations().get(explodeAnimationName).setDurationForAllKeyFrames(20000);
}



    @Override
    public void die() {
        //this.init();
        animations().play(explodeAnimationName);
        this.explosion.start();


        this.addTag("hit");
        this.hit(100);



        Game.audio().playSound(Resources.sounds().get("mixkit-shot-light-explosion-1682"),this);

        this.setCollisionBoxHeight(20);
        this.setCollisionBoxWidth(20);
        this.setCollision(false);



        //Damages Player
        if (this.getCollisionBox().intersects(Player.instance().getBoundingBox())) {
            Player.instance().hit(5);

        }


        //Damages Props
       Collection<Prop> propses = Game.world().environment().getProps("target");
        for (Prop p : propses) {

            if (p.getCollisionBox().intersects(this.getCollisionBox())&&!p.hasTag("explosive")) {

                p.die();

    }


        }
        //Opens Hidden Doors behind breakable Walls
        Collection<Prop> walls = Game.world().environment().getProps("breakablewall");
        for (Prop p : walls) {


            if (p.getCollisionBox().intersects(this.getCollisionBox())) {

                p.die();

            }
        }
        super.die();
    }



    @Override
    public void update() {

        if (!animations().isPlaying(explodeAnimationName)) {

            //super.die();

        }
    }
}


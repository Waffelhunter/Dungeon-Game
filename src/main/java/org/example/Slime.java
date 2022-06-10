package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.physics.Collision;

import java.awt.geom.Rectangle2D;
import java.util.Collection;


@CombatInfo(hitpoints = 100)
@MovementInfo(velocity = 30)
@CollisionInfo(collision = true,collisionBoxWidth = 20,collisionBoxHeight = 20)


public class Slime extends Creature implements IUpdateable {
    //public String drops;
    private int angle;
    private long lastAngleChange;
    private int ANGLE_CHANGE_INTERVAL = 1000;




    public Slime(){
        super("Slime");
        this.setTeam(2);
        this.addTag("enemy");



    }
    @Override
    public void update() {

        if(this.getHitPoints().getRelativeCurrentValue()<= 0) {
            this.die();
            Game.world().environment().remove(this);
        }
        if(this.isDead()){
            return;
        }
        final long currentTick = Game.loop().getTicks();

        if(angle == 0|| Game.time().since(lastAngleChange) > ANGLE_CHANGE_INTERVAL){
            this.angle = Game.random().nextInt(360);
            this.lastAngleChange = currentTick;
        }
        Game.physics().move(this,angle, this.getTickVelocity());


        }





//            if (this.getCollisionBox().intersects(Fireball.instance().getCollisionBox())){
//                this.hit(50);
//                Game.world().environment().remove(Fireball.instance());


    }
//    @Override
//    public void die(){
//





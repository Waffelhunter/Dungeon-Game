package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;


@CombatInfo(hitpoints = 50)
@MovementInfo(velocity = 40)


public class Slime extends Creature implements IUpdateable {
    //public String drops;
    private int angle;
    private long lastAngleChange;
    private int ANGLE_CHANGE_INTERVAL = 2000;

    private static Slime instance;

    public static Slime instance() {
        if (instance == null) {
            instance = new Slime();
        }
        return instance;

    }

    public Slime(){
        super("Slime");
        this.setTeam(2);



    }
    @Override
    public void update() {
        if(this.getHitPoints().getRelativeCurrentValue()<= 0) {
            this.die();
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





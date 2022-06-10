package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.physics.MovementController;

@CollisionInfo(collisionBoxWidth = 21,collisionBoxHeight =5,collision = true)
@MovementInfo(velocity = 80)
@EntityInfo(width = 32,height =32 )
@CombatInfo(hitpoints = 10)


public class Player extends Creature implements IUpdateable {
    private static Player instance;
    static CollisionBox c = new CollisionBox(10,10);




    public static Player instance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;

    }

    private Player() {
        super("Hero");
    }




    @Override
    public void update() {
        c.setCollision(false);
        c.setLocation(Player.instance().getLocation());

        if (this.getHitPoints().getRelativeCurrentValue() == 0) {

            this.die();
        }
        if (SpellManager.FireballCooldown > 0) {
            SpellManager.FireballCooldown--;
        }
        if (SpellManager.ShatterShotCooldown > 0) {
            SpellManager.ShatterShotCooldown--;
        }
    }

    @Override
    protected IMovementController createMovementController() {

        return new KeyboardEntityController<>(this);
    }
}
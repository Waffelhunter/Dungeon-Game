package org.example.entities;

import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import org.example.abilities.InteractAbility;
import org.example.abilities.ShootFireballAbility;

@CollisionInfo(collisionBoxWidth = 21, collisionBoxHeight = 5, collision = true)
@MovementInfo(velocity = 80)
@EntityInfo(width = 32, height = 32)
@CombatInfo(hitpoints = 10)
public class Player extends Creature {
    public static int armor = 10;
    public static int maxArmor = 10;
    private static Player instance;
    private final InteractAbility interact;
    private final ShootFireballAbility shoot;

    private Player() {
        super("Hero");
        addController(new KeyboardEntityController<>(this));
        interact = new InteractAbility(this);
        shoot = new ShootFireballAbility(this);
        addEntityRenderListener(l -> ((Player) l.getEntity()).shoot.render(l.getGraphics()));
        addEntityRenderListener(l -> ((Player) l.getEntity()).interact.render(l.getGraphics()));
    }

    public static Player instance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public boolean hasArmor() {
        return armor != 0;
    }

    public void interact() {
        interact.cast();
    }

    public void shoot() {
        shoot.cast();
    }

    public void damage(int damage) {
        if (this.hasArmor()) {
            int damager = damage;
            if (armor >= damager) {
                armor = armor - damager;

            } else {
                damager = damager - armor;
                armor = 0;
                this.hit(damager);
            }
        } else {
            this.hit(damage);
        }
    }
}


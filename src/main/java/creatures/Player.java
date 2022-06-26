package creatures;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import org.example.SpellManager;

@CollisionInfo(collisionBoxWidth = 21, collisionBoxHeight = 5, collision = true)
@MovementInfo(velocity = 80)
@EntityInfo(width = 32, height = 32)
@CombatInfo(hitpoints = 10)


public class Player extends Creature implements IUpdateable {
    static CollisionBox c = new CollisionBox(10, 10);
    public static int armor = 10;
    public static int maxArmor = 10;
    private static Player instance;


    private Player() {
        super("Hero");
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
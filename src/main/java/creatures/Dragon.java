package creatures;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;
import org.example.FireWallAttack;
import org.example.HomingFireball;

@CombatInfo(hitpoints = 1000)
@CollisionInfo(collisionBoxWidth = 90, collisionBoxHeight = 70, valign = Valign.MIDDLE, collision = true)
public class Dragon extends Creature implements IUpdateable {

    private static Dragon instance;
    private int WallAttacktimer = 300;
    private int HomingAttacktimer = 700;

    public Dragon() {
        super("Dragon");
        this.addTag("enemy");
        this.animations().add(new Animation(Resources.spritesheets().get("DragonAttackAnimation"), false));
        onHit(event -> {
            animations().play("DragonAttackAnimation");
        });


    }

    public static Dragon instance() {
        if (instance == null) {
            instance = new Dragon();

        }
        return instance;

    }

    private void WallAttack() {
        Game.world().environment().add(new FireWallAttack(this.getX() - 40, this.getY()));
    }

    private void HomingAttack() {
        Game.world().environment().add(new HomingFireball(this.getX() - 50, this.getY()));
        Game.world().environment().add(new HomingFireball(this.getX() - 30, this.getY() + 20));
        Game.world().environment().add(new HomingFireball(this.getX() - 30, this.getY() - 20));
    }

    @Override
    public void update() {
        if (this.isDead()) {
            return;
        }
        if (WallAttacktimer == 0) {
            this.WallAttack();
            WallAttacktimer = 300;
        }
        if (this.HomingAttacktimer == 0) {
            this.HomingAttack();
            HomingAttacktimer = 700;
        }
        WallAttacktimer--;
        HomingAttacktimer--;
    }
}

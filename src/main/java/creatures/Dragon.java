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

@CombatInfo(hitpoints = 1000)
@CollisionInfo(collisionBoxWidth = 90,collisionBoxHeight = 90,valign = Valign.MIDDLE, collision = true)
public class Dragon extends Creature implements IUpdateable {

    private int WallAttacktimer = 300;

public Dragon(){
    super("Dragon");
    this.addTag("enemy");
    this.animations().add(new Animation(Resources.spritesheets().get("DragonAttackAnimation"),false));
    onHit(event -> {animations().play("DragonAttackAnimation");});



}
private void WallAttack(){
    Game.world().environment().add(new FireWallAttack(this.getX()-40,this.getY()));
}

    @Override
    public void update() {
    if(this.isDead()){
        return;
    }
    if (WallAttacktimer == 0){
       this.WallAttack();
       WallAttacktimer = 300;
    }
WallAttacktimer --;
    }
}

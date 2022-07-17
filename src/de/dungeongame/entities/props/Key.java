package de.dungeongame.entities.props;

import de.dungeongame.entities.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.Valign;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;

@CollisionInfo(collision = false, valign = Valign.MIDDLE,collisionBoxWidth = 10,collisionBoxHeight = 10)
public class Key extends Prop implements IUpdateable {
    private int opens;
    private String Name;
    public Key(int DoorIndex,String UnlockedRoom) {
        super("Key");
        this.setSize(64, 64);
        opens = DoorIndex;
        Name = UnlockedRoom;
    }

    @Override
    public void update() {
        if (this.getCollisionBox().intersects(Player.instance().getCollisionBox())) {
            Game.world().environment().remove(this);
            InteractableObjects.Keys[opens] = true;
            InteractableObjects.PickUpKey(Name);
        }
    }
}

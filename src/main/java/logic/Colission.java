package logic;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.Prop;
import props.InteractableObjects;

import java.util.Collection;

public class Colission {


    public static Boolean GibColision(Creature Spell) {

        Collection<CollisionBox> boxes = Game.world().getEnvironment(InteractableObjects.currentMap).getCollisionBoxes();
        for (CollisionBox box : boxes) {

            if (box.getCollisionBox().intersects(Spell.getCollisionBox())) {
                return true;

            }

        }
        Collection<Prop> propses = Game.world().environment().getProps("target");
        for (Prop p : propses) {

            if (p.getCollisionBox().intersects(Spell.getCollisionBox()) && !p.isDead()) {
                return true;

            }

        }

        Collection<Prop> Storage = Game.world().environment().getProps("interactable");
        for (Prop p : Storage) {

            if (p.getCollisionBox().intersects(Spell.getCollisionBox())) {
                return true;

            }


        }
        Collection<Creature> Enemys = Game.world().environment().getCreatures("enemy");
        for (Creature c : Enemys) {
            return c.getCollisionBox().intersects(Spell.getCollisionBox());

        }
        return false;
    }

    public void Collision() {

    }
}


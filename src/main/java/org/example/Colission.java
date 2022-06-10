package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.physics.Collision;

import java.util.Collection;

public class Colission {


    public void Collision() {

    }


    public static Boolean GibColision(Creature Spell) {

        Collection<CollisionBox> boxes = Game.world().getEnvironment(InteractableObjects.currentMap).getCollisionBoxes();
        for (CollisionBox box : boxes) {

            if (box.getCollisionBox().intersects(Spell.getCollisionBox())) {
                return true;

            }

        }
        Collection<Prop> propses = Game.world().environment().getProps("target");
        for (Prop p : propses) {

            if (p.getCollisionBox().intersects(Spell.getCollisionBox())&&!p.isDead()) {
                return true;

            }

        }

        Collection<Prop> Storage = Game.world().environment().getProps("interactable");
        for (Prop p : Storage) {

            if (p.getCollisionBox().intersects(Spell.getCollisionBox())) {
                return true;

            }



        }
return false;

    }
}


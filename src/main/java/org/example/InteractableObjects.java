package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.Entity;
import de.gurkenlabs.litiengine.entities.Prop;

import java.util.Collection;

public class InteractableObjects extends Entity {

    static CollisionBox collider = new CollisionBox(20, 20);
    public static String currentMap = "Bibliothek";

    public InteractableObjects() {

    }

    public static void Interact() {
        Collection<Prop> Storage = Game.world().environment().getProps("interactable");
        Collection<Prop> Gateway = Game.world().environment().getProps("door");
        for (Prop p : Storage) {
            collider.setLocation(p.getCenter());

            collider.setCollision(false);
            collider.setName("collider");

            //colision Box collider is set to the current Prop p position
            //if collider intersects with the Players bounds, use hit() to change the state of the Prop to destroyed

            if (Player.instance().getCollisionBox().intersects(collider.getCollisionBox())) {
                    p.hit(100);

                    //spawn the Loot that's in the chest
                    life h = new life("life");
                    Game.world().environment().add(h);
                    h.setLocation(p.getX()-5,p.getY()-5);
                    p.setCollision(true);


                }
            //Door interaction
            for (Prop pr: Gateway){
                pr.setCollision(false);
                if(pr.getCollisionBox().intersects(Player.instance().getBoundingBox())){

                    Game.world().unloadEnvironment();
                    Game.world().loadEnvironment(pr.getName());
                    currentMap = pr.getName();

                    Game.world().camera().setFocus( Game.world().loadEnvironment(pr.getName()).getCenter());

                    PlayerLogic.Logic();

                }
            }
            }


        }
    }


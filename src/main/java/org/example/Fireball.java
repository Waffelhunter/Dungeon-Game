package org.example;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.entities.behavior.State;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.Collision;
import de.gurkenlabs.litiengine.physics.IMovementController;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;


@CollisionInfo(collisionBoxWidth = 10,collisionBoxHeight =10,collision = true)
@MovementInfo(velocity = 60)
@EntityInfo(width = 35,height =41 )
@CombatInfo(hitpoints = 1)
public class Fireball extends Creature implements IUpdateable {
    private static Fireball instance;


    private static int moves = 0;




    public static Fireball instance() {
        if (instance == null) {
            instance = new Fireball();
            moves = 0;
        }
        return instance;
    }

    private Fireball() {
        super("Feuerball");

        moves = 0;
    }













    @Override
    public void update() {


        Fireball fireball = instance();
        if (!(Colission.GibColision(fireball))) {
            if (moves < 50) {
                switch (ProjectileLogic.fd) {
                    case "RIGHT":

                        fireball.setLocation(fireball.getX() + 3, fireball.getY());
                        moves++;
                        break;
                    case "LEFT":
                        fireball.setLocation(fireball.getX() + -3, fireball.getY());
                        moves++;
                        break;
                    case "UP":
                        fireball.setLocation(fireball.getX(), fireball.getY() - 3);
                        moves++;
                        break;
                    case "DOWN":
                        fireball.setLocation(fireball.getX(), fireball.getY() + 3);
                        moves++;
                        break;
                    default:
                        moves++;
                        break;

                }
            } else {

                Game.world().environment().remove(fireball);
                moves = 0;
                SpellManager.state = 0;

            }


        } else {



            Collection<Prop> propses = Game.world().environment().getProps("target");
            for (Prop p : propses) {

                if (p.getCollisionBox().intersects(fireball.getCollisionBox())&&!p.isDead()) {
                    if (p.hasTag("explosive")) {


                        p.die();


                    }
                    else{
                    p.hit(50);

                    if(p.getState()== PropState.DESTROYED&&!p.hasTag("looted")) {
                        life h = new life("life");
                        Game.world().environment().add(h);
                        h.setLocation(p.getX(), p.getY());
                        p.addTag("looted");
                        p.die();
                    }
                    }

                }

                Game.world().environment().remove(fireball);
                moves = 0;
              SpellManager.state = 0;

            }
        }
    }
}


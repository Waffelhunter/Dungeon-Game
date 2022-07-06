package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.PropState;
import logic.Colission;
import props.life;

import java.awt.geom.Point2D;
import java.util.Collection;


@CollisionInfo(collisionBoxWidth = 4, collisionBoxHeight = 4, collision = false)
@EntityInfo(width = 7, height = 7)
@CombatInfo(hitpoints = 1)

public class ShatterShards extends Creature implements IUpdateable {
    private final int Xvel;
    private final int Yvel;

    private int moves;
    private Boolean hit = false;


    public ShatterShards(Point2D spawn, int Xvel, int Yvel) {
        super("Splitball");
        this.Xvel = Xvel;
        this.Yvel = Yvel;

        this.moves = 0;
        Game.world().environment().add(this);
        this.setLocation(spawn);


    }

    public void respawn(Point2D spawn) {
        this.moves = 0;
        this.setLocation(spawn);
        Game.world().environment().add(this);
    }

    @Override
    public void update() {
        if (!(Colission.GibColision(this))) {
            if (moves < 30) {
                this.setLocation(this.getX() + Xvel, this.getY() + Yvel);
                moves++;
            } else {
                Game.world().environment().remove(this);
                moves = 0;
            }


        } else {

            //Dammage und zerstörung

            Collection<Prop> propses = Game.world().environment().getProps("target");
            for (Prop p : propses) {

                if (p.getCollisionBox().intersects(this.getCollisionBox()) && !p.isDead()) {
                    if (p.hasTag("explosive")) {


                        p.die();


                    } else {
                        p.hit(20);
                        //burn(p);
                        if (p.getState() == PropState.DESTROYED && !p.hasTag("looted")) {
                            life h = new life("life");
                            Game.world().environment().add(h);
                            h.setLocation(p.getX(), p.getY());
                            p.addTag("looted");
                            p.die();
                        }
                    }
                }

                Game.world().environment().remove(this);
                moves = 0;

            }
            Collection<Creature> Enemys = Game.world().environment().getCreatures("enemy");
            for (Creature c : Enemys) {
                if (c.getCollisionBox().intersects(this.getCollisionBox())&& hit == false) {
                    c.hit(20);
                    hit = true;
                    Game.world().environment().remove(this);
                    moves = 0;
                }



            }

        }

    }
}

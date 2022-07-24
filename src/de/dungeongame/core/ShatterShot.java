package de.dungeongame.core;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.PropState;
import de.dungeongame.logic.Colission;
import de.dungeongame.entities.props.life;

import java.awt.geom.Point2D;
import java.util.Collection;

@CollisionInfo(collisionBoxWidth = 10, collisionBoxHeight = 10, collision = true)
@EntityInfo(width = 35, height = 41)
@CombatInfo(hitpoints = 1)

public class ShatterShot extends Creature implements IUpdateable {

    private static ShatterShot instance;

    private static int moves = 0;
    ShatterShards s1;
    ShatterShards s2;
    ShatterShards s3;
    ShatterShards s4;
    ShatterShards s5;
    ShatterShards s6;
    ShatterShards s7;
    ShatterShards s8;
    private Boolean fullyShattered;


    private ShatterShot() {
        super("Blitzshot");

        moves = 0;
    }

    public static ShatterShot instance() {
        if (instance == null) {
            instance = new ShatterShot();
            moves = 0;
        }
        return instance;
    }

    public void shatter() {
        Point2D currentPos = ShatterShot.instance().getCenter();
        if (!SpellManager.ShattersExist) {
            SpellManager.ShattersExist = true;

            s1 = new ShatterShards(currentPos, 2, 0);
            s2 = new ShatterShards(currentPos, 0, 2);
            s3 = new ShatterShards(currentPos, -2, 0);
            s4 = new ShatterShards(currentPos, 0, -2);
            s5 = new ShatterShards(currentPos, 1, 1);
            s6 = new ShatterShards(currentPos, -1, -1);
            s7 = new ShatterShards(currentPos, 1, -1);
            s8 = new ShatterShards(currentPos, -1, 1);
            fullyShattered = true;

        }

        s1.respawn(currentPos);
        s2.respawn(currentPos);
        s3.respawn(currentPos);
        s4.respawn(currentPos);
        s5.respawn(currentPos);
        s6.respawn(currentPos);
        s7.respawn(currentPos);
        s8.respawn(currentPos);
        fullyShattered = true;


    }

    public void removeShot() {
        Game.world().environment().remove(ShatterShot.instance());
        SpellManager.state = 0;
        moves = 0;
    }

    @Override
    public void update() {


        ShatterShot shatter = instance();
        if (!(Colission.GibColision(shatter))) {
            if (moves < 30) {
                switch (ProjectileLogic.fd) {
                    case "RIGHT":

                        shatter.setLocation(shatter.getX() + 3, shatter.getY());
                        moves++;
                        break;
                    case "LEFT":
                        shatter.setLocation(shatter.getX() + -3, shatter.getY());
                        moves++;
                        break;
                    case "UP":
                        shatter.setLocation(shatter.getX(), shatter.getY() - 3);
                        moves++;
                        break;
                    case "DOWN":
                        shatter.setLocation(shatter.getX(), shatter.getY() + 3);
                        moves++;
                        break;
                    default:
                        moves++;
                        break;

                }
            } else {
                shatter.shatter();

                this.removeShot();


            }


        } else {

            //Dammage und zerstörung

            Collection<Prop> propses = Game.world().environment().getProps("target");
            for (Prop p : propses) {

                if (p.getCollisionBox().intersects(shatter.getCollisionBox()) && !p.isDead()) {
                    if (p.hasTag("explosive")) {


                        p.die();


                    } else {
                        p.hit(50);
                        if (p.getState() == PropState.DESTROYED && !p.hasTag("looted")) {
                            life h = new life("life");
                            Game.world().environment().add(h);
                            h.setLocation(p.getX(), p.getY());
                            p.addTag("looted");
                            p.die();
                        }
                    }

                }
                shatter.shatter();
                this.removeShot();


            }
            Collection<Creature> Enemys = Game.world().environment().getCreatures("enemy");
            for (Creature c : Enemys) {
                if (c.getCollisionBox().intersects(this.getCollisionBox())) {
                    c.hit(50);
                }
                this.removeShot();
                this.shatter();

            }
        }
    }
}


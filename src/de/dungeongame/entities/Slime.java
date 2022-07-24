package de.dungeongame.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.dungeongame.logic.GameManager;
import de.dungeongame.entities.props.Armor;
import de.dungeongame.entities.props.Key;
import de.dungeongame.entities.props.life;


@CombatInfo(hitpoints = 100)
@MovementInfo(velocity = 30)
@CollisionInfo(collision = true, collisionBoxWidth = 20, collisionBoxHeight = 20)


public class Slime extends Creature implements IUpdateable {
    private static final int STEP_DELAY = 360;
    private static final int ANGLE_CHANGE_INTERVAL = 1000;

    private int angle;
    private long lastAngleChange;
    private int lastHit = 200;
    private long lastWalkDust;


    public Slime() {

        super("Slime");
        this.setTeam(1);
        this.addTag("enemy");
        GameManager.anzahlMonster++;
        System.out.println("nach Slime() = " + GameManager.anzahlMonster + "\t erwartet: 1");

        onMoved(
                event -> {
                    if (Game.time().since(this.lastWalkDust) < STEP_DELAY) {
                        return;
                    }
                    this.lastWalkDust = Game.loop().getTicks();

                });
        onDeath(entity -> {
            int i = Game.random().nextInt(3);
            Prop h;
            switch (i) {
                case 0:
                    h = new life("life");
                    break;

                case 1:
                    h = new Armor();
                    break;

                case 2:
                    h = new Key(0,"Turm");
                    break;

                default:
                    h = null;
                    break;

            }

            GameManager.anzahlMonster = GameManager.anzahlMonster - 1;
            System.out.println("nach onDeath = " + GameManager.anzahlMonster + "\t erwartet: 0");

            if (h != null) {
                Game.world().environment().add(h);
                h.setLocation(this.getX(), this.getY());

            }
        });
    }


    @Override
    public void update() {

        if (this.getHitPoints().getRelativeCurrentValue() <= 0) {
            this.die();
            Game.world().environment().remove(this);
        }

        if (this.isDead()) {
            return;
        }
        if (this.getBoundingBox().intersects(Player.instance().getCollisionBox())&&lastHit > 100) {

                Player.instance().damage(1);
                lastHit = 0;

        }
        if (Player.instance().isDead()) {
            final long currentTick = Game.loop().getTicks();

            if (angle == 0 || Game.time().since(lastAngleChange) > ANGLE_CHANGE_INTERVAL) {
                this.angle = Game.random().nextInt(360);
                this.lastAngleChange = currentTick;
            }
            Game.physics().move(this, angle, this.getTickVelocity());
            return;
        }


        Game.physics().move(this, Player.instance().getCenter(), this.getTickVelocity());

        lastHit++;
    }


}





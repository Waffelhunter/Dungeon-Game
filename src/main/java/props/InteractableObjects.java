package props;

import UI.HUD;
import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;

import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.resources.Resources;
import logic.GameManager;
import org.example.Fireball;
import org.example.ShatterShot;
import org.example.SpellManager;

import java.util.Collection;

public class InteractableObjects  {

    public static String currentMap = "atrium";
    public static long lastDoorInteraction;
    public static boolean[] Keys = new boolean[4];
    static CollisionBox collider = new CollisionBox(20, 20);
    public static String KeyPickUpName;
    public static Boolean pickedUp;

    public static void Interact() {
        Collection<Prop> Storage = Game.world().environment().getProps("interactable");
        Collection<Prop> Gateway = Game.world().environment().getProps("door");
        Collection<Prop> Spellcrates = Game.world().environment().getProps("spells");

        for (Prop p : Storage) {

            collider.setLocation(p.getCenter());
            collider.setCollision(false);
            collider.setName("collider");

            //colision Box collider is set to the current Prop p position
            //if collider intersects with the Players bounds, use hit() to change the state of the Prop to destroyed

            if (Player.instance().getCollisionBox().intersects(collider.getCollisionBox()) && !p.isDead()) {
                p.hit(100);
                Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/573654__the-frisbee-of-peace__wooden-chest-open.wav"));

                //spawn the Loot that's in the chest
                life h = new life("life");
                Game.world().environment().add(h);
                h.setLocation(p.getX() - 5, p.getY() - 5);
                p.setCollision(true);


            }
        }
        //Door interaction
        for (Prop pr : Gateway) {

            pr.setCollision(false);
            if (pr.getBoundingBox().intersects(Player.instance().getBoundingBox()) && Game.time().since(lastDoorInteraction) > 300 && GameManager.anzahlMonster == 0) {
                if (pr.hasTag("lock")) {
                    for (int i = 0; i < Keys.length; i++) {
                        if (Keys[i] == true) {
                            if (pr.hasTag(String.valueOf(i))) {

                                pr.removeTag("lock");
                                break;
                            }
                        }
                    }
                    if (pr.hasTag("lock")) {


                        Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/219487__jarredgibb__door-cupboard-07.wav"));
                        HUD.renderLock = true;
                        lastDoorInteraction = Game.loop().getTicks();
                        return;
                    }
                }

                System.out.println("nach Gateway = " + GameManager.anzahlMonster);

                lastDoorInteraction = Game.loop().getTicks();
                Game.world().environment().remove(Fireball.instance());
                Game.world().environment().remove(ShatterShot.instance());
                SpellManager.state = 0;

                Game.world().loadEnvironment(pr.getName());

                currentMap = pr.getName();
                Game.world().camera().setFocus(Game.world().environment().getCenter());
                GameManager.spawnPlayer(Game.world().environment(), pr);

            }
        }

        for (Prop s : Spellcrates) {

            collider.setLocation(s.getCenter());
            collider.setCollision(false);
            collider.setName("collider");

            if (Player.instance().getCollisionBox().intersects(collider.getCollisionBox())) {
                Spellscroll spell = new Spellscroll(s.getName());
                Game.world().environment().add(spell);
                spell.setLocation(s.getX(), s.getY());

                s.die();
                s.setCollision(true);

            }
        }
    }
    //called by key to render the Message
    public static void PickUpKey(String Name){
        KeyPickUpName = Name;
        pickedUp = true;
    }
}









package UI;

import de.gurkenlabs.litiengine.input.Input;
import logic.GameManager;
import org.example.ProjectileLogic;
import org.example.SpellManager;
import props.InteractableObjects;

import java.awt.event.KeyEvent;

//import static org.example.ProjectileLogic.spawnProjectile;

public class UserInput {


    private UserInput() {
    }


    public static void Input() {
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_SPACE, event -> ProjectileLogic.spawnProjectile());
        Input.keyboard().onKeyPressed(KeyEvent.VK_E, event -> InteractableObjects.Interact());
        Input.keyboard().onKeyPressed(KeyEvent.VK_R, e -> GameManager.respawn());
        Input.keyboard().onKeyPressed(KeyEvent.VK_1, e -> SpellManager.setSpellType(1));
        Input.keyboard().onKeyPressed(KeyEvent.VK_2, e -> SpellManager.setSpellType(2));
        Input.keyboard().onKeyPressed(KeyEvent.VK_3, e -> SpellManager.setSpellType(3));
        Input.keyboard().onKeyPressed(KeyEvent.VK_4, e -> SpellManager.setSpellType(4));


    }


}
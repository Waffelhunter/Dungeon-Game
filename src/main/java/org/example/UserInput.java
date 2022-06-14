package org.example;

import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.KeyEvent;

//import static org.example.ProjectileLogic.spawnProjectile;

public class UserInput {


    private UserInput() {
    }


    public static void Input() {
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
        Input.keyboard().onKeyPressed(KeyEvent.VK_SPACE, event -> ProjectileLogic.spawnProjectile());
        Input.keyboard().onKeyPressed(KeyEvent.VK_E, event -> InteractableObjects.Interact());
        Input.keyboard().onKeyPressed(KeyEvent.VK_ENTER, e -> PlayerLogic.respawn());
        Input.keyboard().onKeyPressed(KeyEvent.VK_1, e -> SpellManager.setSpellType(1));
        Input.keyboard().onKeyPressed(KeyEvent.VK_2, e -> SpellManager.setSpellType(2));
        Input.keyboard().onKeyPressed(KeyEvent.VK_3, e -> SpellManager.setSpellType(3));


    }


}
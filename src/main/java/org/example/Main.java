package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.resources.Resources;

import org.example.UI.InGameScreen;
import org.example.entities.BreakableWall;
import org.example.entities.ExplosiveBarrel;
import org.example.entities.Player;
import org.example.logic.GameManager;

public class
Main {
    public static void main(String[] args) {

        Game.init(args);
        GameManager.init();

        Resources.load("resources/Test1.litidata");

        Game.graphics().setBaseRenderScale(3);

        //create new Screen from class InGameScreen()
        Game.screens().add(new InGameScreen());
        Camera c1 = new Camera();


        c1.getViewport();
        Game.world().camera().setFocus(290,245);

        Game.audio().setListenerLocationCallback(e -> Player.instance().getCenter());
        Game.audio().setMaxDistance(1000);

        PropMapObjectLoader.registerCustomPropType(ExplosiveBarrel.class);
        PropMapObjectLoader.registerCustomPropType(BreakableWall.class);


        //loads the inserted map path
        //file must be a tmx file, and loaded in /src
        Game.world().loadEnvironment("Bibliothek");




        Game.start();
    }
}

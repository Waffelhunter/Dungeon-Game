package org.example;

import UI.MenuScreen;
import UI.UserInput;
import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.resources.Resources;
import props.BreakableWall;
import props.ExplosiveBarrel;

public class
Main {
    public static void main(String[] args) {

        Game.init(args);
        PlayerLogic.Logic();
        //EnemyLogic.Logic();


        UserInput.Input();
        Colission colission = new Colission();

        //load the Liti Library
        Resources.load("Test1.litidata");
        //set the Scale of the Game: pixles * X

        Game.graphics().setBaseRenderScale(3);

        //create new Screen from class InGameScreen()
        Game.screens().add(new InGameScreen());
        Game.screens().add(new MenuScreen());
        Camera c1 = new Camera();


        c1.getViewport();


        Game.audio().setListenerLocationCallback((e) -> Player.instance().getCenter());
        Game.audio().setMaxDistance(1000);


        PropMapObjectLoader.registerCustomPropType(ExplosiveBarrel.class);
        PropMapObjectLoader.registerCustomPropType(BreakableWall.class);


        //loads the inserted map path
        //file must be a tmx file, and loaded in /src
        Game.world().loadEnvironment("TitleScreen");

        Game.screens().display("MENU");
        Game.world().camera().setFocus(Game.world().environment().getCenter());
        Game.start();
    }
}
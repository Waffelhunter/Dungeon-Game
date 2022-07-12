package org.example;

import UI.ColectablesScreen;
import UI.InGameScreen;
import UI.MenuScreen;
import UI.UserInput;
import creatures.Player;
import creatures.Rat;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.resources.Resources;
import logic.BossSpawner;
import logic.Colission;
import logic.CollectablesManager;
import logic.GameManager;
import props.BreakableWall;
import props.ExplosiveBarrel;
import props.Spikes;

public class
Main {
    public static void main(String[] args) {

        Game.init(args);
        Game.window().setTitle("LegendofLogolas");
        Game.window().setIcon(Resources.images().get("src/main/resources/misc/icon.png"));




        UserInput.Input();

        CollectablesManager.init();
        Colission colission = new Colission();

        //load the Liti Library
        Resources.load("game_v4.litidata");
        GameManager.init();
        BossSpawner.init();
        //set the Scale of the Game: pixles * X

        Game.graphics().setBaseRenderScale(3);

        //create new Screen from class InGameScreen()
        Game.screens().add(new InGameScreen());
        Game.screens().add(new MenuScreen());
        Game.screens().add(new ColectablesScreen());
        Camera c1 = new Camera();


        c1.getViewport();


        Game.audio().setListenerLocationCallback((e) -> Player.instance().getCenter());
        Game.audio().setMaxDistance(1000);


        PropMapObjectLoader.registerCustomPropType(ExplosiveBarrel.class);
        PropMapObjectLoader.registerCustomPropType(BreakableWall.class);
        PropMapObjectLoader.registerCustomPropType(Spikes.class);
        CreatureMapObjectLoader.registerCustomCreatureType(Rat.class);

        float safe = Game.config().sound().getSoundVolume();
        Game.config().sound().setSoundVolume(0);
        Game.audio().playSound(Resources.sounds().get("mixkit-shot-light-explosion-1682"));
        Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/219487__jarredgibb__door-cupboard-07.wav"));
        Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/85568__joelaudio__dragon-roar.wav"));
        Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/431174__highpixel__fireball-explosion.wav"));
        Game.audio().playSound(Resources.sounds().get("src/main/resources/misc/573654__the-frisbee-of-peace__wooden-chest-open.wav"));




        //loads the inserted map path
        //file must be a tmx file, and loaded in /src
        Game.world().loadEnvironment("titlescreen");
        Game.config().graphics().setDisplayMode(DisplayMode.BORDERLESS);

        Game.screens().display("MENU");
        Game.world().camera().setFocus(Game.world().environment().getCenter());
        Game.start();
        Game.loop().perform(1000,() -> {
            Game.config().sound().setSoundVolume(safe);
        });

    }
}
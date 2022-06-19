package org.example.UI;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.gui.screens.Screen;

import java.awt.*;

public class MenuScreen extends Screen implements IUpdateable {
    private KeyboardMenu mainMenu;
    public long lastPlayed;

    public MenuScreen() {
        super("Menu");

    }

    private void exit() {
        System.exit(0);

    }

    @Override
    protected void initializeComponents() {
        final double centerX = Game.window().getResolution().getWidth() / 2.0;
        final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
        final double buttonWidth = 450;

        this.mainMenu = new KeyboardMenu(centerX - buttonWidth / 2, centerY * 1.1, buttonWidth, centerY / 2, "Play", "Load", "Collectables ", "Exit");
        this.getComponents().add(this.mainMenu);

        this.mainMenu.onConfirm(c -> {
            switch (c.intValue()) {
                case 0:
                    this.startGame();
                    break;
                case 3:
                    this.exit();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void prepare() {
        this.mainMenu.setEnabled(true);
        super.prepare();
        Game.loop().attach(this);

        Game.window().getRenderComponent().setBackground(Color.BLACK);
        Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
        this.mainMenu.incFocus();
        Game.world().loadEnvironment("TitleScreen");

        //Game.world().camera().setFocus(Game.world().environment().getCenter());
    }

    @Override
    public void render(final Graphics2D g) {
        Game.world().environment().render(g);
        super.render(g);
    }

    @Override
    public void update() {
        if (this.lastPlayed == 0) {
            this.lastPlayed = Game.loop().getTicks();
        }
    }
    private void startGame() {
        this.mainMenu.setEnabled(false);
        Game.window().getRenderComponent().fadeOut(2500);

        Game.loop().perform(3500, () -> {
            Game.screens().display("INGAME-SCREEN");
            Game.window().getRenderComponent().fadeIn(2500);
            Game.world().loadEnvironment("Bibliothek");
            Game.world().camera().setFocus(Game.world().environment().getCenter());
            Game.graphics().setBaseRenderScale(3);



        });
    }
    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        Game.audio().stopMusic();
    }

}

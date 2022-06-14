package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.awt.*;

public class InGameScreen extends GameScreen {
    public static final String NAME = "INGAME-SCREEN";
    private HUD hud;

    public InGameScreen() {
        super(NAME);
    }

    @Override
    protected void initializeComponents() {
        this.hud = new HUD();
        this.getComponents().add(this.hud);

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        if (Game.world().environment() != null) {
            Game.world().environment().render(g);
        }
    }
}

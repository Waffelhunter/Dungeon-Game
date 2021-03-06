package de.dungeongame.ui;

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
        g.setFont(new Font("Mistlock Typeface", Font.PLAIN, 20));
        super.render(g);
    }
}

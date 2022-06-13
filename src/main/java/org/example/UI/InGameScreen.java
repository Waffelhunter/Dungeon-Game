package org.example.UI;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import java.awt.Graphics2D;
import org.example.entities.Player;

public class InGameScreen extends GameScreen {
  private HUD hud;

  public static final String NAME = "INGAME-SCREEN";

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
  }

  public HUD getHud() {
    return hud;
  }
}

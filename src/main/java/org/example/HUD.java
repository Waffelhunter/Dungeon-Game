package org.example;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import javax.tools.Tool;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD extends GuiComponent {

    private static final BufferedImage HEART = Imaging.scale(Resources.images().get("src/main/resources/hud/life.png"), 5.0);
    private static final BufferedImage HEARTEMPTY = Imaging.scale(Resources.images().get("src/main/resources/hud/herzleer.png"), 5.0);
    private static final BufferedImage ARMOR = Imaging.scale(Resources.images().get("src/main/resources/hud/Ruestung.png"), 5.0);
    private static final BufferedImage ARMOREMPTY = Imaging.scale(Resources.images().get("src/main/resources/hud/RuestungLeer.png"),5.0);


    private static final int PADDING = 10;


    protected HUD() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.RED);

        if (Game.world().environment() == null || Player.instance().isDead()) {
            return;

        }
        this.renderHP(g);
        this.renderArmor(g);

    }

    private void renderHP(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING * 7 - HEART.getHeight();
        double x = Game.window().getResolution().getWidth()  - ((Player.instance().getHitPoints().getMax() * (HEART.getWidth() + PADDING) * 1.28) - PADDING);
        for (int i = 0; i < Player.instance().getHitPoints().getMax()-10; i++) {
            BufferedImage img = i < Player.instance().getHitPoints().get() ? HEART : HEARTEMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);

        }
    }

    private void renderArmor(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING * 7 - ARMOR.getHeight();
        double x = Game.window().getResolution().getWidth() / 2 - ((Player.instance().getHitPoints().getMax() * (ARMOR.getWidth() + PADDING) * 0.25) - PADDING);
        for (int i = 10; i < Player.instance().getHitPoints().getMax(); i++) {
            BufferedImage img = i < Player.instance().getHitPoints().get() ? ARMOR : ARMOREMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);
        }
    }
}
















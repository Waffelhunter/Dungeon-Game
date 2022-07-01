package UI;

import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HUD extends GuiComponent {

    private static final BufferedImage HEART = Imaging.scale(Resources.images().get("src/main/resources/hud/life.png"), 5.0);
    private static final BufferedImage HEARTEMPTY = Imaging.scale(Resources.images().get("src/main/resources/hud/herzleer.png"), 5.0);
    private static final BufferedImage ARMOR = Imaging.scale(Resources.images().get("src/main/resources/hud/Ruestung.png"), 5.0);
    private static final BufferedImage ARMOREMPTY = Imaging.scale(Resources.images().get("src/main/resources/hud/RuestungLeer.png"), 5.0);


    private static final int PADDING = 30;


    public HUD() {
        super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.RED);

        if (Game.world().environment() == null) {
            return;

        }
        this.renderHP(g);
        this.renderArmor(g);

    }

    private void renderHP(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING;
        double x = Game.window().getResolution().getWidth() - ((Player.instance().getHitPoints().getMax() * (HEART.getWidth() + PADDING)*2)) ;
        for (int i = 0; i < Player.instance().getHitPoints().getMax(); i++) {
            BufferedImage img = i < Player.instance().getHitPoints().get() ? HEART : HEARTEMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);

        }
    }

    private void renderArmor(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING ;
        double x = Game.window().getResolution().getWidth() - ((Player.maxArmor * (ARMOR.getWidth() + PADDING*0.2) ));
        for (int i = 0; i < Player.maxArmor; i++) {
            BufferedImage img = i < Player.armor ? ARMOR : ARMOREMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);
        }
    }
}
















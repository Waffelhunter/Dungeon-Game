package UI;

import creatures.Dragon;
import creatures.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;
import logic.BossSpawner;
import org.example.SpellManager;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class HUD extends GuiComponent {

    private static final BufferedImage HEART = Imaging.scale(Resources.images().get("src/main/resources/hud/life.png"), 5.0);
    private static final BufferedImage HEARTEMPTY = Imaging.scale(Resources.images().get("src/main/resources/hud/herzleer.png"), 5.0);
    private static final BufferedImage ARMOR = Imaging.scale(Resources.images().get("src/main/resources/hud/Ruestung.png"), 5.0);
    private static final BufferedImage ARMOREMPTY = Imaging.scale(Resources.images().get("src/main/resources/hud/RuestungLeer.png"), 5.0);

    private static final BufferedImage HOTBAR = Imaging.scale(Resources.images().get("src/main/resources/hud/tb.png"), 5.0);

    private  static final BufferedImage Feuerball = Imaging.scale(Resources.images().get("src/main/resources/hud/Feuerball3.png"), 5.0);

    private static final int PADDING = 30;

    private static Color BorderRed = new Color(154, 1, 1, 205);
    private static Color CooldownRed = new Color(154, 1, 1, 37);


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
        this.renderHotbar(g);
        if (BossSpawner.triggered) {
            this.renderDragonHealt(g);
        }

    }

    private void renderHP(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING;
        double x = Game.window().getResolution().getWidth() - ((Player.instance().getHitPoints().getMax() * (HEART.getWidth() + Game.window().getResolution().getWidth() / 15)));
        for (int i = 0; i < Player.instance().getHitPoints().getMax(); i++) {
            BufferedImage img = i < Player.instance().getHitPoints().get() ? HEART : HEARTEMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);

        }
    }

    private void renderArmor(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() + PADDING;
        double x = Game.window().getResolution().getWidth() - ((Player.maxArmor * (ARMOR.getWidth() + PADDING * 0.2)));
        for (int i = 0; i < Player.maxArmor; i++) {
            BufferedImage img = i < Player.armor ? ARMOR : ARMOREMPTY;
            ImageRenderer.render(g, img, x + i * img.getWidth() + PADDING, y);
        }
    }

    private void renderDragonHealt(Graphics2D g) {
        double x = Game.window().getResolution().getWidth() / 3.7 - Dragon.instance().getHitPoints().getMax() * 0.4;
        double y = Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight()*0.57;


        Rectangle2D DragonHealt = new Rectangle2D.Double(x, y, Dragon.instance().getHitPoints().get() * 0.4, 15);

        Rectangle2D DragonHealtBorder = new Rectangle2D.Double(x - 5, y - 5, (Dragon.instance().getHitPoints().getMax() * 0.4) + 10, 25);

        Rectangle2D DragonHealtEmpty = new Rectangle2D.Double(x, y, (Dragon.instance().getHitPoints().getMax() * 0.4), 15);
        g.setColor(Color.GRAY);
        Game.graphics().renderShape(g, DragonHealtBorder);
        g.setColor(Color.BLACK);
        Game.graphics().renderShape(g, DragonHealtEmpty);
        g.setColor(Color.RED);
        Game.graphics().renderShape(g, DragonHealt);

    }
    private void renderHotbar(Graphics2D g){
        double y = Game.window().getResolution().getHeight()/3;
        double x = Game.window().getResolution().getWidth()/25;
        ImageRenderer.render(g,HOTBAR,x,y);
       g.setColor(BorderRed);
       g.setStroke(new BasicStroke(5));
       if(SpellManager.spellType == 1) {
           g.drawRect((int) x + 30, (int) y + 30, 100, 75);
       }
       if(SpellManager.spellType == 2){
           g.drawRect((int) x + 30, (int) y + 142, 100, 75);
       }
        if(SpellManager.spellType == 3){
            g.drawRect((int) x + 30, (int) y + 257, 100, 75);
        }
        if(SpellManager.spellType == 4){
            g.drawRect((int) x + 30, (int) y + 372, 100, 75);
        }
       g.fillRect((int)x + 30,(int)y +30,100, SpellManager.FireballCooldown);
       ImageRenderer.render(g,Feuerball,x+35,y+40);

    }
}

















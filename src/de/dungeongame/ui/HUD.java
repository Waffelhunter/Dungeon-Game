package de.dungeongame.ui;

import de.dungeongame.entities.Dragon;
import de.dungeongame.entities.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;
import de.dungeongame.logic.BossSpawner;
import de.dungeongame.core.SpellManager;
import de.dungeongame.entities.props.InteractableObjects;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class HUD extends GuiComponent {

    private static final BufferedImage HEART = Imaging.scale(Resources.images().get("hud/life.png"), 5.0);
    private static final BufferedImage HEARTEMPTY = Imaging.scale(Resources.images().get("hud/herzleer.png"), 5.0);
    private static final BufferedImage ARMOR = Imaging.scale(Resources.images().get("hud/Ruestung.png"), 5.0);
    private static final BufferedImage ARMOREMPTY = Imaging.scale(Resources.images().get("hud/RuestungLeer.png"), 5.0);
    private static final BufferedImage HOTBAR = Imaging.scale(Resources.images().get("hud/tb.png"), 5.0);
    private static final BufferedImage Feuerball = Imaging.scale(Resources.images().get("hud/Feuerball3.png"), 5.0);
    private static final BufferedImage Splitball = Imaging.scale(Resources.images().get("hud/Bs.png"), 5.0);
    private static final BufferedImage HealthbarIn = Imaging.scale(Resources.images().get("hud/Lebensleiste_innen.png"), 5.0);
    private static final BufferedImage HealthbarOut = Imaging.scale(Resources.images().get("hud/Lebensleiste_Rahmen_v2.png"), 5.0);
    private static final int PADDING = 30;

    public static boolean renderLock = false;
    private static final Color BorderRed = new Color(154, 1, 1, 205);
    private static final Color CooldownRed = new Color(154, 1, 1, 37);
    private static int fader = 150;

    private static final Font f = new Font("Mistlock Typeface", Font.PLAIN, 20);

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
        if (BossSpawner.triggered && Game.world().environment() == Game.world().loadEnvironment("BossRoom")) {
            this.renderDragonHealt(g);
        }
        if (fader == 0) {

            renderLock = false;
            InteractableObjects.pickedUp = false;
            fader = 150;
        }

        if (renderLock == true) {
            this.renderLockedText(g);
            fader -= 1;

        }
        if(InteractableObjects.pickedUp == true){
            this.renderKeyPickUpMessage(g);
            fader -=1;
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
        double x = Game.window().getResolution().getWidth() / 2;
        double y = Game.window().getResolution().getHeight() - PADDING * 4;

        Rectangle2D DragonHealtEmpty = new Rectangle2D.Double(Game.window().getResolution().getWidth() / 3.7 - Dragon.instance().getHitPoints().getMax() * 0.4, Game.window().getResolution().getHeight() - Game.window().getResolution().getHeight() * 0.57, Dragon.instance().getHitPoints().get() * (Dragon.instance().getHitPoints().getMax() / HealthbarIn.getWidth()), 25);
        ImageRenderer.render(g, HealthbarOut, x - HealthbarOut.getWidth() / 2, y);
        ImageRenderer.render(g, HealthbarIn, x - HealthbarIn.getWidth() / 2, y);

        g.setColor(Color.BLACK);
        Game.graphics().renderShape(g, DragonHealtEmpty);


    }

    private void renderHotbar(Graphics2D g) {
        double y = Game.window().getResolution().getHeight() / 3;
        double x = Game.window().getResolution().getWidth() / 25;
        ImageRenderer.render(g, HOTBAR, x, y);
        g.setColor(BorderRed);
        g.setStroke(new BasicStroke(5));
        if (SpellManager.spellType == 1) {
            g.drawRect((int) x + 30, (int) y + 30, 100, 75);
        }
        if (SpellManager.spellType == 2) {
            g.drawRect((int) x + 30, (int) y + 142, 100, 75);
        }
        if (SpellManager.spellType == 3) {
            g.drawRect((int) x + 30, (int) y + 257, 100, 75);
        }
        if (SpellManager.spellType == 4) {
            g.drawRect((int) x + 30, (int) y + 372, 100, 75);
        }
        g.fillRect((int) x + 30, (int) y + 30, 100, SpellManager.FireballCooldown);
        ImageRenderer.render(g, Feuerball, x + 35, y + 40);
        g.fillRect((int) x + 30, (int) y + 145, 100, (int) ((SpellManager.ShatterShotCooldown) * 0.75));
        ImageRenderer.render(g, Splitball, x + 35, y + 155);

    }

    private void renderLockedText(Graphics2D g) {


        g.setColor(new Color(155, 146, 146, fader));
        g.setStroke(new BasicStroke(50));
        g.setFont(f);

        TextRenderer.render(g, "this door is locked", Game.window().getWidth() / 2 - 30, PADDING * 4);


    }
    private void renderKeyPickUpMessage(Graphics2D g) {


        g.setColor(new Color(155, 146, 146, fader));
        g.setStroke(new BasicStroke(50));
        g.setFont(f);

        TextRenderer.render(g, "you found the key to the " + InteractableObjects.KeyPickUpName, Game.window().getWidth() / 2 - 30, PADDING * 4);


    }
}


















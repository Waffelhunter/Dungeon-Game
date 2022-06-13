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

//    private final BufferedImage HEART = Imaging.scale(Resources.images().get("life.png"),5.0);
//    private final BufferedImage HEARTEMPTY  = Imaging.scale(Resources.images().get("herzleer.png"),5.0);
//    //private Image HEARTEMPTY = Toolkit.getDefaultToolkit().getImage("resources/vase/herzleer.png");
//
//    private static final int PADDING = 10;


    protected HUD() {
        super(0,0, Game.window().getResolution().getWidth(),Game.window().getResolution().getHeight());
    }

//@Override
//public void render(Graphics2D g) {
//    super.render(g);
//    g.setColor(Color.RED);
//
//    if (Game.world().environment() == null || Player.instance().isDead()) {
//        return;
//
//    }
//    this.renderHP(g);
//}
//    private void renderHP(Graphics2D g){
//        double y = Game.window().getResolution().getHeight() - PADDING*2 - HEART.getHeight();
//        double x = Game.window().getResolution().getWidth() /2.0 - ((Player.instance().getHitPoints().getMax()*(HEART.getWidth()+PADDING)*0.5) - PADDING);
//        for(int i = 0; i < Player.instance().getHitPoints().getMax();i++){
//          BufferedImage img = i < Player.instance().getHitPoints().getRelativeCurrentValue() ? HEART : HEARTEMPTY;
//            ImageRenderer.render(g,img, x + i * img.getWidth() + PADDING, y);
//
//        }
//    }
}
















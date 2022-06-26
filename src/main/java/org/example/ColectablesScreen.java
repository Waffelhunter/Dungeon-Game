package org.example;


import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;


import java.awt.*;

public class ColectablesScreen extends Screen implements IUpdateable {
public Collectables collect;
    protected ColectablesScreen() {
        super("collectables");
    }
   @Override
   protected void initializeComponents(){
       this.collect = new Collectables(Game.window().getCenter().getX()-Game.window().getResolution().getWidth()/2 , Game.window().getCenter().getY()-Game.window().getResolution().getHeight()/2,Game.window().getResolution().getWidth(),Game.window().getResolution().getHeight());
       this.getComponents().add(this.collect);

   }
   @Override
    public void prepare() {
       this.collect.setEnabled(true);
       super.prepare();
       Game.loop().attach(this);

       Game.window().getRenderComponent().setBackground(Color.BLACK);
       Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());

  }

    @Override
    public void render(final Graphics2D g) {
        Game.world().environment().render(g);
        super.render(g);

    }
    @Override
    public void update() {

    }
    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
        Game.audio().stopMusic();
    }
    public void renderText(Graphics2D g,double x,double y){
        TextRenderer.render( g,"a Gurkenlabs game",  x, y);
    }
}

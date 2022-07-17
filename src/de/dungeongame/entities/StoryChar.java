package de.dungeongame.entities;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.gui.SpeechBubble;
import de.gurkenlabs.litiengine.resources.Resources;

@AnimationInfo(spritePrefix = {"Story_char-idle"})
@CollisionInfo(collision = true, collisionBoxWidth = 20, collisionBoxHeight = 10)
public class StoryChar extends Creature implements IUpdateable {

  private static StoryChar instance;

  private SpeechBubble speechBubble;

  private StoryChar() {
    super("Story_char");
  }

  public static StoryChar instance() {
    if (instance == null) {
      instance = new StoryChar();
    }
    return instance;
  }

  public SpeechBubble getSpeechBubble() {
    return speechBubble;
  }

  public void speak(String text) {
    if (getSpeechBubble() != null) getSpeechBubble().stop();
    this.speechBubble = new SpeechBubble(StoryChar.instance(), text);
    getSpeechBubble().setTypeSound(Resources.sounds().get("ui_click.ogg"));
    getSpeechBubble().start();
  }

  @Override
  public void update() {}
}

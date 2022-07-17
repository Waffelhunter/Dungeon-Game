package creatures;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;

@AnimationInfo(spritePrefix = {"Story_char-idle"})
@CollisionInfo(collision = true,collisionBoxWidth = 20,collisionBoxHeight = 10)

public class StoryChar extends Creature implements IUpdateable {

    private static StoryChar instance;


    public static StoryChar instance() {
        if (instance == null) {
            instance = new StoryChar();

        }
        return instance;

    }
    private StoryChar() {
        super("Story_char");

    }

    @Override
    public void update() {

    }
}

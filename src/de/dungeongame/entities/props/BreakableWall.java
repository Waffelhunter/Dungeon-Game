package de.dungeongame.entities.props;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Prop;

@AnimationInfo(spritePrefix = {"prop-normalBreak", "prop-redEyeBreak", "prop-greenEyeBreak", "dragonEyeBreak"})
public class BreakableWall extends Prop {
    public BreakableWall(String spritesheetName) {
        super(spritesheetName);
    }


    @Override
    public void die() {
        super.die();
        this.addTag("door");
    }
}

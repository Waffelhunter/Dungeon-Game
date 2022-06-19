package org.example;

import de.gurkenlabs.litiengine.entities.Creature;

public class Bookmonster extends Creature {

    private int angle;
    private long lastAngleChange;
    private final int ANGLE_CHANGE_INTERVAL = 1000;
    private int lastHit = 200;

    public Bookmonster() {
        super("Bookmonster");
        this.addTag("enemy");


    }
}

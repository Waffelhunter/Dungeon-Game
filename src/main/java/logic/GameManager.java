package logic;


import creatures.Bookmonster;
import creatures.Player;
import creatures.Slime;
import creatures.StoryChar;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.gui.SpeechBubble;
import props.InteractableObjects;
import props.Spikes;

import java.util.Collection;
import java.util.Optional;


public final class GameManager {
    public static boolean[] besucht;
    public static Environment[] maps;
    public static int anzahlMonster = 0;



    private GameManager() {
    }

    public static void init() {

        InteractableObjects.pickedUp = false;
        besucht = new boolean[22];
        maps = new Environment[22];

        int i = 0;
        Collection<Environment> mapCol = Game.world().getEnvironments();

        for (Environment e : mapCol) {
            maps[i] = e;
            besucht[i] = false;
            i++;


        }
        Game.world().onLoaded(environment -> loaded(environment));


    }

    private static void loaded(Environment e) {
        //spawnPlayer(e);
        spawnEnemy(e);
        PropMapObjectLoader.registerCustomPropType(Spikes.class);

        Game.world().camera().setFocus(e.getCenter());

    }

    public static void spawnEnemy(Environment e) {
        //prüft, ob geladene map schonmal besucht wurde
//        for (int i = 0; i < 22; i++) {
//
//            if (maps[i] == e && (besucht[i] == false)) {

        //das passiert, bei erstem Besuch der map
        Collection<Spawnpoint> es = Game.world().environment().getSpawnpoints("s");
//        for(Spawnpoint s : es){
//            s.spawn(new Bookmonster());
//        }
        Optional<Spawnpoint> enemySpawn = Optional.ofNullable(e.getSpawnpoint("EnemySpawn"));
        enemySpawn.ifPresent(s -> s.spawn(new Slime()));
//        anzahlMonster++;
//        System.out.println("nach enemySpawn =  "+ GameManager.anzahlMonster+ "\t erwartet: 1");
//        enemySpawn.ifPresent(s -> {
//
//                    s.spawn(new Bookmonster());


    }


    //  besucht[i] = true;
//            if (maps[i] == e && (besucht[i] == true)) {
//                //ruft respawn Enemy auf, wenn map schonmal besucht wurde.
//                respawnEnemy(e, Game.random().nextInt(3));
    //    }


    private static void respawnEnemy(Environment e, int max) {


        Optional<Spawnpoint> enemySpawn = Optional.ofNullable(e.getSpawnpoint("EnemySpawn"));
        // enemySpawn.ifPresent(s -> s.spawn(new Slime()));
        if (Game.random().nextInt(2) == 2) {
            for (int i = 0; i < max; i++) {
                enemySpawn.ifPresent(s -> s.spawn(new Bookmonster()));
            }
        }

    }

    public static void spawnPlayer(Environment e, Prop p) {
        if (p == null) {
            Optional<Spawnpoint> summon = Optional.ofNullable(e.getSpawnpoint("s"));
            summon.ifPresent(s -> s.spawn(Player.instance()));
            return;
        }

        String spawn = "s";
        if (p.hasTag("s")) {
            spawn = "n";
        } else {
            if (p.hasTag("o")) {
                spawn = "w";
            } else {
                if (p.hasTag("w")) {
                    spawn = "o";
                }
            }
        }
        // Eine Tür soll dir dich vor eine bestimmte Tür in ein Raum spawnen und nicht einfach in der Mitte

        Optional<Spawnpoint> summon = Optional.ofNullable(e.getSpawnpoint(spawn));
        summon.ifPresent(s -> s.spawn(Player.instance()));
    }

    public static void respawn() {
        anzahlMonster = 0;
        if (Player.instance().isDead()) {
            Player.instance().resurrect();
            Game.world().loadEnvironment("atrium");
            Player.instance().setLocation(Game.world().environment().getSpawnpoint("s").getLocation());
        }
    }
    public static void spawnStoryChar(int State){
        switch (State){
            case 0: return;
            case 1:
                Optional<Spawnpoint> summon = Optional.ofNullable(Game.world().environment().getSpawnpoint("n"));
                summon.ifPresent(s -> s.spawn(StoryChar.instance()));
                Prop p = new Prop("Exclamation Mark");
                p.setCollision(false);
                Game.world().environment().add(p);
                p.setName("marker");
                p.setLocation(StoryChar.instance().getCenter().getX()-17,StoryChar.instance().getCenter().getY()-50);


                return;


        }
    }
}

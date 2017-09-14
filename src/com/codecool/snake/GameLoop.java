package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.powerups.Battery;
import com.codecool.snake.entities.powerups.HealPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GameLoop extends AnimationTimer {

    public static final int POWERUP_DELAY = 1200;

    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        spawnGameObjects();
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    public void spawnGameObjects() {
        Pane pane = Globals.pane;
        if (!Globals.isThereBerry) {
            new SimplePowerup(pane);
        }
        if (!Globals.isTherePowerUp) {
            int random = new Random().nextInt(POWERUP_DELAY);
            if (random < Battery.RESPAWNRATE) {new Battery(pane);}
            else if (random < Battery.RESPAWNRATE + HealPowerup.RESPAWNRATE) {new HealPowerup(pane);}
        }

    }

}

package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.BabyFaceEnemy;
import com.codecool.snake.entities.enemies.EnemyFollow;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.Battery;
import com.codecool.snake.entities.powerups.HealPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GameLoop extends AnimationTimer {

    private static final int POWERUP_DELAY = 1200;
    private static final int ENEMY_DELAY = 600;

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

    private void spawnGameObjects() {
        Pane pane = Globals.pane;
        if (!Globals.isThereBerry) {
            new SimplePowerup(pane);
        }
        Random random = new Random();

        if (!Globals.isTherePowerUp) {
            int randInt = random.nextInt(POWERUP_DELAY);
            if (randInt < Battery.RESPAWNRATE) {new Battery(pane);}
            else if (randInt < Battery.RESPAWNRATE + HealPowerup.RESPAWNRATE) {new HealPowerup(pane);}
        }

        if (Globals.snakeHeadEntity.getSnakeLength()>5) {
            int randInt = random.nextInt(ENEMY_DELAY);
            if (randInt < SimpleEnemy.RESPAWNRATE) new SimpleEnemy(pane);
        }
        if (Globals.snakeHeadEntity.getSnakeLength()>9) {
            int randInt = random.nextInt(ENEMY_DELAY);
            if (randInt < EnemyFollow.RESPAWNRATE) new EnemyFollow(pane);
        }
        if (Globals.snakeHeadEntity.getSnakeLength()>13) {
            int randInt = random.nextInt(ENEMY_DELAY);
            if (randInt < BabyFaceEnemy.RESPAWNRATE) new BabyFaceEnemy(pane);
        }
    }
}

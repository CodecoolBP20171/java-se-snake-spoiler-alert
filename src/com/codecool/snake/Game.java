package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.BabyFaceEnemy;
import com.codecool.snake.entities.enemies.EnemyFollow;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {

    }

    public void spawnGameObjects() {
        SnakeHead snakeHead = new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new BabyFaceEnemy(this);
        new BabyFaceEnemy(this);
        new BabyFaceEnemy(this);
        new BabyFaceEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new EnemyFollow(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case R: restart(); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        spawnGameObjects();
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void restart() {
        for (GameEntity gameObject : Globals.getGameObjects()) {
            gameObject.destroy();
        }
        Globals.gameLoop.stop();
        start();
    }

}

package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.BabyFaceEnemy;
import com.codecool.snake.entities.enemies.EnemyFollow;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealPowerup;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.Battery;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {
    public Game() {
        Globals.pane = this;

    }

    public void initSpawnGameObjects() {
        new SnakeHead(this, 500, 500);
        new SimplePowerup(this);
        Globals.isTherePowerUp = false;
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case R: restart(); break;
                case SPACE: Globals.snakeHeadEntity.shoot(); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });

        initSpawnGameObjects();
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void restart() {
        for (GameEntity gameObject : Globals.getGameObjects()) {
            gameObject.destroy();
        }
        this.getChildren().clear();
        Globals.gameLoop.stop();
        start();
    }



}

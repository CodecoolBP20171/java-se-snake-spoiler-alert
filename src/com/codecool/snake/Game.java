package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

class Game extends Pane {
    Game() {
        Globals.pane = this;
    }

    private void initSpawnGameObjects() {
        new SnakeHead(this, 500, 500);
        new SimplePowerup(this);
        Globals.isTherePowerUp = false;
    }

    void start() {
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

    void restart() {
        for (GameEntity gameObject : Globals.getGameObjects()) {
            gameObject.destroy();
        }
        this.getChildren().clear();
        Globals.gameLoop.stop();
        getChildren().add(Globals.menuBar);
        start();
    }

}

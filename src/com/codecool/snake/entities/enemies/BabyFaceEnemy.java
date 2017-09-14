package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class BabyFaceEnemy extends Enemy {

    public static final int RESPAWNRATE = 1;

    public BabyFaceEnemy(Pane pane) {
        super(pane);
        setImage(Globals.babyFaceEnemy);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            heading = Utils.directionToVector(direction - 180, speed);
            //destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        destroy();
        snakeHead.setImage(Globals.invisibleSnake);

        for (GameEntity entity : Globals.getGameObjects()) {
            if (entity instanceof SnakeBody) {
                entity.setImage(Globals.invisibleSnake);
            }
        }
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        snakeHead.setImage(Globals.snakeHead);
                        for (GameEntity entity : Globals.getGameObjects()) {
                            if (entity instanceof SnakeBody) {
                                entity.setImage(Globals.snakeBody);
                            }
                        }
                    }
                },
                3000
        );
    }

    @Override
    public String getMessage() {
        return "BabyFace enemy made your snake invisible";
    }
}

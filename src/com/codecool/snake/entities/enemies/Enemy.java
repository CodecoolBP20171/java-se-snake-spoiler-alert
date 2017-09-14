package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {

    protected Point2D heading;
    protected static final int damage = 10;
    protected static final int speed = 1;
    protected double direction;
    //int spawnrate?

    public Enemy(Pane pane) {
        super(pane);
        pane.getChildren().add(this);

        //make sure they don't spawn on the player
        Random rnd = new Random();
        SnakeHead player = Globals.snakeHeadEntity;
        do {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        } while (getBoundsInParent().intersects(player.getBoundsInParent()));

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }
}

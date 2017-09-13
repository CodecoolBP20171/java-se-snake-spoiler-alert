package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.animation.RotateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Laser extends GameEntity implements Animatable {

    private Point2D heading;

    public Laser(Pane pane, SnakeHead shooter) {
        super(pane);
        setImage(Globals.laser);
        pane.getChildren().add(this);
        int speed = 5;
        double direction = shooter.getRotate();
        place(shooter);

        heading = Utils.directionToVector(direction, speed);
    }

    private void place(SnakeHead shooter) {
        setRotate(shooter.getRotate());
        setX(shooter.getX());
        setY(shooter.getY());

        this.getTransforms().add(new Rotate(shooter.getRotate() / 360, shooter.getX(), shooter.getY()));
    }


    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        //if it hit an enemy
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent()) && entity instanceof SimpleEnemy) {
                entity.destroy();
                this.destroy();
            }
        }
    }


}

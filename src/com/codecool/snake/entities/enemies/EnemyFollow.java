package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


public class EnemyFollow extends Enemy {

    public static final int RESPAWNRATE = 2;

    public EnemyFollow(Pane pane) {
        super(pane);
        setImage(Globals.followEnemy);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        int direction = Utils.coordinatesToDirection(new Point2D(getX(), getY()), Globals.snakeHeadEntity.getPlace() );
        heading = Utils.headingVectorFromCoords(new Point2D(getX(), getY()), Globals.snakeHeadEntity.getPlace(), speed);
        setRotate(direction);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

}

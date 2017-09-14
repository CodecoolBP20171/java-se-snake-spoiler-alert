package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


// a simple powerup that makes the snake grow
public class SimplePowerup extends Powerup {

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}

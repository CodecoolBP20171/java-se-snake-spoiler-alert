package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


//a powerup that adds laser ammunition
public class Battery extends Powerup {

    public Battery(Pane pane) {
        super(pane);
        setImage(Globals.powerupBattery);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeLaserCharge(1);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got laser charge :)";
    }
}

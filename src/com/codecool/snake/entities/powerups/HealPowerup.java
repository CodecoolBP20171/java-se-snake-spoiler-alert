package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


// a powerup that restores 10 health
public class HealPowerup extends Powerup {

    public static final int RESPAWNRATE = 2;

    public HealPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupHeal);
        Globals.isTherePowerUp = true;

    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(10);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got a healing power-up!";
    }

    @Override
    public void destroy() {
        Globals.isTherePowerUp = false;
        super.destroy();
    }
}

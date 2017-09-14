package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


// a powerup that restores 10 health
public class HealPowerup extends Powerup {

    public static final int RESPAWNRATE = 2;

    public HealPowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupHeal);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

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

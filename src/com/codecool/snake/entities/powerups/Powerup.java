package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.scene.layout.Pane;

import java.util.Random;


abstract public class Powerup extends GameEntity implements Interactable {

    Powerup(Pane pane) {
        super(pane);
        pane.getChildren().add(this);

        Random rnd = new Random();

        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - 40));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 40));
    }
}

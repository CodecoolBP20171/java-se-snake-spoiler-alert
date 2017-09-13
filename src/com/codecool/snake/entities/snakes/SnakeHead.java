package com.codecool.snake.entities.snakes;

import com.codecool.snake.GameOverEvent;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;

    private EventType<GameOverEvent> GAME_OVER = new EventType<>("GAME OVER");

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
        addGameOverHandler();
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {

            this.fireEvent(new GameOverEvent(GAME_OVER));
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void addGameOverHandler() {
        getParent().addEventHandler(GAME_OVER, event -> {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
            int length = getSnakeLength();
            Globals.gameObjects.clear();
            displayGameOverMessage(length);
        });
    }

    private int getSnakeLength() {
        int length = 1;

        for (GameEntity entity : Globals.gameObjects) {
            if (entity instanceof SnakeBody)
                length++;
        }

        return length;
    }

    private void displayGameOverMessage(int length) {
        Text gameOverText = new Text(210, 300, "           GAME OVER!\nYour snake's final length is \n" +
                                                         "                " + length + " parts.");
        gameOverText.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        gameOverText.setFill(Color.RED);
        pane.getChildren().clear();
        pane.getChildren().add(gameOverText);
    }
}

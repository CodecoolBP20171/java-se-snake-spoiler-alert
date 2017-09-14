package com.codecool.snake.entities.snakes;

import com.codecool.snake.GameOverEvent;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import com.codecool.snake.entities.Laser;
import com.codecool.snake.entities.enemies.BabyFaceEnemy;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int laserCharge;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        laserCharge = 3;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);
        drawHealthDisplay();

        addPart(4);
        addGameOverHandler();
        Globals.snakeHeadEntity = this;
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
            this.fireEvent(new GameOverEvent(Globals.GAME_OVER));
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
        drawHealthDisplay();
    }

    private void drawHealthDisplay() {
        clearHealthDisplay();
        for (int i = 1; i <= 10; i++) {
            ImageView healthBit = new ImageView();

            if (i <= health / 10) {
                healthBit.setImage(Globals.healthUnit);
            }
            else {
                healthBit.setImage(Globals.healthEmpty);
            }
            healthBit.setX(i * 20);
            healthBit.setY(20);
            pane.getChildren().add(healthBit);
        }
    }

    private void clearHealthDisplay() {
        ArrayList<Node> toRemove = new ArrayList<>();
        for (Node entity : pane.getChildren()) {
            if (entity instanceof ImageView) {
                ImageView entityImageView = (ImageView)entity;
                if (entityImageView.getImage() == Globals.healthUnit || entityImageView.getImage() == Globals.healthEmpty)
                    toRemove.add(entity);
            }
        }
        for (Node healthBit : toRemove) {
            pane.getChildren().remove(healthBit);
        }
    }

    public Point2D getPlace() {
        return new Point2D(getX(),getY());
    }

    public void shoot() {
        if (laserCharge > 0) {
            new Laser(pane, this);
          laserCharge--;
        }
    }

    public void changeLaserCharge(int diff) {
        laserCharge += diff;
    }

    public int getLaserCharge() {
        return laserCharge;
    }

    private void addGameOverHandler() {
        addEventHandler(Globals.GAME_OVER, gameOverHandler);
    }

    public EventHandler gameOverHandler = event -> {
        System.out.println("Game Over");
        Globals.gameLoop.stop();
        int length = getSnakeLength();
        Globals.gameObjects.clear();
        displayGameOverMessage(length);
    };
    
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

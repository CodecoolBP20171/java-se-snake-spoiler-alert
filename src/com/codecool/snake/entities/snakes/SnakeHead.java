package com.codecool.snake.entities.snakes;

import com.codecool.snake.Display;
import com.codecool.snake.GameOverEvent;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.event.EventHandler;
import com.codecool.snake.entities.Laser;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


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
        Display.drawHealthDisplay(pane, health);
        Display.drawAmmoDisplay(pane, laserCharge);

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
        Display.drawHealthDisplay(pane, health);
    }

    public int getHealth() {
        return health;
    }

    public Point2D getPlace() {
        return new Point2D(getX(),getY());
    }

    public void shoot() {
        if (laserCharge > 0) {
            new Laser(pane, this);
            changeLaserCharge(-1);
        }
    }

    public void changeLaserCharge(int diff) {
        laserCharge += diff;
        Display.refreshAmmoDisplay(laserCharge);
    }

    public int getLaserCharge() {
        return laserCharge;
    }

    private void addGameOverHandler() {
        addEventHandler(Globals.GAME_OVER, event -> {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
            int length = getSnakeLength();
            Globals.gameObjects.clear();
            Display.displayGameOverMessage(length, pane);
            pane.getChildren().add(Globals.menuBar);
        });
    }

    public int getSnakeLength() {
        int length = 1;
        for (GameEntity entity : Globals.gameObjects) {
            if (entity instanceof SnakeBody) length++;
        }
        return length;
    }

    public int getHealth() {
        return health;
    }

}

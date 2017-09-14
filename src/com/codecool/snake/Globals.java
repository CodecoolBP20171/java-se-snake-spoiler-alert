package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.event.EventType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");

    public static Image powerupHeal = new Image("powerup_heal.png");
    public static Image powerupBattery = new Image("powerup_battery.png");

    public static Image healthUnit = new Image("heart.png");
    public static Image healthEmpty = new Image("heart_empty.png");
    public static Image babyFaceEnemy = new Image("babyface.png");
    public static Image invisibleSnake = new Image("invisiblesnake.png");
    public static Image followEnemy = new Image("enemy_follow.png");

    //.. put here the other images you want to use
    public static Image laser = new Image("laser.png");

    public static SnakeHead snakeHeadEntity;

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static boolean isThereBerry;
    public static boolean isTherePowerUp;

    public static Pane pane;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static EventType<GameOverEvent> GAME_OVER = new EventType<>("GAME OVER");

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

}

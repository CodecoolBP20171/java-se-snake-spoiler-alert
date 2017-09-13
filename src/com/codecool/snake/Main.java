package com.codecool.snake;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Button restartButton = new Button("Restart");
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        game.getChildren().add(restartButton);
        primaryStage.show();
        restartButton.setAlignment(Pos.BOTTOM_RIGHT);
        restartButton.setOnAction(e -> restart(primaryStage));
        game.start();
    }

    public void restart(Stage primaryStage){
        Globals.gameLoop.stop();
        Globals.gameObjects.clear();
        System.out.println("Game has been restarted");
        start(primaryStage);


    }
}

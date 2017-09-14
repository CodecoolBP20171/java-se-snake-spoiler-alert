package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        primaryStage.setTitle("Snake Game");
        Scene scene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        game.getChildren().add(menuBar);

        Menu fileMenu = new Menu("File");
        MenuItem newGameItem = new MenuItem("New game");
        MenuItem helpMenuItem = new MenuItem("Help");
        MenuItem exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().add(newGameItem);
        fileMenu.getItems().add(helpMenuItem);
        fileMenu.getItems().add(exitMenuItem);
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        newGameItem.setOnAction(actionEvent -> game.start());

        menuBar.getMenus().addAll(fileMenu);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

package com.codecool.snake;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    private static Game game = new Game();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake Game");
        Scene scene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        Globals.menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        primaryStage.setScene(scene);
        showMenu();
        primaryStage.show();
    }

    private void showMenu(){
        Menu fileMenu = new Menu("File");
        MenuItem newGameItem = new MenuItem("New game");
        MenuItem helpMenuItem = new MenuItem("Help");
        MenuItem exitMenuItem = new MenuItem("Exit");

        fileMenu.getItems().add(newGameItem);
        fileMenu.getItems().add(helpMenuItem);
        fileMenu.getItems().add(exitMenuItem);

        game.getChildren().add(Globals.menuBar);
        helpMenuItem.setOnAction(event -> helpAction());
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        newGameItem.setOnAction(actionEvent -> {
            if (Globals.gameLoop == null) game.start();
            else game.restart();
        });
        Globals.menuBar.getMenus().addAll(fileMenu);
    }

    private void helpAction(){
        Button ok = new Button("I got it!");
        if (Globals.gameLoop != null) Globals.gameLoop.stop();
        final Stage dialog = new Stage();

        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        Text controls = new Text(("Controls:"));
        Text description = new Text(("Turn Right: Right arrow \n" +
                "Turn left: Left arrow \nShoot: Space \nRestart game: R"));
        controls.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        dialogVbox.getChildren().add(controls);
        dialogVbox.getChildren().add(description);
        dialogVbox.getChildren().add(ok);
        ok.setOnAction(actionEvent -> dialog.close() );

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}

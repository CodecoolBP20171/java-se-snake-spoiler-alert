package com.codecool.snake;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Display {

    public static void displayGameOverMessage(int length, Pane pane) {
        Text gameOverText = new Text(210, 300, "           GAME OVER!\nYour snake's final length is \n" +
                "                " + length + " parts.");
        gameOverText.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        gameOverText.setFill(Color.RED);
        pane.getChildren().clear();
        pane.getChildren().add(gameOverText);
    }

    public static void drawHealthDisplay(Pane pane, int health) {
        clearHealthDisplay(pane);
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

    private static void clearHealthDisplay(Pane pane) {
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

    public static void drawAmmoDisplay(Pane pane, int laserCharge) {
        Globals.ammoText = new Text(20, 60, "Laser ammo: " + laserCharge);
        Globals.ammoText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        Globals.ammoText.setFill(Color.rgb(1, 61, 181));
        pane.getChildren().add(Globals.ammoText);
    }

    public static void refreshAmmoDisplay(int laserCharge) {
        Globals.ammoText.setText("Laser ammo: " + laserCharge);
    }
}

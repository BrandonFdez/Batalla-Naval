package com.example.batallanaval.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class represents the second stage (window) of the Battleship game.
 * It loads the second view using the specified FXML file, sets up the stage with a title, icon, and resizable window.
 */
public class BNSecondStage extends Stage {

    /**
     * Constructor for BNSecondStage. It loads the FXML view, sets the stage's properties, and shows the window.
     *
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    public BNSecondStage() throws IOException {
        // Load the FXML file that defines the second stage of the game
        FXMLLoader loader = new FXMLLoader(BNSecondStage.class.getResource("/com/example/batallanaval/Second-view.fxml"));

        // Check if the FXML file is not found and throw an exception if it cannot be loaded
        if (loader.getLocation() == null) {
            throw new IOException("Unable to find the FXML file: /com/example/batallanaval/Second-view.fxml");
        }

        // Load the root element of the scene
        Parent root = loader.load();

        // Create a new scene with the loaded root and set it to the stage
        Scene scene = new Scene(root);

        // Set the application icon using an image resource
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/batallanaval/images/logoBN.png"))));

        // Set the title of the stage
        setTitle("Batalla Naval");

        // Set the stage to be resizable
        setResizable(true);

        // Set the scene on the stage and display it
        setScene(scene);

        // Show the stage (the window)
        show();
    }
}

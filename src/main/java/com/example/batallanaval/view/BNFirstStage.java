package com.example.batallanaval.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class represents the first stage (window) of the Battleship game.
 * It loads the initial view using the specified FXML file and sets up the stage with a title, icon, and non-resizable window.
 */
public class BNFirstStage extends Stage {

    /**
     * Constructor for BNFirstStage. It loads the FXML view, sets the stage's properties, and shows the window.
     *
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    public BNFirstStage() throws IOException {
        // Load the FXML file that defines the first stage of the game
        FXMLLoader loader = new FXMLLoader(BNFirstStage.class.getResource("/com/example/batallanaval/First-view.fxml"));

        // Check if the FXML file is not found and throw an exception if it cannot be loaded
        if (loader.getLocation() == null) {
            throw new IOException("Unable to find the FXML file: /com/example/batallanaval/First-view.fxml");
        }

        // Load the root element of the scene
        Parent root = loader.load();

        // Create a new scene with the loaded root and set it to the stage
        Scene scene = new Scene(root);

        // Set the application icon using an image resource
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/batallanaval/images/logoBN.png"))));

        // Set the title of the stage
        setTitle("Batalla Naval");

        // Set the stage to be non-resizable
        setResizable(false);

        // Set the scene on the stage and display it
        setScene(scene);

        // Show the stage (the window)
        show();
    }
}

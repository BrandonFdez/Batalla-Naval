package com.example.batallanaval;

import com.example.batallanaval.view.BNFirstStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point for the Battleship game application.
 * This class is responsible for launching the JavaFX application and opening the first stage (window) of the game.
 */
public class Main extends Application {

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start method is called when the application is launched.
     * It opens the first stage (window) of the Battleship game.
     *
     * @param primaryStage The primary stage provided by JavaFX, which can be used to set the main application window.
     * @throws Exception If any error occurs during the initialization of the stage or window.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Opens the first stage (the start window) of the Battleship game
        new BNFirstStage();
    }
}

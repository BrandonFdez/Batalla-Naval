package com.example.batallanaval.controller;

import com.example.batallanaval.view.BNSecondStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the initial stage of the Battleship Naval game.
 * Handles user interactions for starting a new game or loading a saved game.
 */
public class BNFirstController {

    // Button for starting a new game, linked to the FXML UI.
    @FXML
    private Button NuevoJuego;

    // Button for loading a saved game, linked to the FXML UI.
    @FXML
    private Button JuegoGuardado;

    /**
     * Handles the "New Game" button click event.
     * Closes the current stage and opens the next stage for game configuration or gameplay.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If the BNSecondStage resources cannot be loaded.
     */
    @FXML
    private void ClickNewGame(ActionEvent event) throws IOException {
        // Retrieve the current stage from the event source and close it.
        Stage FirstStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FirstStage.close();

        // Open the next stage for the game.
        BNSecondStage SecondStage = new BNSecondStage();
    }

    /**
     * Placeholder method for the "Load Saved Game" button click event.
     * Currently not implemented.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    void ClickSaveGame(ActionEvent event) {
        // This method will be implemented in the future to handle saved games.
    }
}

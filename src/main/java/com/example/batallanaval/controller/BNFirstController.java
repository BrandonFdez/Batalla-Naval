package com.example.batallanaval.controller;

import com.example.batallanaval.view.BNSecondStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class BNFirstController {
    @FXML
    private Button NuevoJuego;

    @FXML
    private void ClickNewGame(ActionEvent event) throws IOException {
        // Cerrar la ventana actual (BNFirstStage)
        Stage FirstStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FirstStage.close();
        // Abrir la ventana posterior (BNSecondStage)
        BNSecondStage SecondStage = new BNSecondStage();
    }
}
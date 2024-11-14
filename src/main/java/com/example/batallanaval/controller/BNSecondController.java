package com.example.batallanaval.controller;

import com.example.batallanaval.model.BN; // Importa la clase BN que contiene el modelo de la fragata
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class BNSecondController {

    @FXML
    private Pane MyPane;
    private BN BNModel;  // Instancia del modelo BN
    private Rectangle Frigate;  // Rectangulo para las fragatas
    private Rectangle Destroyer;  // Rectangulo para los destuctores
    private Rectangle Submarine;  // Rectangulo para los Submarinos
    private Rectangle AircraftCarrier;  // Rectangulo para los portaaviones

    // Metodo de inicialización
    @FXML
    public void initialize() {
        // Crear la instancia del modelo BN
        BNModel = new BN();

        // Obtener los rectangulos desde el modelo
        Frigate = BNModel.Frigates();
        Destroyer = BNModel.Destroyers();
        Submarine = BNModel.Submarines();
        AircraftCarrier = BNModel.AircraftCarriers();

        // Añadir los rectángulos al Pane
        MyPane.getChildren().addAll(Frigate, Destroyer, Submarine, AircraftCarrier);
    }
}

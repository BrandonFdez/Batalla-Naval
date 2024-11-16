package com.example.batallanaval.controller;

import com.example.batallanaval.model.BN; // Importa la clase BN que contiene el modelo de la fragata
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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

    // Metodo que permite arrastrar cualquier nodo y rotarlo
    private void MakeDraggableAndRotatable(Node Node) {
        final double[] DragCoordinates = new double[2]; // Este arreglo permite recordar el desplazamiento entre la posición del nodo y la posición del mouse al hacer clic en el nodo

        Node.setOnMousePressed(event -> {
            DragCoordinates[0] = Node.getLayoutX() - event.getSceneX();
            DragCoordinates[1] = Node.getLayoutY() - event.getSceneY();
        });

        Node.setOnMouseDragged(event -> {
            Node.setLayoutX(event.getSceneX() + DragCoordinates[0]);
            Node.setLayoutY(event.getSceneY() + DragCoordinates[1]);
        });

        // Evento para rotar el nodo al hacer clic derecho
        Node.setOnMouseClicked(event -> {
            if (event.isPrimaryButtonDown()) { // Verifica si es clic derecho
                // Cambia la rotación entre 0 y 90 grados
                if (Node.getRotate() == 0) {
                    Node.setRotate(90); // Rotación vertical
                } else {
                    Node.setRotate(0); // Rotación horizontal
                }
            }
        });
    }

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

        // Llama a MakeDraggableAndRotatable para hacer los objetos arrastrables
        MakeDraggableAndRotatable(Frigate);
        MakeDraggableAndRotatable(Destroyer);
        MakeDraggableAndRotatable(Submarine);
        MakeDraggableAndRotatable(AircraftCarrier);
    }
    @FXML
    private void InstructionsClick() {
        BNModel.ShowInstructions(); // Llamada al modelo para que maneje la acción
    }

}
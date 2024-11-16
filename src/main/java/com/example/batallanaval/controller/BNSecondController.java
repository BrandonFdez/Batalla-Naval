package com.example.batallanaval.controller;

import com.example.batallanaval.model.BN; // Importa la clase BN que contiene el modelo de la fragata
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;

public class BNSecondController {

    @FXML
    private Pane MyPane;
    @FXML
    private GridPane BoardGrid;
    private BN BNModel;  // Instancia del modelo BN
    private Rectangle Frigate;  // Rectangulo para las fragatas
    private Rectangle Destroyer;  // Rectangulo para los destuctores
    private Rectangle Submarine;  // Rectangulo para los Submarinos
    private Rectangle AircraftCarrier;  // Rectangulo para los portaaviones

    @FXML
    void OnDragGrid(DragEvent event) {

    }

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

        Node.setOnMouseReleased(event -> snapToGrid(Node)); // Ajustar a la celda más cercana al soltar

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

    private void snapToGrid(Node node) {
        // Obtener el tamaño de las celdas del GridPane
        double cellWidth = BoardGrid.getWidth() / BoardGrid.getColumnCount();
        double cellHeight = BoardGrid.getHeight() / BoardGrid.getRowCount();

        // Obtener las coordenadas relativas al GridPane
        double nodeXInGrid = node.getLayoutX() + node.getTranslateX() - BoardGrid.getLayoutX();
        double nodeYInGrid = node.getLayoutY() + node.getTranslateY() - BoardGrid.getLayoutY();

        // Calcular la columna y fila más cercanas
        int colIndex = (int) Math.floor(nodeXInGrid / cellWidth);
        int rowIndex = (int) Math.floor(nodeYInGrid / cellHeight);

        // Verificar si las coordenadas están dentro del GridPane
        if (colIndex >= 0 && colIndex < BoardGrid.getColumnCount() &&
                rowIndex >= 0 && rowIndex < BoardGrid.getRowCount()) {

            // Remover el nodo de su contenedor actual (Pane)
            MyPane.getChildren().remove(node);

            // Añadir el nodo al GridPane en la posición calculada
            BoardGrid.add(node, colIndex, rowIndex);

            // Ajustar las posiciones internas del nodo para que encaje
            GridPane.setRowIndex(node, rowIndex);
            GridPane.setColumnIndex(node, colIndex);

            // Deshabilitar el movimiento del nodo
            disableDragAndRotation(node);
        } else {
            // Si el nodo está fuera del GridPane, devolverlo a su posición inicial
            //node.setLayoutX(0); //esto esta comentado porque me regresaba las naves a abajo del todo
            //node.setLayoutY(0);
        }
    }


    private void disableDragAndRotation(Node node) {
        // Remover todos los eventos relacionados con el drag y rotación
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
        node.setOnMouseClicked(null);
        node.setOnMouseReleased(null);
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
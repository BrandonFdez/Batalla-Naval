package com.example.batallanaval.controller;

import com.example.batallanaval.model.BN; // Importa la clase BN que contiene el modelo de la fragata
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
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
    private boolean[][] GridOccupied; // Matriz para llevar registro de las celdas ocupadas


    @FXML
    void OnDragGrid(DragEvent event) {

    }

    // Metodo que permite arrastrar cualquier nodo y rotarlo
    private void MakeDraggableAndRotatable(Node Node) {
        final double[] DragCoordinates = new double[2]; // Este arreglo permite recordar el desplazamiento entre la posición del nodo y la posición del mouse al hacer clic en el nodo

        // Evento para comenzar a arrastrar
        Node.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) { // Solo responde al clic izquierdo para iniciar el arrastre
                DragCoordinates[0] = Node.getLayoutX() - event.getSceneX();
                DragCoordinates[1] = Node.getLayoutY() - event.getSceneY();
            }
        });

        // Evento para actualizar la posición del nodo mientras se arrastra
        Node.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) { // Arrastra solo con el clic izquierdo
                Node.setLayoutX(event.getSceneX() + DragCoordinates[0]);
                Node.setLayoutY(event.getSceneY() + DragCoordinates[1]);
            }
        });

        // Ajustar al grid al soltar el clic izquierdo
        Node.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) { // Solo ajusta si se suelta el clic izquierdo
                snapToGrid(Node);
            }
        });

        // Evento para rotar el nodo al hacer clic derecho
        Node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Detectar clic derecho para rotar
                // Cambiar la rotación entre 0 y 90 grados
                if (Node.getRotate() == 0) {
                    Node.setRotate(90); // Rotación vertical
                } else {
                    Node.setRotate(0); // Rotación horizontal
                }
            }
        });
    }

    private void snapToGrid(Node node) {
        double cellWidth = BoardGrid.getWidth() / BoardGrid.getColumnCount();
        double cellHeight = BoardGrid.getHeight() / BoardGrid.getRowCount();

        double nodeXInGrid = node.getBoundsInParent().getMinX() - BoardGrid.getBoundsInParent().getMinX();
        double nodeYInGrid = node.getBoundsInParent().getMinY() - BoardGrid.getBoundsInParent().getMinY();

        int colIndex = (int) Math.round(nodeXInGrid / cellWidth);
        int rowIndex = (int) Math.round(nodeYInGrid / cellHeight);

        int spanLength = (int) (node.getRotate() == 0 ? node.getBoundsInParent().getWidth() / cellWidth
                : node.getBoundsInParent().getHeight() / cellHeight);

        // Verificar si la posición y orientación encajan en el tablero sin exceder los límites
        if (colIndex >= 0 && rowIndex >= 0 && colIndex + spanLength <= BoardGrid.getColumnCount() && rowIndex + spanLength <= BoardGrid.getRowCount()) {
            // Verificar superposición
            if (!isOverlapping(rowIndex, colIndex, spanLength, node.getRotate() == 0)) {
                // Actualizar la matriz de ocupación
                markGridOccupied(rowIndex, colIndex, spanLength, node.getRotate() == 0);

                MyPane.getChildren().remove(node);
                BoardGrid.add(node, colIndex, rowIndex);
                node.setTranslateX(0);
                node.setTranslateY(0);
                disableDragAndRotation(node);
            }
        }
    }

    private boolean isOverlapping(int row, int col, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            if (isHorizontal) {
                if (col + i >= BoardGrid.getColumnCount() || GridOccupied[row][col + i]) {
                    return true;
                }
            } else {
                if (row + i >= BoardGrid.getRowCount() || GridOccupied[row + i][col]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void markGridOccupied(int row, int col, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            if (isHorizontal) {
                GridOccupied[row][col + i] = true;
            } else {
                GridOccupied[row + i][col] = true;
            }
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
        GridOccupied = new boolean[BoardGrid.getRowCount()][BoardGrid.getColumnCount()]; // Inicializa la matriz

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
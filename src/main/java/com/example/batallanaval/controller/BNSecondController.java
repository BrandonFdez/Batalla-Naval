package com.example.batallanaval.controller;

import com.example.batallanaval.model.BN;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.DragEvent;
import javafx.util.Duration;

/**
 * Controller to manage the logic for interacting with the board and game pieces in the Battleship game.
 */
public class BNSecondController {

    @FXML
    private Pane MyPane;
    @FXML
    private GridPane BoardGrid;

    private BN BNModel;
    private Rectangle Frigate;
    private Rectangle Destroyer;
    private Rectangle Submarine;
    private Rectangle AircraftCarrier;

    private boolean[][] GridOccupied; // Grid occupation matrix
    private Rectangle Highlight = new Rectangle(); // Highlight area for placement preview

    @FXML
    void OnDragGrid(DragEvent event) {
        // Empty drag event handler
    }

    /**
     * Makes a node draggable and rotatable.
     */
    private void MakeDraggableAndRotatable(Node node) {
        final double[] DragCoordinates = new double[2];

        node.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                DragCoordinates[0] = node.getLayoutX() - event.getSceneX();
                DragCoordinates[1] = node.getLayoutY() - event.getSceneY();
            }
        });

        node.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                node.setLayoutX(event.getSceneX() + DragCoordinates[0]);
                node.setLayoutY(event.getSceneY() + DragCoordinates[1]);
                resetMouseStopTimer();
                showPlacementHighlight(node);
            }
        });

        node.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                snapToGrid(node);
                MyPane.getChildren().remove(Highlight);
            }
        });

        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                node.setRotate(node.getRotate() == 0 ? 90 : 0);
            }
        });
    }

    /**
     * Shows a highlight area on the grid based on the node's position.
     */
    private void showPlacementHighlight(Node node) {
        double gridX = BoardGrid.getLayoutX();
        double gridY = BoardGrid.getLayoutY();

        double cellSize = 35.0; // Assuming fixed cell size
        double nodeXInGrid = node.getBoundsInParent().getMinX() - gridX;
        double nodeYInGrid = node.getBoundsInParent().getMinY() - gridY;

        double colIndex = Math.round(nodeXInGrid / cellSize) * cellSize;
        double rowIndex = Math.round(nodeYInGrid / cellSize) * cellSize;

        double spanLength = node.getRotate() == 0
                ? node.getBoundsInParent().getWidth() / cellSize
                : node.getBoundsInParent().getHeight() / cellSize;

        Highlight.setWidth(node.getRotate() == 0 ? spanLength * cellSize : cellSize);
        Highlight.setHeight(node.getRotate() == 0 ? cellSize : spanLength * cellSize);
        Highlight.setX(gridX + colIndex);
        Highlight.setY(gridY + rowIndex);
        Highlight.setOpacity(0.3);

        if (!MyPane.getChildren().contains(Highlight)) {
            MyPane.getChildren().add(Highlight);
        }
    }

    /**
     * Snaps a node to the nearest grid cell and validates placement.
     */
    private void snapToGrid(Node node) {
        double cellWidth = BoardGrid.getWidth() / BoardGrid.getColumnCount();
        double cellHeight = BoardGrid.getHeight() / BoardGrid.getRowCount();

        double nodeXInGrid = node.getBoundsInParent().getMinX() - BoardGrid.getBoundsInParent().getMinX();
        double nodeYInGrid = node.getBoundsInParent().getMinY() - BoardGrid.getBoundsInParent().getMinY();

        int colIndex = (int) Math.round(nodeXInGrid / cellWidth);
        int rowIndex = (int) Math.round(nodeYInGrid / cellHeight);

        int spanLength = (int) (node.getRotate() == 0
                ? node.getBoundsInParent().getWidth() / cellWidth
                : node.getBoundsInParent().getHeight() / cellHeight);

        if (isPlacementValid(rowIndex, colIndex, spanLength, node.getRotate() == 0)) {
            markGridOccupied(rowIndex, colIndex, spanLength, node.getRotate() == 0);
            MyPane.getChildren().remove(node);
            BoardGrid.add(node, colIndex, rowIndex);
            node.setTranslateX(0);
            node.setTranslateY(0);
            disableDragAndRotation(node);
        }
    }

    /**
     * Checks if a placement is valid.
     */
    private boolean isPlacementValid(int row, int col, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            if (isHorizontal) {
                if (col + i >= BoardGrid.getColumnCount() || GridOccupied[row][col + i]) return false;
            } else {
                if (row + i >= BoardGrid.getRowCount() || GridOccupied[row + i][col]) return false;
            }
        }
        return true;
    }

    /**
     * Marks cells as occupied.
     */
    private void markGridOccupied(int row, int col, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            if (isHorizontal) GridOccupied[row][col + i] = true;
            else GridOccupied[row + i][col] = true;
        }
    }

    /**
     * Disables dragging and rotation for a node.
     */
    private void disableDragAndRotation(Node node) {
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
        node.setOnMouseClicked(null);
        node.setOnMouseReleased(null);
    }

    /**
     * Resets the mouse stop timer.
     */
    private void resetMouseStopTimer() {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
        pause.setOnFinished(event -> MyPane.getChildren().remove(Highlight));
        pause.play();
    }

    @FXML
    public void initialize() {
        BNModel = new BN();
        GridOccupied = new boolean[BoardGrid.getRowCount()][BoardGrid.getColumnCount()];

        // Crear los barcos con la cantidad específica
        Frigate = BNModel.Frigates();
        Destroyer = BNModel.Destroyers();
        Submarine = BNModel.Submarines();
        AircraftCarrier = BNModel.AircraftCarriers();

        // Añadir el portaaviones
        MyPane.getChildren().add(AircraftCarrier);
        MakeDraggableAndRotatable(AircraftCarrier);

        // Añadir las fragatas y hacerlas arrastrables
        for (int i = 0; i < 4; i++) {
            Rectangle frigate = BNModel.Frigates();
            MyPane.getChildren().add(frigate);
            MakeDraggableAndRotatable(frigate);
        }

        // Añadir los destructores y hacerlos arrastrables
        for (int i = 0; i < 3; i++) {
            Rectangle destroyer = BNModel.Destroyers();
            MyPane.getChildren().add(destroyer);
            MakeDraggableAndRotatable(destroyer);
        }

        // Añadir los submarinos y hacerlos arrastrables
        for (int i = 0; i < 2; i++) {
            Rectangle submarine = BNModel.Submarines();
            MyPane.getChildren().add(submarine);
            MakeDraggableAndRotatable(submarine);
        }
    }

    @FXML
    private void InstructionsClick() {
        BNModel.ShowInstructions();
    }
}
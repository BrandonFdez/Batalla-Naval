package com.example.batallanaval.controller;

import com.example.batallanaval.model.BN; // Imports the BN class that contains the model for the ship
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Controller to manage the logic for interacting with the board and game pieces in the Battleship game.
 * Allows for moving and rotating ships on the board and handles the occupation of the grid.
 */
public class BNSecondController {

    @FXML
    private Pane MyPane;  // Pane where ships will be placed on the board
    @FXML
    private GridPane BoardGrid;  // Grid where ships are distributed
    private BN BNModel;  // Instance of the BN model that contains ship logic
    private Rectangle Frigate;  // Rectangle representing the frigate
    private Rectangle Destroyer;  // Rectangle representing the destroyer
    private Rectangle Submarine;  // Rectangle representing the submarine
    private Rectangle AircraftCarrier;  // Rectangle representing the aircraft carrier
    private boolean[][] GridOccupied; // Matrix tracking the occupied cells on the grid
    private Rectangle Highlight = new Rectangle();
    /**
     * Event triggered when a drag event occurs on the grid. Currently not implemented.
     *
     * @param event The drag event.
     */
    @FXML
    void OnDragGrid(DragEvent event) {
        // Empty method for handling drag events
    }

    /**
     * Method that allows any node to be draggable and rotatable on the board.
     * Triggered by left-click to drag and right-click to rotate the node.
     *
     * @param Node The node (ship) to make draggable and rotatable.
     */
    private void MakeDraggableAndRotatable(Node Node) {
        final double[] DragCoordinates = new double[2]; // Array to store the difference between the node's position and mouse position
        // Event triggered when the left mouse button is pressed to start dragging
        Node.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) { // Only responds to left-click to start dragging
                DragCoordinates[0] = Node.getLayoutX() - event.getSceneX();
                DragCoordinates[1] = Node.getLayoutY() - event.getSceneY();
            }
        });

        // Event triggered while dragging the node
        Node.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) { // Allows dragging only with left-click
                Node.setLayoutX(event.getSceneX() + DragCoordinates[0]);
                Node.setLayoutY(event.getSceneY() + DragCoordinates[1]);

                // Reset the timer every time the mouse moves to track stop event
                resetMouseStopTimer();

                // Resaltar la zona donde el nodo podría ir
                Highlight.setWidth(0);  // Limpiar el área destacada
                Highlight.setHeight(0);

                // Determinar la cantidad de celdas ocupadas por el nodo
                double spanLength = (Node.getRotate() == 0
                        ? Node.getBoundsInParent().getWidth() / 35.0
                        : Node.getBoundsInParent().getHeight() / 35.0);

                // Obtener las coordenadas absolutas del GridPane
                double gridX = BoardGrid.getLayoutX();
                double gridY = BoardGrid.getLayoutY();

                // Calcular la posición del nodo en relación al GridPane, ajustado por su posición en el Pane
                double nodeXInGrid = Node.getBoundsInParent().getMinX() - gridX;
                double nodeYInGrid = Node.getBoundsInParent().getMinY() - gridY;

                // Ajustar para que se alineen al múltiplo de 35 considerando la posición del GridPane
                double colIndex = Math.round(nodeXInGrid / 35.0) * 35;
                double rowIndex = Math.round(nodeYInGrid / 35.0) * 35;

                // Calcular el área resaltada según la orientación (horizontal o vertical)
                if (Node.getRotate() == 0) { // Horizontal
                    Highlight.setWidth(spanLength * 35);
                    Highlight.setHeight(35);
                    Highlight.setX(gridX + colIndex);
                    Highlight.setY(gridY + rowIndex);
                } else { // Vertical
                    Highlight.setWidth(35);
                    Highlight.setHeight(spanLength * 35);
                    Highlight.setX(gridX + colIndex);
                    Highlight.setY(gridY + rowIndex);
                }

                Highlight.setOpacity(0.3); // Para que no opaque el grid, solo lo resalte
                if (!MyPane.getChildren().contains(Highlight)) {
                    MyPane.getChildren().add(Highlight);
                }
            }
        });

        // Snap the node to the grid when the left-click is released
        Node.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) { // Only adjusts when the left-click is released
                snapToGrid(Node); // Adjust the node to the grid
                // Remover el área de resaltado cuando se suelta el nodo
                MyPane.getChildren().remove(Highlight);
            }
        });

        // Event to rotate the node when right-clicked
        Node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) { // Detect right-click to rotate
                // Toggle rotation between 0 and 90 degrees
                if (Node.getRotate() == 0) {
                    Node.setRotate(90); // Rotate to vertical
                } else {
                    Node.setRotate(0); // Rotate to horizontal
                }
            }
        });
    }

    /**
     * Snaps the node to the nearest grid cell based on its position and orientation.
     * Also checks if the node fits within the grid bounds and does not overlap with other ships.
     *
     * @param node The node to be snapped to the grid.
     */
    private void snapToGrid(Node node) {
        double cellWidth = BoardGrid.getWidth() / BoardGrid.getColumnCount();
        double cellHeight = BoardGrid.getHeight() / BoardGrid.getRowCount();

        // Calculate the position of the node within the grid
        double nodeXInGrid = node.getBoundsInParent().getMinX() - BoardGrid.getBoundsInParent().getMinX();
        double nodeYInGrid = node.getBoundsInParent().getMinY() - BoardGrid.getBoundsInParent().getMinY();

        // Find the column and row index corresponding to the node's position
        int colIndex = (int) Math.round(nodeXInGrid / cellWidth);
        int rowIndex = (int) Math.round(nodeYInGrid / cellHeight);

        // Determine the span (length) of the node based on its orientation
        int spanLength = (int) (node.getRotate() == 0 ? node.getBoundsInParent().getWidth() / cellWidth
                : node.getBoundsInParent().getHeight() / cellHeight);

        // Adjust the span check based on orientation
        boolean fitsHorizontally = node.getRotate() == 0 && (colIndex + spanLength) <= BoardGrid.getColumnCount();
        boolean fitsVertically = node.getRotate() != 0 && (rowIndex + spanLength) <= BoardGrid.getRowCount();

        // Check if the position and orientation fit within the grid
        if (colIndex >= 0 && rowIndex >= 0 && (fitsHorizontally || fitsVertically)) {
            // Check if there is any overlap with other ships
            if (!isOverlapping(rowIndex, colIndex, spanLength, node.getRotate() == 0)) {
                // Mark the occupied cells and place the node on the grid
                markGridOccupied(rowIndex, colIndex, spanLength, node.getRotate() == 0);

                MyPane.getChildren().remove(node);
                BoardGrid.add(node, colIndex, rowIndex);
                node.setTranslateX(0);
                node.setTranslateY(0);
                disableDragAndRotation(node); // Disable dragging and rotation once the ship is placed
            }
        }
    }

    /**
     * Reset the timer that will remove the shadow when the mouse stops moving.
     */
    private void resetMouseStopTimer() {
        // Create a pause transition that waits for 0.3 seconds of inactivity
        PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
        pause.setOnFinished(event -> MyPane.getChildren().remove(Highlight)); // Remove the shadow after the pause
        pause.play(); // Start the timer
    }


    /**
     * Checks if the node will overlap with any other placed ships on the grid.
     *
     * @param row The starting row of the node.
     * @param col The starting column of the node.
     * @param length The length of the node.
     * @param isHorizontal Whether the node is placed horizontally.
     * @return True if there is an overlap, false if there isn't.
     */
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

    /**
     * Marks the occupied cells on the grid matrix for a placed node.
     *
     * @param row The starting row of the node.
     * @param col The starting column of the node.
     * @param length The length of the node.
     * @param isHorizontal Whether the node is placed horizontally.
     */
    private void markGridOccupied(int row, int col, int length, boolean isHorizontal) {
        for (int i = 0; i < length; i++) {
            if (isHorizontal) {
                GridOccupied[row][col + i] = true;
            } else {
                GridOccupied[row + i][col] = true;
            }
        }
    }

    /**
     * Disables the drag and rotation events for a node.
     * Once the node is placed on the grid, it can no longer be moved or rotated.
     *
     * @param node The node to disable the drag and rotation events for.
     */
    private void disableDragAndRotation(Node node) {
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
        node.setOnMouseClicked(null);
        node.setOnMouseReleased(null);
    }

    /**
     * Initialization method. Sets up the ship rectangles and adds them to the board.
     * Also enables the drag and rotation functionality for each ship.
     */
    @FXML
    public void initialize() {
        BNModel = new BN();  // Create an instance of the BN model
        GridOccupied = new boolean[BoardGrid.getRowCount()][BoardGrid.getColumnCount()]; // Initialize the occupied grid matrix

        // Get the ship rectangles from the model
        Frigate = BNModel.Frigates();
        Destroyer = BNModel.Destroyers();
        Submarine = BNModel.Submarines();
        AircraftCarrier = BNModel.AircraftCarriers();

        // Add the ships to the Pane
        MyPane.getChildren().addAll(Frigate, Destroyer, Submarine, AircraftCarrier);

        // Make the ships draggable and rotatable
        MakeDraggableAndRotatable(Frigate);
        MakeDraggableAndRotatable(Destroyer);
        MakeDraggableAndRotatable(Submarine);
        MakeDraggableAndRotatable(AircraftCarrier);
    }

    /**
     * Displays the game instructions when the instructions button is clicked.
     */
    @FXML
    private void InstructionsClick() {
        BNModel.ShowInstructions();  // Call the model to show the instructions
    }

}

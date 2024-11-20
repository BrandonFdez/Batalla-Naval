package com.example.batallanaval.model;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Represents the game model for the Naval Battle game.
 * This class implements the IBN interface and provides methods
 * to create game elements (shapes) and display alerts for various scenarios.
 */
public class BN implements IBN {

    /**
     * Draws an "X" using two intersecting lines.
     *
     * @return A Group containing the two lines forming the "X".
     */
    @Override
    public Group DrawX() {
        // Create the first diagonal line
        Line line1 = new Line(0, 0, 20, 20); // Diagonal line from top-left to bottom-right
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(3); // Set line thickness

        // Create the second diagonal line
        Line line2 = new Line(0, 20, 20, 0); // Diagonal line from bottom-left to top-right
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(3); // Set line thickness

        // Return a Group containing both lines
        return new Group(line1, line2);
    }

    /**
     * Draws a circle to represent a missed shot on the grid.
     *
     * @return A Circle object with predefined style.
     */
    @Override
    public Circle DrawCircle() {
        Circle circle = new Circle(14); // Create a circle with radius 14
        circle.setFill(Color.TRANSPARENT); // Set the fill color to transparent
        circle.setStroke(Color.GRAY); // Set the border color
        circle.setStrokeWidth(4); // Set the border thickness
        return circle;
    }

    /**
     * Creates a rectangle representing a frigate.
     *
     * @return A Rectangle styled as a frigate.
     */
    @Override
    public Rectangle Frigates() {
        Rectangle Frigate = new Rectangle(38.5, 470, 35, 35); // Define dimensions and position
        Frigate.setStroke(Color.BLACK);
        Frigate.setFill(Color.LIGHTGRAY);
        return Frigate;
    }

    /**
     * Creates a rectangle representing a destroyer.
     *
     * @return A Rectangle styled as a destroyer.
     */
    @Override
    public Rectangle Destroyers() {
        Rectangle Destroyer = new Rectangle(96.833, 470, 70, 35); // Define dimensions and position
        Destroyer.setStroke(Color.BLACK);
        Destroyer.setFill(Color.WHITESMOKE);
        return Destroyer;
    }

    /**
     * Creates a rectangle representing a submarine.
     *
     * @return A Rectangle styled as a submarine.
     */
    @Override
    public Rectangle Submarines() {
        Rectangle Submarine = new Rectangle(190.166, 470, 105, 35); // Define dimensions and position
        Submarine.setStroke(Color.BLACK);
        Submarine.setFill(Color.MIDNIGHTBLUE);
        return Submarine;
    }

    /**
     * Creates a rectangle representing an aircraft carrier.
     *
     * @return A Rectangle styled as an aircraft carrier.
     */
    @Override
    public Rectangle AircraftCarriers() {
        Rectangle AircraftCarrier = new Rectangle(318.5, 470, 140, 35); // Define dimensions and position
        AircraftCarrier.setStroke(Color.BLACK);
        AircraftCarrier.setFill(Color.GRAY);
        return AircraftCarrier;
    }

    /**
     * Displays the instructions for the game in a dialog box.
     */
    @Override
    public void ShowInstructions() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an information alert
        alert.setTitle("Instrucciones");
        alert.setHeaderText("Instrucciones del juego");

        // Create a TextArea to display detailed instructions
        TextArea textArea = new TextArea();
        textArea.setText("Tablero de posición: Representa el territorio del jugador humano, en él se distribuye" +
                " su flota antes de comenzar la partida y sólo será de observación. Verá la posición de" +
                " sus barcos y los disparos de su oponente en su territorio, pero no podrá realizar ningún" +
                " cambio ni disparo en él.\n" +
                "• Tablero principal: Representa el territorio del jugador máquina, donde tiene" +
                " desplegada su flota. Será aquí donde se desarrollen los movimientos (disparos) del" +
                " jugador humano tratando de hundir los barcos enemigos. Este tablero aparecerá en" +
                " la pantalla del jugador una vez comience la partida y en él quedarán registrados todos" +
                " sus movimientos, reflejando tanto los disparos al agua como los barcos tocados y" +
                " hundidos en tiempo real.\n" +
                "Cada jugador tiene una flota de 10 barcos de diferente tamaño, ubicados de manera" +
                " horizontal o vertical, por lo que cada uno ocupará un número determinado de casillas en el" +
                " tablero distribuidos de la siguiente manera:\n" +
                "• 1 portaaviones: ocupa 4 casillas.\n" +
                "• 2 submarinos: ocupan 3 casillas cada uno.\n" +
                "• 3 destructores: ocupan 2 casillas cada uno.\n" +
                "• 4 fragatas: ocupan 1 casilla cada uno.\n" +
                "En este juego se tiene la siguientes terminologías y movimientos:\n" +
                "• Agua: cuando se dispara sobre una casilla donde no está colocado ningún barco" +
                " enemigo. En el tablero principal del jugador aparecerá una X. Pasa el turno a su" +
                " oponente.\n" +
                "• Tocado: cuando se dispara en una casilla en la que está ubicado un barco enemigo" +
                " que ocupa 2 o más casillas, se destruirá sólo una parte del barco. En el tablero del" +
                " jugador aparecerá esa parte del barco con una marca indicativa de que ha sido tocado.\n" +
                "El jugador vuelve a disparar.\n" +
                "• Hundido: si se dispara en una casilla en la que está ubicado una fragata (1 casilla) u" +
                " otro barco con el resto de las casillas tocadas, el barco se hundirá, es decir, se ha" +
                " eliminado ese barco del juego. Aparecerá en el tablero principal del jugador, el barco" +
                " completo con la marca indicativa de que ha sido hundido. El jugador puede volver a" +
                " disparar, siempre y cuando no haya hundido toda la flota de su enemigo, en cuyo caso" +
                " habrá ganado."); /* Add full text here */;
        textArea.setWrapText(true); // Enable text wrapping
        textArea.setEditable(false); // Disable text editing
        alert.getDialogPane().setContent(textArea); // Add the TextArea to the alert
        textArea.setPrefSize(400, 400); // Set preferred size for the TextArea
        alert.showAndWait();
    }

    /**
     * Displays a defeat message in a dialog box.
     */
    @Override
    public void DefeatAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an information alert
        alert.setTitle("Derrota");
        alert.setHeaderText("¡Oh no! Has perdido la batalla naval.");
        alert.showAndWait();
    }

    /**
     * Displays an error message in a dialog box.
     */
    @Override
    public void ErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Create an error alert
        alert.setTitle("Error");
        alert.setHeaderText("Acción inválida");
        alert.showAndWait();
    }

    /**
     * Displays a victory message in a dialog box.
     */
    @Override
    public void WinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an information alert
        alert.setTitle("Victoria");
        alert.setHeaderText("¡Felicidades! Has ganado la batalla naval.");
        alert.showAndWait();
    }
}
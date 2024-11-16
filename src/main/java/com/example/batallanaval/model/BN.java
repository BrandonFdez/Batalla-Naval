package com.example.batallanaval.model;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BN implements IBN {
    @Override
    public Rectangle Frigates() {
        Rectangle Frigate = new Rectangle(38.5, 470, 35,35);
        Frigate.setStroke(Color.BLACK);
        Frigate.setFill(Color.GRAY);
        return Frigate;
    }

    @Override
    public Rectangle  Destroyers() {
        Rectangle Destroyer = new Rectangle(96.833, 470, 70,35);
        Destroyer.setStroke(Color.BLACK);
        Destroyer.setFill(Color.GRAY);
        return Destroyer;
    }

    @Override
    public Rectangle Submarines() {
        Rectangle Submarine = new Rectangle(190.166, 470, 105,35);
        Submarine.setStroke(Color.BLACK);
        Submarine.setFill(Color.GRAY);
        return Submarine;
    }

    @Override
    public Rectangle AircraftCarriers() {
        Rectangle AircraftCarrier = new Rectangle(318.5, 470, 140,35);
        AircraftCarrier.setStroke(Color.BLACK);
        AircraftCarrier.setFill(Color.GRAY);
        return AircraftCarrier;
    }

    @Override
    public void ShowInstructions() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instrucciones");
        alert.setHeaderText("Instrucciones del juego");

        // Crear un TextArea para mostrar el texto largo
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
                " habrá ganado.");
        textArea.setWrapText(true); // Permite el ajuste automático del texto
        textArea.setEditable(false); // Desactiva la edición del texto
        alert.getDialogPane().setContent(textArea); // Insertar el TextArea en el Alert
        textArea.setPrefSize(400, 400); // Establecer un tamaño preferido para el TextArea
        alert.showAndWait();
    }

    @Override
    public void DefeatAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Derrota");
        alert.setHeaderText("¡Oh no! Has perdido la batalla naval.");
        alert.showAndWait();
    }

    @Override
    public void ErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Acción invalida");
        alert.showAndWait();
    }

    @Override
    public void WinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victoria");
        alert.setHeaderText("¡Felicidades! Has ganado la batalla naval.");
        alert.showAndWait();
    }
}

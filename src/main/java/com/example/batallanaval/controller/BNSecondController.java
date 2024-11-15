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
    private void showInstructions(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instrucciones");
        alert.setHeaderText("Instrucciones del juego");
        alert.setContentText("Tablero de posición: Representa el territorio del jugador humano, en él se distribuye" +
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
        alert.showAndWait();
    }
}
package com.example.batallanaval.model;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BN implements IBN {
    @Override
    public Rectangle Frigates() {
        Rectangle Frigate = new Rectangle(38.5, 300, 35,35);
        Frigate.setStroke(Color.BLACK);
        Frigate.setFill(Color.WHITE);
        return Frigate;
    }

    @Override
    public Rectangle  Destroyers() {
        Rectangle Destroyer = new Rectangle(500, 500, 70,35);
        Destroyer.setStroke(Color.BLACK);
        Destroyer.setFill(Color.WHITE);
        return Destroyer;
    }

    @Override
    public Rectangle Submarines() {
        Rectangle Submarine = new Rectangle(40, 40, 105,35);
        Submarine.setStroke(Color.BLACK);
        Submarine.setFill(Color.WHITE);
        return Submarine;
    }

    @Override
    public Rectangle AircraftCarriers() {
        Rectangle AircraftCarrier = new Rectangle(40, 40, 140,35);
        AircraftCarrier.setStroke(Color.BLACK);
        AircraftCarrier.setFill(Color.WHITE);
        return AircraftCarrier;
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

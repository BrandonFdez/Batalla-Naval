package com.example.batallanaval.model;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public interface IBN {
    Group DrawX();         // Método para dibujar la "X"
    Circle DrawCircle();
    Rectangle Frigates();
    Rectangle Destroyers();
    Rectangle Submarines();
    Rectangle AircraftCarriers();
    void ShowInstructions();
    void DefeatAlert();
    void ErrorAlert();
    void WinAlert();
}

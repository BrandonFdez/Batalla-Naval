package com.example.batallanaval.model;

import javafx.scene.shape.Rectangle;

public interface IBN {
    Rectangle Frigates();
    Rectangle Destroyers();
    Rectangle Submarines();
    Rectangle AircraftCarriers();
    void ShowInstructions();
    void DefeatAlert();
    void ErrorAlert();
    void WinAlert();
}

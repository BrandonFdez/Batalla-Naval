package com.example.batallanaval.model;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Interface that defines the core methods for the Naval Battle game model.
 * Provides methods to create visual elements and display alerts for game states.
 */
public interface IBN {

    /**
     * Draws an "X" to represent a missed shot.
     *
     * @return A Group containing the lines that form the "X".
     */
    Group DrawX();

    /**
     * Draws a circle to represent a missed shot on the board.
     *
     * @return A Circle with predefined style.
     */
    Circle DrawCircle();

    /**
     * Creates a rectangle representing a frigate.
     *
     * @return A Rectangle styled as a frigate.
     */
    Rectangle Frigates();

    /**
     * Creates a rectangle representing a destroyer.
     *
     * @return A Rectangle styled as a destroyer.
     */
    Rectangle Destroyers();

    /**
     * Creates a rectangle representing a submarine.
     *
     * @return A Rectangle styled as a submarine.
     */
    Rectangle Submarines();

    /**
     * Creates a rectangle representing an aircraft carrier.
     *
     * @return A Rectangle styled as an aircraft carrier.
     */
    Rectangle AircraftCarriers();

    /**
     * Displays an information dialog with the game instructions.
     */
    void ShowInstructions();

    /**
     * Displays an alert indicating that the player has been defeated.
     */
    void DefeatAlert();

    /**
     * Displays an error alert for invalid actions.
     */
    void ErrorAlert();

    /**
     * Displays a victory alert when the player wins the game.
     */
    void WinAlert();
}
package com.cheekcollectors.radioapp.nodes;

import javafx.scene.control.Button;

public class FavoriteButton extends Button {

    private int position;

    /**
     * Constructor of FavoriteButton
     * @param label The label of the button
     * @param position The position of the favorite button (0-5) (0 indexed)
     */
    public FavoriteButton(String label, int position) {
        super(label);
        this.position = position;
    }

    /**
     * Return the favorite position of the button
     * @return int The position of the favorite button
     */
    public int getPosition() {
        return position;
    }
}

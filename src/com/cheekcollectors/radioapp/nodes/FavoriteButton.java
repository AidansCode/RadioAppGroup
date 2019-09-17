package com.cheekcollectors.radioapp.nodes;

import javafx.scene.control.Button;

public class FavoriteButton extends Button {

    private int position;

    public FavoriteButton(String label, int position) {
        super(label);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}

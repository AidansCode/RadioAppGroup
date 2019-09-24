package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.FavoriteMode;
import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.nodes.FavoriteButton;
import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.utils.Utilities;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class SetSelectFavoriteEvent implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        Radio radio = RadioAppGUI.getRadio();
        if (!radio.isOn())
            return;

        if (event.getSource() instanceof FavoriteButton ) {
            FavoriteButton button = (FavoriteButton) event.getSource();
            MouseButton mouseButton = event.getButton();
            FavoriteMode favoriteMode = RadioAppGUI.getFavoriteMode();
            int position = button.getPosition();

            if (mouseButton == MouseButton.SECONDARY)
                unselectFavoriteEvent(radio, position);
            else if (favoriteMode == FavoriteMode.SET)
                setFavoriteEvent(radio, position);
            else if (favoriteMode == FavoriteMode.SELECT)
                selectFavoriteEvent(radio, position);
        }
    }

    private static void unselectFavoriteEvent(Radio radio, int position) {
        if (!radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("There is no favorite at position " + (position+1) + "!");
        else {
            boolean shouldRemove = Utilities.getUserConfirmation("Are you sure you want to remove the favorite at position " + (position+1) + "!");
            if (shouldRemove)
                radio.unsetFavorite(position);
        }
    }

    private static void setFavoriteEvent(Radio radio, int position) {
        if (radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("There is already a favorite at this position! Remove it to change.");
        else {
            radio.setFavorite(radio.getCurrentStation(), position);
            Utilities.showUserAlert("You have set the current station to favorite number " + (position+1) + "!");
        }
    }

    private static void selectFavoriteEvent(Radio radio, int position) {
        if (!radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("Can not select favorite! There is no favorite at this position");
        else {
            radio.setFrequency(radio.getFavorite(position));
        }
    }

}

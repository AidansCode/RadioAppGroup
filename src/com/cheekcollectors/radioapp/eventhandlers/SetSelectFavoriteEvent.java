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

    /**
     * Receives the SetSelectFavoriteEvent and decides which action should be taken (3 potential)
     * Can be removing a favorite, setting a favorite, or selecting a favorite
     * @param event The MouseEvent of the event
     */
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

            RadioAppGUI.updateDisplay();
        }
    }

    /**
     * Executes the event in the case of a favorite being unselected
     * @param radio The instance of the Radio class being used
     * @param position The position of the favorite button that was clicked
     */
    private static void unselectFavoriteEvent(Radio radio, int position) {
        if (!radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("There is no favorite at position " + (position+1) + "!");
        else {
            boolean shouldRemove = Utilities.getUserConfirmation("Are you sure you want to remove the favorite at position " + (position+1) + "!");
            if (shouldRemove)
                radio.unsetFavorite(position);
        }
    }

    /**
     * Executes the event in the case of a favorite being set
     * @param radio The instance of the Radio class being used
     * @param position The position of the favorite button that was clicked
     */
    private static void setFavoriteEvent(Radio radio, int position) {
        if (radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("There is already a favorite at this position! Remove it to change.");
        else {
            radio.setFavorite(radio.getCurrentStation(), position);
            Utilities.showUserAlert("You have set the current station to favorite number " + (position+1) + "!\n" +
                    "To remove this favorite, right click on the " +(position+1) +" button");
        }
    }

    /**
     * Executes the event in the case of a favorite being selected
     * @param radio The instance of the Radio class being used
     * @param position The position of the favorite button that was clicked
     */
    private static void selectFavoriteEvent(Radio radio, int position) {
        if (!radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("Can not select favorite! There is no favorite at this  position");
        else {
            radio.setFrequency(radio.getFavorite(position));
        }
    }

}

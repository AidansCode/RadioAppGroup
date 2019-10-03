/*************************************************************************
 * CSC - 223 Fall 2019
 * @author - checkcollectors
 * Date : 10/3
 * PROJECT #2 Radio
 * Class Description:
 *************************************************************************/

package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.FavoriteMode;
import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.nodes.FavoriteButton;
import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.utils.Utilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class SetSelectFavoriteEvent implements EventHandler<ActionEvent> {

    /**
     * Receives the SetSelectFavoriteEvent and decides which action should be taken (3 potential)
     * Can be removing a favorite, setting a favorite, or selecting a favorite
     * @param event The MouseEvent of the event
     */
    @Override
    public void handle(ActionEvent event) {
        Radio radio = RadioAppGUI.getRadio();
        if (!radio.isOn())
            return;

        if (event.getSource() instanceof FavoriteButton ) {
            FavoriteButton button = (FavoriteButton) event.getSource();
            FavoriteMode favoriteMode = RadioAppGUI.getFavoriteMode();
            int position = button.getPosition();
            if (favoriteMode == FavoriteMode.SET)
                setFavoriteEvent(radio, position);
            else if (favoriteMode == FavoriteMode.SELECT)
                selectFavoriteEvent(radio, position);

            RadioAppGUI.updateDisplay();
        }
    }

    /**
     * Executes the event in the case of a favorite being set
     * @param radio The instance of the Radio class being used
     * @param position The position of the favorite button that was clicked
     */
    private static void setFavoriteEvent(Radio radio, int position) {
        if (radio.hasFavoriteAtPosition(position)) {
            boolean shouldRemove = Utilities.getUserConfirmation("Are you sure you want to replace the favorite at position " + (position + 1) + "!");
            if (shouldRemove)
                setFavorite(radio, position);
        }
        else
            setFavorite(radio, position);
    }

    /**
     * Handles the setting of a new favorite and alerts the user of the change
     * @param radio The radio instance being used
     * @param position The position of the new favorite
     */
    private static void setFavorite(Radio radio, int position) {
        radio.setFavorite(radio.getCurrentStation(), position);
        Utilities.showUserAlert("You have set the current station to favorite number " + (position + 1));
    }

    /**
     * Executes the event in the case of a favorite being selected
     * @param radio The instance of the Radio class being used
     * @param position The position of the favorite button that was clicked
     */
    private static void selectFavoriteEvent(Radio radio, int position) {
        if (!radio.hasFavoriteAtPosition(position))
            Utilities.showUserAlert("Can not select favorite! There is no favorite at this position");
        else {
            radio.setFrequency(radio.getFavorite(position));
        }
    }

}

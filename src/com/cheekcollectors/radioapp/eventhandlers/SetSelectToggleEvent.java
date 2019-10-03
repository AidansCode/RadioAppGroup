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
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SetSelectToggleEvent implements EventHandler<ActionEvent> {

    /**
     * Handles the action event called when the set/select toggle button is pressed
     * @param actionEvent The action event of the set/select toggle button
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Radio radio = RadioAppGUI.getRadio();

        if (radio.isOn()) {
            if (RadioAppGUI.getFavoriteMode() == FavoriteMode.SET) {
                RadioAppGUI.setFavoriteMode(FavoriteMode.SELECT);
            } //uses the getFavorite mode method to set the Set/Select button select
            else if (RadioAppGUI.getFavoriteMode() == FavoriteMode.SELECT) {
                RadioAppGUI.setFavoriteMode(FavoriteMode.SET);
            } //uses the getFavorite mode method to set the Set/Select button select
            RadioAppGUI.updateDisplay(); //updates the radios display
        }
    }

}

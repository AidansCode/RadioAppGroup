package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.FavoriteMode;
import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SetSelectToggleEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Radio radio = RadioAppGUI.getRadio();

        if (radio.isOn()) {
            if (RadioAppGUI.getFavoriteMode() == FavoriteMode.SET)
                RadioAppGUI.setFavoriteMode(FavoriteMode.SELECT);
            else
                RadioAppGUI.setFavoriteMode(FavoriteMode.SET);

            RadioAppGUI.updateDisplay();
        }
    }

}

package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.FavoriteMode;
import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SetSelectToggleEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        Radio radio = RadioAppGUI.getRadio();

        if (RadioAppGUI.getFavoriteMode() == FavoriteMode.SET){
            RadioAppGUI.setFavoriteMode(FavoriteMode.SELECT);
        }
        else if (RadioAppGUI.getFavoriteMode() == FavoriteMode.SELECT){
            RadioAppGUI.setFavoriteMode(FavoriteMode.SET);
        }
    }
}

package com.cheekcollectors.radioapp;

import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

    public class PowerButtonActionEvent implements EventHandler<ActionEvent> {
        public void handle(ActionEvent actionEvent){
            Radio radio =RadioAppGUI.getRadio();
            radio.togglePower();
            RadioAppGUI.updateStatus(radio.getStatusString());
        }
}

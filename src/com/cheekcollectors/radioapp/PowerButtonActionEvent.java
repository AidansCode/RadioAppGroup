package com.cheekcollectors.radioapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

    public class PowerButtonActionEvent implements EventHandler<ActionEvent> {
        public void handle(ActionEvent actionEvent){
            Radio radio =RadioApp.getRadio();
            radio.togglePower();
            RadioApp.updateStatus(radio.getStatusString());
        }
}

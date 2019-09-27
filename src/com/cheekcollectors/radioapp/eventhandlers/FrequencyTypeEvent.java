package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FrequencyTypeEvent implements EventHandler<ActionEvent> {


    public void handle(ActionEvent actionEvent) {
        Radio radio = RadioAppGUI.getRadio();

        radio.toggleFrequencyType();
        RadioAppGUI.updateStatus(radio.getStatusString());
    }


}

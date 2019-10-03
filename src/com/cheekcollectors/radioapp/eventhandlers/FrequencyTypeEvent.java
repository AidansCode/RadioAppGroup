package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FrequencyTypeEvent implements EventHandler<ActionEvent> {


    public void handle(ActionEvent actionEvent) {
        Radio radio = RadioAppGUI.getRadio();

        radio.toggleFrequencyType(); //uses the toggleFrequencyType method to change the radio frequency between am and fm
        RadioAppGUI.updateDisplay(); //updates the radios display for the user to see.
    }


}

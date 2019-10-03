package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PowerButtonEvent implements EventHandler<ActionEvent> {

    /**
     * Receives the event of the PowerButtonEvent
     * @param actionEvent The ActionEvent for the PowerButtonEvent
     */
    public void handle(ActionEvent actionEvent){
        Radio radio = RadioAppGUI.getRadio();

        radio.togglePower(); //turns the radio on or off when clicked using the togglePower method
        RadioAppGUI.updateDisplay(); //updtaes the radio's display
    }

}

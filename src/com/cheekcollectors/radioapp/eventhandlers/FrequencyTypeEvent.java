/*************************************************************************
 * CSC - 223 Fall 2019
 * @author - checkcollectors
 * Date : 10/3
 * PROJECT #2 Radio
 * Class Description:
 *************************************************************************/

package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FrequencyTypeEvent implements EventHandler<ActionEvent> {

    /**
     * Handles the action event called when the frequency type is changed
     * @param actionEvent
     */
    public void handle(ActionEvent actionEvent) {
        Radio radio = RadioAppGUI.getRadio();

        radio.toggleFrequencyType(); //change the radio frequency between am/fm
        RadioAppGUI.updateDisplay(); //updates the radios display for the user to see.
    }

}

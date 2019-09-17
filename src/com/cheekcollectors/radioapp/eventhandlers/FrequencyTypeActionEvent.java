package com.cheekcollectors.radioapp.eventhandlers;

import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.FrequencyType;
import com.cheekcollectors.radioapp.radio.Radio;

import java.awt.event.ActionEvent;

public class FrequencyTypeActionEvent {
    private FrequencyType frequencyType;

    public FrequencyTypeActionEvent(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }


    public void handle(ActionEvent actionEvent) {
        Radio radio = RadioAppGUI.getRadio();

        radio.setFrequencyType(frequencyType);
        RadioAppGUI.updateStatus(radio.getStatusString());
    }
}

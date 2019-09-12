package com.cheekcollectors.radioapp.radio;

import com.cheekcollectors.radioapp.RadioAppGUI;

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

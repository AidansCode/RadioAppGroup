package com.cheekcollectors.radioapp.radio;

public class RadioStation {

    private FrequencyType frequencyType;
    private double frequency;

    public RadioStation(FrequencyType frequencyType, double frequency) {
        this.frequencyType = frequencyType;
        this.frequency = frequency;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public double getFrequency() {
        return frequency;
    }

}

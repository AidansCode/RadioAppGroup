package com.cheekcollectors.radioapp.radio;

public class RadioStation {

    private FrequencyType frequencyType;
    private double frequency;

    /**
     * Constructor
     *
     * @param frequencyType The FrequencyType of the new radio station
     * @param frequency The frequency of the new radio station
     */
    public RadioStation(FrequencyType frequencyType, double frequency) {
        this.frequencyType = frequencyType;
        this.frequency = frequency;
    }

    /**
     * Return the frequency type of the radio station
     *
     * @return FrequencyType The type of the frequency of the radio station
     */
    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    /**
     * Return the frequency of the radio station
     *
     * @return double The frequency of the radio station
     */
    public double getFrequency() {
        return frequency;
    }

}

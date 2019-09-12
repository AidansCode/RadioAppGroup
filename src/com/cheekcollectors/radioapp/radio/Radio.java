package com.cheekcollectors.radioapp.radio;

import java.text.DecimalFormat;

public class Radio {

    private static double minAM = 530, maxAM= 1610, minFM = 87.9, maxFM = 107.9, deltaAM = 10, deltaFM = 0.2;
    private static int maxFavorites = 6;

    private boolean isOn;
    private double amFrequency, fmFrequency;
    private FrequencyType frequencyType;
    private RadioStation[] favorites;

    public Radio() {
        isOn = false;
        frequencyType = FrequencyType.AM;
        amFrequency = minAM;
        fmFrequency = minFM;
        favorites = new RadioStation[maxFavorites];
    }

    public Radio(boolean isOn, double amFrequency, double fmFrequency, FrequencyType frequencyType, RadioStation[] favorites) {
        this.isOn = isOn;
        this.amFrequency = amFrequency;
        this.fmFrequency = fmFrequency;
        this.frequencyType = frequencyType;
        this.favorites = favorites;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void togglePower() {
        isOn = !isOn;
    }

    private double getAmFrequency() {
        return amFrequency;
    }

    private void setAmFrequency(double amFrequency) {
        this.amFrequency = amFrequency;
    }

    private double getFmFrequency() {
        return fmFrequency;
    }

    private void setFmFrequency(double fmFrequency) {
        this.fmFrequency = fmFrequency;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public double getFrequency() {
        return (frequencyType == FrequencyType.AM) ? amFrequency : fmFrequency;
    }

    public void setFrequency(FrequencyType frequencyType, double frequency) {
        if (isOn() && isValidFrequency(frequencyType, frequency)) {
            this.frequencyType = frequencyType;

            if (frequencyType == FrequencyType.AM)
                amFrequency = frequency;
            else
                fmFrequency = frequency;
        }
    }

    public void setFrequency(RadioStation radioStation) {
        setFrequency(radioStation.getFrequencyType(), radioStation.getFrequency());
    }

    public void seekForward() {
        if (isOn()) {
            FrequencyType frequencyType = getFrequencyType();

            double newFrequency = getFrequency() + getFrequencyDelta(frequencyType);
            if (!isValidFrequency(frequencyType, newFrequency))
                newFrequency = getFrequencyRange(frequencyType)[0];

            setFrequency(frequencyType, newFrequency);
        }
    }

    public void seekBack() {
        if (isOn()) {
            FrequencyType frequencyType = getFrequencyType();

            double newFrequency = getFrequency() - getFrequencyDelta(frequencyType);
            if (!isValidFrequency(frequencyType, newFrequency))
                newFrequency = getFrequencyRange(frequencyType)[1];

            setFrequency(frequencyType, newFrequency);
        }
    }

    public RadioStation getCurrentStation() {
        return new RadioStation(getFrequencyType(), getFrequency());
    }

    public RadioStation getFavorite(int position) {
        return favorites[position];
    }

    public RadioStation[] getFavorites() {
        return favorites;
    }

    public void setFavorite(RadioStation radioStation, int position) {
        if (isOn())
            favorites[position] = radioStation;
    }

    public void unsetFavorite(int position) {
        if (isOn())
            favorites[position] = null;
    }

    public boolean hasFavoriteAtPosition(int position) {
        return favorites[position] != null;
    }

    public String getStatusString() {
        String result;

        if (isOn())
            result = "ON " + getFrequencyType().toString() + " " + formatFrequency(getFrequencyType(), getFrequency());
        else
            result = "OFF";

        return result;
    }

    private static boolean isValidFrequency(FrequencyType frequencyType, double frequency) {
        double[] frequencyRange = getFrequencyRange(frequencyType);

        //return frequency is outside range set by FrequencyType
        return (frequency > frequencyRange[1] || frequency < frequencyRange[0]);
    }

    public static double[] getFrequencyRange(FrequencyType frequencyType) {
        double[] result = new double[2];
        if (frequencyType == FrequencyType.AM) {
            result[0] = minAM;
            result[1] = maxAM;
        } else {
            result[0] = minFM;
            result[1] = maxFM;
        }

        return result;
    }

    public static double getFrequencyDelta(FrequencyType frequencyType) {
        if (frequencyType == FrequencyType.AM)
            return deltaAM;
        else
            return deltaFM;
    }

    public static String formatFrequency(FrequencyType frequencyType, double frequency) {
        DecimalFormat format;
        if (frequencyType == FrequencyType.AM)
            format = new DecimalFormat("0");
        else
            format = new DecimalFormat("0.0");

        return format.format(frequency);
    }

}

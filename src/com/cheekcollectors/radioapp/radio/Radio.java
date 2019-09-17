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

    /**
     * Radio constructor
     *
     * @param isOn boolean Whether radio is on or not (off)
     * @param amFrequency double Starting AM frequency
     * @param fmFrequency double Starting FM frequency
     * @param frequencyType FrequencyType Last set frequency type
     * @param favorites Aray of 6 favorite radio stations, individual elements can be null
     */
    public Radio(boolean isOn, double amFrequency, double fmFrequency, FrequencyType frequencyType, RadioStation[] favorites) {
        this.isOn = isOn;

        if (isValidFrequency(FrequencyType.AM, amFrequency))
            this.amFrequency = amFrequency;
        else
            this.amFrequency = getFrequencyRange(FrequencyType.AM)[0];

        if (isValidFrequency(FrequencyType.FM, fmFrequency))
            this.fmFrequency = fmFrequency;
        else
            this.fmFrequency = getFrequencyRange(FrequencyType.FM)[0];

        this.frequencyType = frequencyType;

        if (favorites.length == 6)
            this.favorites = favorites;
        else
            this.favorites = new RadioStation[6];
    }

    /**
     * Returns whether or not the radio is on
     *
     * @return boolean Whether or not the radio is on
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Sets whether or not the radio is on
     *
     * @param on boolean Whether or not the radio should be on
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    /**
     * Toggles the power of the radio (off -> on, on -> off)
     */
    public void togglePower() {
        isOn = !isOn;
    }

    /**
     * Get the last set AM frequency
     *
     * @return double The last set AM frequency
     */
    public double getAmFrequency() {
        return amFrequency;
    }

    /**
     * Set the current AM frequency
     *
     * @param amFrequency double The new AM frequency value
     */
    private void setAmFrequency(double amFrequency) {
        if (isValidFrequency(FrequencyType.AM, amFrequency))
            this.amFrequency = amFrequency;
    }

    /**
     * Get the last set FM frequency
     *
     * @return double The new FM frequency value
     */
    public double getFmFrequency() {
        return fmFrequency;
    }

    /**
     * Set the current FM frequency
     *
     * @param fmFrequency double The new FM frequency value
     */
    private void setFmFrequency(double fmFrequency) {
        this.fmFrequency = fmFrequency;
    }

    /**
     * Get the current FrequencyType
     *
     * @return FrequencyType The current FrequencyType
     */
    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    /**
     * Set the current FrequencyType
     *
     * @param frequencyType FrequencyType The new FrequencyType
     */
    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    /**
     * Toggles between the current frequency type (AM -> FM, FM -> AM)
     */
    public void toggleFrequencyType() {
        if (isOn())
            frequencyType = getFrequencyType() == FrequencyType.AM ? FrequencyType.FM : FrequencyType.AM;
    }

    /**
     * Get the current frequency of the radio
     *
     * @return double The current frequency of the radio
     */
    public double getFrequency() {
        return (frequencyType == FrequencyType.AM) ? amFrequency : fmFrequency;
    }

    /**
     * Set the current frequency of the radio
     *
     * @param frequencyType FrequencyType The new FrequencyType of the radio
     * @param frequency double The new frequency of the radio
     */
    public void setFrequency(FrequencyType frequencyType, double frequency) {
        if (isOn() && isValidFrequency(frequencyType, frequency)) {
            this.frequencyType = frequencyType;

            if (frequencyType == FrequencyType.AM)
                amFrequency = frequency;
            else
                fmFrequency = frequency;
        }
    }

    /**
     * Set the current frequency of the radio
     *
     * @param radioStation RadioStation The new radio station of the radio
     */
    public void setFrequency(RadioStation radioStation) {
        setFrequency(radioStation.getFrequencyType(), radioStation.getFrequency());
    }

    /**
     * Move forward delta stations from the current station (AM: n + 10, FM: n + .2)
     */
    public void seekForward() {
        if (isOn()) {
            FrequencyType frequencyType = getFrequencyType();

            double newFrequency = getFrequency() + getFrequencyDelta(frequencyType);
            if (!isValidFrequency(frequencyType, newFrequency))
                newFrequency = getFrequencyRange(frequencyType)[0];

            setFrequency(frequencyType, newFrequency);
        }
    }

    /**
     * Move back delta stations from the current station (AM: n - 10, FM: n - .2)
     */
    public void seekBack() {
        if (isOn()) {
            FrequencyType frequencyType = getFrequencyType();

            double newFrequency = getFrequency() - getFrequencyDelta(frequencyType);
            if (!isValidFrequency(frequencyType, newFrequency))
                newFrequency = getFrequencyRange(frequencyType)[1];

            setFrequency(frequencyType, newFrequency);
        }
    }

    /**
     * Get the current frequency/frequency type of the radio as a RadioStation object
     *
     * @return RadioStation The current radio station the radio is on
     */
    public RadioStation getCurrentStation() {
        return new RadioStation(getFrequencyType(), getFrequency());
    }

    /**
     * Return the radio station at the specified favorite position
     *
     * @param position int The favorite number to check
     * @return RadioStation The radio station set to the specified favorite position
     */
    public RadioStation getFavorite(int position) {
        return favorites[position];
    }

    /**
     * Return an array of all the favorites
     *
     * @return RadioStation[] An array of all the set favorites (elements may be null)
     */
    public RadioStation[] getFavorites() {
        return favorites;
    }

    /**
     * Set the specified radio station to a favorite at a specified position
     *
     * @param radioStation The new favorite RadioStation
     * @param position The position of the new favorite
     */
    public void setFavorite(RadioStation radioStation, int position) {
        if (isOn())
            favorites[position] = radioStation;
    }

    /**
     * Removes the favorite from the specified favorite position
     *
     * @param position int The specified favorite position
     */
    public void unsetFavorite(int position) {
        if (isOn())
            favorites[position] = null;
    }

    /**
     * Returns whether or not there is a favorite at a certain position
     *
     * @param position int The position (1-6) of the potential favorite
     * @return booelan Whether or not there is a favorite at the given position
     */
    public boolean hasFavoriteAtPosition(int position) {
        return favorites[position] != null;
    }

    /**
     * Returns the status of the radio as a string (ON/OFF Freq-Type Frequency)
     *
     * @return The status of the radio as a string (ON/OFF Freq-Type Frequency)
     */
    public String getStatusString() {
        String result;

        if (isOn())
            result = "ON " + getFrequencyType().toString() + " " + formatFrequency(getFrequencyType(), getFrequency());
        else
            result = "OFF";

        return result;
    }

    /**
     * Returns whether or not a given frequency is valid and in the type's range
     *
     * @param frequencyType The FrequencyType of the station to test
     * @param frequency The frequency of the station to test
     * @return boolean Whether or not the given frequency is a valid station
     */
    private static boolean isValidFrequency(FrequencyType frequencyType, double frequency) {
        double[] frequencyRange = getFrequencyRange(frequencyType);

        //return frequency is outside range set by FrequencyType
        return (frequency > frequencyRange[1] || frequency < frequencyRange[0]);
    }

    /**
     * Get the range of valid frequencies for a given frequency type
     *
     * @param frequencyType The frequency type to get the range of
     * @return Array An array with the range of the given frequency type ([0] = minimum frequency, [1] = maximum frequency)
     */
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

    /**
     * Get the delta between valid stations for a given frequency type
     *
     * @param frequencyType The frequency type to get the delta of
     * @return double The delta between frequencies for the given frequency type
     */
    public static double getFrequencyDelta(FrequencyType frequencyType) {
        if (frequencyType == FrequencyType.AM)
            return deltaAM;
        else
            return deltaFM;
    }

    /**
     * Format a given frequency into a string with the correct amount of decimals for the frequency's type
     *
     * @param frequencyType The FrequencyType of the station
     * @param frequency The frequency of the station
     * @return String The formatted frequency
     */
    public static String formatFrequency(FrequencyType frequencyType, double frequency) {
        DecimalFormat format;
        if (frequencyType == FrequencyType.AM)
            format = new DecimalFormat("0");
        else
            format = new DecimalFormat("0.0");

        return format.format(frequency);
    }

}

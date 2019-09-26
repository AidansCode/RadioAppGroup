package com.cheekcollectors.radioapp.fileio;

import com.cheekcollectors.radioapp.FavoriteMode;
import com.cheekcollectors.radioapp.RadioAppGUI;
import com.cheekcollectors.radioapp.radio.FrequencyType;
import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.radio.RadioStation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class RadioDataManager {

    /**
     * Gather all information about the current radio/radio app and save data to a file
     * @param radio The instance of the Radio class being used
     */
    public static void saveData(Radio radio) {
        boolean isOn = radio.isOn();
        double amFrequency = radio.getAmFrequency(), fmFrequency = radio.getFmFrequency();
        FrequencyType frequencyType = radio.getFrequencyType();
        RadioStation[] favorites = radio.getFavorites();
        FavoriteMode favoriteMode = RadioAppGUI.getFavoriteMode();

        DecimalFormat format = new DecimalFormat("0.0");

        try {
            PrintWriter printWriter = new PrintWriter("radio.txt");
            printWriter.write(Boolean.toString(isOn) + System.lineSeparator());
            printWriter.write(favoriteMode.toString() + System.lineSeparator());
            printWriter.write(format.format(amFrequency) + System.lineSeparator());
            printWriter.write(format.format(fmFrequency) + System.lineSeparator());
            printWriter.write(frequencyType.toString() + System.lineSeparator());

            for (int i = 0; i < favorites.length; i++) {
                RadioStation favorite = favorites[i];
                if (favorite == null)
                    printWriter.write("null" + System.lineSeparator());
                else {
                    printWriter.write(favorite.getFrequencyType().toString() + "-" + format.format(favorite.getFrequency()) + System.lineSeparator());
                }
            }
            printWriter.close();
        } catch (Exception e) {} //do nothing, fail gracefully

    }

    /**
     * Read the data stored in the app's storage file from the last use of the program and initialize the radio with the stored data.
     * If there is an exception or the data can not be found, returns null
     * @return The initialized Radio instance with the saved data or null if file not found
     */
    public static Radio readData() {
        try(BufferedReader br = new BufferedReader(new FileReader("radio.txt"))) {
            boolean isOn;
            double amFrequency, fmFrequency;
            FrequencyType frequencyType;
            FavoriteMode favoriteMode;
            RadioStation[] favorites = new RadioStation[6];

            isOn = Boolean.parseBoolean(br.readLine());
            favoriteMode = FavoriteMode.valueOf(br.readLine());
            amFrequency = Double.parseDouble(br.readLine());
            fmFrequency = Double.parseDouble(br.readLine());
            frequencyType = FrequencyType.valueOf(br.readLine());

            for (int i = 0; i < 6; i++) {
                String curLine = br.readLine();
                if (!curLine.equals("null")) {
                    String[] split = curLine.split("-");

                    FrequencyType favoriteType = FrequencyType.valueOf(split[0]);
                    double favoriteFrequency = Double.parseDouble(split[1]);

                    favorites[i] = new RadioStation(favoriteType, favoriteFrequency);
                }
            }

            RadioAppGUI.setFavoriteMode(favoriteMode);
            return new Radio(isOn, amFrequency, fmFrequency, frequencyType, favorites);
        } catch (Exception e) {
            return null;
        }
    }

}

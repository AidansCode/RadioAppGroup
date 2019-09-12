package com.cheekcollectors.radioapp.fileio;

import com.cheekcollectors.radioapp.radio.FrequencyType;
import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.radio.RadioStation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class RadioDataManager {

    public static void saveData(Radio radio) {
        boolean isOn = radio.isOn();
        double amFrequency = radio.getAmFrequency(), fmFrequency = radio.getFmFrequency();
        FrequencyType frequencyType = radio.getFrequencyType();
        RadioStation[] favorites = radio.getFavorites();

        DecimalFormat format = new DecimalFormat("0.0");

        try {
            PrintWriter printWriter = new PrintWriter("radio.txt");
            printWriter.write(Boolean.toString(isOn) + System.lineSeparator());
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

    public static Radio readData() {
        try(BufferedReader br = new BufferedReader(new FileReader("radio.txt"))) {
            boolean isOn;
            double amFrequency, fmFrequency;
            FrequencyType frequencyType;
            RadioStation[] favorites = new RadioStation[6];

            isOn = Boolean.parseBoolean(br.readLine());
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

            return new Radio(isOn, amFrequency, fmFrequency, frequencyType, favorites);
        } catch (Exception e) {
            return null;
        }
    }

}

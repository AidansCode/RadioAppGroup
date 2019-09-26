package com.cheekcollectors.radioapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.concurrent.atomic.AtomicBoolean;

public class Utilities {

    /**
     * Prompts an alert of type confirmation asking the user a given question, giving potential answers of yes/no
     * @param question The question to prompt the user
     * @return boolean Whether the user replied yes(true) or no(false)
     */
    public static boolean getUserConfirmation(String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, question, ButtonType.YES, ButtonType.NO);
        AtomicBoolean result = new AtomicBoolean(false);

        alert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("Yes"))
                result.set(true);
        });

        return result.get();
    }

    /**
     * Prompts an alert of type information to the user. Does not return a response
     * @param message The message to prompt the user
     */
    public static void showUserAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.show();
    }

}

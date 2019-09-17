package com.cheekcollectors.radioapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.concurrent.atomic.AtomicBoolean;

public class Utilities {

    public static boolean getUserConfirmation(String question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, question, ButtonType.YES, ButtonType.NO);
        AtomicBoolean result = new AtomicBoolean(false);

        alert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("Yes"))
                result.set(true);
        });

        return result.get();
    }

    public static void showUserAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.show();
    }

}

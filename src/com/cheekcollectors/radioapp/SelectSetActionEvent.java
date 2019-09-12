package com.cheekcollectors.radioapp;


import com.cheekcollectors.radioapp.radio.Radio;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.awt.event.MouseEvent;


public class SetButtonMouseEvent implements EventHandler<MouseEvent>{
    public void sethandle(MouseEvent mouseEvent){
        Radio radio = RadioAppGUI.getRadio();
        int position = source.getPosition();
        private void wantToSet(Radio radio, position){
            String userquestion = "Would you like to set this favorite number " + (position + 1) + "?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, userquestion, ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response.getText().equals("Yes"))
                    radio.setFavorite(position);
            });
        }

        private void requestToUnset(Radio radio, position) {
            String contentText = "Would you like to unset favorite number " + (position + 1) + "?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, contentText, ButtonType.YES, ButtonType.NO);

            alert.showAndWait().ifPresent(response -> {
                if (response.getText().equals("Yes"))
                    radio.unsetFavorite(position);
            });
        }
    }
}
public class SelectButtonMouseEvent implements EventHandler<MouseEvent>{

}

package com.cheekcollectors.radioapp;

import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.radio.SeekDirection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SeekButtonActionEvent implements EventHandler<ActionEvent> {
    private SeekDirection seek; //creates new private seek
    public SeekButtonActionEvent(SeekDirection seek) {
        this.SeekDirection = seek;
    }
    public void handle(ActionEvent event){
        Radio radio = RadioAppGUI.getRadio();
        if(SeekDirection == SeekDirection.UP){ /*if the seek Forward button is selected the
            radio frequency will increase by .2 if FM is selected
            and 10 if am is selected*/
            radio.seekForward();
        } else { /*if the seek back button is selected the
            radio frequ/ency will decrease by .2 if FM is selected
            and 10 if am is selected*/
            radio.seekBack();
        }
        RadioAppGUI.updateStatus(radio.getStatusString()); //this line updates the status
        //of the button
    }

}

package com.cheekcollectors.radioapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SeekButtonActionEvent implements EventHandler<ActionEvent> {
    private SeekDirection seek; //creates new private seek
    public SeekButtonActionEvent(SeekDirection seek) {
        this.seekDirection = seek;
    }
    public void handler(ActionEvent event){
        Radio radio = RadioApp.getRadio();
        if(seekDirection == seek.FORWARD){ /*if the seek Forward button is selected the
            radio frequency will increase by .2 if FM is selected
            and 10 if am is selected*/
            radio.seekUp();
        } else { /*if the seek back button is selected the
            radio frequency will decrease by .2 if FM is selected
            and 10 if am is selected*/
            radio.seekDown();
        }
        RadioApp.updateStatus(radio.getStatusString()); //this line updates the status
        //of the button
    }
}

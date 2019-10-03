/*************************************************************************
 * CSC - 223 Fall 2019
 * @author - checkcollectors
 * Date : 10/3
 * PROJECT #2 Radio
 * Class Description: Launches GUI for Radio App
 *************************************************************************/

package com.cheekcollectors.radioapp;

import com.cheekcollectors.radioapp.eventhandlers.*;
import com.cheekcollectors.radioapp.fileio.RadioDataManager;
import com.cheekcollectors.radioapp.nodes.FavoriteButton;
import com.cheekcollectors.radioapp.radio.Radio;
import com.cheekcollectors.radioapp.radio.SeekDirection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RadioAppGUI extends Application {

    private static final String APP_TITLE = "Radio Application";
    private static Radio radio;
    private static TextField statusPane;
    private static Button setSelectToggleButton, amFmButton;
    private static FavoriteMode favoriteMode;

    /**
     * Main method of the Java application
     * @param args The arguments passed to the application at launch
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Returns the instance of the Radio class the application is using
     * @return
     */
    public static Radio getRadio() {
        return radio;
    }

    /**
     * Refreshes the display of the radio GUI. Call this after you change something
     */
    public static void updateDisplay() {
        statusPane.setText(radio.getStatusString());

        setSelectToggleButton.setText(favoriteMode.toString());
        amFmButton.setText(radio.getFrequencyType().toString());
    }

    /**
     * Return the current favorite mode of the application
     * @return FavoriteMode The current favorite mode of the application
     */
    public static FavoriteMode getFavoriteMode() {
        return favoriteMode;
    }

    /**
     * Set the current favorite mode of the application
     * @param favoriteMode
     */
    public static void setFavoriteMode(FavoriteMode favoriteMode) {
        RadioAppGUI.favoriteMode = favoriteMode;
    }

    /**
     * Launches the GUI of the application
     * @param primaryStage The primary Stage to be used
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        radio = RadioDataManager.readData();
        if (radio == null) {
            radio = new Radio();
            favoriteMode = FavoriteMode.SET;
        }

        initLayout(primaryStage);
        primaryStage.show();
    }

    /**
     * Called when the program is closing
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        RadioDataManager.saveData(radio);
    }

    /**
     * Initializes the layout of a given stage to use the radio GUI
     * @param primaryStage The stage to be used for the GUI
     */
    private void initLayout(Stage primaryStage) {
        HBox topMenu = new HBox();
        topMenu.setSpacing(25);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setPadding(new Insets(15, 0, 15, 0));

        HBox frequencyTypeMenu = new HBox();
        frequencyTypeMenu.setSpacing(25);
        frequencyTypeMenu.setAlignment(Pos.CENTER);

        HBox middleMenu = new HBox();
        middleMenu.setSpacing(10);
        middleMenu.setAlignment(Pos.CENTER);

        HBox bottomMenu = new HBox();
        bottomMenu.setSpacing(20);
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.setPadding(new Insets(15, 0, 25, 0));

        amFmButton = new Button(radio.getFrequencyType().toString());
        setSelectToggleButton = new Button(getFavoriteMode().toString());
        Button seekBackButton = new Button("<<");
        Button seekForwardButton = new Button(">>");
        Button powerButton = new Button("");
        amFmButton.setPrefSize(50, 30);
        setSelectToggleButton.setPrefSize(100, 30);

        FavoriteButton[] favoriteButtons = new FavoriteButton[6];
        for (int i = 0; i < 6; i++) {
            favoriteButtons[i] = new FavoriteButton(Integer.toString(i + 1), 
                    i);
            favoriteButtons[i].setOnAction(new SetSelectFavoriteEvent());
        }

        amFmButton.setOnAction(new FrequencyTypeEvent());
        setSelectToggleButton.setOnAction(new SetSelectToggleEvent());
        seekBackButton.setOnAction(new SeekButtonEvent(SeekDirection.DOWN));
        seekForwardButton.setOnAction(new SeekButtonEvent(SeekDirection.UP));

        powerButton.setPrefSize(30, 30);
        powerButton.setStyle("-fx-background-image: "
                + "url('resources/power.png'); -fx-background-size: 20px; "
                + "-fx-background-repeat: no-repeat; -fx-background-position: "
                + "center;");
        powerButton.setOnAction(new PowerButtonEvent());

        statusPane = new TextField(radio.getStatusString());
        statusPane.setAlignment(Pos.CENTER);
        statusPane.setDisable(true);
        statusPane.setStyle("-fx-background-color: "
                + "darkolivegreen; -fx-opacity: 1; "
                + "-fx-text-fill: white");

        frequencyTypeMenu.getChildren().addAll(amFmButton, 
                setSelectToggleButton);
        topMenu.getChildren().addAll(powerButton, frequencyTypeMenu);
        middleMenu.getChildren().addAll(seekBackButton, statusPane, 
                seekForwardButton);
        bottomMenu.getChildren().addAll(favoriteButtons);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topMenu);
        mainLayout.setCenter(middleMenu);
        mainLayout.setBottom(bottomMenu);

        Scene scene = new Scene(mainLayout, 400, 150);
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        mainLayout.requestFocus();
    }

}

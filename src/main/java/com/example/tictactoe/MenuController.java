package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button playButton, multiplayButton, exitButton;

    @FXML
    void btnOnePlay(ActionEvent event) {
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("game1-view.fxml"));
        Stage stage = (Stage) playButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(gameLoader.load(), Config.getX(), Config.getY()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnTwoPlay(ActionEvent event) {
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("game2-view.fxml"));
        Stage stage = (Stage) multiplayButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(gameLoader.load(), Config.getX(), Config.getY()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}

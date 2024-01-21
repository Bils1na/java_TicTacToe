package com.example.tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button playButton;

    @FXML
    void btnPlay(ActionEvent event) {
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("ticTacToe-view.fxml"));
        Stage stage = (Stage) playButton.getScene().getWindow();
        try {
            stage.setScene(new Scene(gameLoader.load(), 392, 393));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

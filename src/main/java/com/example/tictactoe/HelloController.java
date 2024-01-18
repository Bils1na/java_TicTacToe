package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private String currentSymbol = "X";
    private String[][] gameField = new String[3][3];

    @FXML
    void btnClick(ActionEvent event) {
        Button button = ((Button)event.getSource());

        int rowIndex = GridPane.getRowIndex(button) == null ? 0 : GridPane.getRowIndex(button);
        int columnIndex = GridPane.getColumnIndex(button) == null ? 0 : GridPane.getColumnIndex(button);
        if (gameField[rowIndex][columnIndex] == null) {
            gameField[rowIndex][columnIndex] = currentSymbol;
            currentSymbol = currentSymbol.equals("X") ? "O" : "X";
            button.setText(currentSymbol);
        }
    }

}

package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class HelloController {

    @FXML
    private String currentSymbol = "X";
    private String[][] gameField = new String[3][3];

    @FXML
    private Line winLine1;
    @FXML
    private Line winLine2;
    @FXML
    private Line winLine3;
    @FXML
    private Line winLine4;
    @FXML
    private Line winLine5;
    @FXML
    private Line winLine6;
    @FXML
    private Line winLine7;
    @FXML
    private Line winLine8;

    @FXML
    void btnClick(ActionEvent event) {
        Button button = ((Button)event.getSource());

        int rowIndex = GridPane.getRowIndex(button) == null ? 0 : GridPane.getRowIndex(button);
        int columnIndex = GridPane.getColumnIndex(button) == null ? 0 : GridPane.getColumnIndex(button);
        if (gameField[rowIndex][columnIndex] == null) {
            gameField[rowIndex][columnIndex] = currentSymbol;
            button.setText(currentSymbol);
            currentSymbol = currentSymbol.equals("X") ? "O" : "X";
            checkFinish(rowIndex, columnIndex);
        }
    }

    private void checkFinish(int row, int column) {
        if (this.gameField[row] != null) {
            if (checkRow(row)) {
                if (winLine5 != null) winLine5.setOpacity(1.0);
            } else if (checkColumn(column)) {
                if (winLine4 != null) winLine4.setOpacity(1.0);
            } else if (checkDiagonalRight() || checkDiagonalLeft()) {
                if (winLine2 != null) winLine2.setOpacity(1.0);
            }
        }
    }

    private boolean checkRow(int row) {
        boolean isWin = true;
        for (int i = 0; i < gameField[row].length; i++) {
            if (gameField[row][i] == null || gameField[row][i].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkColumn(int column) {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][column] == null || gameField[i][column].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkDiagonalLeft() {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][i] == null || gameField[i][i].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkDiagonalRight() {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][Math.abs(i - gameField.length)] == null ||
                    gameField[i][Math.abs(i - gameField.length)].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

}

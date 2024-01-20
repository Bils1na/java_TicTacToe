package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class HelloController {

    private String currentSymbol = "X";
    private String[][] gameField = new String[3][3];

    @FXML
    private Line winLine = new Line();


    @FXML
    void btnClick(ActionEvent event) {
        Button button = ((Button)event.getSource());

        Integer rowIndex = GridPane.getRowIndex(button) == null ? 0 : GridPane.getRowIndex(button);
        Integer columnIndex = GridPane.getColumnIndex(button) == null ? 0 : GridPane.getColumnIndex(button);
        if (gameField[rowIndex][columnIndex] == null) {
            gameField[rowIndex][columnIndex] = currentSymbol;
            button.setText(currentSymbol);
            currentSymbol = currentSymbol.equals("X") ? "O" : "X";
            checkFinish(rowIndex, columnIndex,button);
        }
    }

    private void checkFinish(Integer row, Integer column, Button btn) {
        if (this.gameField[row] != null) {
            if (checkRow(row)) {
                if (winLine != null) renderWinLine(btn, row, null);
            } else if (checkColumn(column)) {
                if (winLine != null) renderWinLine(btn, null, column);
            } else if (checkDiagonalRight() || checkDiagonalLeft()) {
                if (winLine != null) winLine.setOpacity(1.0);
            }
        }
    }

    private boolean checkRow(Integer row) {
        boolean isWin = true;
        for (int i = 0; i < gameField[row].length; i++) {
            if (gameField[row][i] == null || !checkSymbolsInRow(gameField[row][i], row)) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkSymbolsInRow(String symbol, Integer row) {
        boolean isWin = true;
        for (int i = 0; i < gameField[row].length; i++) {
            if (!symbol.equals(gameField[row][i])) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkColumn(Integer column) {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][column] == null || !checkSymbolsInColumn(gameField[i][column], column)) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkSymbolsInColumn(String symbol, Integer column) {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (!symbol.equals(gameField[i][column])) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkDiagonalRight() {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][i] == null || gameField[i][i].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        setWinLine(12.999988555908203, 10.999988555908203, 390, 383);
        return isWin;
    }

    private boolean checkDiagonalLeft() {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][Math.abs(i - (gameField.length - 1))] == null ||
                    gameField[i][Math.abs(i - (gameField.length - 1))].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        setWinLine(390, 11, 13, 383);
        return isWin;
    }

    private void setWinLine(double sX, double sY, double eX, double eY) {
        winLine.setOpacity(1.0);
        winLine.setStartX(sX);
        winLine.setStartY(sY);
        winLine.setEndX(eX);
        winLine.setEndY(eY);
    }

    private void renderWinLine(Button btn, Integer row, Integer column){
        double sX = 0, sY = 0, eX = 0, eY = 0;
        if (row != null) {
            for (Node node : btn.getParent().getChildrenUnmodifiable()) {
                int rowNode = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int colNode = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                if (rowNode == row && colNode == 0) {
                    sX = node.localToParent(0, 0).getX();
                    sY = node.localToParent(0, 0).getY() + btn.getHeight() / 2;

                }
                if (rowNode == row && colNode == gameField.length - 1) {
                    eX = node.localToParent(0, 0).getX() + btn.getWidth();
                    eY = node.localToParent(0, 0).getY() + btn.getHeight() / 2;
                }
            }
            setWinLine(sX, sY, eX, eY);
        } else {
            for (Node node : btn.getParent().getChildrenUnmodifiable()) {
                int rowNode = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int colNode = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                if (colNode == column && rowNode == 0) {
                    sX = node.localToParent(0,0).getX() + btn.getWidth() / 2;
                    sY = node.localToParent(0, 0).getY();
                }
                if (colNode == column && colNode == gameField.length - 1) {
                    eX = node.localToParent(0, 0).getX() + btn.getWidth() / 2;
                    eY = node.localToParent(0, 0).getX() + btn.getHeight();
                }
            }
            setWinLine(sX, sY, eX, eY);
        }
    }
}

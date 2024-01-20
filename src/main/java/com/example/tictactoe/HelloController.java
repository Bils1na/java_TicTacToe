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
//        double x = button.localToParent(0, 0).getX();
//        double y = button.localToParent(0, 0).getY() + button.getHeight() / 2;
//        double eX = button.localToParent(0, 0).getX() + button.getWidth();
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println();
//        System.out.println(eX);
//        System.out.println(y);

        int rowIndex = GridPane.getRowIndex(button) == null ? 0 : GridPane.getRowIndex(button);
        int columnIndex = GridPane.getColumnIndex(button) == null ? 0 : GridPane.getColumnIndex(button);
        if (gameField[rowIndex][columnIndex] == null) {
            gameField[rowIndex][columnIndex] = currentSymbol;
            button.setText(currentSymbol);
            currentSymbol = currentSymbol.equals("X") ? "O" : "X";
            checkFinish(rowIndex, columnIndex,button);
        }
    }

    private void checkFinish(int row, int column, Button btn) {
        if (this.gameField[row] != null) {
            if (checkRow(row)) {
                if (winLine != null) renderWinLine(btn, row);
            } else if (checkColumn(column, btn)) {
                if (winLine != null) winLine.setOpacity(1.0);
            } else if (checkDiagonalRight() || checkDiagonalLeft()) {
                if (winLine != null) winLine.setOpacity(1.0);
            }
        }
    }

    private boolean checkRow(int row) {
        boolean isWin = true;
        for (int i = 0; i < gameField[row].length; i++) {
            if (gameField[row][i] == null || checkSymbolsInRow(row)) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkSymbolsInRow(int row) {
        boolean isWin = true;
        for (int i = 0; i < gameField[row].length; i++) {
            for (int j = 0; j < gameField[row].length; j++) {
                if (!gameField[row][i].equals(gameField[row][j])) {
                    isWin = false;
                    return isWin;
                }
            }
        }
        return isWin;
    }

    private boolean checkColumn(int column, Button btn) {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][column] == null || gameField[i][column].equals("O")) {
                isWin = false;
                return isWin;
            }
        }
        setWinLine(0, 0, 390, 76);
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

    private void renderWinLine(Button btn, int row){
        double sX = 0, sY = 0, eX = 0, eY = 0;
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
    }
}

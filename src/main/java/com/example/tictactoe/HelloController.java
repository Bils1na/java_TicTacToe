package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelloController {

    private String currentSymbol = "X";
    private String[][] gameField = new String[3][3];

    @FXML
    private Line winLine = new Line();
    @FXML
    private GridPane gameFieldUI;


    @FXML
    void btnClick(ActionEvent event) {
        Button button = ((Button)event.getSource());

        Integer rowIndex = GridPane.getRowIndex(button) == null ? 0 : GridPane.getRowIndex(button);
        Integer columnIndex = GridPane.getColumnIndex(button) == null ? 0 : GridPane.getColumnIndex(button);
        if (gameField[rowIndex][columnIndex] == null) {
            gameField[rowIndex][columnIndex] = currentSymbol;
            button.setText(currentSymbol);
            currentSymbol = currentSymbol.equals("X") ? "O" : "X";
            checkFinish(rowIndex, columnIndex, button);
        }
    }

    private void winWindow() {
        Stage winWindow = new Stage();
        winWindow.initModality(Modality.APPLICATION_MODAL);
        winWindow.setTitle("Won");

        Label label = new Label("Congratulations! You won!");
        label.setStyle("-fx-font-size: 20px;");
        Button repeatButton = new Button("Again");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->  {
            repeat();
            winWindow.close();
        });
        repeatButton.setOnAction(e -> {
            repeat();
            winWindow.close();
        });

        VBox winWindowContent = new VBox(20);
        HBox buttons = new HBox();
        buttons.getChildren().addAll(repeatButton, exitButton);
        winWindowContent.getChildren().addAll(label, buttons);
        winWindowContent.setAlignment(javafx.geometry.Pos.CENTER);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);

        Scene dialogScene = new Scene(winWindowContent, 300, 150);
        winWindow.setScene(dialogScene);

        winWindow.showAndWait();
    }

    private void drawWindow() {
        Stage drawWindow = new Stage();
        drawWindow.initModality(Modality.APPLICATION_MODAL);
        drawWindow.setTitle("Draw");

        Label label = new Label("It's draw!\nDo you want to try again?");
        label.setStyle("-fx-font-size: 20px;");
        Button repeatButton = new Button("Again");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e ->  {
            repeat();
            drawWindow.close();
        });
        repeatButton.setOnAction(e -> {
            repeat();
            drawWindow.close();
        });

        VBox drawWindowContent = new VBox(20);
        HBox buttons = new HBox();
        buttons.getChildren().addAll(repeatButton, exitButton);
        drawWindowContent.getChildren().addAll(label, buttons);
        drawWindowContent.setAlignment(javafx.geometry.Pos.CENTER);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);

        Scene dialogScene = new Scene(drawWindowContent, 300, 150);
        drawWindow.setScene(dialogScene);

        drawWindow.showAndWait();
    }

    private void repeat() {
        gameField = new String[3][3];
        for (Node node : gameFieldUI.getChildren()) {
            ((Button)node).setText("");
        }
        setWinLine(0, 0, 0, 0);
    }

    private void checkFinish(Integer row, Integer column, Button btn) {
        if (this.gameField[row] != null) {
            if (checkRow(row)) {
                if (winLine != null) {
                    renderWinLine(btn, row, null);
                    winWindow();
                }
            } else if (checkColumn(column)) {
                if (winLine != null){
                    renderWinLine(btn, null, column);
                    winWindow();
                }
            } else if (checkDiagonalRight() || checkDiagonalLeft()) {
                if (winLine != null) {
                    winLine.setOpacity(1.0);
                    winWindow();
                }
            }
        }
        draw();
    }

    private void draw() {
        boolean isGame = true;
        for (Node node: gameFieldUI.getChildren()) {
            if (((Button) node).getText().isEmpty()) {
                isGame = false;
            }
        }
        if (isGame) {
            repeat();
            drawWindow();
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
        setWinLine(0, 0, 392, 393);
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
        setWinLine(392, 0, 0, 393);
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
                if (colNode == column && rowNode == gameField.length - 1) {
                    eX = node.localToParent(0, 0).getX() + btn.getWidth() / 2;
                    eY = node.localToParent(0, 0).getY() + btn.getHeight();
                }
            }
            setWinLine(sX, sY, eX, eY);
        }
    }
}

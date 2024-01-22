package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.util.Random;

public class GameOneController {
    private final String playerSymbol = "X", computerSymbol = "O";
    private String[][] gameField = new String[3][3];
    private final Random rnd = new Random();
    private boolean isPlayed = true;



    @FXML
    private Line winLine = new Line();
    @FXML
    private GridPane gameFieldUI;


    @FXML
    void btnClick(ActionEvent event) {
        isPlayed = true;
        Button button = ((Button)event.getSource());

        Integer rowIndex = GridPane.getRowIndex(button) == null ? 0 : GridPane.getRowIndex(button);
        Integer columnIndex = GridPane.getColumnIndex(button) == null ? 0 : GridPane.getColumnIndex(button);
        if (gameField[rowIndex][columnIndex] == null) {
            gameField[rowIndex][columnIndex] = playerSymbol;
            button.setText(playerSymbol);
            checkFinish(rowIndex, columnIndex);
            if (isPlayed) computerTurn();
        }
    }

    private void computerTurn() {
        while (true) {
            int computerRow = rnd.nextInt(3);
            int computerCol = rnd.nextInt(3);

            for (Node node : gameFieldUI.getChildren()) {
                Button btn = (Button) node;
                int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
                int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
                if (isFull()) {
                    return;
                }  else if (rowIndex == computerRow && columnIndex == computerCol && checkCellRow(rowIndex)) {
                    System.out.println("ok");
                    btn.setText(computerSymbol);
                    gameField[computerRow][computerCol] = computerSymbol;
                    checkFinish(computerRow, computerCol);
                    return;
                } else if (rowIndex == computerRow && columnIndex == computerCol && btn.getText().isEmpty()) {
                    btn.setText(computerSymbol);
                    gameField[computerRow][computerCol] = computerSymbol;
                    checkFinish(computerRow, computerCol);
                    return;
                }
            }
        }
    }

    private boolean checkCellRow(Integer row) {
        boolean isWin = false;
        for (int i = 0; i < gameField[row].length; i++) {
            if (gameField[row][i] == null) {
                isWin = true;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean isFull() {
        boolean isFull = true;
        for (Node node : gameFieldUI.getChildren()) {
            Button btn = (Button) node;
            if (btn.getText().isEmpty()) {
                isFull = false;
                return isFull;
            }
        }
        return isFull;
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
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Stage stage = (Stage) gameFieldUI.getScene().getWindow();
            try {
                stage.setScene(new Scene(menuLoader.load(), Config.getX(), Config.getY()));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
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
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Stage stage = (Stage) gameFieldUI.getScene().getWindow();
            try {
                stage.setScene(new Scene(menuLoader.load(), Config.getX(), Config.getY()));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
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

    private void checkFinish(Integer row, Integer column) {
        if (this.gameField[row] != null) {
            if (checkRow(row)) {
                if (winLine != null) {
                    isPlayed = false;
                    renderWinLine(row, null, null);
                    winWindow();
                }
            } else if (checkColumn(column)) {
                if (winLine != null){
                    isPlayed = false;
                    renderWinLine(null, column, null);
                    winWindow();
                }
            } else if (checkDiagonalRight()) {
                if (winLine != null) {
                    isPlayed = false;
                    renderWinLine(null, null, true);
                    winWindow();
                }
            } else if (checkDiagonalLeft()) {
                if (winLine != null) {
                    isPlayed = false;
                    renderWinLine(null, null, false);
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
            isPlayed = false;
            repeat();
            drawWindow();
        }
    }

    private boolean checkRow(Integer row) {
        boolean isWin = false;
        for (int j = 0; j < gameField[row].length; j++) {
            if (gameField[row][j] != null && checkSymbolsInRow(gameField[row][j], row)) {
                isWin = true;
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
            if (gameField[i][i] == null || !checkSymbolsDiagonalRight(gameField[i][i])) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkSymbolsDiagonalRight(String symbol) {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (!symbol.equals(gameField[i][i])) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkDiagonalLeft() {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (gameField[i][Math.abs(i - (gameField.length - 1))] == null ||
                    !checkSymbolsDiagonalLeft(gameField[i][Math.abs(i - (gameField.length - 1))])) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private boolean checkSymbolsDiagonalLeft(String symbol) {
        boolean isWin = true;
        for (int i = 0; i < gameField.length; i++) {
            if (!symbol.equals(gameField[i][Math.abs(i - (gameField.length - 1))])) {
                isWin = false;
                return isWin;
            }
        }
        return isWin;
    }

    private void setWinLine(double sX, double sY, double eX, double eY) {
        winLine.setOpacity(1.0);
        winLine.setStartX(sX);
        winLine.setStartY(sY);
        winLine.setEndX(eX);
        winLine.setEndY(eY);
    }

    private void renderWinLine(Integer row, Integer column, Boolean isRight){
        double sX = 0, sY = 0, eX = 0, eY = 0;
        if (row != null) {
            for (Node node : gameFieldUI.getChildren()) {
                int rowNode = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int colNode = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                if (rowNode == row && colNode == 0) {
                    sX = node.localToParent(0, 0).getX();
                    sY = node.localToParent(0, 0).getY() + ((Button)node).getHeight() / 2;
                }
                if (rowNode == row && colNode == gameField.length - 1) {
                    eX = node.localToParent(0, 0).getX() + ((Button)node).getWidth();
                    eY = node.localToParent(0, 0).getY() + ((Button)node).getHeight() / 2;
                }
            }
            setWinLine(sX, sY, eX, eY);
        } else if (column != null) {
            for (Node node : gameFieldUI.getChildren()) {
                int rowNode = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int colNode = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                if (colNode == column && rowNode == 0) {
                    sX = node.localToParent(0,0).getX() + ((Button)node).getWidth() / 2;
                    sY = node.localToParent(0, 0).getY();
                }
                if (colNode == column && rowNode == gameField.length - 1) {
                    eX = node.localToParent(0, 0).getX() + ((Button)node).getWidth() / 2;
                    eY = node.localToParent(0, 0).getY() + ((Button)node).getHeight();
                }
            }
            setWinLine(sX, sY, eX, eY);
        } else if (isRight) {
            for (Node node : gameFieldUI.getChildren()) {
                int rowNode = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int colNode = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                if (rowNode == 0 && colNode == 0) {
                    sX = node.localToParent(0,0).getX();
                    sY = node.localToParent(0, 0).getY();
                }
                if (rowNode == 2 && colNode == 2) {
                    eX = node.localToParent(0, 0).getX() + ((Button)node).getWidth();
                    eY = node.localToParent(0, 0).getY() + ((Button)node).getHeight();
                }
            }
            setWinLine(sX, sY, eX, eY);
        } else {
            for (Node node : gameFieldUI.getChildren()) {
                int rowNode = GridPane.getRowIndex(node) == null ? 0 : GridPane.getRowIndex(node);
                int colNode = GridPane.getColumnIndex(node) == null ? 0 : GridPane.getColumnIndex(node);
                if (rowNode == 0 && colNode == 2) {
                    sX = node.localToParent(0, 0).getX() + ((Button) node).getWidth();
                    sY = node.localToParent(0, 0).getY();
                }
                if (rowNode == 2 && colNode == 0) {
                    eX = node.localToParent(0, 0).getX();
                    eY = node.localToParent(0, 0).getY() + ((Button) node).getHeight();
                }
            }
            setWinLine(sX, sY, eX, eY);
        }
    }
}

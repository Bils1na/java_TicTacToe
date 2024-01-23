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
import java.util.Objects;
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
            if (isPlayed) {
                    computerTurn();
            }
        }
    }

    private boolean checkEnemyWinRow(Integer row) {
        Integer rowNull = row == 0 ? null : row;
        if ((gameField[row][0] != null && gameField[row][1] != null)
                && (gameField[row][0].equals(playerSymbol) && gameField[row][1].equals(playerSymbol))
                && gameField[row][2] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) != null
                        && (Objects.equals(GridPane.getRowIndex(node), rowNull) && GridPane.getColumnIndex(node) == 2)) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[row][2] = computerSymbol;
            checkFinish(row, 2);
            return true;
        } else if ((gameField[row][1] != null && gameField[row][2] != null)
                && (gameField[row][1].equals(playerSymbol) && gameField[row][2].equals(playerSymbol))
                && gameField[row][0] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) == null && Objects.equals(GridPane.getRowIndex(node), rowNull)) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[row][0] = computerSymbol;
            checkFinish(row, 0);
            return true;
        } else if ((gameField[row][0] != null && gameField[row][2] != null)
                && (gameField[row][0].equals(playerSymbol) && gameField[row][2].equals(playerSymbol))
                && gameField[row][1] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) != null
                && GridPane.getColumnIndex(node) == 1 && Objects.equals(GridPane.getRowIndex(node), rowNull)) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[row][1] = computerSymbol;
            checkFinish(row, 1);
            return true;
        }
        return false;
    }

    private boolean checkEnemyWinColumn(Integer column) {
        Integer columnNull = column == 0 ? null : column;
        if ((gameField[0][column] != null && gameField[1][column] != null)
                && (gameField[0][column].equals(playerSymbol) && gameField[1][column].equals(playerSymbol))
                && gameField[2][column] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null
                        && (GridPane.getRowIndex(node) == 2 && GridPane.getColumnIndex(node) == columnNull)) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[2][column] = computerSymbol;
            checkFinish(2, column);
            return true;

        } else if ((gameField[1][column] != null && gameField[2][column] != null)
                && (gameField[1][column].equals(playerSymbol) && gameField[2][column].equals(playerSymbol))
                && gameField[0][column] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) == columnNull && GridPane.getRowIndex(node) == null) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[0][column] = computerSymbol;
            checkFinish(0, column);
            return true;
        }  else if ((gameField[0][column] != null && gameField[2][column] != null)
                && (gameField[0][column].equals(playerSymbol) && gameField[2][column].equals(playerSymbol))
                && gameField[1][column] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null
                        && GridPane.getColumnIndex(node) == columnNull && GridPane.getRowIndex(node) == 1) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[1][column] = computerSymbol;
            checkFinish(1, column);
            return true;
        }
        return false;
    }

    private boolean checkEnemyWinDiagonalRight() {
        if (((gameField[0][0] != null && gameField[1][1] != null)
                && (gameField[0][0].equals(playerSymbol) && gameField[1][1].equals(playerSymbol)
                && gameField[2][2] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                        && GridPane.getRowIndex(node) == 2 && GridPane.getColumnIndex(node) == 2) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[2][2] = computerSymbol;
            checkFinish(2, 2);
            return true;
        } else if (((gameField[0][0] != null && gameField[2][2] != null)
                && (gameField[0][0].equals(playerSymbol) && gameField[2][2].equals(playerSymbol)
                && gameField[1][1] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                        && GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[1][1] = computerSymbol;
            checkFinish(1, 1);
            return true;
        } else if (((gameField[1][1] != null && gameField[2][2] != null)
                && (gameField[1][1].equals(playerSymbol) && gameField[2][2].equals(playerSymbol)
                && gameField[0][0] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) == null && GridPane.getColumnIndex(node) == null) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[0][0] = computerSymbol;
            checkFinish( 0, 0);
            return true;
        }
        return false;
    }

    private boolean checkEnemyWinDiagonalLeft() {
        if (((gameField[2][0] != null && gameField[0][2] != null)
                && (gameField[2][0].equals(playerSymbol) && gameField[0][2].equals(playerSymbol)
                && gameField[1][1] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                        && GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[1][1] = computerSymbol;
            checkFinish(1, 1);
            return true;
        } else if (((gameField[2][0] != null && gameField[1][1] != null)
                && (gameField[2][0].equals(playerSymbol) && gameField[1][1].equals(playerSymbol)
                && gameField[0][2] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) == null && GridPane.getColumnIndex(node) != null
                        && GridPane.getColumnIndex(node) == 2) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[0][2] = computerSymbol;
            checkFinish(0, 2);
            return true;
        } else if (((gameField[1][1] != null && gameField[0][2] != null)
                && (gameField[1][1].equals(playerSymbol) && gameField[0][2].equals(playerSymbol)
                && gameField[2][0] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 2
                        && GridPane.getColumnIndex(node) == null) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[2][0] = computerSymbol;
            checkFinish( 2, 0);
            return true;
        }
        return false;
    }

    private boolean checkOwnWinRow(Integer row) {
        Integer rowNull = row == 0 ? null : row;
        if ((gameField[row][0] != null && gameField[row][1] != null)
                && (gameField[row][0].equals(computerSymbol) && gameField[row][1].equals(computerSymbol))
                && gameField[row][2] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) != null
                        && (GridPane.getRowIndex(node) == rowNull && GridPane.getColumnIndex(node) == 2)) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[row][2] = computerSymbol;
            checkFinish(row, 2);
            return true;
        } else if ((gameField[row][1] != null && gameField[row][2] != null)
                && (gameField[row][1].equals(computerSymbol) && gameField[row][2].equals(computerSymbol))
                && gameField[row][0] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == rowNull) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[row][0] = computerSymbol;
            checkFinish(row, 0);
            return true;
        } else if ((gameField[row][0] != null && gameField[row][2] != null)
                && (gameField[row][0].equals(computerSymbol) && gameField[row][2].equals(computerSymbol))
                && gameField[row][1] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) != null
                        && GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == rowNull) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[row][1] = computerSymbol;
            checkFinish(row, 1);
            return true;
        }
        return false;
    }

    private boolean checkOwnWinColumn(Integer column) {
        Integer columnNull = column == 0 ? null : column;
        if ((gameField[0][column] != null && gameField[1][column] != null)
                && (gameField[0][column].equals(computerSymbol) && gameField[1][column].equals(computerSymbol))
                && gameField[2][column] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null
                        && (GridPane.getRowIndex(node) == 2 && GridPane.getColumnIndex(node) == columnNull)) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[2][column] = computerSymbol;
            checkFinish(2, column);
            return true;

        } else if ((gameField[1][column] != null && gameField[2][column] != null)
                && (gameField[1][column].equals(computerSymbol) && gameField[2][column].equals(computerSymbol))
                && gameField[0][column] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getColumnIndex(node) == columnNull && GridPane.getRowIndex(node) == null) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[0][column] = computerSymbol;
            checkFinish(0, column);
            return true;
        }  else if ((gameField[0][column] != null && gameField[2][column] != null)
                && (gameField[0][column].equals(computerSymbol) && gameField[2][column].equals(computerSymbol))
                && gameField[1][column] == null) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null
                        && GridPane.getColumnIndex(node) == columnNull && GridPane.getRowIndex(node) == 1) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[1][column] = computerSymbol;
            checkFinish(1, column);
            return true;
        }
        return false;
    }

    private boolean checkOwnWinDiagonalRight() {
        if (((gameField[0][0] != null && gameField[1][1] != null)
                && (gameField[0][0].equals(computerSymbol) && gameField[1][1].equals(computerSymbol)
                && gameField[2][2] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                        && GridPane.getRowIndex(node) == 2 && GridPane.getColumnIndex(node) == 2) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[2][2] = computerSymbol;
            checkFinish(2, 2);
            return true;
        } else if (((gameField[0][0] != null && gameField[2][2] != null)
                && (gameField[0][0].equals(computerSymbol) && gameField[2][2].equals(computerSymbol)
                && gameField[1][1] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                        && GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[1][1] = computerSymbol;
            checkFinish(1, 1);
            return true;
        } else if (((gameField[1][1] != null && gameField[2][2] != null)
                && (gameField[1][1].equals(computerSymbol) && gameField[2][2].equals(computerSymbol)
                && gameField[0][0] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) == null && GridPane.getColumnIndex(node) == null) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[0][0] = computerSymbol;
            checkFinish( 0, 0);
            return true;
        }
        return false;
    }

    private boolean checkOwnWinDiagonalLeft() {
        if (((gameField[2][0] != null && gameField[0][2] != null)
                && (gameField[2][0].equals(computerSymbol) && gameField[0][2].equals(computerSymbol)
                && gameField[1][1] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null
                        && GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[1][1] = computerSymbol;
            checkFinish(1, 1);
            return true;
        } else if (((gameField[2][0] != null && gameField[1][1] != null)
                && (gameField[2][0].equals(computerSymbol) && gameField[1][1].equals(computerSymbol)
                && gameField[0][2] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) == null && GridPane.getColumnIndex(node) != null
                        && GridPane.getColumnIndex(node) == 2) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[0][2] = computerSymbol;
            checkFinish(0, 2);
            return true;
        } else if (((gameField[1][1] != null && gameField[0][2] != null)
                && (gameField[1][1].equals(computerSymbol) && gameField[0][2].equals(computerSymbol)
                && gameField[2][0] == null))) {
            for (Node node : gameFieldUI.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 2
                        && GridPane.getColumnIndex(node) == null) {
                    ((Button) node).setText(computerSymbol);
                }
            }
            gameField[2][0] = computerSymbol;
            checkFinish( 2, 0);
            return true;
        }
        return false;
    }

    private void computerTurn() {
        if (checkOwnWinDiagonalRight() || checkOwnWinDiagonalLeft()) {

        } else if (checkOwnWinColumn(0) || checkOwnWinColumn(1) || checkOwnWinColumn(2)) {

        } else if (checkOwnWinRow(0) || checkOwnWinRow(1) || checkOwnWinRow(2)) {

        } else if (checkEnemyWinDiagonalRight() || checkEnemyWinDiagonalLeft()) {

        } else if (checkEnemyWinColumn(0) || checkEnemyWinColumn(1) || checkEnemyWinColumn(2)) {

        } else if (checkEnemyWinRow(0) || checkEnemyWinRow(1) || checkEnemyWinRow(2)) {

        } else if (isCleanCenter()) {
            for (Node node : gameFieldUI.getChildren()) {
                Button btn = (Button) node;
                if ((GridPane.getRowIndex(btn) != null && GridPane.getColumnIndex(btn) != null)
                        && (GridPane.getRowIndex(btn) == 1 && GridPane.getColumnIndex(btn) == 1)) {
                    btn.setText(computerSymbol);
                    gameField[1][1] = computerSymbol;
                    checkFinish(1, 1);
                    return;
                }
            }
        } else if (isCleanDiagonalCell()) {
            for (Node node : gameFieldUI.getChildren()) {
                Button btn = (Button) node;
                int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
                int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
                if (rowIndex == 0 && columnIndex == 0 && Objects.equals(btn.getText(), "")) {
                    btn.setText(computerSymbol);
                    gameField[0][0] = computerSymbol;
                    checkFinish(0, 0);
                    return;
                } else if (rowIndex == 0 && columnIndex == 2 && Objects.equals(btn.getText(), "")) {
                    btn.setText(computerSymbol);
                    gameField[0][2] = computerSymbol;
                    checkFinish(0, 2);
                    return;
                } else if (rowIndex == 2 && columnIndex == 0 && Objects.equals(btn.getText(), "")) {
                    btn.setText(computerSymbol);
                    gameField[2][0] = computerSymbol;
                    checkFinish(2, 0);
                    return;
                } else if (rowIndex == 2 && columnIndex == 2 && Objects.equals(btn.getText(), "")) {
                    btn.setText(computerSymbol);
                    gameField[2][2] = computerSymbol;
                    checkFinish(2, 2);
                    return;
                }
            }
        } else {
            while (true) {
                int computerRow = rnd.nextInt(3);
                int computerCol = rnd.nextInt(3);

                for (Node node : gameFieldUI.getChildren()) {
                    Button btn = (Button) node;
                    int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
                    int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
                    if (isFull()) {
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
    }

    private boolean isCleanCenter() {
        boolean isCleanCenter = false;
        for (Node node : gameFieldUI.getChildren()) {
            if ((GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null)
                    && (GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == 1)
                    && gameField[1][1] == null) {
                isCleanCenter = true;
                return isCleanCenter;
            }
        }
        return isCleanCenter;
    }

    private boolean isCleanDiagonalCell() {
        boolean isCleanCell = false;
        for (Node node : gameFieldUI.getChildren()) {
            Button btn = (Button) node;
            int rowIndex = GridPane.getRowIndex(btn) == null ? 0 : GridPane.getRowIndex(btn);
            int columnIndex = GridPane.getColumnIndex(btn) == null ? 0 : GridPane.getColumnIndex(btn);
            if (rowIndex == 0 && columnIndex == 0  && Objects.equals(btn.getText(), "")
                    || rowIndex == 0 && columnIndex == 2 && Objects.equals(btn.getText(), "")
                    || rowIndex == 2 && columnIndex == 0 && Objects.equals(btn.getText(), "")
                    || rowIndex == 2 && columnIndex == 2 && Objects.equals(btn.getText(), "")) {
                isCleanCell = true;
                return isCleanCell;
            }
        }
        return isCleanCell;
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

    private void loseWindow() {
        Stage loseWindow = new Stage();
        loseWindow.initModality(Modality.APPLICATION_MODAL);
        loseWindow.setTitle("Lose");

        Label label = new Label("You lose!");
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
            loseWindow.close();
        });
        repeatButton.setOnAction(e -> {
            repeat();
            loseWindow.close();
        });

        VBox loseWindowContent = new VBox(20);
        HBox buttons = new HBox();
        buttons.getChildren().addAll(repeatButton, exitButton);
        loseWindowContent.getChildren().addAll(label, buttons);
        loseWindowContent.setAlignment(javafx.geometry.Pos.CENTER);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);

        Scene dialogScene = new Scene(loseWindowContent, 300, 150);
        loseWindow.setScene(dialogScene);

        loseWindow.showAndWait();
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
                    if (gameField[row][0].equals("X")) {
                        winWindow();
                    } else {
                        loseWindow();
                    }
                }
            } else if (checkColumn(column)) {
                if (winLine != null){
                    isPlayed = false;
                    renderWinLine(null, column, null);
                    if (gameField[0][column].equals("X")) {
                        winWindow();
                    } else {
                        loseWindow();
                    }
                }
            } else if (checkDiagonalRight()) {
                if (winLine != null) {
                    isPlayed = false;
                    renderWinLine(null, null, true);
                    if (gameField[0][0].equals("X")) {
                        winWindow();
                    } else {
                        loseWindow();
                    }
                }
            } else if (checkDiagonalLeft()) {
                if (winLine != null) {
                    isPlayed = false;
                    renderWinLine(null, null, false);
                    if (gameField[2][0].equals("X")) {
                        winWindow();
                    } else {
                        loseWindow();
                    }
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

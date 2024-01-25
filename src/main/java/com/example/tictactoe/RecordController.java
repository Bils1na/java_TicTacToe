package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayDeque;

public class RecordController {
    private ArrayDeque<String> data;
    private Integer number = 1;
    @FXML
    private GridPane recordTable;

    @FXML
    public void initialize() {
        data = DatabaseHandler.downloadDatabase();
//        for (Node node : recordTable.getChildren()) {
//            Integer row = GridPane.getRowIndex(node);
//            Integer column = GridPane.getColumnIndex(node);
//            if (node instanceof Label && data != null) {
//                Label label = (Label) node;
//                if (column == null && label.getText().isEmpty()) {
//                    label.setText(String.valueOf(number));
//                    number++;
//
//                }
//                if (!data.isEmpty() && column != null && column == 1
//                        && label.getText().isEmpty()) label.setText(data.pop());
//                if (!data.isEmpty()  && column != null && column == 2
//                        && label.getText().isEmpty()) label.setText(data.pop());
        int rows = recordTable.getRowConstraints().size();
        int columns = recordTable.getColumnConstraints().size();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Label label = (Label) recordTable.getChildren().get(column + row * columns);
//                label.setText(data.pop());
//                if (GridPane.getRowIndex(label) != null) {
                    if (!data.isEmpty() && GridPane.getColumnIndex(label) != null) {
                        label.setText(data.pop());
                    } else {
                        label.setText(String.valueOf(number));
                        number++;
                    }
                    if (data.isEmpty()) return;
//                }
            }
        }
        System.out.println(data);

    }

}




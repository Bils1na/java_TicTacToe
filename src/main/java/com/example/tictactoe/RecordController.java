package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayDeque;

public class RecordController {
    private ArrayDeque<ArrayDeque<String>> data;
    private Integer number = 1;
    @FXML
    private GridPane recordTable;

    @FXML
    public void initialize() {
        data = DatabaseHandler.downloadDatabase();
        for (ArrayDeque<String> arr : data) {
            for (Node node : recordTable.getChildren()) {
                Integer column = GridPane.getColumnIndex(node);
                if (node instanceof Label && data != null) {
                    Label label = (Label) node;
                    if (column == null && label.getText().isEmpty()) {
                        label.setText(String.valueOf(number));
                        number++;
                    }
                    if (!arr.isEmpty() && column != null && column == 1
                            && label.getText().isEmpty() && arr.peek().matches(".*[a-zA-Z].*")) {
                        label.setText(arr.pop());
                    }
                    if (!arr.isEmpty() && column != null && column == 2
                            && label.getText().isEmpty()) {
                        label.setText(arr.pop());
                    }
                }
            }
        }
    }

}




module com.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.tictactoe to javafx.fxml;
    exports com.example.tictactoe;
}
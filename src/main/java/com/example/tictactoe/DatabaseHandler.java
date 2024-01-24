package com.example.tictactoe;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class DatabaseHandler {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/XODB";
    private static final String USERNAME = "Artem";
    private static final String PASSWORD = "admin";
    public static void addToDatabase(String name, Integer score) {
        try {
            // Загружаем драйвер JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Пример выполнения запроса SELECT
//            String selectQuery = "SELECT * FROM USERS";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
//                ResultSet resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    // Обрабатываем результаты запроса
//                    String column1Value = resultSet.getString("NAME");
//                    String column2Value = resultSet.getString("SCORE");
//                    System.out.println("NAME: " + column1Value + ", SCORE: " + column2Value);
//                }
//            }

//            // Пример выполнения запроса INSERT
            String insertQuery = "INSERT INTO USERS (NAME, SCORE) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, String.valueOf(score));
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected");
            }

            // Закрываем соединение
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

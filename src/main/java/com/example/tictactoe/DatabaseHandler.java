package com.example.tictactoe;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class DatabaseHandler {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/XODB",
            USERNAME = "Artem", PASSWORD = "admin";

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
//            String insertQuery = "INSERT INTO USERS (NAME, SCORE) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//                preparedStatement.setString(1, name);
//                preparedStatement.setString(2, String.valueOf(score));
//                int rowsAffected = preparedStatement.executeUpdate();
//                System.out.println(rowsAffected + " row(s) affected");
//            }
            System.out.println("connection...");
            if (existInDatabase(name)) {
                String insertQuery = "INSERT INTO USERS (NAME, SCORE) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, String.valueOf(score));
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) affected");
                }
            } else {
                String updateQuery = "UPDATE USERS SET SCORE = ? WHERE NAME = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setInt(1, score);
                    preparedStatement.setString(2, name);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("OK");
                    } else {
                        System.out.println("Not OK");
                    }
                }
            }

            // Закрываем соединение
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean existInDatabase(String name) {
        try {
            // Загружаем драйвер JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String selectQuery = "SELECT * FROM USERS";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String checkName = resultSet.getString("NAME");
                    if (checkName.equals(name)) return false;
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayDeque<String> downloadDatabase() {
        ArrayDeque<String> data = new ArrayDeque<>();
        try {
            // Загружаем драйвер JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

//             Пример выполнения запроса SELECT
            String selectQuery = "SELECT * FROM USERS";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    // Обрабатываем результаты запроса
                    data.add(resultSet.getString("NAME"));
                    data.add(resultSet.getString("SCORE"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

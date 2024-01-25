package com.example.tictactoe;

import java.sql.*;
import java.util.ArrayDeque;

public class DatabaseHandler {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/XODB",
            USERNAME = "Artem", PASSWORD = "admin";

    public static void addToDatabase(String name, Integer score) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            System.out.println("connection...");
            if (existInDatabase(name)) {
                String insertQuery = "INSERT INTO USERS (NAME, SCORE) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, String.valueOf(score));
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) affected");
                }
            } else if (checkScore(name, score)){
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
            System.out.println("disconnection...");
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean existInDatabase(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

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

    private static boolean checkScore(String name, Integer score) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String selectQuery = "SELECT * FROM USERS";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Integer checkScore = resultSet.getInt("SCORE");
                    String checkName = resultSet.getString("NAME");
                    if (name.equals(checkName) && checkScore > score) return false;
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayDeque<ArrayDeque<String>> downloadDatabase() {
        ArrayDeque<ArrayDeque<String>> data = new ArrayDeque<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String selectQuery = "SELECT * FROM USERS ORDER BY SCORE DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ArrayDeque<String> userData = new ArrayDeque<>();
                    userData.add(resultSet.getString("NAME"));
                    userData.add(resultSet.getString("SCORE"));
                    data.add(userData);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

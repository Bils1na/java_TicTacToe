package com.example.tictactoe;

public class Config {
    private static boolean isPlayer;

    public static boolean isPlayer() {
        return isPlayer;
    }

    public static void setPlayer(boolean player) {
        isPlayer = player;
    }
}

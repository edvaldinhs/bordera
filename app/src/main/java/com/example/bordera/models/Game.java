package com.example.bordera.models;

public class Game {
    private final String name;
    private final String id;
    private final int colorResId;

    public Game(String name, String id, int colorResId) {
        this.name = name;
        this.id = id;
        this.colorResId = colorResId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getColorResId() {
        return colorResId;
    }
}
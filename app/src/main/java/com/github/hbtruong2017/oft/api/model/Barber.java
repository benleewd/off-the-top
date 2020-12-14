package com.github.hbtruong2017.oft.api.model;

public class Barber {
    private String name;
    private String description;
    private int image;

    public Barber(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

}

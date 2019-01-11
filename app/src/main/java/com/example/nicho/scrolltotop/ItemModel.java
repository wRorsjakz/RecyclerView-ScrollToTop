package com.example.nicho.scrolltotop;

public class ItemModel {
    private String title;
    private String description;

    //Constructor
    public ItemModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

package ru.geekbraind.lesson5.base.enums;

public enum Categories {
    FOOD(1, "Food"),
    ELECTRONIC(2, "Electronic");

    public final int id;
    public final String title;

    Categories(int id, String title) {
        this.id = id;
        this.title = title;
    }
}

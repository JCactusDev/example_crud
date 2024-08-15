package com.github.jcactusdev.example_crud.entity;

public enum ClientOrderStatus {
    NOTAGREED("Не согласовано"),
    AGREED("Согласовано"),
    LOADING("Погрузка"),
    ONWAY("В пути"),
    CLOSED("Закрыт"),
    CANCELLED("Отменен");

    private final String view;

    ClientOrderStatus(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }
}

package com.teylas.roommanager;

public enum RoomType {
    ECONOMY("Economy"), PREMIUM("Premium");

    RoomType(String type) {
        this.type = type;
    }
    private String type;

    public String getType() {
        return type;
    }
}

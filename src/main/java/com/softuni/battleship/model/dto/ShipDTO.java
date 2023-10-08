package com.softuni.battleship.model.dto;

public class ShipDTO {
    private String name;
    private Long health;
    private Long power;
    private Long userID;

    public String getName() {
        return name;
    }

    public ShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public ShipDTO setHealth(Long health) {
        this.health = health;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public ShipDTO setPower(Long power) {
        this.power = power;
        return this;
    }

    public Long getUserID() {
        return userID;
    }

    public ShipDTO setUserID(Long userID) {
        this.userID = userID;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s -- %s -- %s", name, health, power);
    }
}

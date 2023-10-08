package com.softuni.battleship.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ShipBattleDTO {
    @NotBlank
    @Size(min = 3, max = 20)
    private String shipAttacker;
    @NotBlank
    @Size(min = 3, max = 20)
    private String shipDefender;

    public String getShipAttacker() {
        return shipAttacker;
    }

    public ShipBattleDTO setShipAttacker(String shipAttacker) {
        this.shipAttacker = shipAttacker;
        return this;
    }

    public String getShipDefender() {
        return shipDefender;
    }

    public ShipBattleDTO setShipDefender(String shipDefender) {
        this.shipDefender = shipDefender;
        return this;
    }
}

package com.softuni.battleship.model.dto;

import com.softuni.battleship.model.enums.CategoryEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CreateShipDTO {
    @NotBlank
    @Size(min = 2, max = 10)
    private String name;
    @Positive
    private Long power;
    @Positive
    private Long health;
    @PastOrPresent
    private LocalDate created;
    @NotNull
    private CategoryEnum category;

    public String getName() {
        return name;
    }

    public CreateShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public CreateShipDTO setPower(Long power) {
        this.power = power;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public CreateShipDTO setHealth(Long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public CreateShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public CreateShipDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}

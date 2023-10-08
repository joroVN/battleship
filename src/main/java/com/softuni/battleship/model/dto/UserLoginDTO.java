package com.softuni.battleship.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank(message = "Username not valid.")
    @Size(min = 3, max = 10, message = "Username length must be more than 3 characters.")
    private String username;
    @NotBlank(message = "Password not valid.")
    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}

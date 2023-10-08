package com.softuni.battleship.model.dto;

import com.softuni.battleship.model.validation.PasswordMatch;
import com.softuni.battleship.model.validation.UniqueEmail;
import com.softuni.battleship.model.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@PasswordMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class UserRegisterDTO {
    @NotBlank(message = "Username not valid.")
    @Size(min = 3, max = 20, message = "Username should be between 3 and 20 characters.")
    @UniqueUsername(message = "Username already exists.")
    private String username;
    @NotBlank(message = "Full name not valid.")
    @Size(min = 5, max = 20, message = "Full name should be between 5 and 20 characters.")
    private String fullName;
    @NotBlank(message = "Email not valid.")
    @UniqueEmail(message = "Email already exists.")
    private String email;
    @NotBlank(message = "Password not valid.")
    @Size(min = 3, message = "Password length must be more than 3 characters")
    private String password;
    @NotBlank(message = "Password not valid.")
    @Size(min = 3, message = "Password length must be more than 3 characters")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

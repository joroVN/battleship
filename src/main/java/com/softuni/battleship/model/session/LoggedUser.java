package com.softuni.battleship.model.session;

import com.softuni.battleship.model.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
public class LoggedUser {

    private Long id;
    private String username;

    public void login(UserEntity user){
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout(){
        this.id = null;
        this.username = null;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }
}

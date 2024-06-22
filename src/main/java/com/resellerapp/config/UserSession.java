package com.resellerapp.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private long id;

    private String username;

    public void login(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public boolean isLoggedIn() {
        return id > 0;
    }

    public void logout() {
        id = 0;
        username = null;
    }

    public Long id() {
        return id;
    }

    public String username() {
        return username;
    }
}

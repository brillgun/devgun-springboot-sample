package com.devgun.sample.springboot.config.auth.dto;

import com.devgun.sample.springboot.domain.user.User;

public class SessionUser {
        String name;
        String email;
        String picture;

    public SessionUser(User user) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Object getName() {
        return name;
    }
}

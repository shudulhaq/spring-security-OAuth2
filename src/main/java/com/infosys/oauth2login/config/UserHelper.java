package com.infosys.oauth2login.config;

import com.infosys.oauth2login.model.UserHaq;
import org.springframework.security.core.userdetails.User;

public class UserHelper extends User {
    private static final long serialVersionUID = 1L;

    public UserHelper(UserHaq user){
        super(
                user.getUsername(),
                user.getPassword(),
                user.getListOfgrantedAuthorities()
        );
    }
}

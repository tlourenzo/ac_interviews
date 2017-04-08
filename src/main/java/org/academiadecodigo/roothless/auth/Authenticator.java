package org.academiadecodigo.roothless.auth;

import org.academiadecodigo.roothless.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by codecadet on 04/04/17.
 */
@Component
public class Authenticator {

    @Autowired private UserService userService;

    public Authenticator() {
    }

    public boolean authenticate(String username, String password) {
        return userService.authenticate(username,password);
    }
}

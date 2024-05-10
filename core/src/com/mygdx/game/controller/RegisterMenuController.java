package com.mygdx.game.controller;

import com.mygdx.game.AtomicBomber;
import com.mygdx.game.model.User;
import com.mygdx.game.view.RegisterMenuScreen;

public class RegisterMenuController {

    public boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            RegisterMenuScreen.showEmptyFieldErr();
            return false;
        }
        else if (doesUsernameAlreadyExist(username)) {
            RegisterMenuScreen.usernameAlreadyExists();
            return false;
        }
       else   {
            User.setCurrentUser(new User(username, password, 0, User.getRandomAvatar()));
            RegisterMenuScreen.removeErrorMessage();
            return true;
        }
    }
    public boolean doesUsernameAlreadyExist(String username){
        for (User user : User.getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


}

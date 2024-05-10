package com.mygdx.game.controller;

import com.mygdx.game.AtomicBomber;
import com.mygdx.game.model.User;
import com.mygdx.game.view.LoginMenuScreen;
import com.mygdx.game.view.MainMenuScreen;

public class LoginMenuController {
    private AtomicBomber game = AtomicBomber.getInstance();

    public boolean login(String username, String password) {
        boolean userExists = false;
        boolean passwordCorrect = false;

        for (User user : User.getUsers()) {
            if (user.getUsername().equals(username)) {
                userExists = true;
                if (user.getPassword().equals(password)) {
                    passwordCorrect = true;
                    User.setCurrentUser(user);
                   return true;
                }
            }
        }

        if (!userExists) {
            LoginMenuScreen.showNoSuchUserErr();
        } else if (!passwordCorrect) {
            LoginMenuScreen.showWrongPassErr();
        }
        return false;
    }
}

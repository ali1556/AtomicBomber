package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private String username;
    private String password;
    private int highScore;
    public static User currentUser;
    Texture avatar;
    private static ArrayList<User> users = new ArrayList<User>();

    public User(String username, String password, int highScore,Texture avatar) {
        this.username = username;
        this.password = password;
        this.highScore = highScore;
        this.avatar = avatar;
        users.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    public boolean changeUsername(String username) {
        if (!usernameAlreadyExists(username)) {
            this.setUsername(username);
            return true;
        }
        return false;
    }

    public void changePassword (String password) {
        this.setPassword(password);
    }

    private static boolean usernameAlreadyExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public void deleteUser() {
        users.remove(this);
    }

    private void changeAvatar(Texture avatar) {
        this.avatar = avatar;
    }
    private void setCustomAvatar(String path) {
        this.avatar = new Texture(path);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public Texture getAvatar() {
        return avatar;
    }

    public void setAvatar(Texture avatar) {
        this.avatar = avatar;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static Texture getRandomAvatar() {
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1; // Generates a random number between 1 and 4
        return new Texture("extra/avatar_" + randomNumber + ".png");
    }

    /* TO DO
    drop and drag avatar by user
     */
}

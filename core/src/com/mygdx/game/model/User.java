package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private String username;
    private String password;
    private int lastRound;
    private int score;
    private int kills;
    private int accuracy;
    private int chosenDifficulty;
    public static User currentUser;
    public User(String username, String password, int lastRound, int score, int kills, int accuracy, int chosenDifficulty,Texture avatar) {
        this.username = username;
        this.password = password;
        this.lastRound = lastRound;
        this.score = score;
        this.kills = kills;
        this.accuracy = accuracy;
        this.chosenDifficulty = chosenDifficulty;
        this.avatar = avatar;
        users.add(this);
    }

    public int getLastRound() {
        return lastRound;
    }

    public void setLastRound(int lastRound) {
        this.lastRound = lastRound;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getChosenDifficulty() {
        return chosenDifficulty;
    }

    public void setChosenDifficulty(int chosenDifficulty) {
        this.chosenDifficulty = chosenDifficulty;
    }

    public Texture avatar;
    private static ArrayList<User> users = new ArrayList<User>();


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

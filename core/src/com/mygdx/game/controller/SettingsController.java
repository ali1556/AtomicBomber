package com.mygdx.game.controller;

import com.mygdx.game.AtomicBomber;

public class SettingsController {
    private String difficulty;
    private boolean sound;
    private boolean blackAndWhite;
    private AtomicBomber game ;
    public void setSound(boolean b) {
        this.sound = b;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public void setBlackAndWhite(boolean b) {
        this.blackAndWhite = b;
    }
}

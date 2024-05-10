package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.model.User;
import com.mygdx.game.view.StartMenuScreen;


public class AtomicBomber extends Game {
    public SpriteBatch batch;

    public static User user;
    public static int currentWave = 1;
    public static int difficulty = 1;
    public static boolean mute = false;
    public static int clusterBombCount;
    public static boolean isPaused = false;
    public static boolean isGameFreezed = false;
    public static int killCount = 0;
    public static int hitCount = 0;
    public static int firedCount = 0;
    public static int radioActiveBombCount = 0;
    public static Music music;
    public Skin skin;


    public void create() {
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.setScreen(new StartMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
    public static AtomicBomber getInstance() {
        return new AtomicBomber();
    }

}

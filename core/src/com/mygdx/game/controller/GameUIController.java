package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.model.GameAssets;
import com.mygdx.game.model.Player;
//import com.mygdx.game.model.ResourceManager;
import com.mygdx.game.model.User;
import com.mygdx.game.view.GameScreen;
import com.mygdx.game.view.MainMenuScreen;


public class GameUIController {
    public static GameUIController gameUIController = new GameUIController();
    public Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    public Table table = new Table();
    public Label waveNumber = new Label("Wave: " , skin);
    public Label killCount = new Label("0" , skin);
    public Label radioActiveBombCount = new Label("RadioActive: 0" , skin);
    public Label clusterBombCount = new Label("Cluster: 0" , skin );
    public Label playerHealth = new Label("HP: 100" , skin );
    public Window pauseWindow = new Window("Paused", skin);
    public TextButton exitToMainMenu = new TextButton("Exit Without Save", skin);
    public TextButton exitToMainMenuSave = new TextButton("Save And Exit", skin);
    public TextButton stopMusic = new TextButton("Stop Music", skin);
    public Label controls = new Label("WASD or UpDownRightLeft Arrow to move, Space to shoot, R radioactive, C cluster", skin, "default");
    private final Slider chooseFromSongs = new Slider(0, 2, 1, false, skin);

    private GameUIController() {
        table.setFillParent(true);
        table.center();

        table.addActor(killCount);
        killCount.setPosition(10 , (float) Gdx.graphics.getHeight() - killCount.getHeight() - 10);
        table.addActor(waveNumber);
        waveNumber.setPosition((float) Gdx.graphics.getWidth() - waveNumber.getText().length * 34 , (float) Gdx.graphics.getHeight() - waveNumber.getHeight() - 10);
        table.addActor(radioActiveBombCount);
        radioActiveBombCount.setPosition(10 , (float) Gdx.graphics.getHeight() - radioActiveBombCount.getHeight() - 60);
        table.addActor(clusterBombCount);
        clusterBombCount.setPosition(10, (float) Gdx.graphics.getHeight() - clusterBombCount.getHeight() - 110);
        table.addActor(playerHealth);
        playerHealth.setPosition((float) Gdx.graphics.getWidth() - waveNumber.getText().length * 34 , (float) Gdx.graphics.getHeight() - playerHealth.getHeight() - 60);
        playerHealth.setColor(Color.GREEN);

        table.addActor(pauseWindow);
        pauseWindow.setSize(1500, 700);
        pauseWindow.setPosition((float) Gdx.graphics.getWidth() / 2 - pauseWindow.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - pauseWindow.getHeight() / 2);
        pauseWindow.setVisible(false);
        pauseWindow.setMovable(true);
        pauseWindow.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(chooseFromSongs).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(controls).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(stopMusic).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(exitToMainMenuSave).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);
        pauseWindow.add(exitToMainMenu).fillX();
        pauseWindow.row().pad(10, 0, 10, 0);

        playerHealth.setFontScale(1.5f);
        radioActiveBombCount.setFontScale(1.5f);
        clusterBombCount.setFontScale(1.5f);
        killCount.setFontScale(1.5f);
        waveNumber.setFontScale(1.5f);
    }

    public void updateKillCount(){
        killCount.setText("Kills: " + AtomicBomber.killCount);
    }
    public void updateWaveNumber(){
        waveNumber.setText("Wave : " + AtomicBomber.currentWave);
    }
    public void updateRadioActiveBombCount(){
        radioActiveBombCount.setText("RadioActive: " + AtomicBomber.radioActiveBombCount);
    }
    public void updateClusterBombCount(){
        clusterBombCount.setText("Cluster: " + AtomicBomber.clusterBombCount);
    }
    public void updatePlayerHealth(){
        playerHealth.setText("HP: " + Player.player.playerHealth);

        if (Player.player.playerHealth < 20){
            playerHealth.setColor(Color.RED);
        }
        else if (Player.player.playerHealth < 60){
            playerHealth.setColor(Color.YELLOW);
        }
        else {
            playerHealth.setColor(Color.GREEN);
        }
    }

    public void pauseWindow(AtomicBomber game){
        if (game.isPaused){
            pauseWindow.setVisible(true);
        } else {
            pauseWindow.setVisible(false);
        }

    }

    private void exitToMainMenuSave(AtomicBomber game){
        exitToMainMenuSave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int waveEnded = AtomicBomber.currentWave;
                ((GameScreen)game.getScreen()).fullDispose();
                game.setScreen(new MainMenuScreen(game, User.getCurrentUser()));
                if (AtomicBomber.user != null){
                    AtomicBomber.user.setKills(AtomicBomber.killCount);
                    AtomicBomber.user.setAccuracy(AtomicBomber.hitCount / AtomicBomber.firedCount);
                    AtomicBomber.user.setLastRound(waveEnded);
//                    Resour.loadAndSave(GameAssets.gameAssets.saveDataPath);
                }
                else {
                    AtomicBomber.currentWave = waveEnded;
                }
            }
        });
    }



}

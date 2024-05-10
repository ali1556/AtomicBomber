package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.model.User;

public class SettingsScreen implements Screen {
    private AtomicBomber game;
    private SettingsController settingsController;
    private Texture background;
    private Stage stage;
    private Label difficultyField;
    private TextButton hard;
    private TextButton medium;
    private TextButton easy;
    private Label difficultySet;
    private TextButton back;
    private TextButton muteButton;
    private TextButton blackAndWhite; // * To do * //

    public SettingsScreen(AtomicBomber game, SettingsController settingsController) {
        this.game = game;
        this.settingsController = settingsController;
    }

    @Override
    public void show() {
        background = new Texture("extra/background.jpg");
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        difficultyField = new Label("Difficulty : ", game.skin);
        hard = new TextButton("Hard", game.skin);
        medium = new TextButton("Medium", game.skin);
        easy = new TextButton("Easy", game.skin);
        difficultySet = new Label("Difficulty Set", game.skin);
        difficultySet.setVisible(false);
        back = new TextButton("Back", game.skin);
        muteButton = new TextButton("Mute", game.skin, "default");
        blackAndWhite = new TextButton("Black and White", game.skin); // * To do * //
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game, User.getCurrentUser()));
            }
        });
        hard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsController.setDifficulty("hard");
                difficultySet.setVisible(false);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        difficultySet.setVisible(true);
                    }
                }, 0.3f); // 0.3 seconds delay
            }
        });

        medium.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsController.setDifficulty("medium");
                difficultySet.setVisible(false);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        difficultySet.setVisible(true);
                    }
                }, 0.3f); // 0.3 seconds delay
            }
        });


        easy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                settingsController.setDifficulty("easy");
                difficultySet.setVisible(false);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        difficultySet.setVisible(true);
                    }
                }, 0.3f); // 0.3 seconds delay
            }
        });

        table.add(difficultyField).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(hard).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(medium).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(easy).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(difficultySet).fillX().uniformX().center();
        table.row().pad(10, 0, 10, 0);
        table.add(muteButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(back).fillX().uniformX();
    }



    @Override
    public void render(float v) {
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    private void muteButton(AtomicBomber game){
        muteButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (AtomicBomber.mute){
                    muteButton.setText("Mute");
                    AtomicBomber.music.setVolume(100);
                }
                else {
                    muteButton.setText("Unmute");
                    AtomicBomber.music.setVolume(0);
                }
                AtomicBomber.mute = !AtomicBomber.mute;
                game.getScreen().dispose();
                game.setScreen(new SettingsScreen(game, settingsController));
            }
        });
    }
}

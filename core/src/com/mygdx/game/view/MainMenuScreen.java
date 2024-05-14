package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.controller.ProfileMenuController;
import com.mygdx.game.controller.LeaderboardController;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.User;

public class MainMenuScreen implements Screen {
    private Texture background;
    User user;
    private Stage stage;
    private final AtomicBomber game;

    public MainMenuScreen(AtomicBomber game, User user) {
        this.game = game;
        this.user = user;
    }

    @Override
    public void show() {
        background = new Texture("extra/background.jpg");
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create label to display username and avatar
        String username = User.getCurrentUser() != null ? User.getCurrentUser().getUsername() : "Guest";
        Label userInfoLabel = new Label("Username: " + username, game.skin);
        // Add avatar image here if available

        // Create buttons
        TextButton profileButton = new TextButton("Profile", game.skin);
        TextButton leaderboardButton = new TextButton("Leaderboard", game.skin);
        TextButton settingsButton = new TextButton("Settings", game.skin);
        TextButton exitButton = new TextButton("Exit", game.skin);
        TextButton logoutButton = new TextButton("Logout", game.skin);
        TextButton playNewGameButton = new TextButton("Play New Game", game.skin);

        // Add button listeners
        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ProfileMenuScreen(game, new ProfileMenuController()));
            }
        });

        leaderboardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LeaderboardScreen(game, new LeaderboardController()));
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game, new SettingsController()));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User.setCurrentUser(null);
                game.setScreen(new StartMenuScreen(game));
            }
        });
        playNewGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, Player.getCurrentPlayer()));
            }
        });

        Texture avatarTexture = User.getCurrentUser() != null ? User.getCurrentUser().getAvatar() : null;
        Image avatarImage = new Image(avatarTexture);

        // Add components to the table
        table.add(avatarImage).size(64, 64).top().left().pad(20).center().row(); // Adjust size as needed
        table.add(userInfoLabel).top().left().pad(20).center().row();
        table.add(playNewGameButton).width(200).pad(10).row();
        table.add(profileButton).width(200).pad(10).row();
        table.add(leaderboardButton).width(200).pad(10).row();
        table.add(settingsButton).width(200).pad(10).row();
        table.add(logoutButton).width(200).pad(10).row();
        table.add(exitButton).width(200).pad(10).row();

    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();

        // Draw the UI stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        background.dispose();
        stage.dispose();
    }
}

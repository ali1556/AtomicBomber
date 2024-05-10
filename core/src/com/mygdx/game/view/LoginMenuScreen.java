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
import com.mygdx.game.controller.LoginMenuController;
import com.mygdx.game.model.User;

public class LoginMenuScreen implements Screen {
    private Texture background;
    private static Label passwordIsWrong;
    private static Label noSuchUser;
    private Stage stage;
    private final AtomicBomber game;
    private final LoginMenuController controller;

    public LoginMenuScreen(AtomicBomber game) {
        this.game = game;
        this.controller = new LoginMenuController();
    }

    @Override
    public void show() {
        background = new Texture("extra/background.jpg");
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create text fields for username and password input
        final TextField usernameField = new TextField("", game.skin);
        final TextField passwordField = new TextField("", game.skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Create labels for text fields
        Label usernameLabel = new Label("Username : ", game.skin);
        Label passwordLabel = new Label("Password : ", game.skin);

        // Create submit button
        TextButton loginButton = new TextButton("Login", game.skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (controller.login(username, password)) {
                    game.setScreen(new MainMenuScreen(game, User.getCurrentUser()));
                };
            }
        });

        // Create play as guest button
        TextButton guestButton = new TextButton("Play as Guest", game.skin);
        TextButton back = new TextButton("Back", game.skin);
        guestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {;
                User user = new User("Guest", "", 0, User.getRandomAvatar());
                User.setCurrentUser(user);
                game.setScreen(new MainMenuScreen(game, user));
            }
        });

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {;
                game.setScreen(new StartMenuScreen(game));
            }
        });


        table.add(usernameLabel).padBottom(10);
        table.add(usernameField).width(150).padBottom(10).row();
        table.add(passwordLabel).padBottom(10);
        table.add(passwordField).width(150).padBottom(10).row();
        table.row().padBottom(10); // Add an empty row
        table.add(loginButton).colspan(2).width(150).padBottom(10).row();
        table.add(guestButton).colspan(2).width(150).padBottom(10).row();
        passwordIsWrong = new Label("Wrong password!", game.skin);
        passwordIsWrong.setVisible(false);
        table.add(passwordIsWrong).colspan(2).padBottom(10).row();
        noSuchUser = new Label("No such user!", game.skin);
        noSuchUser.setVisible(false);
        table.add(noSuchUser).colspan(2).padBottom(10).row();
        table.add(back).colspan(2).padBottom(10).row();
    }

    public static void showWrongPassErr() {
        passwordIsWrong.setVisible(true);
    }

    public static void hideErrorMessageErr() {
        passwordIsWrong.setVisible(false);
    }
    public static void showNoSuchUserErr() {
        noSuchUser.setVisible(true);
    }
    public static void hideNoSuchUserErr() {
        noSuchUser.setVisible(false);
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

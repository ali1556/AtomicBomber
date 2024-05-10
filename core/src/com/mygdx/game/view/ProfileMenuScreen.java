package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.controller.ProfileMenuController;
import com.mygdx.game.model.User;

public class ProfileMenuScreen implements Screen {
    public ProfileMenuScreen(AtomicBomber game, ProfileMenuController profileMenuController) {
        this.game = game;
        this.controller = profileMenuController;
    }

    private AtomicBomber game;
    private Texture background;
    private Stage stage;
    private ProfileMenuController controller;
    private Label userNameChanged;
    private Label passwordChanged;
    private Label usernameTaken;

    @Override
    public void show() {
        background = new Texture("extra/background.jpg");
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        final TextButton changeUserNameButton = new TextButton("Change Username", game.skin);
        final TextButton changePasswordButton = new TextButton("Change Password", game.skin);
        final TextButton deleteAccountButton = new TextButton("Delete Account", game.skin);
        final TextButton enterAvatarMenuButton = new TextButton("Enter Avatar Menu", game.skin);
        final TextField newUsernameField = new TextField("", game.skin);
        final TextField newPasswordField = new TextField("", game.skin);
        final Label newUsernameLabel = new Label("New Username : ", game.skin);
        final Label newPasswordLabel = new Label("New Password : ", game.skin);
        final TextButton back = new TextButton("Back", game.skin);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x , float y)  {
                game.setScreen(new MainMenuScreen(game, User.getCurrentUser()));
            }
        });



        changeUserNameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (
                User.getCurrentUser().changeUsername(newUsernameField.getText())){
                    usernameTaken.setVisible(false);
                    passwordChanged.setVisible(false);
                userNameChanged.setVisible(true);
                }
                else {
                    userNameChanged.setVisible(false);
                    passwordChanged.setVisible(false);
                    usernameTaken.setVisible(true);
                }
    }
        });

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User.getCurrentUser().changePassword(newPasswordField.getText());
                userNameChanged.setVisible(false);
                usernameTaken.setVisible(false);
                passwordChanged.setVisible(true);
    }
        });

        deleteAccountButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User.getCurrentUser().deleteUser();

                game.setScreen(new StartMenuScreen(game));
    }
        });

        enterAvatarMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
    }
        });

        table.add(newUsernameLabel).padBottom(10);
        table.add((newUsernameField)).width(150).padBottom(10).row();
        table.add(changeUserNameButton).colspan(2).width(150).padBottom(10).row();
        userNameChanged = new Label("Username Successfully changed", game.skin);
        userNameChanged.setVisible(false);
        usernameTaken = new Label("Username is already taken", game.skin);
        usernameTaken.setVisible(false);
        table.add(usernameTaken).colspan(2).padBottom(10).row();
        table.add(userNameChanged).colspan(2).padBottom(10).row();
        table.add(newPasswordLabel).padBottom(10);
        table.add(newPasswordField).width(150).padBottom(10).row();
        table.add(changePasswordButton).colspan(2).width(150).padBottom(10).row();
        passwordChanged = new Label("Password Successfully changed", game.skin);
        passwordChanged.setVisible(false);
        table.add(passwordChanged).colspan(2).padBottom(10).row();
        table.add(deleteAccountButton).center();
        table.add(enterAvatarMenuButton).center();
        table.row();
        table.add(back).colspan(2).width(150).padBottom(10).row();
    }

    @Override
    public void render(float v) {
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
}

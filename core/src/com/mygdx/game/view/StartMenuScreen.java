package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.AtomicBomber;

public class StartMenuScreen implements Screen {
    Texture background;
    TextButton loginButton;
    TextButton registerButton;
    TextButton exit;
    AtomicBomber game;
    private Stage stage;
    // Constructor
    public StartMenuScreen(AtomicBomber game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        background = new Texture("extra/background.jpg");
        loginButton = new TextButton("Login", game.skin);
        loginButton.addListener(new ClickListener(){
            @Override
                    public void clicked(InputEvent event, float x , float y) {
                game.setScreen(new LoginMenuScreen(game));
            }
        });
        registerButton = new TextButton("Register", game.skin);
        registerButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterMenuScreen(game));
            }
        });
        exit = new TextButton("Exit", game.skin);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(registerButton).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(loginButton).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(exit).fillX().uniformX();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    // Method to change the screen to LoginMenuScreen
    @Override
    public void resize(int width, int height) {
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
        // Dispose textures
        background.dispose();
    }
}
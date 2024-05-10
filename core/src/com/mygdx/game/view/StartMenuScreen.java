package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.AtomicBomber;

public class StartMenuScreen implements Screen {
    Texture background;
    Texture loginButton;
    Texture registerButton;
    AtomicBomber game;

    // Constructor
    public StartMenuScreen(AtomicBomber game) {
        // Load textures
        background = new Texture("extra/background.jpg");
        loginButton = new Texture("extra/loginButton.png");
        registerButton = new Texture("extra/registerButton.png");

        // Assign the game instance
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw login button
        float startX = Gdx.graphics.getWidth() / 2 - 2 * 60;
        float buttonHeight = 60;
        float buttonWidth = 70;
        game.batch.draw(
                loginButton,
                startX,
                Gdx.graphics.getHeight() / 2 - buttonHeight / 2, // Center vertically
                buttonWidth,
                buttonHeight
        );

        // Draw register button
        float buttonGap = 50;
        game.batch.draw(
                registerButton,
                startX + buttonWidth + buttonGap,
                Gdx.graphics.getHeight() / 2 - buttonHeight / 2, // Center vertically
                buttonWidth,
                buttonHeight
        );
        game.batch.end();

        // Check for button clicks
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

            // Check if the touch is within the bounds of the login button
            if (touchX >= startX && touchX <= startX + buttonWidth &&
                    touchY >= (Gdx.graphics.getHeight() / 2 - buttonHeight / 2) && touchY <= (Gdx.graphics.getHeight() / 2 + buttonHeight / 2)) {
                goToLoginScreen();
            }

            // Check if the touch is within the bounds of the register button
            if (touchX >= startX + buttonWidth + buttonGap && touchX <= startX + 2 * buttonWidth + buttonGap &&
                    touchY >= (Gdx.graphics.getHeight() / 2 - buttonHeight / 2) && touchY <= (Gdx.graphics.getHeight() / 2 + buttonHeight / 2)) {
                goToRegisterScreen();
            }
        }
    }

    // Method to change the screen to LoginMenuScreen
    public void goToLoginScreen() {
        game.setScreen(new LoginMenuScreen(game)); // Assuming LoginMenuScreen constructor requires a game instance
    }

    // Method to change the screen to RegisterMenuScreen
    public void goToRegisterScreen() {
        game.setScreen(new RegisterMenuScreen(game)); // Assuming RegisterMenuScreen constructor requires a game instance
    }

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
        loginButton.dispose();
        registerButton.dispose();
    }
}